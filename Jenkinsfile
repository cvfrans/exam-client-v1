pipeline {
   agent any
   
   tools {
      maven "maven-3.6"
   }
   
   stages {
	   	stage('Build') {
		    steps {
				sh "mvn clean install -Dmaven.test.skip=true"          
	        }
	    }
		stage('Unit Test') {
			steps {
				sh "mvn test"
	        }
		}
		stage('Build Docker Image') {
			steps {
				tool name: 'dockerapp', type: 'org.jenkinsci.plugins.docker.commons.tools.DockerTool'
				sh "docker build -t cvfrans/exam-clientapp ."
				withCredentials([string(credentialsId: 'docker-hub-id', variable: 'dockerHubPwd')]) {
    				sh "docker login -u cvfrans -p ${dockerHubPwd}"    				
				}
				sh "docker push cvfrans/exam-clientapp"				
	        }
		}		
   }
}
