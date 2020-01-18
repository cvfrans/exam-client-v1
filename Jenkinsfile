def EC2_USER = 'ubuntu'
def EC2_PRIVATE_IP = '172.31.24.203'
def DOCKERHUB_USER = 'cvfrans'
def IMAGE_NAME = 'exam-clientapp'
def CONTAINER_NAME = 'apiclient'
def stopContainer 
def deleteContainer
def runContainer

pipeline {	
   	agent any

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
					stopContainer = '[ -z $(docker ps -f "name=${CONTAINER_NAME}" -aq) ] || docker stop ${CONTAINER_NAME}'
					deleteContainer = '[ -z $(docker container ls -aq) ] || docker rm ${CONTAINER_NAME}'
					runContainer = 'docker run -d --name ${CONTAINER_NAME} -p 80:8080 ${DOCKERHUB_USER}/${IMAGE_NAME}'
	    			
					sh "ssh -o StrictHostKeyChecking=no ${EC2_USER}@${EC2_PRIVATE_IP} '${stopContainer} | ${deleteContainer} | ${runContainer}'"
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
