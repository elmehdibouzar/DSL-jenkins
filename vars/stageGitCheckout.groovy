def call(String git_url, String git_branch, def body) { 
    stage('git checkout') {
        container('build') {
            gitCheckout("${git_url}", git_branch)     
        }
        container('jnlp') {
            git_author = sh(returnStdout: true, script: 'git show -s --pretty=%an').trim()
            currentBuild.description = "${currentBuild.description} - ${git_author}"
        }
    }
}
