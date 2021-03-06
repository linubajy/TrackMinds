/*
Always use '/' as the other one is treated as a charater in groovy script
Updated with downloading artifact from jfrog to PC
Updated with SSH Agent -
*/

pipeline{
  agent any;
  tools
  {
    maven 'maven'
    jdk 'JDK'
  }
       
  stages {
    
          stage('Fetch')
          {
            steps 
            {
               
               git branch: 'master', url: 'https://github.com/linubajy/TrackMinds.git'
            }
          }
    
          stage("Test")
          { 
            steps
            {
              bat 'mvn clean'
              bat 'mvn test'
            } 
          }
          stage("Build")
          {
             steps
            {
               bat 'mvn package'
            } 
              
          } 
    
          stage("SonarQube analysis") 
          {
            steps
            {
              withSonarQubeEnv('sonar-server') 
              {
                withMaven(maven:'maven')
                {
                  bat 'java -version'
                  bat 'mvn sonar:sonar'
                }
              }
            }  
          }
    
    
        stage('Sonar Analysis')
        {
          agent any
          steps
          {
             withSonarQubeEnv('sonar-server')
            {
                 echo 'Using sonarQube'
                 bat 'java -version'
                 bat 'mvn clean package sonar:sonar'
            }
          }
        }
        
    
         stage("Quality Gate") {
            steps {
              sleep(60)
              timeout(time: 1, unit: 'HOURS') {
                script{
                        def qg = waitForQualityGate() 
                        if (qg.status != 'OK')
                        {
                            error "Pipeline aborted due to gate failure : ${qg.status}"
                            waitForQualityGate abortPipeline: true
                         }
                    }
              }
            }
          }
          
          stage('collect artifact')
          {
                steps{
                    archiveArtifacts artifacts: 'target/*.jar', followSymlinks: false
                 }
            }      
         stage('deploy to artifactory')
         {
            steps{
    
             
                rtUpload (
                    serverId: 'jfrog',
                    spec: '''{
                    "files": [
                         {
                             "pattern": "target/*.jar",
                             "target": "art-doc-dev-loc/springbootApp/"
                        }
                     ]
                }''',
 
  
    buildName: 'Build1',
    buildNumber: '1'
    )
              
   
              
              
              
              
              
              
     }}
    stage('Deploy to S3 Bucket')
         {
                steps
                {
                    withCredentials([[$class: 'AmazonWebServicesCredentialsBinding', accessKeyVariable: 'AWS_ACCESS_KEY_ID', credentialsId: '67e0088d-4a51-4afd-b053-d8b8698a66ba', secretKeyVariable: 'AWS_SECRET_ACCESS_KEY']])
                    {
                        s3Upload(file:'C:/Users/Linu/.jenkins/workspace/aws-pipeline/target/ClassRoom--1-0.0.1-SNAPSHOT.jar', bucket:'jenkins-jarfiles', path:'jenkins-jarfiles/ClassRoom--1-0.0.1-SNAPSHOT.jar')
                     }         
                    
                }
         }   
     
    }
  
    post {  
      success{
       
        rtDownload (
                    serverId: 'jfrog',
                    spec: '''{
                    "files": [
                         {
                             "pattern": "art-doc-dev-loc/springbootApp/",
                             "target": "artifacts/"
                        }
                     ]
                }''',
 
  
           buildName: 'Build2',
           buildNumber: '2'      
           )
           
            sshagent(['aws']){
                         bat 'ssh -o StrictHostKeyChecking=no ubuntu@52.38.156.206 pwd'
                    //    bat 'scp -r /var/jenkins_home/workspace/aws-pipeline/artifacts/springbootApp/*.jar ubuntu@52.38.156.206:/home/ubuntu/artifacts'
                     bat 'scp -r C:/Users/Linu/.jenkins/workspace/aws-pipeline/artifacts/springbootApp/*.jar ubuntu@52.38.156.206:/home/ubuntu/artifacts'
                          
                      
              
        }
           
              
      }
      
         
         failure {  
             echo 'Mail being sent'
            // mail bcc: '', body: "<b>Example</b><br>Project: ${env.JOB_NAME} <br>Build Number: ${env.BUILD_NUMBER} <br> URL de build: ${env.BUILD_URL}", cc: '', charset: 'UTF-8', from: 'blinu.1997@gmail.com', mimeType: 'text/html', replyTo: '', subject: "ERROR CI: Project name -> ${env.JOB_NAME}", to: "blinu.1997@gmail.com";  
         }  
          
     }  
  
  
}
