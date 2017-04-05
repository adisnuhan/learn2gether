@ECHO OFF

@REM WARNING: This file is created by the Configuration Wizard.
@REM Any changes to this script may be lost when adding extensions to this configuration.

SETLOCAL

@REM --- Start Functions ---

GOTO :ENDFUNCTIONS

:usage
	echo Need to set SERVER_NAME and ADMIN_URL environment variables or specify
	echo them in command line:
	echo Usage: %1 SERVER_NAME {ADMIN_URL}
	echo for example:
	echo %1 managedserver1 http://LP27187:7001
GOTO :EOF


:ENDFUNCTIONS

@REM --- End Functions ---

@REM *************************************************************************
@REM This script is used to start a managed WebLogic Server for the domain in
@REM the current working directory.  This script can either read in the SERVER_NAME and
@REM ADMIN_URL as positional parameters or will read them from environment variables that are 
@REM set before calling this script. If SERVER_NAME is not sent as a parameter or exists with a value
@REM as an environment variable the script will EXIT. If the ADMIN_URL value cannot be determined
@REM by reading a parameter or from the environment a default value will be used.
@REM 
@REM  For additional information, refer to "Administering Server Startup and Shutdown for Oracle WebLogic Server"
@REM *************************************************************************

@REM  Set SERVER_NAME to the name of the server you wish to start up.

set DOMAIN_NAME=domains

set ADMIN_URL=http://LP27187:7001

@REM  Set WLS_USER equal to your system username and WLS_PW equal  

@REM  to your system password for no username and password prompt 

@REM  during server startup.  Both are required to bypass the startup

@REM  prompt.

set WLS_USER=

set WLS_PW=

@REM  Set JAVA_OPTIONS to the java flags you want to pass to the vm. i.e.: 

@REM  set JAVA_OPTIONS=-Dweblogic.attribute=value -Djava.attribute=value

set JAVA_OPTIONS=-Dweblogic.security.SSL.trustedCAKeyStore="C:\FBM\wls12130\wlserver\server\lib\cacerts" %JAVA_OPTIONS%

@REM  Set JAVA_VM to the java virtual machine you want to run.  For instance:

@REM  set JAVA_VM=-server

set JAVA_VM=

@REM  Set SERVER_NAME and ADMIN_URL, they must be specified before starting

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
		CALL :usage %0
		GOTO :EOF
	)
) else (
	set ADMIN_URL=%1
	shift
)

@REM Export the admin_url whether the user specified it OR it was sent on the command-line

set ADMIN_URL=%ADMIN_URL%

set SERVER_NAME=%SERVER_NAME%

set DOMAIN_HOME=C:\ANU\project\domains

if "%1"=="" (
	@REM  Call Weblogic Server with our default params since the user did not specify any other ones
	call "%DOMAIN_HOME%\bin\startWebLogic.cmd" nodebug noderby
) else (
	@REM  Call Weblogic Server with the params the user sent in INSTEAD of the defaults
	for %%p in (%*) do CALL :func300 %%p
	GOTO :endfunc300
		:func300
		set p=%1
		if "%SERVER_NAME_SKIPPED%"=="" (
			set SERVER_NAME_SKIPPED=true
		) else (
			if "%URL_SKIPPED%"=="" (
				set URL_SKIPPED=true
			) else (
				set CMD_LINE_ARGS=%CMD_LINE_ARGS% %p%
			)
		)
		GOTO :EOF
	:endfunc300
	call "%DOMAIN_HOME%\bin\startWebLogic.cmd" %CMD_LINE_ARGS%


)



ENDLOCAL