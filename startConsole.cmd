set ANU_HOME=c:\ANU
set WORKSPACE=c:\ANU\project
start %WORKSPACE%\tools\conemu\ConEmu64.exe -dir %WORKSPACE% -cmd cmd.exe /K "chcp 65001 && %WORKSPACE%\setBuildEnvironment.cmd  && doskey /macros && conemuc -GuiMacro FontSetSize(1,5)" -new_console:t:"Java 7 - FBM-APP - Console - Sulzer"