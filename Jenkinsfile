node {
   def mvnHome
   stage('Preparation') { // for display purposes
      // Get some code from a GitHub repository
      git 'https://github.com/lt9512/webdemo.git'
      // Get the Maven tool.
      // ** NOTE: This 'M3' Maven tool must be configured
      // **       in the global configuration.
      mvnHome = tool 'M3'

      properties([
        buildDiscarder(logRotator(artifactDaysToKeepStr: '', artifactNumToKeepStr: '', daysToKeepStr: '', numToKeepStr: '10')),
        [$class: 'GithubProjectProperty', displayName: '', projectUrlStr: 'https://github.com/lt9512/webdemo/'],
        pipelineTriggers([[$class: 'GitHubPushTrigger']])
    ])


   }
   stage('Build') {
      // Run the maven build
      if (isUnix()) {
         sh "'${mvnHome}/bin/mvn' -Dmaven.test.failure.ignore clean package"
      } else {
         bat(/"${mvnHome}\bin\mvn" -Dmaven.test.failure.ignore clean package/)
      }
   }

   stage('Automated Tests') {
      junit '**/target/surefire-reports/TEST-*.xml'
      archive 'target/*.jar'
   }

   stage('Deploy to Prod') {
        // Depends on the 'Credentials Binding Plugin'
        // (https://wiki.jenkins-ci.org/display/JENKINS/Credentials+Binding+Plugin)
        withCredentials([[$class          : 'UsernamePasswordMultiBinding', credentialsId: 'cloudfoundry',
                          usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD']]) {
            sh '''
                curl -L "https://cli.run.pivotal.io/stable?release=linux64-binary&source=github" | tar -zx
                ./cf api https://api.run.pivotal.io
                ./cf auth $USERNAME $PASSWORD
                ./cf target -o luthomps -s development
                ./cf push lukegeekweekly -p target/*.jar
               '''
        }
    }
}