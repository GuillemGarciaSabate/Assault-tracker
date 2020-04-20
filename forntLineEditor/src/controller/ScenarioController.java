package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import javax.swing.JButton;
import model.MapGenerator;
import view.BaseLayer;


public class ScenarioController  implements ActionListener, MouseWheelListener, MouseListener {
	
	private BaseLayer BL = new BaseLayer();
	private MapGenerator MG = new MapGenerator();
	private Double X1, Y1 = new Double(0.0d);

	
	private Double refLat[] = {0.0d,104.44814,58.70706,30.8266,15.6854,7.6296,3.8148,
			1.43520000000003,0.71725400000003,0.36086200000003,0.17590000000003,0.08931999999997,
			0.04463000000003,0.02336000000003,0.01090000000003,0.00572000000003,0.00270000000003,
			0.00144000000003,0.00074000000003,0.00030000000003};
	
	private Double refLong[] = {0.0d,226.64148,112.36946,56.18242,27.98962,13.99481,7.01374,
			3.501777999999997,1.738806000000003,0.869844000000003,0.445019999999997,0.218180000000003,
			0.10799999,0.051840000000003,0.026340000000003,0.012980000000003,0.006899999999997,
			0.003360000000003,0.001979999999997,0.00282};

	
	public ScenarioController() {
						
	}
	
	/**Prepares the default display and the default map activates**/
	public void defaultLaunch() {
		
		MG.setLati(0.0d);
		MG.setLongi(0.0d);
		MG.setZoom(1);
		MG.prepareMap(1, 0.0d, 0.0d);
		BL.activate();
		
	}
	
	public MapGenerator getMG() {
		return MG;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() instanceof JButton) {	
			
			JButton butonType = (JButton)e.getSource();
			String buton = butonType.getText();
			
			if (buton.equals("^")){				
				MG.prepareMap(0, this.selectLat(MG.getZoom()), 0.0d);				
			}
			
			if (buton.equals("v")){				
				MG.prepareMap(0, -this.selectLat(MG.getZoom()), 0.0d);							
			}

			if (buton.equals("<")){				
				MG.prepareMap(0, 0.0d, -this.selectLong(MG.getZoom()));							
			}

			if (buton.equals(">")){				
				MG.prepareMap(0, 0.0d, this.selectLong(MG.getZoom()));							
			}
			
			if (buton.equals("+")){				
				MG.prepareMap(1,0.0d,0.0d);								
			}
			
			if (buton.equals("-")){				
				MG.prepareMap(-1,0.0d,0.0d);							
			}
			
			BL.activate();	
			
			if (buton.equals("Save phase")){
				
				this.defaultLaunch();
				
			}		    
		}
	}
	
	/**Scales the map with the mouse wheel**/
	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		
		int notches = e.getWheelRotation();
		notches *= -1;
		MG.prepareMap(notches,0.0d,0.0d);
		BL.activate();
		
	}
	
	/**Select the scale for advancing on longitude**/
	private Double selectLong(Integer Z){
		
		switch(Z){
		/**Earth level**/
		case 1: return 10.0d;
		case 2: return 10.0d;
		case 3: return 8.0d;
		/**Continent**/
		case 4: return 5.5d;
		case 5: return 5.0d;
		/**Country**/
		case 6: return 2.5d;
		case 7: return 2.0d;
		case 8: return 1.5d;
		case 9: return 0.8d;
		/**Province**/
		case 10: return 0.5d;
		case 11: return 0.2d;
		case 12: return 0.005d;
		/**City**/
		case 13: return 0.0045d;
		case 14: return 0.0040d;
		case 15: return 0.0035d;
		case 16: return 0.0030d;
		/**Street level**/
		case 17: return 0.0015d;
		case 18: return 0.0010d;
		case 19: return 0.0001d;
		case 20: return 0.0005d;
		default: return 5.0d;
		}
	}	
	/**Select the scale for advancing on latitude**/	
	private Double selectLat(Integer Z){
			
			switch(Z){
			/**Earth level**/
			case 1: return 20.0d;
			case 2: return 20.0d;
			case 3: return 8.0d;
			/**Continent**/
			case 4: return 5.5d;
			case 5: return 5.0d;
			/**Country**/
			case 6: return 2.5d;
			case 7: return 2.0d;
			case 8: return 1.5d;
			case 9: return 0.8d;
			/**Province**/
			case 10: return 0.5d;
			case 11: return 0.2d;
			case 12: return 0.005d;
			/**City**/
			case 13: return 0.0045d;
			case 14: return 0.0040d;
			case 15: return 0.0035d;
			case 16: return 0.0030d;
			/**Street level**/
			case 17: return 0.0015d;
			case 18: return 0.0010d;
			case 19: return 0.0001d;
			case 20: return 0.0005d;
			default: return 5.0d; 
			}
		
	}

	/**Upon on clicking a place, coordinates are extracted and the map is zoomed there**/
	@Override
	public void mousePressed(MouseEvent e) {
		
		Double refLongitudes = refLong[MG.getZoom()-1];
		Double refLatitudes = refLat[MG.getZoom()-1];
		
		//mandatory in the formula
		refLatitudes = refLatitudes * (-1);
		refLongitudes = Math.abs(refLongitudes);
								
		X1 = (e.getPoint().getY()  - MG.getImIc().getIconHeight()/2)*refLatitudes/MG.getImIc().getIconHeight() + MG.getLati();
		Y1 = (e.getPoint().getX() - MG.getImIc().getIconWidth()/2)*refLongitudes/MG.getImIc().getIconWidth()  + MG.getLongi();
		
		MG.setLati(0.0d);
		MG.setLongi(0.0d);
		MG.prepareMap(1, X1, Y1);
		 
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	
	}

	public void setMG(MapGenerator mP) {
		MG = mP;
	}
	
	public void setBL(BaseLayer bL) {
		BL = bL;
	}
	
	/**Unused**/
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub	
	}
	
	/**Unused**/
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub	
	}
	
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub		
	}


}
