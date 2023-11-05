pipeline {
    agent any

    stages {


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
          stage('Unit test') {
            steps {
                sh 'mvn test'
            }
        }
        stage('Nexus'){
            steps {
                sh 'mvn deploy -DskipTests'
            }
        }
         stage('Building Image') {
            steps {
                script {
                    def dockerImageName = 'achat:1.0'  // Nom de l'image Docker
                    sh "docker build -t $dockerImageName ."
                }
            }
        }
        stage('Push to Docker Hub') {
            steps {
                script {
                    def dockerhubUsername = 'sofienlking'
                    def dockerhubPassword = 'Sofienbentarekbou7'
                    def dockerImageName = 'achat:1.0'
                    sh "docker login -u $dockerhubUsername -p $dockerhubPassword"
                    sh "docker tag $dockerImageName $dockerhubUsername/$dockerImageName"
                    sh "docker push $dockerhubUsername/$dockerImageName"
                }
            }
        }
        stage('Run Docker Compose') {
            steps {
                sh 'docker compose up -d'
            }
        }
        stage('Prometheus') {
            steps {
                sh 'docker start prometheus'
            }
        }
        stage('Grafana') {
            steps {
                sh 'docker start grafana'
            }
        }
    }

    post {
        success {
            echo 'Le pipeline a réussi. Les étapes sont terminées avec succès.'
        }
        failure {
            echo 'Le pipeline a échoué. Veuillez vérifier les étapes précédentes pour plus de détails.'
        }
    }
}
