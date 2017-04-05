@ECHO OFF

@REM WARNING: This file is created by the Configuration Wizard.
@REM Any changes to this script may be lost when adding extensions to this configuration.

SETLOCAL

@REM --- Start Functions ---

GOTO :ENDFUNCTIONS

:usage
	echo You must have a value for SERVER_NAME either set as an environment variable or the first parameter on the command-line.
	echo ADMIN_URL defaults to t3:\\LP27187:7001 if not set as an environment variable or the second command-line parameter.
	echo USER_NAME and PASSWORD are required for shutting the server down when running in production mode:
	echo Usage: %1 {SERVER_NAME} {ADMIN_URL} {USER_NAME} {PASSWORD}
	echo for example:
	echo %1 managedserver1 t3://LP27187:7001 weblogic weblogic
GOTO :EOF


:ENDFUNCTIONS

@REM --- End Functions ---

@REM *************************************************************************
@REM This script is used to stop a managed WebLogic Server for the domain in
@REM the current working directory.  This script reads in the SERVER_NAME and
@REM ADMIN_URL as positional parameters or will read them from environment variables that are 
@REM set before calling this script. If SERVER_NAME is not sent as a parameter or exists with a value
@REM as an environment variable the script will EXIT. If the ADMIN_URL value cannot be determined
@REM by reading a parameter or from the environment a default value will be used.
@REM 
@REM Then this script calls the stopWebLogic script under %WL_HOME%\server\bin.
@REM 
@REM Other variables that stopWebLogic takes are:
@REM 
@REM WLS_USER       - cleartext user for server shutdown
@REM WLS_PW         - cleartext password for server shutdown
@REM JAVA_OPTIONS   - Java command-line options for running the server. (These
@REM                  will be tagged on to the end of the JAVA_VM)
@REM JAVA_VM        - The java arg specifying the VM to run.  (i.e. -server, 
@REM                  -hotspot, etc.)
@REM 
@REM For additional information, refer to "Administering Server Startup and Shutdown for Oracle WebLogic Server"
@REM 
@REM *************************************************************************

@REM  Set SERVER_NAME and ADMIN_URL, they must be specified before stopping

@REM  a managed server, detailed information can be found in

@REM  Administering Server Startup and Shutdown for Oracle WebLogic Server

if "%1"=="" (
	if "%SERVER_NAME%"=="" (
		CALL :usage %0
		GOTO :EOF
	)
) else (
	set SERVER_NAME=%1
	shift
)

if "%1"=="" (
	if "%ADMIN_URL%"=="" (
		set ADMIN_URL=t3://LP27187:7001
	)
) else (
	set ADMIN_URL=%1
	shift
)

set DOMAIN_HOME=C:\ANU\project\domains

call "%DOMAIN_HOME%\bin\stopWebLogic.cmd" %1 %2



ENDLOCAL