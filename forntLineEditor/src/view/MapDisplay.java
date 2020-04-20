package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import controller.MouseUnitController;
import controller.ScenarioController;


public class MapDisplay{
	
	private JPanel jpMap = new JPanel();
	private JPanel jpButtons;
	private JPanel jpInnerButtons;
	private JPanel jpZoomButtons;
	private JPanel jpScenario = new JPanel();
	private JLayeredPane layeredPane = new JLayeredPane();
	private JButton jbZoomIn = new JButton();
	private JButton jbZoomOut = new JButton();
	private JButton jbLeft = new JButton();
	private JButton jbRight = new JButton();
	private JButton jbUp = new JButton();
	private JButton jbDown = new JButton();
	private JLabel jlimage;
	private JLabel[] jlTrooper = new JLabel[100];
	private ImageIcon im = new ImageIcon();
	private ImageIcon[] jiUnit = new ImageIcon[100];
	private MouseUnitController MUC;
	private ScenarioController SC;
	private int unitIndex;
	private int iconSize = 30;

	
	public MapDisplay(){
				
		jbZoomIn.setText("+");
		jbZoomOut.setText("-");
		jbLeft.setText(">");
		jbRight.setText("<");
		jbUp.setText("^");
		jbDown.setText("v");
				
	}
	
    public JPanel getPanel() {
    	jpScenario.add(layeredPane);
        return jpScenario;
    }
    
    /***This method first prepares the buttons to move around the map
     * and later prepares the image of the map inside a panel**/
    
    public void setPanel(ImageIcon extIm) {
    	   	  	
    	unitIndex = 0;
    	jpMap.removeAll();
    	layeredPane.removeAll();
    	
    	im = extIm;
    	jlimage = new JLabel();
    	jlimage.setIcon(extIm);   	
    	jpMap.add(jlimage);   	
    	jpMap.setBounds(0, 0, im.getIconWidth(), im.getIconHeight());   
    	
    	this.mapDevelopment(0, true, "notEstablished");
    	
    	layeredPane.add(jpMap, JLayeredPane.DEFAULT_LAYER);   	
    	layeredPane.setPreferredSize(new Dimension(im.getIconWidth(),im.getIconHeight()));

    	
    }
    
    /**The lines to define actual positions are drawn
     * on the image will be latter drawn on the map**/
    
    public void drawFrontLines() {
    	
    }
    

	/**sets the controller to listen buttons**/
	public void setController(ScenarioController SC){
		
		this.SC = SC;
		
		//-start
		jpMap.addMouseListener(SC);
		//jpMap.addMouseMotionListener(SC);
		//-end
		
		jpMap.addMouseWheelListener(SC);
		jbZoomIn.addActionListener(SC);
		jbZoomOut.addActionListener(SC);
		jbRight.addActionListener(SC);
		jbLeft.addActionListener(SC);
		jbUp.addActionListener(SC);
		jbDown.addActionListener(SC);
		
	}

	/**Associates the mouse controller from the main view with the IT frame**/
	public void setMouseUnitController(MouseUnitController MC) {
		MUC= MC;
		MC.setMD(this);
	}
	
	/**The integer i defines the number of the unit icon we are going to place on the map
	 * The boolean a defines if we are going to place the buttons or the icons.
	 * The string type defines which Icon will be displayed**/
	public void mapDevelopment(int i, boolean a, String type) {
		
		
		if(a == true) {
						
			jpButtons = new JPanel(new BorderLayout());
	    	jpInnerButtons = new JPanel(new GridLayout(1,3));
	    	jpZoomButtons = new JPanel(new GridLayout(2,1));
	 
	    	jpZoomButtons.add(jbZoomIn);
	    	jpZoomButtons.add(jbZoomOut);
	    	jpInnerButtons.add(jbRight);
	    	jpInnerButtons.add(jpZoomButtons);
	    	jpInnerButtons.add(jbLeft);
	    	
	    	jpButtons.add(jbUp, BorderLayout.PAGE_START);
	    	jpButtons.add(jpInnerButtons,  BorderLayout.CENTER);
	    	jpButtons.add(jbDown, BorderLayout.PAGE_END);
	    	
	    	jpButtons.setBounds(im.getIconWidth()-130, im.getIconHeight()-110, 130, 110);
	    	layeredPane.add(jpButtons, JLayeredPane.PALETTE_LAYER);
	    	
	    	if(unitIndex>0) {
		    	jlTrooper[unitIndex].removeMouseListener(MUC);
		    	jlimage.removeMouseListener(MUC);
		    	jlTrooper[unitIndex].removeMouseMotionListener(MUC);
	    	}

		}
		
		if(a == false) { 
			
			layeredPane.remove(jpButtons);
						
			if(i == 0) {
			
				MUC.setElementIndex(unitIndex);
				
				this.selectIcon(unitIndex, type);
				
				jiUnit[unitIndex].setImage(jiUnit[unitIndex].getImage().getScaledInstance(30, 30, 1));
				
				jlTrooper[unitIndex] = new JLabel();
				jlTrooper[unitIndex].setIcon(jiUnit[unitIndex]);
				
				
				jlTrooper[unitIndex].addMouseListener(MUC);
				jlTrooper[unitIndex].addMouseMotionListener(MUC);	
								
				jlTrooper[unitIndex].setBounds(im.getIconWidth()-130, im.getIconHeight()-110, 30, 30);
				
				layeredPane.add(jlTrooper[unitIndex], JLayeredPane.PALETTE_LAYER);	
				
			}
						
		}
		
		layeredPane.repaint();
		
	}
	
	
	/**unitIndex is an integer which represent the index of the unit we are placing on the map
	 * it's used by the MouseController to know which icon we are dragging**/
	public void unitIndexAdd() {
		unitIndex++;
	}
	
	/**This method selects the correct image for the Icon 
	 * according to the type of unit will be displayed**/
	private void selectIcon(int unitIndex, String type) {
		
		if(type.equals("Infantry")) {
			jiUnit[unitIndex] = new ImageIcon(getClass().getResource("/resources/infantry.png"));
		}
		if(type.equals("Tanks")) {
			jiUnit[unitIndex] = new ImageIcon(getClass().getResource("/resources/tank.png"));
		}
		if(type.equals("Artillery")) {
			jiUnit[unitIndex] = new ImageIcon(getClass().getResource("/resources/artillery.png"));
		}
		if(type.equals("APC")) {
			jiUnit[unitIndex] = new ImageIcon(getClass().getResource("/resources/apc.png"));
		}
		if(type.equals("Trucks")) {
			jiUnit[unitIndex] = new ImageIcon(getClass().getResource("/resources/truck.png"));
		}
		
	}
	
	public void increase() {
			
		iconSize = 40;
		jiUnit[unitIndex].setImage(jiUnit[unitIndex].getImage().getScaledInstance(iconSize, iconSize, 1));
		jlTrooper[unitIndex].setBounds(im.getIconWidth()-130, im.getIconHeight()-110, iconSize, iconSize);
		layeredPane.updateUI();
		
	}
	
	public void decrease() {
		
		iconSize = 25;
		jiUnit[unitIndex].setImage(jiUnit[unitIndex].getImage().getScaledInstance(iconSize, iconSize, 1));
		jlTrooper[unitIndex].setBounds(im.getIconWidth()-130, im.getIconHeight()-110, iconSize, iconSize);
		layeredPane.updateUI();
			
	}
	
	
	public Integer getUnitIndex() {
		return unitIndex;
	}
	

	public JLayeredPane getLayeredPane() {
		return layeredPane;
	}


	public ScenarioController getSC() {
		return SC;
	}


	
}
	

