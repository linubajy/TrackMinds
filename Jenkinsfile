/*
Always use '/' as the other one is treated as a charater in groovy script
Updated with downloading artifact from jfrog to PC
Updated with SSH Agent -
*/

pipeline{
  agent any
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
              sh 'mvn test'
            } 
          }
          stage("Build")
          {
             steps
            {
               sh 'mvn package'
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
    
    */
        stage('Sonar Analysis')
        {
          agent any
          steps
          {
             withSonarQubeEnv('sonarToken')
            {
                 sh 'java -version'
                 sh 'mvn clean package sonar:sonar'
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
     */
     
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
                         sh 'ssh -o StrictHostKeyChecking=no ubuntu@52.38.156.206 pwd'
                        sh 'scp -r /var/jenkins_home/workspace/aws-pipeline/artifacts/springbootApp/*.jar ubuntu@52.38.156.206:/home/ubuntu/artifacts'
                      
              
        }
           
              
      }
      
         
         failure {  
             echo 'Mail being sent'
            // mail bcc: '', body: "<b>Example</b><br>Project: ${env.JOB_NAME} <br>Build Number: ${env.BUILD_NUMBER} <br> URL de build: ${env.BUILD_URL}", cc: '', charset: 'UTF-8', from: 'blinu.1997@gmail.com', mimeType: 'text/html', replyTo: '', subject: "ERROR CI: Project name -> ${env.JOB_NAME}", to: "blinu.1997@gmail.com";  
         }  
          
     }  
  
  
}
