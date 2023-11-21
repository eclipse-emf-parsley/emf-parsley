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
     jdk "openjdk-jdk17-latest"
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
        jacoco (
          classPattern: 'plugins/org.eclipse.emf.parsley.*/**/classes,dsl/org.eclipse.emf.parsley.dsl/**/classes,dsl/org.eclipse.emf.parsley.dsl.ui/**/classes,dsl/org.eclipse.emf.parsley.dsl.additional.builder/**/classes',
          exclusionPattern: '**/*StandaloneSetup*.class,**/*ExecutableExtensionFactory*.class,**/*UiModule*.class,**/*RuntimeModule*.class,**/*Activator*.class,**/*Nature*.class,**/EmfParsleyDslProjectCreator.class,**/EmfParsleyDslNewProjectWizard*.class,**/Abstract*ProposalProvider*,**/*Internal*,**/*Sequencer*,**/*GrammarAccess*,**/antlr/*.*,**/internal/*.*,**/org/eclipse/emf/parsley/dsl/model/impl/*.*,**/org/eclipse/emf/parsley/dsl/model/util/*.*,**/org/eclipse/emf/parsley/rap/**/*.*,**/org/eclipse/emf/parsley/runtime/**/*.*,**/org/eclipse/emf/parsley/tests/**/*.*,**/org/eclipse/emf/parsley/junit4/**/*.*,**/org/eclipse/emf/parsley/inject/parameters/*.*',
          maximumBranchCoverage: '100',
          maximumClassCoverage: '100',
          maximumComplexityCoverage: '100',
          maximumLineCoverage: '100',
          maximumMethodCoverage: '100',
          minimumBranchCoverage: '90',
          minimumClassCoverage: '90',
          minimumComplexityCoverage: '90',
          minimumLineCoverage: '90',
          minimumMethodCoverage: '90',
          sourceInclusionPattern: '**/*.java',
          sourcePattern: 'plugins/org.eclipse.emf.parsley.*/src,plugins/org.eclipse.emf.parsley.*/xtend-gen,dsl/org.eclipse.emf.parsley.dsl/src,dsl/org.eclipse.emf.parsley.dsl/xtend-gen,dsl/org.eclipse.emf.parsley.dsl.ui/src,dsl/org.eclipse.emf.parsley.dsl.ui/xtend-gen,dsl/org.eclipse.emf.parsley.dsl.additional.builder/src,dsl/org.eclipse.emf.parsley.dsl.additional.builder/xtend-gen'
        )
      }
    }
  }

  post {
    always {
      junit testResults: '**/target/surefire-reports/*.xml'
      archiveArtifacts artifacts: '**/target/work/data/.metadata/.log, **/screenshots/, **/wm.err, **/hs_err_pid*.log'
    }
    success {
      archiveArtifacts artifacts: 'target/repository/, **/target/work/data/.metadata/.log, **/site/jacoco-aggregate/'
    }
  }
}
