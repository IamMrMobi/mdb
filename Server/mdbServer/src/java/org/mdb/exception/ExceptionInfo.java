/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mdb.exception;

/**
 *
 * @author Thomas
 */
public class ExceptionInfo {
    
    private Class type;
    
    private String message;
    
    private ExceptionInfo parent;
    
    /**
     * Constructs a new empty ExceptionInfo.
     * 
     * However new objects of this class should be created using
     * the static method {@code forException} which derives it from
     * an existing Exception object.
     */
    private ExceptionInfo(){
        
    }

    /**
     * Gets the type of the underlying {@code Exception} of this {@code ExceptionInfo} object.
     * 
     * @return the class of the exception which has been thrown
     */
    public Class getType() {
        return type;
    }

    public void setType(Class type) {
        this.type = type;
    }

    /**
     * Gets the message of the underlying {@code Exception} of this {@code ExceptionInfo} object.
     * 
     * @return the message of the exception which has been thrown or null in case
     *         the Exception did not have any message
     */
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Gets the {@code ExceptionInfo} object of the cause of the underlying {@code Exception}.
     * 
     * @return the parent {@code ExceptionInfo} of the {@code Exception} or null in case
     *         the Exception did not have a cause
     */
    public ExceptionInfo getParent() {
        return parent;
    }

    public void setParent(ExceptionInfo parent) {
        this.parent = parent;
    }
    
    /**
     * Constructs a new {@code ExceptionInfo} object of the given {@code Throwable}.
     * If the throwable has a cause also the parent of the object will be set otherwise
     * it will be set to null.
     * 
     * @param e the {@code Throwable} of which the {@code ExceptionInfo} and its parents should be constructed
     * @return the instance of the {@code ExceptionInfo} build for this {@code Throwable}
     */
    public static ExceptionInfo forException(Throwable e){
        ExceptionInfo info = new ExceptionInfo();
        
        info.setType(e.getClass());
        info.setMessage(e.getMessage());
        
        if (e.getCause() != null){
            info.setParent(forException(e.getCause()));
        }
        return info;
    }
}
