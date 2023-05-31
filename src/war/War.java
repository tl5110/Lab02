package war;

/**
 * The main program for the card game, War.  It is run on the command line as:<br>
 * <br>
 * java War cards-per-player seed<br>
 * <br>
 *
 * @author RIT CS
 * @author Tiffany Lee
 */

public class War {
    /** The maximum number of cards a single player can have */
    public final static int MAX_CARDS_PER_PLAYER = 26;

    /** The first player */
    private final Player p1;

    /** The second player */
    private final Player p2;

    /** The current round */
    private int round;

    /**
     * Initialize the game.
     *
     * @param cardsPerPlayer the number of cards for a single player
     */
    public War(int cardsPerPlayer) {
        Pile initial = new Pile("Initial");
        this.p1 = new Player(1);
        this.p2 = new Player(2);
        for(Rank rank : Rank.values()){
            for(Suit suit : Suit.values()){
                Card card = new Card(rank, suit);
                initial.addCard(card);
            }
        }
        initial.shuffle();
        System.out.println(initial);
        for (int i = 0; i < cardsPerPlayer; i++) {
            p1.addCard(initial.drawCard(false));
            p2.addCard(initial.drawCard(false));
        }
    }

    /** Play the full game. */
    public void playGame() {
        this.round = 1;
        while (p1.hasCard() && p2.hasCard()) {
            playRound();
        }
        System.out.println(p1);
        System.out.println(p2);
        if (!p2.hasCard()) {
            p1.setWinner();
            System.out.println("P1 WINS!");
        } else if (!p1.hasCard()) {
            p2.setWinner();
            System.out.println("P2 WINS!");
        }
    }

    /** Play a single round of the game */
    private void playRound() {
        System.out.println("ROUND " + round);
        System.out.println(p1);
        System.out.println(p2);
        // draws cards
        Card card1 = p1.drawCard();
        Card card2 = p2.drawCard();
        // prints each card drawn by each player
        System.out.println("P1 card: " + card1.toString());
        System.out.println("P2 card: " + card2.toString());
        // adds cards to Trick Pile
        Pile trick = new Pile("Trick");
        trick.addCard(card1);
        trick.addCard(card2);
        //checks if card1 and card2 are equal or beats
        if (card1.equals(card2)) {
            while(card1.equals(card2)) {
                System.out.println("WAR!");
                System.out.println(p1);
                System.out.println(p2);
                if (!p2.hasCard()) {
                    System.out.println("P1 wins round gets " + trick);
                    p1.addCards(trick);
                    break;
                } else if (!p1.hasCard()) {
                    System.out.println("P2 wins round gets " + trick);
                    p2.addCards(trick);
                    break;
                } else {
                    card1 = p1.drawCard();
                    card2 = p2.drawCard();
                    System.out.println("P1 card: " + card1.toString());
                    System.out.println("P2 card: " + card2.toString());
                    trick.addCard(card1);
                    trick.addCard(card2);
                    if (card1.beats(card2)) {
                        System.out.println("P1 wins round gets " + trick);
                        p1.addCards(trick);
                    } else if (card2.beats(card1)) {
                        System.out.println("P2 wins round gets " + trick);
                        p2.addCards(trick);
                    }
                }
            }
        } else if (card1.beats(card2)) {
            System.out.println("P1 wins round gets " + trick);
            p1.addCards(trick);
        } else if(card2.beats(card1)){
            System.out.println("P2 wins round gets " + trick);
            p2.addCards(trick);
        }
        ++round;
    }

    /**
     * The main method get the command line arguments, then constructs
     * and plays the game.  The Pile's random number generator and seed
     * need should get set here using Pile.setSeed(seed).
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java War cards-per-player seed");
        } else {
            int cardsPerPlayer = Integer.parseInt(args[0]);
            // must be between 1 and 26 cards per player
            if (cardsPerPlayer <= 0 || cardsPerPlayer > MAX_CARDS_PER_PLAYER) {
                System.out.println("cards-per-player must be between 1 and " + MAX_CARDS_PER_PLAYER);
            } else {
                // set the rng seed
                Pile.setSeed(Integer.parseInt(args[1]));
                War war = new War(cardsPerPlayer);
                war.playGame();
            }
        }
    }
}
