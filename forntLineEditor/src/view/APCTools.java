package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

import controller.InfantryToolController;

public class APCTools extends InfantryTools {
	
	private JPanel jpAPCTools = new JPanel();
	private JTextArea jtaTargetDistance = new JTextArea();
	private JTextArea jtaQuantity = new JTextArea();
	private JTextArea jtaOtherAPC = new JTextArea();
	private JRadioButton jrReachable = new JRadioButton("Yes");
	private JRadioButton jrNotReachable = new JRadioButton("No");
	private JRadioButton jrDisplay = new JRadioButton("Yes, show catalogue");
	private JRadioButton jrDontDisplay = new JRadioButton("No, I know the APC");
	private JComboBox<String> cbHours = new JComboBox<String>();
	private JComboBox<String> cbMinutes = new JComboBox<String>();
	private JComboBox<String> cbAPC = new JComboBox<String>();
	private JComboBox<String> cbSkills = new JComboBox<String>();
	private JComboBox<String> cbEnergies = new JComboBox<String>();
	private ArrayList<String> arAPC = new ArrayList<String>();
	private ArrayList<Object> brigadeMatrix= new ArrayList<Object>();
	

	
	public APCTools(){
			
		jpAPCTools.setLayout(new BoxLayout(jpAPCTools, BoxLayout.PAGE_AXIS));
		jpAPCTools.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));	
		
	}

	/**This is the main panel to display the infantry attributes**/
	public JPanel displayAPCTools(int index) {
		
		
		jpAPCTools.removeAll();
		
	
		jpAPCTools.add(this.sectionDistance());		
		jpAPCTools.add(this.sectionGrup1());
		jpAPCTools.add(this.sectionQuantity());		
		jpAPCTools.add(this.Grup2(index));
		jpAPCTools.add(this.sectionTimeSelector());
		jpAPCTools.add(this.sectionSkills());	
		jpAPCTools.add(this.sectionEnergies());
		
		jpAPCTools.add(Box.createRigidArea(new Dimension(0, 10)));		
		jpAPCTools.add(super.iconInteraction(null, false));
		
		jpAPCTools.setBorder(new TitledBorder("APC parameters"));

		return jpAPCTools;
	}
	
	
	/**Depending on the index option builds the panel for the Firearms**/
	public JPanel Grup2(int index) {
		
		if(index == 0) {
			
			JLabel jlAPC = new JLabel();
			jlAPC.setText("Display APC catalog?");
			jlAPC.setFont(new Font("SansSerif", Font.BOLD, 16));
			
			JPanel jpGrup2 = new JPanel();
			jpGrup2.setLayout(new FlowLayout(FlowLayout.LEFT));
			
			jpGrup2.add(jlAPC);
			jpGrup2.add(jrDisplay);
			jpGrup2.add(jrDontDisplay);
			
			return jpGrup2;
		}
		
		if(index == 1) {
			
			JLabel jlAPC = new JLabel();
			jlAPC.setText("Choose your APC:");
			jlAPC.setFont(new Font("SansSerif", Font.BOLD, 16));
			
			JPanel jpAPC = new JPanel();
						
			for (int i=0; i<arAPC.toArray().length; i++) {
				cbAPC.addItem(arAPC.get(i));
			}
						
			jpAPC.add(jlAPC);
			jpAPC.add(cbAPC);
			
			return jpAPC;
		}
		
		if(index == 2) {
			
			JLabel jlAPC = new JLabel();
			jlAPC.setText("Choose your APC:");
			jtaOtherAPC.setLineWrap(true);
			
			JPanel jpAPC = new JPanel();
								
			jpAPC.add(jtaOtherAPC);
			
			return jpAPC;
		}
		return null;
			
	}
	
	public void setAPCs(ArrayList<String> arAPC ) {
		Collections.sort(arAPC, String.CASE_INSENSITIVE_ORDER);
		this.arAPC = arAPC;
	}
	

	/**draws the section of the trooper skills on the campaign**/
	public JPanel sectionSkills() {
		
		JLabel jlSkills = new JLabel();
		jlSkills.setText("Crew military skills: ");
		jlSkills.setFont(new Font("SansSerif", Font.BOLD, 16));;
		String[] skills = {"-select skill level-", "Excellent", "Very Good", "Good", "Normal", "Low", "None"};
		
		JPanel jpSkills = new JPanel();
		jpSkills.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		for (int i=0; i<skills.length; i++) {
			cbSkills.addItem(skills[i]);
		}
		
		jpSkills.add(jlSkills);
		jpSkills.add(cbSkills);
		jpSkills.setFont(new Font("SansSerif", Font.BOLD, 16));
		
		return jpSkills;
	}
	
	/**draws the section the trooper quantity of the campaign**/
	public JPanel sectionEnergies() {
		
		JLabel jlEnergies = new JLabel();
		jlEnergies.setText("Crew rest time: ");
		jlEnergies.setFont(new Font("SansSerif", Font.BOLD, 16));
		String[] Energies = {"-select rest time-", "+2 Days", "36 to 24 Hours", "24 to 12 hours", "12 to 8 hours", "Less 8 hours", "None"};
		
		JPanel jpEnergies = new JPanel();		
		jpEnergies.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		for (int i=0; i<Energies.length; i++) {
			cbEnergies.addItem(Energies[i]);
		}
		
		jpEnergies.add(jlEnergies);
		jpEnergies.add(cbEnergies);	
		
		return jpEnergies;

	}
	
	/**draws the section the trooper quantity of the campaign**/
	public JPanel sectionTimeSelector() {

		return super.sectionTimeSelector();
	}
	
	/**draws the section the trooper quantity of the campaign**/
	public JPanel sectionQuantity() {
		
		JLabel jlQuantity = new JLabel();
		jlQuantity.setText("APCs available: ");
		jlQuantity.setFont(new Font("SansSerif", Font.BOLD, 16));
		jtaQuantity.setLineWrap(true);
		
		JPanel jpQuantity = new JPanel();
		jpQuantity.setLayout(new FlowLayout(FlowLayout.LEFT));
		jpQuantity.add(jlQuantity);
		jpQuantity.add(jtaQuantity);
		
		return jpQuantity;
		
	}
	
	/**draws the section to specify distance to the enemy**/
	public JPanel sectionGrup1() {
		
		JLabel jlDistance = new JLabel();
		jlDistance.setText("Are terrain objects blocking advance?");
		jlDistance.setFont(new Font("SansSerif", Font.BOLD, 16));
		
		JPanel jpGrup1 = new JPanel();
		jpGrup1.setLayout(new FlowLayout(FlowLayout.LEFT));
		jpGrup1.add(jlDistance);
		jpGrup1.add(jrReachable);
		jpGrup1.add(jrNotReachable);
		
		return jpGrup1;
		
	}

	/**draws the section to specify type of terrain to reach the enemy**/
	public JPanel sectionDistance() {
		
		JLabel jlTargetDistance = new JLabel();
		jlTargetDistance.setText("Distance in meters to objective: ");
		jlTargetDistance.setFont(new Font("SansSerif", Font.BOLD, 16));
		jtaTargetDistance.setLineWrap(true);
		
		JPanel jpDistance = new JPanel();
		jpDistance.setLayout(new FlowLayout(FlowLayout.LEFT));
		jpDistance.add(jlTargetDistance);
		jpDistance.add(jtaTargetDistance);
		
		return jpDistance;
		
	}
		
	/**Prepares the deepest part of the row in the matrix**/
	@SuppressWarnings("unchecked")
	public ArrayList<Object> packData(){
		
		brigadeMatrix.add(jtaTargetDistance.getText());
		
		if(jrReachable.isSelected()) {
			brigadeMatrix.add(jrReachable.getText());
		}else {
			brigadeMatrix.add(jrReachable.getText());
		}
		
		brigadeMatrix.add(jtaQuantity.getText());
		
		if(jrDisplay.isSelected()) {
			brigadeMatrix.add(cbAPC.getSelectedItem().toString());
		}else {
			brigadeMatrix.add(jtaOtherAPC.getText());
		}
		
		JPanel extraction = (JPanel) jpAPCTools.getComponent(4);
		cbHours = (JComboBox<String>) extraction.getComponent(1);
		cbMinutes = (JComboBox<String>) extraction.getComponent(2);
		
		brigadeMatrix.add(cbHours.getSelectedItem().toString());
		brigadeMatrix.add(cbMinutes.getSelectedItem().toString());
		brigadeMatrix.add(cbSkills.getSelectedItem().toString());
		brigadeMatrix.add(cbEnergies.getSelectedItem().toString());
		
		return brigadeMatrix;
	}
	
	public JPanel iconInteraction(Object C, Boolean saveB) {
		
		return super.iconInteraction(C, saveB);
		
	}
	
	/**sets the controller to listen**/
	public void setController(InfantryToolController APC){
		
		jrDisplay.addActionListener(APC);
		jrDontDisplay.addActionListener(APC);
		super.iconInteraction(APC, false);
		
	}
	
	/**
	 * @return the cbHours
	 */
	public JComboBox<String> getCbHours() {
		return cbHours;
	}


	/**
	 * @return the cbMinutes
	 */
	public JComboBox<String> getCbMinutes() {
		return cbMinutes;
	}

	
	
	
}
