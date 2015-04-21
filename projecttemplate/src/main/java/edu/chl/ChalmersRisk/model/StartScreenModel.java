package edu.chl.ChalmersRisk.model;

/**
 * Created by Malin on 2015-04-17.
 */
public class StartScreenModel {


    public void newGame(){
        //hardcode for a new game
        Continent[] continents = new Continent[4];
        continents[0].setName("Afrika");
        continents[1].setName("Europa");
        continents[2].setName("Malin");
        continents[3].setName("Amrika");

        Player player1 = new Player("Malle");
        Player player2 = new Player("Balle");
    }
}
