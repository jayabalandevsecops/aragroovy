folder("$BUName"){}
folder("$BUName/$ProductName"){}
pipelineJob("$BUName/$ProductName/CICD_$AppName"){
    parameters{
        stringParam("AppRepo", "$ApplicationRepo", "GIT URL - App Repo")
        stringParam("AppName", "$AppName", "App Nane")
        stringParam("TestRunTool", "$UniTestRun", "")
        stringParam("NodeName", "$NodeName", "")
        activeChoiceParam('Branch'){
            description('Branch to be build')
            choiceType('SINGLE_SELECT')
            groovyScript{
                script('["Select", "master", "develop", "feature"]')
                fallbackScript('Fallback choice')
            }
        }
    }
    definition{
        cps{
            def jobScript = new readFileFromWorkspace('cit.groovy')
            script(jobScript)
            def approvals = org.jenkinsci.plugins.sciptsecurity.scripts.ScriptApprovals.get()
            approvals.approveScript(approvals.hash(jobScript, "groovy"))
        }
    }
}