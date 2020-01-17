FROM jenkins/jenkins:lts
USER root
RUN apt update && 
RUN apt install apt-transport-https ca-certificates curl software-properties-common
RUN curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo apt-key add -
RUN add-apt-repository "deb [arch=amd64] https://download.docker.com/linux/ubuntu bionic stable"
RUN apt update
RUN apt-cache policy docker-ce
RUN apt install -y docker-ce
RUN usermod -a -G docker jenkins

RUN apt-get install -y maven
RUN apt-get install -y nodejs

USER jenkins