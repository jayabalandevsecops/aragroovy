folder("$BUName"){}
folder("$BUName/$ProductName"){}
pipelineJob("$BUName/$ProductName/CICD_$AppName"){
    parameters{
        stringParam("AppRepo", "$ApplicationRepo", "GIT URL - App Repo")
        stringParam("AppName", "$AppName", "App Nane")
        stringParam("UnitTestTool", "$UnitTestRun", "")
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
            def jobScript = readFileFromWorkspace('cit.groovy')
            def approvals = org.jenkinsci.plugins.scriptsecurity.scripts.ScriptApproval.get()
            approvals.approveScript(approvals.hash(jobScript, "groovy"))
            script(jobScript)
        }
    }
}