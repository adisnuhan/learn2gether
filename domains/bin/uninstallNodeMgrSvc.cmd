@ECHO OFF

@REM WARNING: This file is created by the Configuration Wizard.
@REM Any changes to this script may be lost when adding extensions to this configuration.

SETLOCAL

@REM *************************************************************************
@REM  This script is used to uninstall a NodeManager Windows Service.
@REM  This script should be used only when node manager is configured per domain.
@REM  This script sets the following variables before installing 
@REM  the Windows Service:
@REM 
@REM  WL_HOME    - The root directory of your WebLogic installation
@REM  PROD_NAME  - The product name. Here it will product name and domain name
@REM  *************************************************************************

set WL_HOME=C:\FBM\wls12130\wlserver
for %%i in ("%WL_HOME%") do set WL_HOME=%%~fsi

set PROD_NAME=Oracle Weblogic domains

@REM  Call uninstall node manager service

call "%WL_HOME%\server\bin\uninstallNodeMgrSvc.cmd"



ENDLOCAL