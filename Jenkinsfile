pipeline {
    agent any

    stages {
        stage('Hi!') {
            steps {
                script {
                    echo 'Hello, World!'
                }
            }
        }

        stage('Git Checkout') {
            steps {
                git branch: 'sofien', url: 'https://github.com/sofienbou/DevOps'
            }
        }

        stage('Clean') {
            steps {
                sh 'mvn clean'
            }
        }

        stage('Compile') {
            steps {
                sh 'mvn compile'
            }
        }

        stage('SonarQube') {
            steps {
                withSonarQubeEnv(installationName: 'DevOps') {
                  sh 'mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=sonar'
                }
            }
        }
        stage('Nexus'){
            steps {
                sh 'mvn deploy -DskipTests'
            }
        }
    }

    post {
        success {
            echo 'Le pipeline a réussi. Les étapes de nettoyage, de compilation et d\'analyse SonarQube sont terminées avec succès.'
        }
        failure {
            echo 'Le pipeline a échoué. Veuillez vérifier les étapes précédentes pour plus de détails.'
        }
    }
}
