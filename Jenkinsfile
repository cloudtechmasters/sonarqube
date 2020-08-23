pipeline{
    agent any
    tools {
        maven 'maven3'
    }
    stages{
        stage("SCM"){
            steps{
                git 'https://github.com/cloudtechmasters/sonarqube.git'
            }
        }
        stage("Build Artifact") {
            steps{
                sh "mvn clean package"
            }
        }
        stage("Deploy to Sonar") {
            steps{
                withSonarQubeEnv(installationName: 'sonar-scanner', credentialsId: 'sonar-token') {
                    sh "${ tool ("sonar-scanner")}/sonar-scanner -Dsonar.projectKey=hellospringboot -Dsonar.projectName=hellospringboot -Dsonar.sourceEncoding=UTF-8 -Dsonar.sources=src"
                }
            }
        }
	stage("Quality Gate") {
            steps {
              timeout(time: 1, unit: 'HOURS') {
                waitForQualityGate abortPipeline: true
                }
            }
        }
    }
}
