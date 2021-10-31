create table t_application
(
    uuid           varchar(255) not null
        primary key,
    change_log     varchar(255) null,
    change_log_url varchar(255) null,
    name           varchar(255) null
);

create table t_application_version
(
    uuid           varchar(255) not null
        primary key,
    change_log     varchar(255) null,
    change_log_url varchar(255) null,
    download_url   varchar(255) null,
    file_path      varchar(255) null,
    file_size      int          null,
    name           varchar(255) null,
    package_name   varchar(255) null,
    platform       int          null,
    system_type    int          null,
    update_time    datetime(6)  null,
    version        varchar(255) null
);

create table t_role
(
    id      int auto_increment
        primary key,
    name    varchar(255) null,
    name_zh varchar(255) null
);

create table t_server
(
    uuid          varchar(255) not null
        primary key,
    create_time   datetime(6)  null,
    update_time   datetime(6)  null,
    charset       varchar(255) null,
    ip_address    varchar(255) null,
    name          varchar(255) null,
    password      varchar(255) null,
    platform      varchar(255) null,
    ssh_enable    bit          null,
    ssh_port      int          null,
    system_type   int          null,
    telnet_enable bit          null,
    telnet_port   int          null,
    username      varchar(255) null,
    operator_id   bigint       null,
    constraint FKkc8mtbr3mn7ax3bhw6l1bd3sh
        foreign key (operator_id) references t_user (id)
);

create table t_user
(
    id                      bigint auto_increment
        primary key,
    account_non_expired     bit          not null,
    account_non_locked      bit          not null,
    credentials_non_expired bit          not null,
    enabled                 bit          not null,
    password                varchar(255) null,
    username                varchar(255) null
);

create table t_user_roles
(
    user_id  bigint not null,
    roles_id int    not null,
    constraint FKj47yp3hhtsoajht9793tbdrp4
        foreign key (roles_id) references t_role (id),
    constraint FKpqntgokae5e703qb206xvfdk3
        foreign key (user_id) references t_user (id)
);

