import java.util.ArrayList;
public class Guess {

    private ArrayList<Card> cards;

    private boolean isAccusation;

    public Guess(Card theCard1 , Card theCard2 , Card theCard3 , boolean isAccusation)
    {
        cards = new ArrayList<>();
        cards.add(theCard1);
        cards.add(theCard2);
        cards.add(theCard3);


        this.isAccusation = isAccusation;
    }

    public ArrayList<Card> getCards()
    {
        return cards;
    }

    public String toString()
    {
        return cards.get(0).getValue() + " in " + cards.get(1).getValue() + " with the " + cards.get(2).getValue();
    }

    public boolean checkAccusation()
    {
        return isAccusation;
    }


}
