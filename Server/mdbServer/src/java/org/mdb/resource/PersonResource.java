/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mdb.resource;

import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import lib.db.controller.Controller;
import lib.db.controller.Pair;
import org.mdb.XmlSerializable;
import org.mdb.entity.Person;

/**
 *
 * @author Thomas
 */
@Path("person")
public class PersonResource {
    
    private final Controller<Person> controller;

    public PersonResource(){
        controller = new Controller<>("MDB_PU", Person.class);
    }
    
    @PUT
    @Path("/create")
    @Produces(MediaType.TEXT_XML)
    public Response createPerson(@FormParam("name") String name, @FormParam("surname") String surname){
        Person person = new Person();
        
        person.setName(name);
        person.setSurname(surname);
        
        try {
            controller.insert(person);
        } catch (Exception ex){
            throw new WebApplicationException("Failed to create person.", ex, Response.Status.INTERNAL_SERVER_ERROR);
        }        
        return Response
            .status(Response.Status.OK)
            .entity(XmlSerializable.getXmlStream().toXML(person))
            .build();
    }
    
    @POST
    @Path("/update/{id}")
    @Produces(MediaType.TEXT_XML)
    public Response updatePerson(@PathParam("id") Long id, @FormParam("name") String name, @FormParam("surname") String surname){
        Person person = controller.querySingle("Person.findById", new Pair("id", id));
        
        if (person == null){
            throw new WebApplicationException("Person does not exist.", Response.Status.NOT_FOUND);
        }        
        person.setName(name);
        person.setSurname(surname);
        
        if (!controller.update("Person.update", person)){
            throw new WebApplicationException("Person could not be updated.", Response.Status.INTERNAL_SERVER_ERROR);
        }        
        return Response.status(Response.Status.OK).build();
    }
    
    @DELETE
    @Path("/delete/{id}")
    @Produces(MediaType.TEXT_XML)
    public Response deletePerson(@PathParam("id") Long id){
        Person person = controller.querySingle("Person.findById", new Pair("id", id));
        
        if (person == null){
            throw new WebApplicationException("Person does not exist.", Response.Status.NOT_FOUND);
        }
        
        try {
            controller.delete(person);
        } catch (Exception ex){
            throw new WebApplicationException("Failed to delete person.", ex, Response.Status.INTERNAL_SERVER_ERROR);
        }        
        return Response.status(Response.Status.OK).build();
    }
}
