package org.nic.pmusha.utility;

import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;



public class QueryConstructor {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private  String constructQuery(String name)
	{

		try{
		
			
			InputStream inputStream = this.getClass().getResourceAsStream("/queries/"+name);

		//	String queryString = IOUtils.toString(jrxmlInputStream, "UTF-8");
			
		    byte[] bytes= IOUtils.toByteArray(inputStream);

			
		    String s = new String(bytes);   
		    
		    System.out.println("string query"+s);

			return s;

		}catch(Exception e){

			e.printStackTrace();
			return null;
		}

	}
	
	
	public static String getQuery(String name) {	
	
		
	String query=new QueryConstructor().constructQuery(name);		
		
	return query;		
		
		
	}
	


}
