package gov.hhs.onc.iishubpilot.logback.impl;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.pattern.color.HighlightingCompositeConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.pattern.color.ANSIConstants;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.ObjectUtils;

public class HubPriorityConverter extends HighlightingCompositeConverter {
    private final static Map<Level, String> FG_COLOR_CODES = new HashMap<>();

    static {
        FG_COLOR_CODES.put(Level.TRACE, (ANSIConstants.BOLD + ANSIConstants.DEFAULT_FG));
        FG_COLOR_CODES.put(Level.DEBUG, (ANSIConstants.BOLD + ANSIConstants.WHITE_FG));
        FG_COLOR_CODES.put(Level.INFO, (ANSIConstants.BOLD + ANSIConstants.BLUE_FG));
        FG_COLOR_CODES.put(Level.WARN, (ANSIConstants.BOLD + ANSIConstants.YELLOW_FG));
        FG_COLOR_CODES.put(Level.ERROR, (ANSIConstants.BOLD + ANSIConstants.RED_FG));
    }

    @Override
    protected String transform(ILoggingEvent event, String msg) {
        return (BooleanUtils.toBoolean(this.getFirstOption()) ? super.transform(event, msg) : msg);
    }

    @Override
    protected String getForegroundColorCode(ILoggingEvent event) {
        return ObjectUtils.defaultIfNull(FG_COLOR_CODES.get(event.getLevel()), ANSIConstants.DEFAULT_FG);
    }
}
