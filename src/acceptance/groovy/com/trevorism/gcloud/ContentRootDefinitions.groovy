package com.trevorism.gcloud

import io.cucumber.groovy.EN
import io.cucumber.groovy.Hooks

this.metaClass.mixin(Hooks)
this.metaClass.mixin(EN)

def contextRootContent
def pingContent

Given(~/^the application is alive$/) { ->
    try{
        new URL("https://active.project.trevorism.com/ping").text
    }
    catch (Exception ignored){
        Thread.sleep(10000)
        new URL("https://active.project.trevorism.com/ping").text
    }
}

When(~/^I navigate to "([^"]*)"$/) { String url ->
    contextRootContent = new URL(url).text
}

When(~/^I navigate to \/ping on "([^"]*)"$/) { String url ->
    pingContent = new URL("${url}/ping").text
}

Then(~/^then a link to the help page is displayed$/) { ->
    assert contextRootContent
    assert contextRootContent.contains("/help")
}

Then(~/^pong is returned, to indicate the service is alive$/) { ->
    assert pingContent == "pong"
}