package controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JRadioButton;

import model.InfantryMetrics;
import model.APCMetrics;
import view.BaseLayer;

public class InfantryToolController implements ActionListener{
	
	private BaseLayer BL = new BaseLayer();
	private InfantryMetrics IM = new InfantryMetrics();
	private APCMetrics APCM = new APCMetrics();
		

	public InfantryToolController() {
		
	}
	
	/**If the user needs to display a list the option 1 is sent to BL to download it from the Internet
	 * If the user wants to manually enter the weapon the option 2 is sent to BL**/
	@Override
	public void actionPerformed(ActionEvent e) {
						
		if (e.getSource() instanceof JRadioButton) {
			
			JRadioButton methodFetch = (JRadioButton)e.getSource();
			String mFetch = methodFetch.getText();
			
			if(BL.getUnitType().equals("Infantry")) {
				this.optionsInfantry(mFetch);
			}
			
			if(BL.getUnitType().equals("APC")) {
				this.optionsAPC(mFetch);
			}
		    
		}
		
		if (e.getSource() instanceof JButton) {
			
			JButton unitType = (JButton)e.getSource();
			String unit = unitType.getText();
			
			/**This allow the positioning of troops and prepare the next toolBox**/
			if (unit.equals("Place on map")){
				
				if(BL.getUnitType().equals("Infantry")) {
					BL.getIT().iconInteraction(null, true);
				}
				
				if(BL.getUnitType().equals("APC")) {
					BL.getAPC().iconInteraction(null, true);
				}				
				
				BL.getMD().unitIndexAdd();
				BL.getMD().mapDevelopment(0, false, BL.getUnitType());
				
			}else if (unit.equals("Enlarge")){
				
				BL.getMD().increase();
				
			}else if (unit.equals("Reduce")){
				
				BL.getMD().decrease();	
				
			}else if (unit.equals("Done")){
				
				if(BL.getUnitType().equals("Infantry")) {
					BL.getIT().iconInteraction(null, false);
				}
				
				if(BL.getUnitType().equals("APC")) {
					BL.getAPC().iconInteraction(null, false);
				}
				
				BL.getMD().mapDevelopment(0, true, BL.getUnitType());
			
			}
		    
		}
			
	}
	
	/**This method prepares the InfantryToolBox according to the
	 * selected options**/
	private void optionsInfantry(String mFetch) {
		
		if (mFetch.equals("Yes, show catalogue")){				
			BL.getIT().setFireArms(IM.getRifles());
			BL.displayInfantryTools(1);				
		}else{				
			BL.displayInfantryTools(2);				
		}
		
	}
	
	/**This method prepares the APCToolBox according to the
	 * selected options**/
	private void optionsAPC(String mFetch) {
		
		if (mFetch.equals("Yes, show catalogue")){				
			BL.getAPC().setAPCs(APCM.getAPC());
			BL.displayAPCTools(1);				
		}else{				
			BL.displayAPCTools(2);				
		}
		
	}

	public BaseLayer getBL() {
		return BL;
	}

	public void setBL(BaseLayer bL) {
		BL = bL;
	}
	
}
