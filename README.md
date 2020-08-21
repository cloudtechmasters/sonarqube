# Deploy spring-boot-hello to sonarqube

## Pre-requisites:
    - Install Java
    - Install Git
    - Install Maven
    - Install Jenkins
## Install Java:
    yum install java-1.8.0-openjdk-devel -y
## Install Git:
    yum install git -y
## Install Apache-Maven:
    wget https://mirrors.estointernet.in/apache/maven/maven-3/3.6.3/binaries/apache-maven-3.6.3-bin.tar.gz
    tar xvzf apache-maven-3.6.3-bin.tar.gz

    vi /etc/profile.d/maven.sh
    --------------------------------------------
    export MAVEN_HOME=/opt/apache-maven-3.6.3
    export PATH=$PATH:$MAVEN_HOME/bin
    --------------------------------------------

    source /etc/profile.d/maven.sh
    mvn -version
## Install Jenkins
    sudo wget -O /etc/yum.repos.d/jenkins.repo http://pkg.jenkins.io/redhat-stable/jenkins.repo
    sudo rpm --import http://pkg.jenkins.io/redhat-stable/jenkins.io.key
    sudo yum install jenkins -y
    service jenkins start
## Integrate Sonarqube with jenkins
1. Add sonarqube plugin 
2. Add SonarQube servers with in jenkins
   - Need to create authentication token with Sonarqube server
   
     ![image](https://user-images.githubusercontent.com/68885738/90910319-bebffd00-e3f4-11ea-8590-c9ae9018973e.png)
     
     Click on My Account
     
     ![image](https://user-images.githubusercontent.com/68885738/90910508-0ba3d380-e3f5-11ea-918a-1234e695ba01.png)
     
     select security and give some name for token and then click on Generate
3. Add SonarQube servers details with in "configure system"

![image](https://user-images.githubusercontent.com/68885738/90910714-689f8980-e3f5-11ea-889c-68e63b8302ce.png)

Name: sonar-scanner
Server URL: http://54.210.37.165:9000/
Server authentication token: (Create secret text with authentication token)

4. Add SonarQube Scanner with in "Global Tool Configuration"

![image](https://user-images.githubusercontent.com/68885738/90910959-dea3f080-e3f5-11ea-8d79-6062bff26d25.png)

## Create new pipeline job with jenkinsfile content and build
![image](https://user-images.githubusercontent.com/68885738/90911197-3cd0d380-e3f6-11ea-8b9e-27ff41492e4a.png)

Click on "SonarQube" and check details
