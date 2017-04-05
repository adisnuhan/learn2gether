@ECHO OFF

@REM WARNING: This file is created by the Configuration Wizard.
@REM Any changes to this script may be lost when adding extensions to this configuration.

@REM *************************************************************************
@REM   Server scoped startup configuration.
@REM *************************************************************************

@REM Associate all admin-server server-groups to AdminServerStartupGroup

if NOT "%STARTUP_GROUP%"=="" (
	if "%STARTUP_GROUP%"=="BASE-WLS-ADMIN-SVR" (
		set STARTUP_GROUP=AdminServerStartupGroup
	)
)

@REM Associate server with a startup group

if "%STARTUP_GROUP%"=="" (
	if "%SERVER_NAME%"=="weblogic" (
		set STARTUP_GROUP=AdminServerStartupGroup
	)
)

@REM Startup parameters for STARTUP_GROUP AdminServerStartupGroup

if "%STARTUP_GROUP%"=="AdminServerStartupGroup" (
	@REM POST_CLASSPATH.
	if NOT "%POST_CLASSPATH%"=="" (
		set POST_CLASSPATH=%POST_CLASSPATH%;%MW_HOME%\oracle_common\modules\com.oracle.cie.config-wls-online_8.1.0.0.jar
	) else (
		set POST_CLASSPATH=%MW_HOME%\oracle_common\modules\com.oracle.cie.config-wls-online_8.1.0.0.jar
	)
)

