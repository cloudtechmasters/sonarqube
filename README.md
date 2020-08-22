# Deploy spring-boot-hello to sonarqube

## Pre-requisites:
    - Install Java
    - Install Git
    - Install Maven
    - Install Jenkins
    - Install sonarqube
    - Install sonar-scanner
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
## Sonarqube installation
   [Sonarqube installaton](./sonarqube-installation.md)
## Instll sonar-scanner
     cd /opt
     wget https://binaries.sonarsource.com/Distribution/sonar-scanner-cli/sonar-scanner-cli-4.4.0.2170-linux.zip
     unzip sonar-scanner-cli-4.4.0.2170-linux.zip
     cd sonar-scanner-4.4.0.2170-linux/conf/sonar-scanner.properties
   ![image](https://user-images.githubusercontent.com/68885738/90954007-29714700-e48e-11ea-815d-fa851fc21839.png)
Give sonaeqube url inside of "sonar-scanner.properties" file
## Integrate Sonarqube with jenkins
1. Add sonarqube plugin 
2. Add SonarQube servers with in jenkins
   - Need to create authentication token with in Sonarqube server
   
     ![image](https://user-images.githubusercontent.com/68885738/90910319-bebffd00-e3f4-11ea-8590-c9ae9018973e.png)
   
   - Need to create webhook with in Sonarqube server (use Jenkins server URL)
    
    ![image](https://user-images.githubusercontent.com/68885738/90953421-06906400-e489-11ea-9f1d-859b3b9fa7b8.png)
        
     Click on My Account
     
     ![image](https://user-images.githubusercontent.com/68885738/90910508-0ba3d380-e3f5-11ea-918a-1234e695ba01.png)
     
     Fill details and click on create
     
     ![image](https://user-images.githubusercontent.com/68885738/90953480-80285200-e489-11ea-8ec1-0eedb4635efb.png)
     
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
