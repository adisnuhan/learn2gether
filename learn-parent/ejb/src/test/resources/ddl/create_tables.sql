/*______________________________________________________________________________
//
// Project: learn-parent
//    File: $HeadURL: $
// Version: $Id: $
//______________________________________________________________________________
//
//    Created by: Adis Nuhanovic, nuhanovic.vkl@gmail.com
// Creation date: 12.02.2017
//    Changed by: $Author: $
//   Change date: $Date::            #$
//   Description: DDL for user table
//______________________________________________________________________________
//
// Copyright: (C) BMW AG 2017, all rights reserved
//______________________________________________________________________________
*/


CREATE SCHEMA IF NOT EXISTS LEARN2GETHER;

CREATE TABLE LEARN2GETHER.USER (
   `ID` int(11) NOT NULL AUTO_INCREMENT,
   `MYUSER` varchar(30) NOT NULL,
   `EMAIL` varchar(30) DEFAULT NULL,
   PRIMARY KEY (`id`)
 ) ;
