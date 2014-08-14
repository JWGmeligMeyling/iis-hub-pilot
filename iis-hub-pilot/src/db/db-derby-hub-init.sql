create table audit_events_submit_single_message (
    event_id bigint generated always as identity(start with 1, increment by 1),
    req_msg_id varchar(128),
    req_timestamp timestamp not null,
    req_protocol varchar(16) not null,
    req_method varchar(8) not null,
    req_scheme varchar(16) not null,
    req_server_name varchar(256) not null,
    req_server_port int not null,
    req_context_path varchar(128) not null,
    req_servlet_path varchar(128) not null,
    req_query varchar(1024),
    req_local_name varchar(256) not null,
    req_local_port int not null,
    req_remote_name varchar(256) not null,
    req_remote_port int not null,
    req_auth_type varchar(16) not null,
    req_user_principal varchar(1024) not null,
    req_headers varchar(16384) not null,
    req_dest_id varchar(128),
    resp_code int not null,
    resp_headers varchar(16384) not null,
    resp_dest_id varchar(128),
    resp_dest_uri varchar(1024)
);

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
