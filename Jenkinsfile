pipeline {
  agent {
    kubernetes {
      inheritFrom 'centos-7'
    }
  }
  
  options {
    buildDiscarder(logRotator(numToKeepStr:'20'))
    disableConcurrentBuilds()
    timeout(time: 300, unit: 'MINUTES')
  }

  tools {
     jdk "openjdk-jdk11-latest"
  }

  stages {
    stage('Build') {
      steps {
        wrap([$class: 'Xvnc', takeScreenshot: false, useXauthority: true]) {
          sh """
            metacity --sm-disable --replace 2> wm.err &
            ./mvnw -f releng/org.eclipse.emf.parsley.parent/pom.xml clean verify -Prcp-build,jacoco
          """
        }
      }
    }
  }

  post {
    always {
      junit testResults: '**/target/surefire-reports/*.xml'
      archiveArtifacts artifacts: '**/target/work/data/.metadata/.log, **/screenshots/, **/wm.err, **/hs_err_pid*.log'
    }
    success {
      archiveArtifacts artifacts: 'target/repository/, **/target/work/data/.metadata/.log'
    }
  }
}
