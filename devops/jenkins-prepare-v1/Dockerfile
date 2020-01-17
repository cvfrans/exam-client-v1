FROM jenkins/jenkins:lts
USER root
RUN apt-get update
RUN apt install -y apt-transport-https ca-certificates curl software-properties-common
RUN curl -fsSL https://download.docker.com/linux/ubuntu/gpg |  apt-key add -
RUN add-apt-repository "deb [arch=amd64] https://download.docker.com/linux/ubuntu bionic stable"
RUN apt-get update
RUN apt-get -y upgrade
RUN apt-cache policy docker-ce
RUN apt-get -y install docker-ce
RUN usermod -a -G docker jenkins

RUN apt-get install -y maven
RUN apt-get install -y nodejs

USER jenkins
