package edu.chl.ChalmersRisk.view;


import edu.chl.ChalmersRisk.gui.InformationStrip;
import edu.chl.ChalmersRisk.gui.TerritoryView;
import edu.chl.ChalmersRisk.gui.TopStrip;
import edu.chl.ChalmersRisk.model.Maps;
import edu.chl.ChalmersRisk.model.Territory;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

/**
 * Created by rutanjr on 2015-03-31.
 * A board for the risk game. This class will do the graphical work, holding the map and painting/repainting it.
 */
public class GameBoard extends BorderPane {

    private TerritoryView[] buttons;
    private Text message;
    private InformationStrip infoStrip;
    private TopStrip topInfo;
    private Maps map;


    public GameBoard(){
        this.setTop(new Button("YOLO"));
    }

    public GameBoard(Maps map) {
       
        //informationstrip at the bottom.
        infoStrip = new InformationStrip();
        this.setBottom(infoStrip);

        //informationStrip at the top
        topInfo = new TopStrip();
        this.setTop(topInfo);
        topInfo.setPrefSize(this.getWidth(),70);

        //"map"
        this.map = map;
        Pane gp = new Pane();
        buttons = new TerritoryView[map.getTerritories().size()];
        gp.getChildren().add(map.getBackground());

        int i = 0;
        for(Territory t : map.getTerritories()) {
            buttons[i] = new TerritoryView(t);
            //buttons[i].setText(t.getName() + " [ "+t.getTroops()+" ] ");
            gp.getChildren().add(buttons[i]);
            i++;
        }
        this.setCenter(gp);
    }

    /**
     * Updates the gameboard. All territories are updated. Only done in phase 1 & 2.
     * @param phaseNumber Updates if its 1 or 2
     */
    public void update(int phaseNumber){

        //if placing troops phase
        if(phaseNumber == 1 || phaseNumber == 2){

            //update text on all the buttons
            for(int i =0; i<buttons.length;i++){
                buttons[i].paintButton();
            }

        }

        if(phaseNumber == 2){



        }
    }

    public TerritoryView[] getTerritoryViews(){
        return buttons;
    }

    public Text getGametext(){
        return infoStrip.getGameText();
    }

    /**
     * Sets the text in the infostrip to this message.
     * @param text message to show.
     */
    public void setGameText(String text){
        infoStrip.setGameText(text);
    }

    public Text getMessage(){
        return message;
    }

    /**
     * Sets the topstrip message to this.
     * @param text to show in the topstrip.
     */
    public void setMessage(String text){
        topInfo.setGameText(text);
    }

    /**
     * Gives this reference to the informationstrip.
     * @return InfoStrip of the board.
     */
    public InformationStrip getInfoStrip(){
        return infoStrip;
    }

    /**
     * returns the instance of the map
     * @return map.
     */
    public Maps getMap(){
        return map;
    }

    public void setDiceArea(int [] rolls, boolean isAttacker){
        topInfo.setDiceArea(rolls, isAttacker);
    }

}
