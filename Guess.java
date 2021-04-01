/*----------------------------------------------------------------
    Name       : Bhautik Sojitra
    Student No : 7900140
    File       : Guess.java
    Course     : COMP 2150
    Assignment : 3

    Purpose    : Info related to the guess made by the player - three cards and boolean which checks if it is accusation
 -----------------------------------------------------------------*/
import java.util.ArrayList;

public class Guess {

    private ArrayList<Card> cards; // arrayList of three cards

    private boolean isAccusation; // to check the accusation

    //constructor - which initialise all the fields
    public Guess(Card theCard1 , Card theCard2 , Card theCard3 , boolean isAccusation)
    {
        cards = new ArrayList<>();
        cards.add(theCard1); // adds the cards to the list
        cards.add(theCard2);
        cards.add(theCard3);


        this.isAccusation = isAccusation;
    }

    // returns the cards
    public ArrayList<Card> getCards()
    {
        return cards;
    }

    // returns the string info related to the fields
    public String toString()
    {
        return cards.get(0).getValue() + " in " + cards.get(1).getValue() + " with the " + cards.get(2).getValue();
    }

    // returns if the guess consists of accusation or suggestion
    public boolean checkAccusation()
    {
        return isAccusation;
    }


}
