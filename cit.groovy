node("$NodeName"){
    wrks = env.WORKSPACE
    stage("Prepare"){
        println("Preparing the environment...")
        git(
            url: "git@github.com:jayabalandevsecops/aragroovy.git",
            branch: "master"
        )
        dir('config'){
            git(
                url: "git@github.com:jayabalandevsecops/aragroovyconf.git",
                branch: "master"
            )
        }
        println("Prepared the environment..!")
    }
    stage("Clone"){
        println("Cloning the App repo...")
        load('app/clone.groovy')
        println("Cloned the app repo..!")
    }
    stage("Build"){
        println("Building the App...")
        load('app/build.groovy')
        println("Build the App..!")
    }
    stage("Deploy"){
        println("Deploying the app...")
        sh "chmod +x scripts/dep.sh"
        sh "scripts/dep.sh"
        println("Deployed the app..!")
    }
}