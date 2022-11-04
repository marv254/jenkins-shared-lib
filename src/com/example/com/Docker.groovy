#!/usr/bin/env groovy

package com.example.com

class Docker implements Serializable{
    def script

    Docker(script){
        this.script = script
    }

    def buildJar(){
        script.echo "Building the application fot the branch $script.BRANCH_NAME"
        script.sh "mvn clean package"
    }
    def buildDockerImage(String imageName){
        script.echo "building the docker image..."
        script.withCredentials([script.usernamePassword(credentialsId:'dockerhub-creds', usernameVariable: 'USER', passwordVariable: 'PASS')]){
            script.sh "docker build -t $imageName ."
            script.sh "echo $script.PASS | docker login -u $script.USER --password-stdin"
            script.sh "docker push $imageName"
        }
    }


}