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
				def appImage = docker.build("clientapp:latest")
				testImage.inside {
					sh 'make test'
				}
	        }
		}		
   }
}
