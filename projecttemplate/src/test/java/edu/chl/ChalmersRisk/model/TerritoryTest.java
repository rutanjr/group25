package edu.chl.ChalmersRisk.model;

import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

/**
 * Created by viking on 30/05/15.
 */
public class TerritoryTest {
    @Test
    public void testIsConnectedTo() throws Exception {
        ArrayList<Territory> territories = new ArrayList<Territory>();
        Continent testContinent = new Continent("test",1,territories);
        Player testPlayer1 = new Player("1","red");
        Player testPlayer2 = new Player("2", "blue");


        Territory testTer1 = new Territory("Test1",testContinent,testPlayer1);
        Territory testTer2 = new Territory("Test1",testContinent,testPlayer1);

        //* This is a known bug that is being worked on.
        //Test when no territories are connected.
        assertFalse(testTer1.isConnectedTo(testTer2));
        //*
        ArrayList<Territory> adjTers1 = new ArrayList<Territory>();
        adjTers1.add(testTer2);
        testTer1.addAdjacent(adjTers1);

        //Test when two territories are connected.
        assertTrue(testTer1.isConnectedTo(testTer2));

        Territory testTer3 = new Territory("Test1",testContinent,testPlayer1);
        ArrayList<Territory> adjTers2 = new ArrayList<Territory>();
        adjTers2.add(testTer3);
        testTer2.addAdjacent(adjTers2);


        Territory testTer4 = new Territory("Test1",testContinent,testPlayer1);
        ArrayList<Territory> adjTers3 = new ArrayList<Territory>();
        adjTers3.add(testTer4);
        testTer3.addAdjacent(adjTers3);

        Territory testTer5 = new Territory("Test1",testContinent,testPlayer1);
        ArrayList<Territory> adjTers4 = new ArrayList<Territory>();
        adjTers4.add(testTer5);
        testTer4.addAdjacent(adjTers4);

        //Test with a chain of connected territories.
        assertTrue(testTer1.isConnectedTo(testTer5));

        ArrayList<Territory> adjTers5 = new ArrayList<Territory>();
        adjTers5.add(testTer2);
        adjTers5.add(testTer4);
        testTer3.addAdjacent(adjTers5);

        //Test with a chain that includes a loop.
        assertTrue(testTer1.isConnectedTo(testTer5));

        ArrayList<Territory> adjTers6 = new ArrayList<Territory>();
        testTer5.addAdjacent(adjTers6);

        Territory testTer6 = new Territory("Test1",testContinent,testPlayer1);

        //Test with unreachable territory.
        assertFalse(testTer1.isConnectedTo(testTer6));

        Territory testTer7 = new Territory("Test1",testContinent,testPlayer2);
        ArrayList<Territory> adjTers7 = new ArrayList<Territory>();
        adjTers7.add(testTer7);
        testTer5.addAdjacent(adjTers7);

        //Test moving to territory that the player doesn't own.
        assertFalse(testTer1.isConnectedTo(testTer7));
    }
}
