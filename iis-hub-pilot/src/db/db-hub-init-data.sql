/*==============================================================================
= INSERT DATA: DESTINATIONS
==============================================================================*/
insert into destinations (
    dest_id,
    dest_uri
) values (
    '${hub.data.dest.iis.dev.id}',
    '${hub.data.dest.iis.dev.uri}'
);

/*==============================================================================
= INSERT DATA: TOMCAT USERS
==============================================================================*/
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

/*==============================================================================
= INSERT DATA: TOMCAT USER ROLES
==============================================================================*/
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
