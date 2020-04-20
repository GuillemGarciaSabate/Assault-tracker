package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;

import model.DataMgmt;
import view.BaseLayer;


public class GeneralToolController implements ActionListener{

	private int i = 0;
	private BaseLayer BL = new BaseLayer();
	private DataMgmt DM = new DataMgmt();
	private PhaseToolController PC = new PhaseToolController();
	private String currentUnit = new String();
	private InfantryToolController ITC = new InfantryToolController();
	private TankToolController TTC = new TankToolController();
	private ArtilleryToolController ATC = new ArtilleryToolController();

	public GeneralToolController() {
				
	}
		
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() instanceof JComboBox) {
			
			JComboBox<?> unitType = ((JComboBox<?>) e.getSource());
			String unit = unitType.getSelectedItem().toString();
			currentUnit = unit;
			
			
			if (unit.equals("Infantry")){
				
				BL.getIT().setController(ITC);
				BL.displayInfantryTools(0);
				
			}
			if (unit.equals("Tanks")){
				
				BL.getTT().setController(TTC);
				BL.displayTankTools(0);
				
			}
			if (unit.equals("Artillery")){
				
				BL.getAT().setController(ATC);
				BL.displayArtilleryTools(0);
				
			}
			if (unit.equals("APC")){
				
				BL.getAPC().setController(ITC);
				BL.displayAPCTools(0);
				
			}
			if (unit.equals("Trucks")){
				
				BL.getTruck().setController(ATC);
				BL.displayTruckTools(0);
				
			}
			
			BL.setUnitType(currentUnit);
			
		    
		}
		
		if (e.getSource() instanceof JButton) {
			
			JButton jbSave = (JButton) e.getSource();
			String saveB = jbSave.getText();
			

			if (saveB.equals("Save phase")){
				
				
				if (currentUnit.equals("Infantry")){	
					DM.newPhase(BL.getIT().getCbHours().getSelectedItem().toString(),BL.getIT().getCbMinutes().getSelectedItem().toString(), PC);					
				}	
				if (currentUnit.equals("Artillery")){	
					DM.newPhase(BL.getAT().getCbHours().getSelectedItem().toString(), BL.getAT().getCbMinutes().getSelectedItem().toString(),PC);					
				}
				if (currentUnit.equals("Tanks")){	
					DM.newPhase(BL.getTT().getCbHours().getSelectedItem().toString(),BL.getTT().getCbMinutes().getSelectedItem().toString(), PC);					
				}
				if (currentUnit.equals("APC")){	
					DM.newPhase(BL.getAPC().getCbHours().getSelectedItem().toString(),BL.getAPC().getCbMinutes().getSelectedItem().toString(), PC);					
				}
				if (currentUnit.equals("Trucks")){	
					DM.newPhase(BL.getTruck().getCbHours().getSelectedItem().toString(),BL.getTruck().getCbMinutes().getSelectedItem().toString(), PC);					
				}
				
				BL.getMD().mapDevelopment(100, false, currentUnit);
				DM.savePhase(BL.getMD().getPanel(), i);	
				i++;
				
			}
			
			if (saveB.equals("New phase")){
				
				BL.getMD().getSC().defaultLaunch();
				
			}
		    
		}
			
	}

	public InfantryToolController getITC() {
		return ITC;
	}

	public TankToolController getTTC() {
		return TTC;
	}

	public ArtilleryToolController getATC() {
		return ATC;
	}

	public BaseLayer getBL() {
		return BL;
	}

	public void setBL(BaseLayer bL) {
		BL = bL;
		ITC.setBL(BL);
		TTC.setBL(BL);
		ATC.setBL(BL);
	}
	
	public void setDM(DataMgmt dM) {
		 DM = dM;
		 PC.setDM(dM);
	}

	public DataMgmt getDM() {
		return DM;
	}
	
	
}
