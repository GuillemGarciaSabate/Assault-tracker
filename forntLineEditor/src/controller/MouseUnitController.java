package controller;


import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JLayeredPane;

import model.DataMgmt;
import view.BaseLayer;
import view.MapDisplay;



public class MouseUnitController implements MouseListener, MouseMotionListener {
	
    private double X,Y;
    private int index;
    private DataMgmt DM;
    private BaseLayer BL;
    private JLayeredPane layeredPane;
    private String unitType = new String();
	
	public MouseUnitController() {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
				
		X = e.getPoint().getX();
		Y = e.getPoint().getY();

	}

	@Override
	
	/**It gathers the coordinates of the deployed unit
	 * saves the introduced information and prepares the map again**/
	public void mouseReleased(MouseEvent e) {
					
		unitType = BL.getUnitType();
		DM.setUpCoordinates(layeredPane.getComponent(index-1).getLocation());
		
		if(unitType.equals("Infantry")) {
			DM.buildAdvance(BL.getIT().packData());			
		}
		if(unitType.equals("Tanks")) {
			DM.buildAdvance(BL.getTT().packData());
		}
		if(unitType.equals("Artillery")) {
			DM.buildAdvance(BL.getAT().packData());
		}
		if(unitType.equals("APC")) {
			DM.buildAdvance(BL.getAPC().packData());
		}
		if(unitType.equals("Trucks")) {
			DM.buildAdvance(BL.getTruck().packData());
		}
		
	}
	
	/**Selects the last element placed on the layeredPane (map)
	 * and fixes an element where the user drags it**/
	@Override
	public void mouseDragged(MouseEvent e) {
											
		layeredPane.getComponent(index-1).setLocation(e.getX()+e.getComponent().getX() - ((int) X), e.getY()+e.getComponent().getY() - ((int) Y));                             
			
	}
	
	public void setMD(MapDisplay MD) {

		layeredPane = MD.getLayeredPane();
		
	}
	
	public void setDM(DataMgmt dM) {
		DM = dM;
	}
	
	public void setBL(BaseLayer bL) {
		BL = bL;
	}
	
	public void setElementIndex(int index) {
		this.index = index;
	}
		
	//Unimplemented methods
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	

}
