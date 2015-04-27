package edu.chl.ChalmersRisk.view;


import edu.chl.ChalmersRisk.model.*;
import edu.chl.ChalmersRisk.utilities.Constants;
import javafx.beans.NamedArg;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;

/**
 * Created by rutanjr on 2015-03-31.
 * A board for the risk game. This class will do the graphical work, holding the map and painting/repainting it.
 */
public class GameBoard extends GridPane {

    Button[] buttons;

    public GameBoard(){

        this.setAlignment(Pos.CENTER);
        this.setHgap(25);
        this.setVgap(25);
        this.add(new Button("YOLO"), 1, 1);
        System.out.println("hallå");


    }

    public GameBoard(Maps map) {

        this.setAlignment(Pos.CENTER);
        this.setHgap(25);
        this.setVgap(25);

        buttons = new Button[map.getTerritories().size()];
        int i = 0;
        for(Territory t : map.getTerritories()) {
            buttons[i] = new Button(t.getName());
            this.add(buttons[i], 1, i);
            i++;
        }


    }




}
