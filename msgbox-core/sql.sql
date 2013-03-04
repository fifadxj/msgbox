
    alter table CATEGORIES 
        drop 
        foreign key FK6A31321CDE6B9868;

    alter table MESSAGES 
        drop 
        foreign key FK131AF14C6C1DE07D;

    drop table if exists CATEGORIES;

    drop table if exists MESSAGES;

    drop table if exists USERS;

    create table CATEGORIES (
        ID bigint not null auto_increment,
        DESCRIPTION varchar(255),
        NAME varchar(255) not null unique,
        PARENT_CATEGORY_ID bigint,
        primary key (ID)
    ) ENGINE=InnoDB;

    create table MESSAGES (
        ID bigint not null auto_increment,
        CONTENT longtext not null,
        CREATED_DATE DATETIME not null,
        DISABLED bit not null,
        MODIFIED_DATE DATETIME not null,
        RANK integer not null,
        SOURCE varchar(255),
        TOP bit not null,
        CATEGORY_ID bigint not null,
        primary key (ID)
    ) ENGINE=InnoDB;

    create table USERS (
        ID bigint not null auto_increment,
        PASSWORD varchar(255) not null,
        USERNAME varchar(255) not null unique,
        primary key (ID)
    ) ENGINE=InnoDB;

    alter table CATEGORIES 
        add index FK6A31321CDE6B9868 (PARENT_CATEGORY_ID), 
        add constraint FK6A31321CDE6B9868 
        foreign key (PARENT_CATEGORY_ID) 
        references CATEGORIES (ID);

    alter table MESSAGES 
        add index FK131AF14C6C1DE07D (CATEGORY_ID), 
        add constraint FK131AF14C6C1DE07D 
        foreign key (CATEGORY_ID) 
        references CATEGORIES (ID);
