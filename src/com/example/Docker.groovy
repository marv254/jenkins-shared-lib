#!/usr/bin/env groovy

package com.example

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
            script.sh "docker build -t $imageName ."

        }
    
    def dockerLogin() {
        script.withCredentials([script.usernamePassword(credentialsId: 'dockerhub-creds', usernameVariable: 'USER', passwordVariable: 'PASS')]) {
            script.sh "echo $script.PASS | docker login -u $script.USER --password-stdin"
        }
    }

    def dockerPush(String imageName){
        script.sh "docker push $imageName"
    }

}