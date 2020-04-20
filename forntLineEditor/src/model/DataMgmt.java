package model;

import java.awt.AWTException;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import controller.PhaseToolController;
import view.BaseLayer;

public class DataMgmt{
	
	private BaseLayer BL = new BaseLayer();
	private ArrayList<ArrayList<?>> advanceMatrix = new ArrayList<ArrayList<?>>();
	private MapGenerator MG;
	private int currentPhase = 0;
	private double Lat;
	private double Long;
	
	public DataMgmt() {
				
	}
	
	public void newPhase(String H, String M, PhaseToolController PC) {
		
		BL.updatePhases(H,M,PC);
		
	}
	
	/**This method saves the phase in an image**/
	public void savePhase(JPanel panel, int i) {
				
		BufferedImage imagebuf=null;
		
	    try {
	        imagebuf = new Robot().createScreenCapture(panel.getBounds());
	    } catch (AWTException e1) {
	        // TODO Auto-generated catch block
	        e1.printStackTrace();
	    }  
	     Graphics2D graphics2D = imagebuf.createGraphics();
	     panel.paint(graphics2D);
	     try {
	    	 
	        ImageIO.write(imagebuf,"jpeg", new File("../forntLineEditor/assaultPlan/OperationPhase_"+i+".jpeg"));
	    } catch (Exception e) {
	        // TODO Auto-generated catch block
	        System.out.println("error");
	    }
	     
	    currentPhase++;
	     
		
	}
	
	/**Displays the image containing the selected phase**/
	public void reloadPhase(int i) {
		
		BufferedImage image;
		try {
			
			image = ImageIO.read(new File("../forntLineEditor/assaultPlan/OperationPhase_"+i+".jpeg"));
			ImageIcon im;
		    im = new ImageIcon(image);
		    BL.setMD(im);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
					
	}
	/**
	 * This method receives the data for every unit on the map and prepares
	 * a matrix full with all the combat information to use later on metrics
	 * 
	 * #################################################
	 * ##BrigadeInfo,Latitude,Longitude,UnitIndex,Phase#
	 * #################################################
	 * **/
	public void buildAdvance(ArrayList<Object> brigadeMatrix) {
		
		brigadeMatrix.add(Lat);
		brigadeMatrix.add(Long);
		brigadeMatrix.add(BL.getMD().getUnitIndex());//we need to make clear that unitIndex doesn't grow as we change unit types
		brigadeMatrix.add(currentPhase);
				
		advanceMatrix.add(brigadeMatrix);
				
	}
	
	/**Extracts the coordinates of a unit to save 
	 * the current position on the brigadeMatrix**/
	public void setUpCoordinates(Point p) {
		
		Double refLongitudes = MG.getRefLongitudes(MG.getZoom()-1);
		Double refLatitudes = MG.getRefLatitudes(MG.getZoom()-1);
	
		//High cances we need to uncomment this and delete the IF ELSE
		/**	refLatitudes = refLatitudes * (-1);
		refLongitudes = Math.abs(refLongitudes);**/
		
		if(MG.getLati()<0) {
			refLatitudes = Math.abs(refLatitudes);			
		}else {
			refLatitudes = refLatitudes * (-1);
		}
		
		if(MG.getLongi()<0) {
			refLongitudes = refLongitudes * (-1);
		}else {
			refLongitudes = Math.abs(refLongitudes);
		}
				
		//Equirectangular projection: optimal for small streets 
		Long = (p.getX() - 10 +15 - MG.getImIc().getIconWidth()/2)*refLongitudes/MG.getImIc().getIconWidth()  +MG.getLongi(); 
		Lat = (p.getY() - 10 +15 - MG.getImIc().getIconHeight()/2)*refLatitudes/MG.getImIc().getIconHeight() + MG.getLati();
				
	}

	public void setMG(MapGenerator mG) {
		MG = mG;
	}

	public void setBL(BaseLayer bL) {
		BL = bL;
	}


}
