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

set EAR_FILE=.\ear\target\learn-parent.ear

:START

call build.cmd

set COPY_CMD=copy /Y %EAR_FILE% %DEPLOY_DIR%
echo %COPY_CMD%
%COPY_CMD%


echo ==========================================
echo ==========================================
echo Press any key to restart the build process
echo ==========================================

pause > nul

GOTO:START