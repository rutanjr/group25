package edu.chl.ChalmersRisk.view;

import edu.chl.ChalmersRisk.model.Project;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class ProjectView extends JFrame {

    private final JButton button = new JButton(Project.PROJECT_BUTTON_TEXT), dieRollButton = new JButton("Roll die");
    private final JLabel pressesLabel;
    private final JLabel dieRollLabel;

    public ProjectView(Project project) {
        super(Project.PROJECT_WINDOW_TEXT);

        final GridLayout layout = new GridLayout(0, 2);
        setLayout(layout);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pressesLabel = new JLabel(String.valueOf(project.getPresses()));
        pressesLabel.setHorizontalAlignment(SwingConstants.CENTER);
        pressesLabel.setLabelFor(button);
        dieRollLabel = new JLabel(String.valueOf(project.getDieRoll()));
        dieRollLabel.setHorizontalAlignment(SwingConstants.CENTER);
        dieRollLabel.setLabelFor(dieRollButton);

        add(button);
        add(pressesLabel);
        add(dieRollButton);
        add(dieRollLabel);
        pack();
    }

    public JButton getDieRollButton () {
        return dieRollButton;
    }

    public JLabel getDieRollLabel() {
        return dieRollLabel;
    }

    public JButton getButton() {
        return button;
    }

    public JLabel getPressesLabel() {
        return pressesLabel;
    }
}
