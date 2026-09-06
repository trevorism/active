package com.trevorism.gcloud

import io.cucumber.groovy.EN
import io.cucumber.groovy.Hooks

this.metaClass.mixin(Hooks)
this.metaClass.mixin(EN)

String baseUrl = System.getenv("ACCEPTANCE_BASE_URL") ?: "https://active.project.trevorism.com"

def contextRootContent
def pingContent

Given(~/^the application is alive$/) { ->
    try{
        new URL("${baseUrl}/api/ping").text
    }
    catch (Exception ignored){
        Thread.sleep(10000)
        new URL("${baseUrl}/api/ping").text
    }
}

When(~/^I navigate to the context root$/) { ->
    contextRootContent = new URL("${baseUrl}/api").text
}

When(~/^I navigate to the ping endpoint$/) { ->
    pingContent = new URL("${baseUrl}/api/ping").text
}

Then(~/^then a link to the help page is displayed$/) { ->
    assert contextRootContent
    assert contextRootContent.contains("/help")
}

Then(~/^pong is returned, to indicate the service is alive$/) { ->
    assert pingContent == "pong"
}