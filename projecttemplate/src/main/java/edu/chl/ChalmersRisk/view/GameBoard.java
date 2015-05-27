package edu.chl.ChalmersRisk.view;


import edu.chl.ChalmersRisk.controller.Controller;
import edu.chl.ChalmersRisk.gui.InformationStrip;
import edu.chl.ChalmersRisk.gui.TerritoryView;
import edu.chl.ChalmersRisk.gui.TopStrip;
import edu.chl.ChalmersRisk.model.*;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

import java.awt.event.MouseEvent;

/**
 * Created by rutanjr on 2015-03-31.
 * A board for the risk game. This class will do the graphical work, holding the map and painting/repainting it.
 */
public class GameBoard extends BorderPane {

    private TerritoryView[] buttons;
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
        Pane gp = new Pane();
        buttons = new TerritoryView[map.getTerritories().size()];
        //ImageView chalmersFullMap = new ImageView("Chalmers.png");
        //gp.getChildren().add(chalmersFullMap);


        int i = 0;
        for(Territory t : map.getTerritories()) {
            buttons[i] = new TerritoryView(t);
            //buttons[i].setText(t.getName() + " [ "+t.getTroops()+" ] ");
            gp.getChildren().add(buttons[i]);
            i++;
        }
        this.setCenter(gp);




    }

    public void update(int phaseNumber){

        //if placing troops phase
        if(phaseNumber == 1){

            //update text on all the buttons
            for(int i =0; i<buttons.length;i++){
                //buttons[i].setText(buttons[i].getTerritory().getName() + " [ "+buttons[i].getTerritory().getAmountOfTroops()+" ] ");
                buttons[i].paintButton();
            }

        }
    }

    public TerritoryView[] getButtons(){
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
