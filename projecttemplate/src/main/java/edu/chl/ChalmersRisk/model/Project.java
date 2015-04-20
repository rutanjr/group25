package edu.chl.ChalmersRisk.model;

public class Project {
	public static final String PROJECT_WINDOW_TEXT = "ProjectTemplate";
	public static final String PROJECT_BUTTON_TEXT = "Press me!";

	private int presses, lastRoll;
    private Dice die = new Dice();

    public int getDieRoll() {
        return lastRoll;
    }

    public void rollDie() {
        lastRoll = die.rollDie();
    }

	public int getPresses() {
		return presses; 
	}

	public void incrementPresses() {
		this.presses++;
	}
}
