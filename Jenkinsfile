pipeline {
   agent any
   
   tools {
      maven "maven-3.6"
   }
   
   stages {
	   	stage('Compile') {
		    steps {
				sh "mvn clean compile"          
	        }
	    }
		stage('Unit Testing') {
			steps {
				sh "mvn clean compile"          
	        }
		}
		stage('Install') {
			steps {
				sh "mvn install"        
	        }
		}
   }
}
