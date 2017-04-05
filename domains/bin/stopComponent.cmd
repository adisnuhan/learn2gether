@ECHO OFF

@REM WARNING: This file is created by the Configuration Wizard.
@REM Any changes to this script may be lost when adding extensions to this configuration.

SETLOCAL

@REM --- Start Functions ---

GOTO :ENDFUNCTIONS

:usage
	echo Usage: %1 {help} COMPONENT_NAME {showErrorStack}
	echo Where:
	echo   help           - Optional. Show this usage.
	echo   COMPONENT_NAME - Required. System Component name, only one name allowed
	echo   showErrorStack - Optional. Show error stack if provided.
GOTO :EOF


:ENDFUNCTIONS

@REM --- End Functions ---

if "%~1"=="" (
	CALL :usage %0
	GOTO :EOF
)
@REM When the parameter contains white spaces only, like '   ', error out.
set param=%1
for %%i in ("%param%") do set param=%%~fsi
if "%param%."=="." (
	echo Error: Invalid System Component name: %1
	CALL :usage %0
	GOTO :EOF
)


if "%1"=="showErrorStack" (
	CALL :usage %0
	GOTO :EOF
)

set showErrorStack=false
set doUsage=false
for %%p in (%*) do call :SET_PARAM %%p
GOTO :CMD_LINE_DONE
	:SET_PARAM
	for %%q in (%1) do set noQuotesParam=%%~q
	if /i "%noQuotesParam%" == "showErrorStack" (
		set showErrorStack=true
		GOTO :EOF
	)
	if /i "%noQuotesParam%" == "help" (
		set doUsage=true
		GOTO :EOF
	) else (
		if NOT "%componentName%"=="" (
			CALL :usage %0
			GOTO :EOF
		)
		set componentName=%1
	)
	GOTO :EOF
:CMD_LINE_DONE


if "%doUsage%"=="true" (
	CALL :usage %0
	GOTO :EOF
)

set WL_HOME=C:\FBM\wls12130\wlserver
for %%i in ("%WL_HOME%") do set WL_HOME=%%~fsi

set DOMAIN_HOME=C:\ANU\project\domains

set PY_LOC=%TEMP%.\stopComponent.py
for %%i in ("%PY_LOC%") do set PY_LOC=%%~fsi


if "%showErrorStack%"=="false" (
	echo try: >"%PY_LOC%" 
	echo   stopComponentInternal^('%componentName%', r'%DOMAIN_HOME%'^) >>"%PY_LOC%" 
	echo   exit^(^) >>"%PY_LOC%" 
	echo except Exception,e: >>"%PY_LOC%" 
	echo   print 'Error:', sys.exc_info^(^)[1] >>"%PY_LOC%" 
	echo   exit^(^) >>"%PY_LOC%" 
) else (
	echo stopComponentInternal^('%componentName%', r'%DOMAIN_HOME%'^) >"%PY_LOC%" 
	echo exit^(^) >>"%PY_LOC%" 
)

echo Stopping System Component %componentName% ...

@REM Using WLST...

call "%WL_HOME%\..\oracle_common\common\bin\wlst.cmd" -i %PY_LOC%  2>&1 

if exist %PY_LOC% (
	del %PY_LOC%


)

echo Done

GOTO :EOF



ENDLOCAL