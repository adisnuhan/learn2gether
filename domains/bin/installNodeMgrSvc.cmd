@ECHO OFF

@REM WARNING: This file is created by the Configuration Wizard.
@REM Any changes to this script may be lost when adding extensions to this configuration.

SETLOCAL

@REM *************************************************************************
@REM  This script is used to install a NodeManager Windows Service.
@REM  This script should be used only when node manager is configured per domain.
@REM  This script sets the following variables before installing 
@REM  the Windows Service:
@REM 
@REM  WL_HOME    - The root directory of your WebLogic installation
@REM  PROD_NAME  - The product name. Here it will product name and domain name
@REM  NODEMGR_HOME - The node manager home.
@REM  NODEMGR_HOST - The node manager host.
@REM  NODEMGR_PORT - The node manager port.
@REM  *************************************************************************

set WL_HOME=C:\FBM\wls12130\wlserver
for %%i in ("%WL_HOME%") do set WL_HOME=%%~fsi

set PROD_NAME=Oracle Weblogic domains

set NODEMGR_HOME=C:\ANU\project\domains\nodemanager
for %%i in ("%NODEMGR_HOME%") do set NODEMGR_HOME=%%~fsi

set NODEMGR_HOST=localhost

set NODEMGR_PORT=5556

set DOMAIN_HOME=C:\ANU\project\domains
for %%i in ("%DOMAIN_HOME%") do set DOMAIN_HOME=%%~fsi

set JAVA_OPTIONS=%JAVA_OPTIONS% -Dweblogic.RootDirectory=%DOMAIN_HOME% 

@REM  Call install node manager service

call "%WL_HOME%\server\bin\installNodeMgrSvc.cmd"



ENDLOCAL