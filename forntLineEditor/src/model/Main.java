package model;

import javax.swing.SwingUtilities;

import controller.ScenarioController;
import controller.GeneralToolController;
import view.BaseLayer;


/**
 * This is the main method, called to compile the program
 * @version 1.0
 * @author Guillem Garcia Sabate
 */
public class Main {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
									
				/**Creating model-map generator**/
				MapGenerator MG = new MapGenerator();
				/**Creating model-Infantry engines**/
				InfantryMetrics IM = new InfantryMetrics();
				/**Creating model-Data Handler**/
				DataMgmt DM = new DataMgmt();
				DM.setMG(MG);
				
				/**Creating view**/
				BaseLayer BL = new BaseLayer();
				
				/**Creating relation m-->view**/
				MG.setViewUpdater(BL);
				IM.setBL(BL);
				DM.setBL(BL);
				
				/**Creating tools controller**/
				GeneralToolController GC = new GeneralToolController();
				ScenarioController SC = new ScenarioController();
				/**Creating relation c-->view**/
				GC.setBL(BL);
				
				/**Creating relation c-->model**/
				GC.setDM(DM);
				SC.setMG(MG);
				
				/**Creating scenario & launch relation v-->controller**/
				
				SC.setBL(BL);				
				SC.defaultLaunch();
								
				/**Creating relation v-->controller**/ 
				BL.setToolsController(GC);
				BL.setScenarioController(SC);
				BL.setVisible(true);
			
			}
		});
	}
}
