package com.example.SpringCrud.Exception;

public class RecordNotFoundException extends Exception {

	/**
	 * 
	 */
	String msg="";
	private static final long serialVersionUID = 1L;
         public RecordNotFoundException(String msg) {
        	  super(msg);
         }
	             
	

}
