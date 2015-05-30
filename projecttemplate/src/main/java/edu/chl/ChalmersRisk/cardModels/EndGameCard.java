package edu.chl.ChalmersRisk.cardModels;

/**
 * A card that upon being drawn will force the game to end (score will be calculated and a winner decided)
 *
 * Created by chrh on 2015-05-19.
 */
public class EndGameCard implements ICard{

    // This card might not be activated if drawn in the early phases of the game, minTurn decides whether this
    // card can be activated.
    private int minTurn;
    private String title, message;

    public EndGameCard(int minTurn) {
        this.minTurn = minTurn;
        this.title = "End Game";
        this.message = "Due to unforeseen event, the battle over Chalmers has ended.";
    }
    @Override
    public String getTitle() {
        return this.title;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    @Override
    public int phaseCheck() {
        return 4;
    }

    @Override
    public void turnCard() {
        /*
        if (getGameTurn() >= this.minTurn) {
            //TODO effect of the card, we need a way to get the current turn.
        }
        */
    }
}