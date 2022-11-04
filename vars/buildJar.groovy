#!/usr/bin/env groovy

def call(){
    echo "Building the application fot the branch $BRANCH_NAME"
    sh "mvn clean package"
}
