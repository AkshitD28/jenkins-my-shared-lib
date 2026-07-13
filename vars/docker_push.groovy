def call(String credId, String imageName){
    withCredentials([usernamePassword(
                    credentialsId: "${credId}",
                    passwordVariable: 'dockerHubPass',
                    usernameVariable: 'dockerHubUser'
                )]) {
                    // Log in to Docker Hub
                    sh "docker login -u ${env.dockerHubUser} -p ${env.dockerHubPass}"
                    
                    // Tag the image (Ensure this matches your local image name)
                    sh "docker image tag "${imageName}" ${env.dockerHubUser}/${imageName}"
                    
                    // Push the tagged image
                    sh "docker push ${env.dockerHubUser}/${imageName}:latest"
                }
}
