package model;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import view.BaseLayer;

public class MapGenerator {
	
	private Integer zoom = 1;
	private Double longi = 0.0d;
	private Double lati = 0.0d;
	private BufferedImage bufImage = null;
	private ImageIcon imIc;
	private BaseLayer BL;
	private Double refLatitudes[] = {0.0d,0.0d,0.0d,0.0d,0.0d,0.0d,0.0d,1.43520000000003,0.71725400000003,0.36086200000003,0.17590000000003,0.08931999999997,0.04463000000003,0.02336000000003,0.01090000000003,0.00572000000003,0.00270000000003,0.00144000000003,0.00074000000003,0.00030000000003};
	private Double refLongitudes[] = {0.0d,0.0d,0.0d,0.0d,0.0d,0.0d,0.0d,3.501777999999997,1.738806000000003,0.869844000000003,0.445019999999997,0.218180000000003,0.10799999,0.051840000000003,0.026340000000003,0.012980000000003,0.006899999999997,0.003360000000003,0.001979999999997,0.00282};
	
	public MapGenerator() {
		
	}
	
	/**Download the main map**/
	public void prepareMap(Integer z, Double a, Double b){

		zoom = zoom + z;
		lati = lati +a;
		if(Math.abs(lati)>90){
			lati = 0.0d;
		}
		longi = longi + b;
		if(Math.abs(longi)>180){
			longi = 0.0d;
		}
		
		try {
										
		    URL url = new URL("https://maps.googleapis.com/maps/api/staticmap?center="+lati+","+longi+"&zoom="+zoom+"&size=800x350&scale=4&key=XXXXXXXXXXXXXXXXXXXXXXXXX");	    
		    bufImage = ImageIO.read(url);		   
		    imIc = new ImageIcon(bufImage);		    

		    BL.setMD(imIc);

		    
		} catch (IOException e) {
			e.printStackTrace();
            System.exit(1);
		}
		
	}
	
	public Integer getZoom() {
		return zoom;
	}

	public void setZoom(Integer zoom) {
		this.zoom = zoom;
	}

	public Double getLongi() {
		return longi;
	}

	public void setLongi(Double longi) {
		this.longi = longi;
	}

	public Double getLati() {
		return lati;
	}

	public void setLati(Double lati) {
		this.lati = lati;
	}
	
	public ImageIcon getImIc() {
		return imIc;
	}

	public Double getRefLatitudes(int index) {
		return refLatitudes[index];
	}

	public Double getRefLongitudes(int index) {
		return refLongitudes[index];
	}

	/**Establish relation between the map generator and the GUI.
	 * This is the dominance relation between the Model to the View**/
	public void setViewUpdater(BaseLayer extBL) {
		BL = extBL;
	}

}
