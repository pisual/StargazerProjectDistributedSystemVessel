package com.stargazerproject.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SerializableUtil {
	
//	public static byte[] serialize(Object object) throws IOException{
//		Schema Object_Schema = RuntimeSchema.getSchema(object.getClass());
//		ByteArrayOutputStream temp = new ByteArrayOutputStream();
//		LinkedBuffer BUFFER = LinkedBuffer.allocate();
//		ProtobufIOUtil.writeTo(temp, object, Object_Schema, BUFFER);
//		byte[] body = temp.toByteArray();
//		return body;
//	}
//	
//	public static Object deserialize(byte[] byteArray){
//		Schema Object_Schema = RuntimeSchema.getSchema(new Object().getClass());
//		Object object = Object_Schema.newMessage();
//		ProtobufIOUtil.mergeFrom(byteArray, object, Object_Schema);
//		return object;
//	}
	
	public static byte[] serialize(Object object) throws IOException{
		byte[] bytes = null;     
        ByteArrayOutputStream bos = new ByteArrayOutputStream();     
        try {       
            ObjectOutputStream oos = new ObjectOutputStream(bos);        
            oos.writeObject(object);       
            oos.flush();        
            bytes = bos.toByteArray ();     
            oos.close();        
            bos.close();       
        } catch (IOException ex) {       
            ex.printStackTrace();  
        }     
        return bytes;   
	}
	
	public static Object deserialize(byte[] byteArray){
		 Object obj = null;     
	        try {       
	            ByteArrayInputStream bis = new ByteArrayInputStream (byteArray);       
	            ObjectInputStream ois = new ObjectInputStream (bis);       
	            obj = ois.readObject();     
	            ois.close();  
	            bis.close();  
	        } catch (IOException ex) {       
	            ex.printStackTrace();  
	        } catch (ClassNotFoundException ex) {       
	            ex.printStackTrace();  
	        }     
	        return obj;   
	}
	
	
}
