/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mdb.exception;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import org.mdb.XmlSerializable;

/**
 *
 * @author Thomas
 */
@Provider
public class GenericExceptionMapper implements ExceptionMapper<Exception> {

    @Override
    public Response toResponse(Exception e) {
        ExceptionInfo info = ExceptionInfo.forException(e);
        
        return Response
            .status(Response.Status.INTERNAL_SERVER_ERROR)
            .type(MediaType.TEXT_XML)
            .entity(XmlSerializable.getXmlStream().toXML(info))
            .build();
    }
}
