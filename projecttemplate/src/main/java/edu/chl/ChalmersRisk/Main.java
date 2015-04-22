package edu.chl.ChalmersRisk;

import edu.chl.ChalmersRisk.controller.ChalmersRisk;
import edu.chl.ChalmersRisk.controller.ProjectController;
import edu.chl.ChalmersRisk.gui.MainFrame;
import edu.chl.ChalmersRisk.model.Project;
import edu.chl.ChalmersRisk.view.ProjectView;
import javax.swing.SwingUtilities;

/*
  Application entry class (if using standard java and Swing)
*/
public final class Main {
	private Main() {
		/* No instances allowed! */
	}

	public static void main(String[] args) {
	//	SwingUtilities.invokeLater(() -> {
     //               final Project project = new Project();
      //              final ProjectView projectView = new ProjectView(project);
       //
        //            ProjectController.create(project, projectView);
         //           projectView.setVisible(true);
          //      });


        new ChalmersRisk(new MainFrame());
	}
}
