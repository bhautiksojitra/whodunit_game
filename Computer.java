/*----------------------------------------------------------------
    Name       : Bhautik Sojitra
    Student No : 7900140
    File       : Computer.java
    Course     : COMP 2150
    Assignment : 3

    Purpose    : Manages the computer player in the game and implements IPlayer interface
 -----------------------------------------------------------------*/
// imported packages as needed.
import java.util.ArrayList;
import java.util.Collections;

//the class that implements the function of the computer while running the game
public class Computer implements IPlayer{

    //Initialise the Human Player which can be useful to implement some common methods
    private Human computerPlayer;

    private ArrayList<Card> remainingCards; // keep track of the cards that are remained to be examined
    private ArrayList<Card> myCards; // the cards that computer is assigned with
    private ArrayList<Card> guessedCards; // the cards that computer guessed in its last turn
    private ArrayList<Card> people; // pointer to the total cards
    private ArrayList<Card> location;
    private ArrayList<Card> weapon;
    private boolean canAccuse; // checks if computer can directly accuse

    public Computer()
    {
        // Initialise all the fields
        computerPlayer = new Human();
        remainingCards = new ArrayList<>();
        guessedCards  = new ArrayList<>();
        myCards = new ArrayList<>();
        canAccuse = false;
    }

    // will set up the player
    public void setUp( int numPlayers, int index, ArrayList<Card> ppl,
                       ArrayList<Card> places, ArrayList<Card> weapons)
    {
        //takes the help of the setup method in Human class
        computerPlayer.setUp(numPlayers , index , ppl , places , weapons);

        // add all the cards to the list initially
        remainingCards.addAll(ppl);
        remainingCards.addAll(places);
        remainingCards.addAll(weapons);

        people = ppl;
        location = places;
        weapon = weapons;
    }

    public void setCard (Card c)
    {
        // when a player is assigned a card it should remove that card from the list
        myCards.add(c);
        remainingCards.remove(c);
    }
    //returns the index
    public int getIndex(){ return computerPlayer.getIndex();}

    //checks if the player can answer to other player's guess
    public Card canAnswer(Guess g, IPlayer ip)
    {
        //to keep track fo more than one card to answer
        ArrayList<Card> temp = new ArrayList<>();

        for(int i = 0 ; i < myCards.size() ; i++)
        {
            //add all the cards that it has from the guess into the temp list
            if (g.getCards().contains(myCards.get(i)))
            {
                temp.add(myCards.get(i));
            }
        }
        //return any random card from the list
        Collections.shuffle(temp);
        if(temp.size() != 0)
            return temp.get(0);

        return null;
    }

    //Guess the cards during it's turn
    public Guess getGuess()
    {
        //checks if it can directly accuse or not
        if(!canAccuse) {

            // gets three random but smart choice from the list and make a guess with those cards
            Card card1 = retrieveCard(people.get(0).getType(), remainingCards);
            Card card2 = retrieveCard(location.get(0).getType(), remainingCards);
            Card card3 = retrieveCard(weapon.get(0).getType(), remainingCards);

            // add it to the temp list to keep track of it during th next guess
            guessedCards.add(card1);
            guessedCards.add(card2);
            guessedCards.add(card3);

            // if it has more than three cards remained to be examined then the player will make suggestion
            if (remainingCards.size() > 3) {
                Guess g = new Guess(card1, card2, card3, false);
                return g;
            } else {
                Guess g = new Guess(card1, card2, card3, true);
                return g;
            }
        }
        else
        {
            Guess g = new Guess(guessedCards.get(0), guessedCards.get(1), guessedCards.get(2), true);
            return g;
        }// In the case when it can directly accuse the cards
    }

    // Helps to get the cards for getGuess method
    private Card retrieveCard(String type, ArrayList<Card> source)
    {

        //shuffle to produce randomness
        Collections.shuffle(source);
        int i = 0;
        Card card = source.get(i);
        while(i < source.size())
        {
            i++;
            if(type.equals(card.getType())) // returns the card of the correct type
                return card;

            card = source.get(i);
        }
        return null;

    }

    // Stores the info based on the answers of the other players
    public void receiveInfo(IPlayer ip, Card c)
    {
        // if anyone could answer the guess then remove the card from the list
        if(ip != null && c != null) {
            remainingCards.remove(c);
            guessedCards = new ArrayList<>();
            canAccuse = false;
        }
        else
        {
            System.out.println("No one could answer the Guess !");
            canAccuse = true;
        }// if not then the previous guess was the correct solution and will make the accusation
    }
}
