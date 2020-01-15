pipeline {
   agent any

   stages {
      stage('Compile') {
        steps {
			withMaven(maven : 'maven-3.6') {
				sh "mvn clean compile"
			}           
         }
      }
	  stage('Unit Testing') {
		steps {
			withMaven(maven : 'maven-3.6') {
				sh "mvn test"
			}           
         }
	  }
	  stage('Install') {
		steps {
			withMaven(maven : 'maven-3.6') {
				sh "mvn install"
			}           
         }
	  }
   }
}
