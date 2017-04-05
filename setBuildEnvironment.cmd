@echo off
REM ______________________________________________________________________________
REM 
REM  Project: Learn2Gether
REM  File: $HeadURL: https://github.com/adisnuhan/learn2gether.git $
REM  Version: $Id: setBuildEnvironment.cmd 4054 2016-12-13 05:11:51Z qx72871 $
REM ______________________________________________________________________________
REM 
REM  created by: Adis Nuhanovic; nuhanovic.vkl@gmail.com
REM  description: configure Learn2Gether build environment
REM ______________________________________________________________________________
REM 
REM  Copyright: (C) Bosnian Cheater 2017, all rights reserved
REM ______________________________________________________________________________
REM 


REM set JAVA_HOME=c:\ANU\jdk1.7.0_51
set JAVA_HOME=c:\ANU\jdk1.7.0_51
SET WORKSPACE= c:\ANU\learn2gether\learn-parent\
SET DEPLOY_DIR=c:\ANU\learn2gether\domains\autodeploy

REM MAVEN
set MAVEN_HOME=c:\ANU\apache-maven-3.3.9
set MAVEN_OPTS=-Xmx1024m -XX:MaxPermSize=256M
set MAVEN_SETTINGS=-s c:\ANU\apache-maven-3.3.9\conf\settings.xml
set PATH=%PATH%;%MAVEN_HOME%\bin;



   
    
:finish
		title "Lear2Gether -- Build Environment -- JDK 1.7.0_51 - MAVEN 3.3.9"
		@echo ================================================================================
		
		@echo ================================================================================
		@echo JAVA_HOME=%JAVA_HOME%
		@echo MAVEN_HOME=%MAVEN_HOME%
		@echo DEPLOY_DIR=%DEPLOY_DIR%
		@echo MAVEN_SETTINGS=%MAVEN_SETTINGS%
		@echo ================================================================================
		@echo OK


