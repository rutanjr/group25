package edu.chl.ChalmersRisk.view;


import edu.chl.ChalmersRisk.controller.ChalmersRisk;
import edu.chl.ChalmersRisk.controller.Controller;
import edu.chl.ChalmersRisk.gui.InformationStrip;
import edu.chl.ChalmersRisk.gui.TerritoryButton;
import edu.chl.ChalmersRisk.gui.TopStrip;
import edu.chl.ChalmersRisk.model.*;
import edu.chl.ChalmersRisk.utilities.Constants;
import javafx.beans.NamedArg;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.util.ArrayList;

/**
 * Created by rutanjr on 2015-03-31.
 * A board for the risk game. This class will do the graphical work, holding the map and painting/repainting it.
 */
public class GameBoard extends BorderPane {

    private TerritoryButton[] buttons;
    private Text message;
    private Controller controller;
    private InformationStrip infoStrip;
    private TopStrip topInfo;

    public GameBoard(){


        this.setTop(new Button("YOLO"));
        System.out.println("hallå");


    }

    public GameBoard(Maps map,Controller controller) {
        this.controller = controller;


        //informationstrip at the bottom.
        infoStrip = new InformationStrip();
        this.setBottom(infoStrip);


        //informationStrip at the top
        topInfo = new TopStrip();
        this.setTop(topInfo);
        topInfo.setPrefSize(this.getWidth(),70);


        //"map"
        GridPane gp = new GridPane();
        gp.setHgap(25);
        gp.setVgap(25);
        buttons = new TerritoryButton[map.getTerritories().size()];


        int i = 0;
        for(Territory t : map.getTerritories()) {
            buttons[i] = new TerritoryButton(t);
            buttons[i].setText(t.getName() + " [ "+t.getTroops()+" ] ");
            gp.add(buttons[i],i,0);
            i++;
        }
        this.setCenter(gp);




    }

    public void update(int phaseNumber){

        //if placing troops phase
        if(phaseNumber == 1){

            //update text on all the buttons
            for(int i =0; i<buttons.length;i++){
                buttons[i].setText(buttons[i].getTerritory().getName() + " [ "+buttons[i].getTerritory().getAmountOfTroops()+" ] ");
                buttons[i].paintButton();
            }

        }
    }

    public TerritoryButton[] getButtons(){
        return buttons;
    }

    public Text getGametext(){
        return infoStrip.getGameText();
    }

    public void setGameText(String text){
        infoStrip.setGameText(text);
    }

    public Text getMessage(){
        return message;
    }
    public void setMessage(String text){
        topInfo.setGameText(text);
    }

    public void setController(Controller controller){
        this.controller = controller;
    }

    public InformationStrip getInfoStrip(){
        return infoStrip;
    }




}
