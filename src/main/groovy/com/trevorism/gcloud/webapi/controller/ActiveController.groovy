package com.trevorism.gcloud.webapi.controller

import com.google.gson.Gson
import com.trevorism.gcloud.webapi.model.ActiveLibrary
import com.trevorism.gcloud.webapi.model.ActiveWebapp
import com.trevorism.gcloud.webapi.model.ListData
import com.trevorism.gcloud.webapi.model.TrevorismProject
import com.trevorism.https.DefaultSecureHttpClient
import com.trevorism.https.SecureHttpClient
import io.swagger.annotations.ApiOperation

import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.PathParam
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

@Path("active")
class ActiveController {

    private static final String ACTIVE_SERVICE_URL = "https://list.data.trevorism.com/api/6553743902375936/content"
    private static final String ACTIVE_LIBRARIES_URL = "https://list.data.trevorism.com/api/6246650553040896/content"

    Gson gson = new Gson()
    SecureHttpClient secureHttpClient = new DefaultSecureHttpClient()

    @ApiOperation(value = "")
    @GET
    @Path("service")
    @Produces(MediaType.APPLICATION_JSON)
    List<ActiveWebapp> getActiveServices() {
        String json = secureHttpClient.get(ACTIVE_SERVICE_URL)
        ListData list = gson.fromJson(json, ListData)
        list.data.sort().collect{name ->
            new ActiveWebapp(name: name, url: "", github: "https://github.com/trevorism/$name", statusUrl: "https://github.com/trevorism/$name/actions/workflows/deploy.yml/badge.svg")
        }
    }

    @ApiOperation(value = "")
    @GET
    @Path("service/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    TrevorismProject getProjectFromService(@PathParam("name") String name) {
        try{
            String json = secureHttpClient.get("https://project.trevorism.com/project/service/$name")
            Gson gson = new Gson()
            gson.fromJson(json, TrevorismProject)
        }catch(Exception e){
            return new TrevorismProject()
        }
    }


    @ApiOperation(value = "")
    @GET
    @Path("library")
    @Produces(MediaType.APPLICATION_JSON)
    List<ActiveLibrary> getActiveLibs() {
        String json = secureHttpClient.get(ACTIVE_LIBRARIES_URL)
        ListData list = gson.fromJson(json, ListData)
        list.data.sort().collect{name ->
            new ActiveLibrary(name: name, version: "https://github.com/trevorism/$name/releases/latest", github: "https://github.com/trevorism/$name", statusUrl: "https://github.com/trevorism/$name/actions/workflows/build.yml/badge.svg")
        }
    }

    @ApiOperation(value = "")
    @GET
    @Path("library/{name}/version")
    @Produces(MediaType.APPLICATION_JSON)
    String getLibraryVersion(@PathParam("name") String name) {
        String json = secureHttpClient.get("https://github.project.trevorism.com/repo/$name/release")
        return json
    }

}
