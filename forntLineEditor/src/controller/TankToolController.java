package controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JRadioButton;

import model.TankMetrics;
import view.BaseLayer;

public class TankToolController implements ActionListener{
	
	private BaseLayer BL = new BaseLayer();
	private TankMetrics TM = new TankMetrics();
		

	public TankToolController() {
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
				
		/**If the user needs to display a list the option 1 is sent to BL to download it from the Internet
		 * If the user wants to manually enter the weapon the option 2 is sent to BL**/
		if (e.getSource() instanceof JRadioButton) {
			
			JRadioButton unitType = (JRadioButton)e.getSource();
			String unit = unitType.getText();
			
			if (unit.equals("Yes, show catalogue")){
				
				//Trespasses the assault rifles to the infantry panel
				BL.getTT().setTanks(TM.getTanks());
				BL.displayTankTools(1);
				
			}
			
			if (unit.equals("No, I know the tank")){
				
				BL.displayTankTools(2);
				
			}
		    
		}
		
		if (e.getSource() instanceof JButton) {
			
			JButton unitType = (JButton)e.getSource();
			String unit = unitType.getText();
			
			/**This allow the positioning of troops and prepare the next toolBox**/
		    
			if (unit.equals("Place on map")){
								
				BL.getTT().iconInteraction(null, true);			
				BL.getMD().unitIndexAdd();
				BL.getMD().mapDevelopment(0, false, "Tanks");
				
			}else if (unit.equals("Enlarge")){
				
				BL.getMD().increase();
				
			}else if (unit.equals("Reduce")){
				
				BL.getMD().decrease();	
				
			}else if (unit.equals("Done")){
								
				BL.getTT().iconInteraction(null, false);			
				BL.getMD().mapDevelopment(0, true, "Tanks");
			
			}
			
		}
			
	}

	public BaseLayer getBL() {
		return BL;
	}

	public void setBL(BaseLayer bL) {
		BL = bL;
	}
	
}
