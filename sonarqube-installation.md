## sonarqube-installation

## Step 1: Update the server
	  sudo yum update -y
## Step 2: Install java 8
	  sudo yum install java-1.8.0-openjdk-devel -y
## Step 3: Login as root and execute the following commands
	  sysctl vm.max_map_count
	  sysctl fs.file-max
	  ulimit -n
	  ulimit -u

## Setup PostgreSQL 10 Database For SonarQube
    amazon-linux-extras install postgresql10 vim epel -y
    yum install -y postgresql-server postgresql-devel
    /usr/bin/postgresql-setup --initdb
Need to change config file as shown in below
    
    vi /var/lib/pgsql/data/pg_hba.conf
Replace Method name "peer" to "md5"

![image](https://user-images.githubusercontent.com/68885738/90953619-aef2f800-e48a-11ea-9b50-489183e9b0c1.png)

Enable  postgresql:
    
    systemctl enable postgresql
Start postgresql:

    systemctl start postgresql

Login into Database
	  
    su - postgres
You can get into Postgres console by typing
	  
    psql
Create a sonarqubedb database
	  
    create database sonarqubedb;
Create the sonarqube DB user with a strongly encrypted password
	  
    create user sonarqube with encrypted password 'Cloud#436';
Next, grant all privileges to sonrqube user on sonarqubedb;
	  
    grant all privileges on database sonarqubedb to sonarqube
Exit the psql prompt using the following command
	  
    \q
Switch to your sudo user using the exit command
	  
    exit

## Setup Sonarqube Web Server
Download the latest sonarqube installation file to /opt folder. You can get the latest download link from here. http://www.sonarqube.org/downloads/
	
    cd /opt
    sudo wget https://binaries.sonarsource.com/Distribution/sonarqube/sonarqube-7.6.zip
	
Unzip sonarqube source files and rename the folder.
	
    sudo unzip sonarqube-7.6.zip
    sudo mv sonarqube-7.6 sonarqube
    chown -R ec2-user:ec2-user /opt/sonarqube
	
Open /opt/sonarqube/conf/sonar.properties file
	
     sudo vi /opt/sonarqube/conf/sonar.properties
![image](https://user-images.githubusercontent.com/68885738/90953687-7acc0700-e48b-11ea-94f9-4b32f8f170b0.png)
![image](https://user-images.githubusercontent.com/68885738/90953736-c1b9fc80-e48b-11ea-88f9-2629c85fdf56.png)
![image](https://user-images.githubusercontent.com/68885738/90953772-05146b00-e48c-11ea-8dab-143be09d878b.png)

Navigate to the start script directory
	
    cd /opt/sonarqube/bin/linux-x86-64
Change the Run as User: to ec2-user
	  
    vi sonar.sh
Start the sonarqube service
	
    sh sonar.sh start
    sh sonar.sh status
Troubleshooting Sonarqube:
All the logs of sonarqube are present in the /opt/sonarqube/logs directory
	
    cd /opt/sonarqube/logs
