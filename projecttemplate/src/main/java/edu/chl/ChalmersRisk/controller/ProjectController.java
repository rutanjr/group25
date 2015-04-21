package edu.chl.ChalmersRisk.controller;

import edu.chl.ChalmersRisk.model.Project;
import edu.chl.ChalmersRisk.view.ProjectView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProjectController {
	private final Project project;
	private final ProjectView projectView;
	public static final int KO = 1;

	public static ProjectController create(Project project, ProjectView projectView) {
		return new ProjectController(project, projectView);
	}

	private ProjectController(Project project, ProjectView projectView) {
		projectView.getButton().addActionListener(new ProjectButtonPressed());
        projectView.getDieRollButton().addActionListener(new DieRollButtonPressed());

		this.project = project;
		this.projectView = projectView;
	}

	private class ProjectButtonPressed implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			project.incrementPresses();
			projectView.getPressesLabel().setText(String.valueOf(project.getPresses()));
		}
	}

    private class DieRollButtonPressed implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            project.rollDie();
            projectView.getDieRollLabel().setText(String.valueOf(project.getDieRoll()));
        }
    }

}
