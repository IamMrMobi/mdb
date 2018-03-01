/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mdb;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import org.mdb.entity.Person;
import org.mdb.exception.ExceptionInfo;

/**
 *
 * @author Thomas
 */
public interface XmlSerializable {
    
    /**
     * Creates a new instance of {@code XStream} and initializes it with
     * the common aliases and attributes used within this project.
     * 
     * @return the XStream instance
     */
    public static XStream getXmlStream(){
        XStream stream = new XStream(new StaxDriver());
        
        stream.alias("exception", ExceptionInfo.class);
        stream.alias("person", Person.class);
        
        stream.useAttributeFor(Person.class, "id");
        stream.useAttributeFor(ExceptionInfo.class, "type");
        
        return stream;
    }
}
