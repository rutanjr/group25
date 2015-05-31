package edu.chl.ChalmersRisk.gui;

import javafx.geometry.Insets;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

/**
 * Created by Malin on 2015-05-19.
 */
public class TopStrip extends BorderPane{

    private Text gameText;

    private DiceArea defenderDiceArea;
    private DiceArea attackerDiceArea;


    public TopStrip(){
        gameText = new Text("A new game has started");
        this.setPadding(new Insets(15, 12, 15, 12));
        this.setStyle("-fx-background-color: #fba6b0;");


        GridPane gridPane = new GridPane();

        gridPane.setHgap(10);

        defenderDiceArea = new DiceArea(2,"Defender's dice");
        attackerDiceArea = new DiceArea(3,"Attacker's dice");


        gridPane.add(defenderDiceArea,2,1);
        gridPane.add(attackerDiceArea,1,1);

        this.setRight(gridPane);
        this.setLeft(gameText);



    }


    public Text getGameText(){
        return gameText;
    }

    public void setGameText(String text){
        gameText.setText(text);
    }

    public void setDefenderDice(int [] rolls){
        defenderDiceArea.setDice(rolls);
    }

    public void setAttackerDiceArea(int [] rolls){
        attackerDiceArea.setDice(rolls);
    }

    public void setDiceArea(int [] rolls, boolean isAttacker){


        if(isAttacker){
            setAttackerDiceArea(rolls);
        }else{
            setDefenderDice(rolls);
        }

    }


}
