@echo off
REM ______________________________________________________________________________
REM
REM  Project: FZA
REM  File: $HeadURL: https://lpintrae.muc:4756/svn/fza/code/trunk/build/fza/fza-admin/build.cmd $
REM  Version: $Id: build.cmd 4222 2016-01-26 07:23:00Z qx21005 $
REM ______________________________________________________________________________
REM
REM  created by: Stefan Knaus; stefan.knaus@sulzer.de
REM  creation DATE: 24.04.2014
REM  changed by: $Author: qx21005 $
REM  CHANGE DATE: $Date:: 2016-01-26 #$
REM  description: builds fza-admin component
REM ______________________________________________________________________________
REM
REM  Copyright: (C) BMW AG 2010, all rights reserved
REM ______________________________________________________________________________
REM
call ../setBuildEnvironment.cmd

title "LEARN2GETHER - JDK 1.7 - MAVEN 3.3.9 - maven clean install"

SET CMD=call mvn %MAVEN_SETTINGS% -Dmaven.test.skip=true -Ddevbuild=true clean install
echo %CMD%
%CMD%