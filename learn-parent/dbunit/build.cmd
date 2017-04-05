@echo off
REM ______________________________________________________________________________
REM 
REM  Project: FZA
REM  File: $HeadURL: https://lpintrae.muc:4756/svn/fza/code/trunk/build/fza/fza/build.cmd $
REM  Version: $Id: build.cmd 4186 2015-12-16 14:43:41Z qx72871 $
REM ______________________________________________________________________________
REM 
REM  created by: Stefan Knaus; stefan.knaus@sulzer.de
REM  creation DATE: 24.04.2014
REM  changed by: $Author: qx72871 $
REM  CHANGE DATE: $Date:: 2015-12-16 #$
REM  description: builds all fza components
REM ______________________________________________________________________________
REM 
REM  Copyright: (C) BMW AG 2010, all rights reserved
REM ______________________________________________________________________________
REM 
call ../setBuildEnvironment.cmd

title "DBUnit - JDK 1.7 - MAVEN 3.0 - maven clean install"

SET CMD=call mvn %MAVEN_SETTINGS% -Dmaven.test.skip=true -Ddevbuild=true clean install
echo %CMD%
%CMD%