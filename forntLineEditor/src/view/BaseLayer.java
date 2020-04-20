package view;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import controller.MouseUnitController;
import controller.PhaseToolController;
import controller.ScenarioController;
import controller.GeneralToolController;



public class BaseLayer extends JFrame{
	
	private static final long serialVersionUID = 1L; //needs a revision
	private JPanel jpToolkit = new JPanel();
	private JPanel jpCommonTools = new JPanel();
	private JPanel jpScenario = new JPanel();
	private JPanel jpPhases = new JPanel();
	private JPanel jpCurrentToolBox = new JPanel();
	private JPanel jpScroll = new JPanel();
	private JScrollPane jsPhases = new JScrollPane();
	private JComboBox<String> cbType = new JComboBox<String>();	
	private JButton jbSavePhase = new JButton();
	private JButton jbNewPhase = new JButton();
	private ArrayList<String> unitTypes = new ArrayList<String>( );
	
	private int butonIndex = 0;
	private String unitType = new String();
	
	private GridBagConstraints gbc = new GridBagConstraints();
	private MapDisplay MD = new MapDisplay(); 
	private MouseUnitController MUC = new MouseUnitController();
	
	private InfantryTools IT = new InfantryTools();
	private TankTools TT = new TankTools();
	private ArtilleryTools AT = new ArtilleryTools();
	private APCTools APC = new APCTools();
	private TruckTools Truck = new TruckTools();

	
	public BaseLayer(){
		
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		String[] types = {"-select unit type-", "Infantry", "Artillery", "Tanks", "APC", "Trucks"};
		
		for (int i=0; i<types.length; i++) {
			unitTypes.add(types[i]);
		}
				
		setLayout(new GridBagLayout());
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		jpScenario.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.add(jpScenario, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		jpPhases.setPreferredSize(new Dimension(400,100));
		jpPhases.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.loadPhases();
		this.add(jpPhases, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 0;	
		gbc.gridheight = 2;
		gbc.fill = GridBagConstraints.VERTICAL;
		jpToolkit.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		jpToolkit.add(this.buildToolkit());
		this.add(jpToolkit, gbc);
		
	}
	
	/**Prints the panel containing the main tools after selecting
	 *  a unit type a controller displays a new panel beneath**/
	
	public JPanel buildToolkit() {		
		

		Font font = new Font("Times New Roman", Font.BOLD, 20);
		
		for (int i=0; i<unitTypes.toArray().length; i++) {
			cbType.addItem(unitTypes.get(i));
		}
		
		cbType.setPreferredSize(new Dimension(200, 25));
		//we assume the higher toolBox available will be 560, otherwise change
		jbSavePhase.setMaximumSize(new Dimension(560, 25));
		jbNewPhase.setMaximumSize(new Dimension(560, 25));
		
		jpCommonTools.setLayout(new BoxLayout(jpCommonTools,BoxLayout.PAGE_AXIS));
		
		jbSavePhase.setText("Save phase");	
		jbSavePhase.setAlignmentX(CENTER_ALIGNMENT);
		jbNewPhase.setText("New phase");	
		jbNewPhase.setAlignmentX(CENTER_ALIGNMENT);

		Border toolsBorder = new TitledBorder(null, "Select unit type",TitledBorder.LEFT, TitledBorder.ABOVE_TOP, font, Color.BLACK);
		jpCommonTools.setBorder(toolsBorder);
		
		jpCommonTools.add(cbType);	
		jpCommonTools.add(Box.createRigidArea(new Dimension(0, 5)));
		jpCommonTools.add(jbSavePhase);
		jpCommonTools.add(Box.createRigidArea(new Dimension(0, 5)));
		jpCommonTools.add(jbNewPhase);
		jpCommonTools.add(Box.createRigidArea(new Dimension(0, 5)));
		jpCommonTools.add(jpCurrentToolBox);
		
		return jpCommonTools;
		
	}
	
	
	/**Loads the history box for the offensive phases**/
	
	public void loadPhases(){
		
		JLabel jlTitle = new JLabel();
		JPanel jpPhTitle = new JPanel();
		
		jlTitle.setText("Campaign Phases:");
		jlTitle.setFont(new Font("Times New Roman", Font.BOLD, 20));
		jlTitle.setBackground(Color.GRAY);
		jpPhTitle.add(jlTitle);
		
		BoxLayout layout = new BoxLayout(jpScroll, BoxLayout.Y_AXIS);
		BoxLayout layout2 = new BoxLayout(jpPhases, BoxLayout.Y_AXIS);
		
		jpScroll.setLayout(layout);
		jpPhases.setLayout(layout2);
		
		jsPhases.setViewportView(jpScroll);
		
		jpPhases.add(jpPhTitle);
		jpPhases.add(jsPhases);
			
	}
	
	/**Updates the phase history box and allows the
	 * user to click on each phase and access it**/
	public void updatePhases(String H, String M, PhaseToolController PC) {
		
		JButton jbPhase = new JButton();
		jbPhase.setText(butonIndex+" Phase starting at: "+H+":"+M);
		jbPhase.setMaximumSize(new Dimension(jpPhases.getWidth(),25));
		jpScroll.add(jbPhase);
		jbPhase.addActionListener(PC);
		jsPhases.getViewport().revalidate();
		butonIndex++;
		this.pack();
		
	}
	
	/**prepares the infantry options and make the frame visible**/
	public void displayInfantryTools(int i) {
		
		jpCommonTools.remove(jpCurrentToolBox);
		jpCurrentToolBox = IT.displayInfantryTools(i);
		jpCommonTools.add(jpCurrentToolBox, BorderLayout.CENTER);
		this.setVisible(true);
		this.pack();

	}
	
	/**prepares the tank options and make the frame visible**/
	public void displayTankTools(int i) {
		
		jpCommonTools.remove(jpCurrentToolBox);
		jpCurrentToolBox = TT.displayTankTools(i);
		jpCommonTools.add(jpCurrentToolBox, BorderLayout.CENTER);
		this.setVisible(true);
		this.pack();

	}
	
	/**prepares the artillery options and make the frame visible**/
	public void displayArtilleryTools(int i) {
		
		jpCommonTools.remove(jpCurrentToolBox);
		jpCurrentToolBox = AT.displayArtilleryTools(i);
		jpCommonTools.add(jpCurrentToolBox, BorderLayout.CENTER);
		this.setVisible(true);
		this.pack();

	}
	
	/**prepares the tank options and make the frame visible**/
	public void displayAPCTools(int i) {
		
		jpCommonTools.remove(jpCurrentToolBox);
		jpCurrentToolBox = APC.displayAPCTools(i);
		jpCommonTools.add(jpCurrentToolBox, BorderLayout.CENTER);
		this.setVisible(true);
		this.pack();

	}
	
	/**prepares the truck options and make the frame visible**/
	public void displayTruckTools(int i) {
		
		jpCommonTools.remove(jpCurrentToolBox);
		jpCurrentToolBox = Truck.displayTruckTools(i);
		jpCommonTools.add(jpCurrentToolBox, BorderLayout.CENTER);
		this.setVisible(true);
		this.pack();

	}
	
	/**Prints the panel containing the map on the main frame**/
	public void activate() {
		
		jpScenario.add(MD.getPanel());
		this.pack();
		
	}
	
	/**sets the controller to listen tools**/
	public void setToolsController(GeneralToolController GC){

		MUC.setDM(GC.getDM());
		MUC.setBL(this);
				
		jbSavePhase.addActionListener(GC);
		jbNewPhase.addActionListener(GC);
		cbType.addActionListener(GC);
	}
	
	/**sets the controller to listen scenario**/
	public void setScenarioController(ScenarioController SC){
		MD.setMouseUnitController(MUC);		
		MD.setController(SC);
	}

	public MapDisplay getMD() {
		return MD;
	}

	/**Hands the map from MG to MD**/
	public void setMD(ImageIcon mD) {
		MD.setPanel(mD);
	}

	public InfantryTools getIT() {
		return IT;
	}
	
	public TankTools getTT() {
		return TT;
	}
	
	public ArtilleryTools getAT() {
		return AT;
	}
	
	public APCTools getAPC() {
		return APC;
	}
	
	public TruckTools getTruck() {
		return Truck;
	}

	public String getUnitType() {
		return unitType;
	}

	public void setUnitType(String unitType) {
		this.unitType = unitType;
	}
	

}
