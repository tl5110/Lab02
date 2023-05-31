package war;

import java.util.ArrayList;
import java.util.Random;
import java.util.Collections;

/**
 * Represents a pile of cards.
 *
 * @author Tiffany Lee
 */
public class Pile {
    /** The collection of cards. */
    private final ArrayList<Card> cards;

    /** The name of the pile of cards. */
    private final String name;

    /** The random number generator */
    private static Random rng;

    /**
     * Creates the pile of cards for a given seed.
     * Initially there are no cards in the collection (an empty list).
     *
     * @param name the pile's name
     */
    public Pile(String name){
        this.name = name;
        this.cards = new ArrayList<>();
    }

    /**
     * Adds a card to the bottom of the pile (leaving the face setting as is)
     *
     * @param card the card to add
     */
    public void addCard(Card card){
        cards.add(card);
    }

    /**
     * Removes all the cards from the pile by clearing it out
     */
    public void clear(){
        cards.clear();
    }

    /**
     * Gets the next top card from the pile
     *
     * @param faceUP should the card be set to face up when drawn
     * @return the card that was at the top of the pile
     */
    public Card drawCard(boolean faceUP){
        if(cards.get(0).isFaceUp()){
            shuffle();
            System.out.println(this);
        }
        Card top = cards.get(0);
        if(faceUP){
            top.setFaceUp();
            cards.remove(0);
            return top;
        }
        cards.remove(0);
        return top;
    }

    /**
     * Returns the collection of cards in the pile's current state
     *
     * @return ArrayList of all the cards stated above
     */
    public ArrayList<Card> getCards(){
        return this.cards;
    }

    /**
     * Is there at least one card in the pile (it is not empty)?
     *
     * @return true if there is at least one card in the pile, false otherwise
     */
    public boolean hasCard(){
        return !cards.isEmpty();
    }

    /**
     * Creates and sets the seed for the random number generator
     *
     * @param seed the seed value
     */
    public static void setSeed(long seed){
        rng = new Random(seed);
        rng.setSeed(seed);
    }

    /**
     * Shuffles the cards and sets them all face down
     */
    public void shuffle(){
        for(Card card : cards){
            card.setFaceDown();
        }
        Collections.shuffle(cards, rng);
        System.out.println("Shuffling " + name + " pile");
    }

    /**
     * Returns a string representation of the pile in the format:
     * "{name} pile: first-card second-card..." with each card having
     * a string representation containing its rank, suit and whether the
     * card is face up or not. For example,
     * "{name} pile: A♧(D) 7♠(D)..."
     *
     * @return the string described above
     */
    @Override
    public String toString(){
        if (!hasCard()){
            return "" + this.name + " pile: ";
        }
        return "" + this.name + " pile: " + getCards().toString().
                        replace("[", "").
                        replace("]", "").
                        replace(",", "") + " ";
    }
}
