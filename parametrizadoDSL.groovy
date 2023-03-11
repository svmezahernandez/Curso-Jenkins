job('ejemplo2-job-DLS'){
	description('Job DSL de ejemplo para el curso de Jenkins')
    scm {
      git('https://github.com/macloujulian/jenkins.job.parametrizado.git','main') { node ->
        node / gitConfigName('svmezahernandez')
        node / gitConfigEmail('svmezahernandez@gmail.com')
      }
    }
    parameters {
        stringParam('nombre', defaultValue='Sergio', description = 'Parametro de cadena del job booleano')
        choiceParam('planeta',['Mercurio','Venus','Tierra','Marte','Jupiter','Saturno','Urano','Neptuno'])
        booleanParam('agente',false)
    }
    triggers {
      	cron('H/20 * * * *')
    }
    steps {
      	shell("bash jobscript.sh")
    }
    publishers {
      	mailer('svmezahernandez@gmail.com',true,true)
        slackNotifier {
            notifyAborted(true)
            notifyEveryFailure(true)
            notifyNotBuilt(false)
            notifyUnstable(false)
            notifyBackToNormal(true)
            notifySuccess(false)
            notifyRepeatedFailure(false)
            startNotification(false)
            includeTestSummary(false)
            includeCustomMessage(false)
            customMessage(null)
            sendAs(null)
            commitInfoChoice('NONE')
            teamDomain(null)
            authToken(null)
        }
    }    
}
