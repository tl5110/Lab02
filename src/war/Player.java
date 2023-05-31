package war;

import java.util.ArrayList;

/**
 * Represents a single player in the game.
 *
 * @author Tiffany Lee
 */
public class Player {
    /** A player's pile of cards */
    private final Pile pile;

    /** Is this player the winner of the game? */
    private boolean winner;

    /**
     * Creates the player with their id. Their pile's name should be
     * "P1" or "P2" based on that id. Initially they are not the winner
     * of the game
     *
     * @param id this player's id
     */
    public Player(int id){
        this.pile = new Pile("P" + id);
        this.winner = false;
    }

    /**
     * Adds a single card to the bottom of the player's pile
     *
     * @param card the card to add
     */
    public void addCard(Card card){
        pile.addCard(card);
    }

    /**
     * Add the collection of cards from the incoming pile to the bottom of
     * player's pile, in order.
     *
     * @param cards the incoming pile of cards to add to this player's pile
     */
    public void addCards(Pile cards){
        for (int i = 0; i < cards.getCards().size(); i++) {
            pile.addCard(cards.getCards().get(i));
        }
    }

    /**
     * Remove a card from the top of the pile, switching it to be face up
     *
     * @return the newly removed card from the top of the pile
     */
    public Card drawCard(){
        return pile.drawCard(true);
    }

    /**
     * Are there any cards left in this player's pile?
     *
     * @return whether there are cards in the player's pile or not
     */
    public boolean hasCard(){
        return pile.hasCard();
    }

    /**
     * Is this player the winner?
     *
     * @return whether this player was the winner or not
     */
    public boolean isWinner(){
        return winner;
    }

    /** Declares this player to be the winner */
    public void setWinner(){
        this.winner = true;
    }

    /**
     * Returns a string representation of this player's pile, e.g.:
     * "P1 pile: 7♢(D) J♧(D)"
     *
     * @return the string mentioned above
     */
    @Override
    public String toString(){
        return pile.toString();
    }
}
