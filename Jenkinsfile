pipeline {
  agent any
  environment {
    APP_HOST = '18.216.132.205'
  }
  stages {
    stage('Build') {
      steps { dir('backend') { sh './gradlew clean assemble' } }
    }
    stage('Test') {
      steps { dir('backend') { sh './gradlew test' } }
      post { always { junit 'backend/build/test-results/test/*.xml' } }
    }
    stage('Deploy') {
      when { branch 'main' }
      steps {
        sshagent(['app-server-ssh']) {
          sh 'scp -o StrictHostKeyChecking=no backend/build/libs/backend-0.0.1-SNAPSHOT.jar deploy@$APP_HOST:/tmp/app.jar'
          sh 'ssh -o StrictHostKeyChecking=no deploy@$APP_HOST sudo /opt/dal-taxi/deploy.sh'
        }
      }
    }
  }
}
