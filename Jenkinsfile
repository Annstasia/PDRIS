pipeline {
    agent any

    tools {
        maven 'maven-3.6.3'
    }

    stages {
        stage('Check Environment') {
            steps {
                sh 'echo "JAVA_HOME=$JAVA_HOME"'
                sh 'echo "PATH=$PATH"'
                sh 'java -version || echo "Java not found"'
                sh 'mvn -version || echo "Maven not found"'
                sh 'docker version'
                sh 'docker compose version'
            }
        }
        stage('Build') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Test') {
            steps {
                // Запуск тестов
                sh 'mvn test -P allure'
            }
            post {
                always {
                    // Публикация отчетов о тестах
                    allure includeProperties:
                     false,
                     jdk: '',
                     results: [[path: 'target/allure-results']]
                }
            }
        }

        stage('Static Analysis') {
            steps {
                withSonarQubeEnv("SonarServer1") {
                    script {
                        sh 'mvn clean package sonar:sonar -P sonar'
                    }
                }
                echo 'Static Analysis Completed'
            }
        }
        stage('Deploy') {
            steps {
                sh 'docker compose up -d'
            }
        }
    }


    post {
        success {
            echo 'Сборка прошла успешно!'
        }
        failure {
            echo 'Сборка завершилась с ошибкой.'
        }
    }
}
