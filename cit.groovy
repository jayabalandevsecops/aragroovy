node("$NodeName"){
wrks = env.WORKSPACE
stage("Prepare"){
    println("Preparing the environment...")
    git(
        url: "git@github.com:jayabalandevsecops/aragroovy.git",
        branch: master
    )
    dir('Config'){
        git(
            url: "git@github.com:jayabalandevsecops/aragroovyconf.git",
            branch: master
        )
    }
    println("Prepared the environment..!")
}
}