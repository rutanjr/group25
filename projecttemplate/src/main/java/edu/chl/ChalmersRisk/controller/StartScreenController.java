package edu.chl.ChalmersRisk.controller;

import edu.chl.ChalmersRisk.model.StartScreenModel;
import edu.chl.ChalmersRisk.view.StartScreen;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Malin on 2015-04-17.
 */
public class StartScreenController {

    private final StartScreenModel startScreenModel;
    private final StartScreen screenView;

    public static StartScreenController create(StartScreenModel startScreenModel, StartScreen screenView) {
        return new StartScreenController(startScreenModel, screenView);
    }

    private StartScreenController(StartScreenModel startScreenModel, StartScreen screenView) {
        screenView.getStartButton().addActionListener(new ButtonPressed());

        this.startScreenModel = startScreenModel;
        this.screenView = screenView;
    }

    private class ButtonPressed implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            startScreenModel.newGame();

        }
    }
}
