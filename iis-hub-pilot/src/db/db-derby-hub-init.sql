create table destinations (
    dest_id varchar(128) not null primary key,
    dest_uri varchar(1024) not null
);

create table tomcat_users (
    user_name varchar(128) not null primary key,
    user_pass varchar(128) not null
);

create table tomcat_user_roles (
    user_name varchar(128) not null references tomcat_users(user_name),
    role_name varchar(128) not null
);

insert into destinations (
    dest_id,
    dest_uri
) values (
    '${hub.data.dest.iis.dev.id}',
    '${hub.data.dest.iis.dev.uri}'
);

insert into tomcat_users (
    user_name,
    user_pass
) values (
    'CN=${hub.crypto.mgr.key.store.entry.iis.dev.alias}',
    'null'
);

insert into tomcat_users (
    user_name,
    user_pass
) values (
    'CN=${hub.crypto.mgr.key.store.entry.iis.hub.alias}',
    'null'
);

insert into tomcat_user_roles (
    user_name,
    role_name
) values (
    'CN=${hub.crypto.mgr.key.store.entry.iis.dev.alias}',
    'hubSecRole'
);

insert into tomcat_user_roles (
    user_name,
    role_name
) values (
    'CN=${hub.crypto.mgr.key.store.entry.iis.hub.alias}',
    'hubSecRole'
);
