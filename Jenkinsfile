pipeline {
  agent {
    kubernetes {
      inheritFrom 'centos-8'
    }
  }
  
  options {
    buildDiscarder(logRotator(numToKeepStr:'20'))
    disableConcurrentBuilds()
    timeout(time: 300, unit: 'MINUTES')
  }

  tools {
     maven "apache-maven-3.8.5"
     jdk "openjdk-jdk11-latest"
  }

  stages {
    stage('Show') {
      steps {
          sh """
            ls -al
          """
      }
    }

    stage('Build') {
      steps {
          wrap([$class: 'Xvnc', takeScreenshot: false, useXauthority: true]) {
          sh """
            mvn -f releng/org.eclipse.emf.parsley.parent/pom.xml clean verify
          """
          }
      }
    }
  }

  post {
    always {
      junit testResults: '**/target/surefire-reports/*.xml'
      archiveArtifacts artifacts: '**/target/work/data/.metadata/.log, **/screenshots, **/hs_err_pid*.log'
    }
    success {
      archiveArtifacts artifacts: 'target/repository/, **/target/work/data/.metadata/.log'
    }
    cleanup {
      script {
        def curResult = currentBuild.currentResult

        if (curResult != 'SUCCESS' || lastResult != 'SUCCESS') {
          def color = ''
          switch (curResult) {
            case 'SUCCESS':
              color = '#00FF00'
              break
            case 'UNSTABLE':
              color = '#FFFF00'
              break
            case 'FAILURE':
              color = '#FF0000'
              break
            default: // e.g. ABORTED
              color = '#666666'
          }
        }
      }
    }
  }
}
