package gov.hhs.onc.iishubpilot.crypto.impl;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.LoggingEvent;
import gov.hhs.onc.iishubpilot.context.HubProfiles;
import java.io.PrintStream;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Consumer;
import org.aopalliance.intercept.MethodInterceptor;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.StrBuilder;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.target.SingletonTargetSource;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration("cryptoDebugConfig")
@Profile({ HubProfiles.DEV_MODE })
public class HubCryptoDebugConfiguration implements DisposableBean {
    private final static String SUN_SEC_PKG_NAME = "sun.security.ssl";

    private final static String SUN_SEC_PKG_NAME_PREFIX = SUN_SEC_PKG_NAME + ClassUtils.PACKAGE_SEPARATOR;

    private final static String SUN_SEC_DEBUG_CLASS_NAME = SUN_SEC_PKG_NAME_PREFIX + "Debug";
    private final static String PRINT_STREAM_CLASS_NAME = PrintStream.class.getName();

    private final static String PROXY_PRINT_STREAM_CLASS_NAME_PREFIX = "$" + PRINT_STREAM_CLASS_NAME + "$$EnhancerBySpringCGLIB$$";

    private final static String PRINT_METHOD_NAME = "print";
    private final static String PRINTLN_METHOD_NAME = "println";

    private final static ThreadLocal<StrBuilder> THREAD_PRINT_STR_BUILDER = new ThreadLocal<StrBuilder>() {
        @Override
        protected StrBuilder initialValue() {
            return new StrBuilder();
        }
    };

    private final static Map<PrintStream, Consumer<PrintStream>> DELEGATE_PRINT_STREAM_MAP = new HashMap<>(2);

    private final static Logger LOGGER = ((Logger) LoggerFactory.getLogger(HubCryptoDebugConfiguration.class));

    @Bean(name = "cryptoDebugPrintStreamErr")
    public PrintStream getErrPrintStream() {
        return buildProxyPrintStream(System.err, System::setErr);
    }

    @Bean(name = "cryptoDebugPrintStreamOut")
    public PrintStream getOutPrintStream() {
        return buildProxyPrintStream(System.out, System::setOut);
    }

    @Override
    public synchronized void destroy() throws Exception {
        for (Entry<PrintStream, Consumer<PrintStream>> delegatePrintStreamEntry : DELEGATE_PRINT_STREAM_MAP.entrySet()) {
            delegatePrintStreamEntry.getValue().accept(delegatePrintStreamEntry.getKey());
        }
    }

    private synchronized static PrintStream buildProxyPrintStream(PrintStream delegatePrintStream, Consumer<PrintStream> delegateStreamSetFunc) {
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setProxyTargetClass(true);

        proxyFactory.setTargetSource(new SingletonTargetSource(delegatePrintStream) {
            private final static long serialVersionUID = 0L;

            @Override
            public Class<?> getTargetClass() {
                return PrintStream.class;
            }
        });

        proxyFactory.addAdvice(((MethodInterceptor) (invocation) -> {
            Method invocationMethod = invocation.getMethod();
            String invocationMethodName = invocationMethod.getName();
            Object[] invocationArgs = invocation.getArguments();

            if (invocationMethod.getDeclaringClass().getName().equals(PRINT_STREAM_CLASS_NAME)
                && (invocationMethodName.equals(PRINT_METHOD_NAME) || invocationMethodName.equals(PRINTLN_METHOD_NAME)) && (invocationArgs.length == 1)) {
                StackTraceElement[] stackTraceElems = new Throwable().getStackTrace();
                int numStackTraceElems = stackTraceElems.length;
                StackTraceElement stackTraceElem;

                for (int a = 0; a < numStackTraceElems; a++) {
                    if (StringUtils.startsWith((stackTraceElem = stackTraceElems[a]).getClassName(), PROXY_PRINT_STREAM_CLASS_NAME_PREFIX)
                        && stackTraceElem.getMethodName().equals(invocationMethodName)) {
                        if (StringUtils.startsWith(stackTraceElems[++a].getClassName(), SUN_SEC_PKG_NAME_PREFIX)) {
                            while (stackTraceElems[a].getClassName().equals(SUN_SEC_DEBUG_CLASS_NAME)) {
                                a++;
                            }

                            StrBuilder printStrBuilder = THREAD_PRINT_STR_BUILDER.get();
                            printStrBuilder.append(invocationArgs[0]);

                            if (invocationMethodName.equals(PRINTLN_METHOD_NAME)) {
                                LoggingEvent srcEvent = new LoggingEvent(Logger.FQCN, LOGGER, Level.TRACE, printStrBuilder.build(), null, null);
                                srcEvent.setCallerData(ArrayUtils.subarray(stackTraceElems, a, numStackTraceElems));

                                LOGGER.callAppenders(srcEvent);

                                THREAD_PRINT_STR_BUILDER.remove();
                            }

                            return null;
                        } else {
                            break;
                        }
                    }
                }
            }

            return invocationMethod.invoke(delegatePrintStream, invocationArgs);
        }));

        PrintStream proxyPrintStream = ((PrintStream) proxyFactory.getProxy());

        DELEGATE_PRINT_STREAM_MAP.put(delegatePrintStream, delegateStreamSetFunc);

        delegateStreamSetFunc.accept(proxyPrintStream);

        return proxyPrintStream;
    }
}
