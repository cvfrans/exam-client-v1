
def stopContainer = '[ -z $(docker ps -f "name=${env.CONTAINER_NAME}" -aq) ] || docker stop ${env.CONTAINER_NAME}'
def deleteContainer = '[ -z $(docker container ls -aq) ] || docker rm ${env.CONTAINER_NAME}'
def runContainer = 'docker run -d --name ${env.CONTAINER_NAME} -p 80:8080 ${env.DOCKERHUB_USER}/${env.IMAGE_NAME}'

pipeline {	
   	agent any

   	environment {
   		EC2_USER = 'ubuntu'
		EC2_PRIVATE_IP = '172.31.24.203'
		DOCKERHUB_USER = 'cvfrans'
		IMAGE_NAME = 'exam-clientapp'
		CONTAINER_NAME = 'apiclient'		
   	}

   	stages {
	   	stage('Build') {
		    steps {
				sh 'mvn clean install -Dmaven.test.skip=true'
	        }
	    }
		stage('Unit Test') {
			steps {
				sh 'mvn test'
	        }
		}
		stage('Build Docker Image') {
			steps {
				sh 'docker build -t ${DOCKERHUB_USER}/${IMAGE_NAME} .'
				withCredentials([string(credentialsId: 'docker-hub-id', variable: 'dockerHubPwd')]) {
    				sh 'docker login -u ${DOCKERHUB_USER} -p ${dockerHubPwd}'    				
				}
				sh 'docker push ${DOCKERHUB_USER}/${IMAGE_NAME}'
	        }
		}
		stage('Deploy Container') {
 			steps {
				sshagent(['aws-ec2-ubuntu-id']) {										
	    			sh 'ssh -o StrictHostKeyChecking=no ${EC2_USER}@${EC2_PRIVATE_IP} ${stopContainer}'
	    			sh 'ssh -o StrictHostKeyChecking=no ${EC2_USER}@${EC2_PRIVATE_IP} ${deleteContainer}'
	    			sh 'ssh -o StrictHostKeyChecking=no ${EC2_USER}@${EC2_PRIVATE_IP} ${runContainer}'
				}
			}
		}	
   	}
   	post {
		always {
   			cleanWs()
   		}
	}
}
