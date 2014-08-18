/*==============================================================================
= CREATE: TABLES
==============================================================================*/
create table audit_events_submit_single_message (
    event_id bigserial,
    req_msg_id varchar(128),
    req_timestamp timestamp not null,
    req_protocol varchar(16) not null,
    req_method varchar(8) not null,
    req_scheme varchar(16) not null,
    req_server_name varchar(256) not null,
    req_server_port int not null,
    req_context_path varchar(256) not null,
    req_servlet_path varchar(256) not null,
    req_path varchar(256),
    req_query varchar(256),
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
    resp_dest_uri varchar(1024),
    resp_fault_code varchar(128),
    resp_fault_subcodes varchar(1024),
    resp_fault_reason varchar(2048)
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
