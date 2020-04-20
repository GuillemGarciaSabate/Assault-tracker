package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;
import controller.InfantryToolController;

public class InfantryTools {
	
	private JPanel jpInfantryTools = new JPanel();
	private JPanel jplogoInteraction = new JPanel();
	private JTextArea jtaTargetDistance = new JTextArea();
	private JTextArea jtaQuantity = new JTextArea();
	private JTextArea jtaOtherFirearm = new JTextArea();
	private JRadioButton jrReachable = new JRadioButton("Yes");
	private JRadioButton jrNotReachable = new JRadioButton("No");
	private JRadioButton jrDisplay = new JRadioButton("Yes, show catalogue");
	private JRadioButton jrDontDisplay = new JRadioButton("No, I know the riffle");
	private JButton jbAddTroop = new JButton("Place on map");
	private JButton jbLarge = new JButton("Enlarge");
	private JButton jbReduce = new JButton("Reduce");
	private JButton jbDone = new JButton("Done");
	private JComboBox<String> cbHours = new JComboBox<String>();
	private JComboBox<String> cbMinutes = new JComboBox<String>();
	private JComboBox<String> cbFireArm = new JComboBox<String>();
	private JComboBox<String> cbSkills = new JComboBox<String>();
	private JComboBox<String> cbEnergies = new JComboBox<String>();
	private ArrayList<String> arrifles = new ArrayList<String>();
	private ArrayList<Object> brigadeMatrix = new ArrayList<Object>();
	
