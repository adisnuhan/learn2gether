@ECHO OFF

@REM WARNING: This file is created by the Configuration Wizard.
@REM Any changes to this script may be lost when adding extensions to this configuration.

SETLOCAL

@REM *************************************************************************
@REM  This script is used to stop a Replicated Store Daemon.
@REM  This script should be used only when a Replicated Store is configured for a domain.
@REM  If JAVA_HOME is not set, setDomainEnv is called to initialize JAVA_HOME and other variables (see setDomainEnv.sh).
@REM  *************************************************************************

set WL_HOME=C:\FBM\wls12130\wlserver
for %%i in ("%WL_HOME%") do set WL_HOME=%%~fsi

set DOMAIN_HOME=C:\ANU\project\domains
for %%i in ("%DOMAIN_HOME%") do set DOMAIN_HOME=%%~fsi

@REM  stop RSDaemon, this will call setDomainEnv first if JAVA_HOME is not set

call "%WL_HOME%\server\bin\stopRSDaemon.cmd" $@



ENDLOCAL