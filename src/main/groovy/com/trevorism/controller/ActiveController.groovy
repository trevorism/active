package com.trevorism.controller

import com.google.gson.Gson
import com.trevorism.https.AppClientSecureHttpClient
import com.trevorism.model.ActiveLibrary
import com.trevorism.model.ActiveWebapp
import com.trevorism.model.ListData
import com.trevorism.model.TrevorismProject
import com.trevorism.https.SecureHttpClient

import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag


@Controller("/api/active")
class ActiveController {

    private static final String ACTIVE_SERVICE_URL = "https://list.data.trevorism.com/api/6553743902375936/content"
    private static final String ACTIVE_LIBRARIES_URL = "https://list.data.trevorism.com/api/6246650553040896/content"

    Gson gson = new Gson()
    SecureHttpClient secureHttpClient = new AppClientSecureHttpClient()

    @Tag(name = "Active Operations")
    @Operation(summary = "Gets active services in the Trevorism ecosystem")
    @Get(value = "/service", produces = MediaType.APPLICATION_JSON)
    List<ActiveWebapp> getActiveServices() {
        String json = secureHttpClient.get(ACTIVE_SERVICE_URL)
        ListData list = gson.fromJson(json, ListData)
        list.data.sort().collect{name ->
            new ActiveWebapp(name: name, url: "", github: "https://github.com/trevorism/$name", statusUrl: "https://github.com/trevorism/$name/actions/workflows/deploy.yml/badge.svg")
        }
    }

    @Tag(name = "Active Operations")
    @Operation(summary = "Gets a project from the service in the Trevorism ecosystem")
    @Get(value = "/service/{name}", produces = MediaType.APPLICATION_JSON)
    TrevorismProject getProjectFromService(String name) {
        try{
            String json = secureHttpClient.get("https://project.trevorism.com/project/service/$name")
            Gson gson = new Gson()
            gson.fromJson(json, TrevorismProject)
        }catch(Exception e){
            return new TrevorismProject()
        }
    }

    @Tag(name = "Active Operations")
    @Operation(summary = "Gets libraries in the Trevorism ecosystem")
    @Get(value = "/library", produces = MediaType.APPLICATION_JSON)
    List<ActiveLibrary> getActiveLibs() {
        String json = secureHttpClient.get(ACTIVE_LIBRARIES_URL)
        ListData list = gson.fromJson(json, ListData)
        list.data.sort().collect{name ->
            new ActiveLibrary(name: name, version: "https://github.com/trevorism/$name/releases/latest", github: "https://github.com/trevorism/$name", statusUrl: "https://github.com/trevorism/$name/actions/workflows/build.yml/badge.svg")
        }
    }

    @Operation(summary = "")
    @Get(value = "/library/{name}/version", produces = MediaType.APPLICATION_JSON)
    String getLibraryVersion(String name) {
        String json = secureHttpClient.get("https://github.project.trevorism.com/repo/$name/release")
        return json
    }

}
