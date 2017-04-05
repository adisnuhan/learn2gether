@ECHO OFF

@REM WARNING: This file is created by the Configuration Wizard.
@REM Any changes to this script may be lost when adding extensions to this configuration.

SETLOCAL

@REM *************************************************************************
@REM  This script starts a Replicated Store Daemon.
@REM  It should only be used when a Replicated Store is configured in a domain.
@REM  This script sets the following variables before starting 
@REM  a Daemon:
@REM 
@REM  WL_HOME    - The root directory of your WebLogic installation
@REM  *************************************************************************

set WL_HOME=C:\FBM\wls12130\wlserver
for %%i in ("%WL_HOME%") do set WL_HOME=%%~fsi

@REM  start RSDaemon

call "%WL_HOME%\server\bin\startRSDaemon.cmd" $@



ENDLOCAL