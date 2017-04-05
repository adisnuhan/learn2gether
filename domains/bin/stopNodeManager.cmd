@ECHO OFF

@REM WARNING: This file is created by the Configuration Wizard.
@REM Any changes to this script may be lost when adding extensions to this configuration.

SETLOCAL

@REM *************************************************************************
@REM  This script is used to stop the NodeManager for this domain.
@REM  This script should be used only when node manager is configured per domain.
@REM  This script sets the following variables before stopping 
@REM  the node manager:
@REM 
@REM  WL_HOME    - The root directory of your WebLogic installation
@REM  NODEMGR_HOME  - The product name. Here it will product name and domain name
@REM  *************************************************************************

set WL_HOME=C:\FBM\wls12130\wlserver
for %%i in ("%WL_HOME%") do set WL_HOME=%%~fsi

set NODEMGR_HOME=C:\ANU\project\domains\nodemanager
for %%i in ("%NODEMGR_HOME%") do set NODEMGR_HOME=%%~fsi

set DOMAIN_HOME=C:\ANU\project\domains
for %%i in ("%DOMAIN_HOME%") do set DOMAIN_HOME=%%~fsi

set ROOT_DIRECTORY=C:\ANU\project\domains
for %%i in ("%ROOT_DIRECTORY%") do set ROOT_DIRECTORY=%%~fsi

@REM  stop node manager

call "%WL_HOME%\server\bin\stopNodeManager.cmd"



ENDLOCAL