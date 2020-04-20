package controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JRadioButton;

import model.ArtilleryMetrics;
import model.TruckMetrics;
import view.BaseLayer;

public class ArtilleryToolController implements ActionListener{
	
	private BaseLayer BL = new BaseLayer();
	private ArtilleryMetrics AM = new ArtilleryMetrics();
	private TruckMetrics TM = new TruckMetrics();
		

	public ArtilleryToolController() {
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
				
		/**If the user needs to display a list the option 1 is sent to BL to download it from the Internet
		 * If the user wants to manually enter the weapon the option 2 is sent to BL**/
		if (e.getSource() instanceof JRadioButton) {
			
			JRadioButton methodFetch = (JRadioButton)e.getSource();
			String mFetch = methodFetch.getText();
			
			if(BL.getUnitType().equals("Artillery")) {
				this.optionsArtillery(mFetch);
			}
			if(BL.getUnitType().equals("Trucks")) {
				this.optionsTrucks(mFetch);
			}
		    
		}
		
		if (e.getSource() instanceof JButton) {
			
			JButton unitType = (JButton)e.getSource();
			String unit = unitType.getText();
			
			/**This allow the positioning of troops and prepare the next toolBox**/
			if (unit.equals("Place on map")){
				
				if(BL.getUnitType().equals("Artillery")) {
					BL.getAT().iconInteraction(null, true);
				}
				
				if(BL.getUnitType().equals("Trucks")) {
					BL.getTruck().iconInteraction(null, true);
				}				
				
				BL.getMD().unitIndexAdd();
				BL.getMD().mapDevelopment(0, false, BL.getUnitType());
				
			}else if (unit.equals("Enlarge")){
				
				BL.getMD().increase();
				
			}else if (unit.equals("Reduce")){
				
				BL.getMD().decrease();	
				
			}else if (unit.equals("Done")){
				
				if(BL.getUnitType().equals("Artillery")) {
					BL.getAT().iconInteraction(null, false);
				}
				
				if(BL.getUnitType().equals("Trucks")) {
					BL.getTruck().iconInteraction(null, false);
				}
				
				BL.getMD().mapDevelopment(0, true, BL.getUnitType());
			
			}
		    
		}
			
	}

	/**This method prepares the ArtilleryToolBox according to the
	 * selected options**/
	private void optionsArtillery(String mFetch) {
		
		if (mFetch.equals("Yes, show catalogue")){				
			BL.getAT().setBatteries(AM.getArtillery());
			BL.displayArtilleryTools(1);			
		}else{				
			BL.displayArtilleryTools(2);			
		}
		
	}
	
	/**This method prepares the TruckToolBox according to the
	 * selected options**/
	private void optionsTrucks(String mFetch) {
		
		if (mFetch.equals("Yes, show catalogue")){				
			BL.getTruck().setTrucks(TM.getTrucks());
			BL.displayTruckTools(1);			
		}else{				
			BL.displayTruckTools(2);			
		}
		
	}
	
	public BaseLayer getBL() {
		return BL;
	}

	public void setBL(BaseLayer bL) {
		BL = bL;
	}
	
}
