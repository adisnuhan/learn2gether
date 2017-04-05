#!/bin/sh

# WARNING: This file is created by the Configuration Wizard.
# Any changes to this script may be lost when adding extensions to this configuration.

# *************************************************************************
#  This script is used to start a NodeManager.
#  This script should be used only when node manager is configured per domain.
#  This script sets the following variables before starting 
#  the node manager:
# 
#  WL_HOME    - The root directory of your WebLogic installation
#  NODEMGR_HOME  - The product name. Here it will product name and domain name
#  *************************************************************************

WL_HOME="C:/FBM/wls12130/wlserver"

NODEMGR_HOME="C:/ANU/project/domains/nodemanager"
export NODEMGR_HOME

DOMAIN_HOME="C:/ANU/project/domains"

JAVA_OPTIONS="${JAVA_OPTIONS} -Dweblogic.RootDirectory=${DOMAIN_HOME} "
export JAVA_OPTIONS

#  start node manager

${WL_HOME}/server/bin/startNodeManager.sh

