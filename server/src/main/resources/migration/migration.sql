/*
----------------------------------
- Create your migration scripts here
----------------------------------
*/

DROP TABLE IF EXISTS cropTypes;
DROP TABLE IF EXISTS farms;
DROP TABLE IF EXISTS farmFields;
DROP TABLE IF EXISTS drones;
DROP TABLE IF EXISTS failures;
DROP TABLE IF EXISTS plantedCrops;
DROP TABLE IF EXISTS notifications;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS users_farms;

create table cropTypes
(
    typeId integer not null
        constraint cropTypes_pk
            primary key autoincrement,
    name varchar(50)
        constraint cropTypes_uc
            unique,
    minGrowthDays integer,
    maxGrowthDays integer
);

create table users
(
    adriaId varchar(50) not null
        constraint users_pk
            primary key,
    fullName varchar(100)
);

create table farms
(
    farmId integer not null
        constraint farmCompanies_pk
            primary key autoincrement,
    ownerId VARCHAR(50) not null references users(adriaId),
    name varchar(50)
);

create table farmFields
(
    farmId integer not null
        constraint farmFields_farmCompanies_companyId_fk
            references farms,
    farmFieldId integer not null,
    name varchar(50)
        constraint farmFields_uc not null,
           /* unique,*/
    constraint farmFields_pk
        primary key (farmId, farmFieldId)
);

create table plantedCrops
(
    farmId integer not null
        constraint plantedCrops_farmCompanies_farmId_fk
            references farms,
    farmFieldId integer not null
        constraint plantedCrops_farmFields_fieldId_fk
            references farmFields,
    cropId integer not null,
    typeId integer not null
        constraint plantedCrops_cropTypes_typeId_fk
            references cropTypes,
    harvestDate dateTime,
    plantedDate dateTime,
    constraint plantedCrops_pk
        primary key (farmId, farmFieldid, cropId, typeId)
);

create table drones
(
    droneId varchar(50) not null
        constraint drones_pk
            primary key,
    userId varchar(50) null
        constraint drones_farmCompanies_companyId_fk
            references users,
    manufacturingDate date,
    isFunctional boolean,
    needsRepair boolean,
    batteryLevel integer,
    currentActivity varchar(50)
);

create table failures
(
    droneId varchar(50) not null
        constraint failures_drones_droneId_fk
            references drones,
    failureTime datetime not null,
    constraint failures_pk
        primary key (droneId, failureTime)
);

create table notifications
(
    notificationId integer not null,
    userId varchar(50) not null
        references users,
    title varchar(50) not null,
    message varchar(1000) not null,
    read boolean not null,
    constraint notifications_pk
        primary key (notificationId)
);
