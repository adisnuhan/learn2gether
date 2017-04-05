CREATE SCHEMA IF NOT EXISTS FZA;

--------------------------------------------------------
-- FZA.AFZ_DATENQUELLE
--------------------------------------------------------
create table FZA.AFZ_DATENQUELLE ( 
    QUELLE  VARCHAR2 (2 CHAR)  NOT NULL 
  , BESCHREIBUNG  VARCHAR2 (200 CHAR)  NOT NULL 
  , QUALITAET  NUMBER NOT NULL 
);

alter table FZA.AFZ_DATENQUELLE
   add constraint PK_AFZ_DATENQUELLE	primary key (QUELLE);
