package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import view.BaseLayer;

public class InfantryMetrics {
	
	private ArrayList<String> arrifles = new ArrayList<String>( );
	//private BaseLayer BL = new BaseLayer();
	
	public InfantryMetrics() {
		
	}

	/**A request is thrown first to a local database and if the resource 
	 * is not found there a known trustable website is visited and the data 
	 * gathered there**/
	public ArrayList<String> getRifles() {
		
			try {
				
			    URL url = new URL("http://www.military-today.com/firearms.htm");
			    
			    // create a url connection object
			    URLConnection urlConnection = url.openConnection();
			    
			    // wrap the url connection in a buffered reader
			    BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
			    
			    String line;

			    Boolean rifleAct = false;

	            while ((line = reader.readLine()) != null) {
	            		            	
	            	if(line.contains("Assault Rifles")) { 
	            		
	            		rifleAct = true;
	            		
	            	}
	            	
	            	if(rifleAct == true && line.contains("alt=") && !line.contains("flags")) {
	            		
	            		String[] parts = line.split("alt=\"");
	            		parts[1] = parts[1].substring(0,parts[1].indexOf("\""));
	            			            		
	            		if(parts[1].equals("Top 10 Pistols")) {
	            			
	            			return arrifles;
	            			
	            		}
		                            		
	            		arrifles.add(parts[1]);
	            		
	            	}
	            }

	            reader.close();
	            
			    
			} catch (IOException e) {
				e.printStackTrace();
	            System.exit(1);
			}
			
			return arrifles;
			
		}
	
		
	public void setBL(BaseLayer BL) {
		//this.BL = BL;
	}
	

}
