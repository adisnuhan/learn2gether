@echo off
REM ______________________________________________________________________________
REM 
REM  Project: Learn2Gether
REM  File: $HeadURL: https://github.com/adisnuhan/learn2gether.git $
REM  Version: $Id: setBuildEnvironment.cmd 4054 2016-12-13 05:11:51Z qx72871 $
REM ______________________________________________________________________________
REM 
REM  created by: Adis Nuhanovic; nuhanovic.vkl@gmail.com
REM  description: configure Learn2Gether build environment
REM ______________________________________________________________________________
REM 
REM  Copyright: (C) Bosnian Cheater 2017, all rights reserved
REM ______________________________________________________________________________
REM 

call ../setBuildEnvironment.cmd

title "LEARN2GETHER - JDK 1.7 - MAVEN 3.3.9 - maven clean install"

SET CMD=call mvn %MAVEN_SETTINGS% -Dmaven.test.skip=true -Ddevbuild=true clean install
echo %CMD%
%CMD%