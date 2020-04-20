package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import model.DataMgmt;


public class PhaseToolController implements ActionListener{
	

	private DataMgmt DM = new DataMgmt();
	
	public PhaseToolController() {
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if (e.getSource() instanceof JButton) {
			
			String butonIndex = new String();
			JButton jbSave = (JButton) e.getSource();
			butonIndex = jbSave.getText();			
			int i = Character.getNumericValue(butonIndex.charAt(0));
			
			DM.reloadPhase(i);
			    
		}
			
	}
	
	public void setDM(DataMgmt dM) {
		 DM = dM;
	}
	

}
