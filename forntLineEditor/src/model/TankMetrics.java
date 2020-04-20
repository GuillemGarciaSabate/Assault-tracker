package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import view.BaseLayer;

public class TankMetrics {
	
	private ArrayList<String> artanks = new ArrayList<String>( );
	//private BaseLayer BL = new BaseLayer();
	
	public TankMetrics() {
		
	}

	/**A request is thrown first to a local database and if the resource 
	 * is not found there a known trustable website is visited and the data 
	 * gathered there**/
	public ArrayList<String> getTanks() {
		
			try {
				
			    URL url = new URL("http://www.military-today.com/tanks.htm");
			    
			    // create a url connection object
			    URLConnection urlConnection = url.openConnection();
			    
			    // wrap the url connection in a buffered reader
			    BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
			    
			    String line;

			    Boolean mbtAct = false;

	            while ((line = reader.readLine()) != null) {
	            		            	
	            	if(line.contains("Main Battle Tanks")) { 
	            		
	            		mbtAct = true;
	            		
	            	}
	            	
	            	if(mbtAct == true && line.contains("alt=") && !line.contains("flags")) {
	            		
	            		String[] parts = line.split("alt=\"");
	            		parts[1] = parts[1].substring(0,parts[1].indexOf("\""));
	            			            		
	            		if(parts[1].equals("Top 10 Main Battle Tanks") || parts[1].contains("Intellectus Publishing ")) {
	            			
	            			return artanks;
	            			
	            		}
		                            		
	            		artanks.add(parts[1]);
	            		
	            	}
	            }

	            reader.close();
	            
			    
			} catch (IOException e) {
				e.printStackTrace();
	            System.exit(1);
			}
			
			return artanks;
			
		}
	
		
	public void setBL(BaseLayer BL) {
		//this.BL = BL;
	}
	

}