	public InfantryTools(){
			
		jpInfantryTools.setLayout(new BoxLayout(jpInfantryTools, BoxLayout.PAGE_AXIS));
		jpInfantryTools.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));	
		
	}

	/**This is the main panel to display the infantry attributes**/
	public JPanel displayInfantryTools(int index) {
		
		
		jpInfantryTools.removeAll();
		
	
		jpInfantryTools.add(this.sectionDistance());		
		jpInfantryTools.add(this.sectionGrup1());
		jpInfantryTools.add(this.sectionQuantity());		
		jpInfantryTools.add(this.Grup2(index));
		jpInfantryTools.add(this.sectionTimeSelector());
		jpInfantryTools.add(this.sectionSkills());	
		jpInfantryTools.add(this.sectionEnergies());
		

		jpInfantryTools.add(Box.createRigidArea(new Dimension(0, 10)));
		
		jpInfantryTools.add(this.iconInteraction(null, false));
		
		
		jpInfantryTools.setBorder(new TitledBorder("Fireteam parameters"));

		return jpInfantryTools;
	}
	
	
	/**Depending on the index option builds the panel for the Firearms**/
	public JPanel Grup2(int index) {
		
		if(index == 0) {
			
			JLabel jlFirearm = new JLabel();
			jlFirearm.setText("Display riffle catalog?");
			jlFirearm.setFont(new Font("SansSerif", Font.BOLD, 16));
			
			JPanel jpGrup2 = new JPanel();
			jpGrup2.setLayout(new FlowLayout(FlowLayout.LEFT));
			
			jpGrup2.add(jlFirearm);
			jpGrup2.add(jrDisplay);
			jpGrup2.add(jrDontDisplay);
			
			return jpGrup2;
		}
		
		if(index == 1) {
			
			JLabel jlFirearm = new JLabel();
			jlFirearm.setText("Choose your weapon:");
			jlFirearm.setFont(new Font("SansSerif", Font.BOLD, 16));
			
			JPanel jpFireArm = new JPanel();
						
			for (int i=0; i<arrifles.toArray().length; i++) {
				cbFireArm.addItem(arrifles.get(i));
			}
						
			jpFireArm.add(jlFirearm);
			jpFireArm.add(cbFireArm);
			
			return jpFireArm;
		}
		
		if(index == 2) {
			
			JLabel jlFirearm = new JLabel();
			jlFirearm.setText("Choose your weapon:");
			jtaOtherFirearm.setLineWrap(true);
			
			JPanel jpFireArm = new JPanel();
								
			jpFireArm.add(jlFirearm);
			jpFireArm.add(jtaOtherFirearm);
			
			return jpFireArm;
		}
		return null;
			
	}
	
	/**Fixes an arrayList of firearms downloaded from a Database
	 * to a local variable which will be added on a drop-down menu**/
	public void setFireArms(ArrayList<String> arrifles ) {
		Collections.sort(arrifles, String.CASE_INSENSITIVE_ORDER);
		this.arrifles = arrifles;
	}
	

	/**draws the section of the trooper skills on the campaign**/
	public JPanel sectionSkills() {
		
		JLabel jlSkills = new JLabel();
		jlSkills.setText("Troopers military skills: ");
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
		jlEnergies.setText("Troopers rest time: ");
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
		
		JLabel jlTime = new JLabel();
		jlTime.setText("Operation starting at: ");	
		jlTime.setFont(new Font("SansSerif", Font.BOLD, 16));
		for (int i=0; i<25; i++) {		
			if(i<10) {
				cbHours.addItem("0"+Integer.toString(i));
			}else {
				cbHours.addItem(Integer.toString(i));
			}			
		}
		
		for (int i=0; i<61; i++) {		
			if(i<10) {
				cbMinutes.addItem("0"+Integer.toString(i));
			}else {
				cbMinutes.addItem(Integer.toString(i));
			}			
		}
		
		JPanel jpTime = new JPanel();
		jpTime.setLayout(new FlowLayout(FlowLayout.LEFT));
		jpTime.add(jlTime);
		jpTime.add(cbHours);
		jpTime.add(cbMinutes);
		
		return jpTime;
		
	}
	
	/**draws the section the trooper quantity of the campaign**/
	public JPanel sectionQuantity() {
		
		JLabel jlQuantity = new JLabel();
		jlQuantity.setText("Troopers available: ");
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
	public ArrayList<Object> packData(){
		
		brigadeMatrix.add(jtaTargetDistance.getText());
		
		if(jrReachable.isSelected()) {
			brigadeMatrix.add(jrReachable.getText());
		}else {
			brigadeMatrix.add(jrReachable.getText());
		}
		
		brigadeMatrix.add(jtaQuantity.getText());
		
		if(jrDisplay.isSelected()) {
			brigadeMatrix.add(cbFireArm.getSelectedItem().toString());
		}else {
			brigadeMatrix.add(jtaOtherFirearm.getText());
		}
		
		brigadeMatrix.add(cbHours.getSelectedItem().toString());
		brigadeMatrix.add(cbMinutes.getSelectedItem().toString());
		brigadeMatrix.add(cbSkills.getSelectedItem().toString());
		brigadeMatrix.add(cbEnergies.getSelectedItem().toString());
		
		return brigadeMatrix;
	}
	
	/**Displays the options to customize the icons:  
	 * C is the controller, it's fixed after the Main sends it to setController. 
	 * saveB is a boolean, false for the Add button & true for the options buttons**/
	public JPanel iconInteraction(Object C, Boolean saveB) {
		
		jbAddTroop.setFont(new Font("SansSerif", Font.BOLD, 12));
		jbLarge.setFont(new Font("SansSerif", Font.BOLD, 12));
		jbReduce.setFont(new Font("SansSerif", Font.BOLD, 12));
		jplogoInteraction.removeAll();
		
		if(C instanceof ActionListener) {
			
			jbAddTroop.addActionListener((ActionListener) C);
			jbLarge.addActionListener((ActionListener) C);
			jbReduce.addActionListener((ActionListener) C);
			jbDone.addActionListener((ActionListener) C);
			
			jplogoInteraction.add(jbAddTroop);
			
		}else if(saveB == true){
			
			jplogoInteraction.add(jbLarge);
			jplogoInteraction.add(jbReduce);
			jplogoInteraction.add(jbDone);
			
						
		}else if(saveB == false) {
			jplogoInteraction.add(jbAddTroop);
		}
		
		jplogoInteraction.updateUI();
		return jplogoInteraction;
	}
	
	/**sets the controller to listen**/
	public void setController(InfantryToolController TC){
		
		jrDisplay.addActionListener(TC);
		jrDontDisplay.addActionListener(TC);
		this.iconInteraction(TC, false);
		
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
