@echo off
REM ______________________________________________________________________________
REM
REM  Project: FBMV
REM  File: $HeadURL: https://lpintrae.muc:4756/svn/fbm-viewer/trunk/build/fbmv/build_and_deploy.cmd $
REM  Version: $Id: build_and_deploy.cmd 2124 2015-08-21 09:09:01Z qxk4666 $
REM ______________________________________________________________________________
REM
REM  created by: Stefan Knaus; stefan.knaus@sulzer.de
REM  creation DATE: 24.04.2014
REM  changed by: $Author: qxk4666 $
REM  CHANGE DATE: $Date:: 2015-08-21 #$
REM  description: builds and deploys FBM-Viewer to local server
REM ______________________________________________________________________________
REM
REM  Copyright: (C) BMW AG 2010, all rights reserved
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