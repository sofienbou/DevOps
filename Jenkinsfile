pipeline {
    agent any
    environment {
        version = '1.0'
    }
    stages {
        stage('Git Checkout') {
            steps {
                script {
                    git branch: 'maryembranch', url: 'https://github.com/jihedmakthri/cloudwizard-5arctic3.git';
                }
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean package'
            }
        }

     stage('SonarQube') {
            steps {
                script {

                        sh 'mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=sonar'

                }
            }
        }


stage('Unit Tests') {
            steps {

                    // Assurez-vous que vous êtes dans le répertoire du projet Maven
                    // Exécutez les tests unitaires avec Maven
                    sh 'mvn test'

            }
            post {
                always {
                    // Archive les rapports de test unitaires JUnit
                    junit '**/target/surefire-reports/TEST-*.xml'
                }
            }
        }

        stage('Mockito Tests') {
            steps {

                    // Assurez-vous que vous êtes dans le répertoire du projet Maven
                    // Exécutez les tests Mockito avec Maven (assurez-vous d'avoir configuré les tests Mockito correctement)
                    sh 'mvn clean test -Pmockito-tests'

            }
            post {
                always {
                    // Archive les rapports de test Mockito JUnit
                    junit '**/target/surefire-reports/TEST-*.xml'
                }
            }
        }

        stage ('NEXUS DEPLOY') {

       steps {
       sh 'mvn deploy -DskipTests'
      }
    }

       stage('Build docker image'){
            steps{
                script{
                    sh 'docker build -t maryemtayeb/achat .'
                }
            }
        }
        stage('push image to hub') {
            steps {
                script {
                  withCredentials([string(credentialsId: 'docker-hub', variable: 'docker-hub')]) {
                      sh 'docker login -u maryemtayeb -p Maryouma1998'
    // some block
}
      sh 'docker push maryemtayeb/achat'
                }
            }
        }

    }
    }


