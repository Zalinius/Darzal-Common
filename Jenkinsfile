
@Library('zalinius-shared-library') _

pipeline {
    agent any
    tools {
        maven 'maven3'
    }
    stages {
   		// Note that the agent automatically checks out the source code from Github	
        stage('Build') {
            steps {
                sh 'mvn --batch-mode clean verify'
            }
        }
        stage('Deploy') {
            when {
                branch 'main'
            }
            environment {
                SONAR_CREDS = credentials('sonar')
            }
            steps {
                sonarScan()
                sh 'mvn --batch-mode clean install'  //Install publishes to the local jenkins Maven repo
	        }
	    }
    }
    
    post {
        always  { testReport()}    
        success { githubSuccess() }    
        failure {
            githubFailure() 
            script {
                if (env.BRANCH_NAME == 'main') {
                    discordSend description: "main branch build failed", footer: "😬 😬 😬", link: env.BUILD_URL, result: currentBuild.currentResult, title: env.JOB_NAME, webhookURL: "${DISCORD_WEBHOOK}"
                }
            }
        }    
    }
}
