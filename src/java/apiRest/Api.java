/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apiRest;

import apiRest.controlador.Controlador;
import apiRest.Modelo.Response;
import com.google.gson.Gson;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author aldis
 */
@Path("api")
public class Api {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of Api
     */
    public Api() {
    }

    /**
     * Retrieves representation of an instance of apiRest.Api
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/xml")
    public String getXml() {
        //TODO return proper representation object
        return "<datos>hola</datos>";
    }

    /**
     * PUT method for updating or creating an instance of Api
     *
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(String content) {
    }

    @POST
    public String postXml(@FormParam("dato1") String dato1, @FormParam("dato2") String dato2) {

        Controlador controlador = new Controlador();
        Response response = controlador.calcularRespuesta(dato1, dato2);
        
        if(response!=null){
            return controlador.convertirJson(response);
        }else{
            return "Por favor verificar los datos enviados.";
        }
        
        
              

    }

}
