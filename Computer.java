// imported packages as needed.
import java.util.ArrayList;
import java.util.Collections;

//the class that implements the function of the computer while running the game
public class Computer implements IPlayer{

    private Human computerPlayer;
    private ArrayList<Card> remainingCards;
    private ArrayList<Card> myCards;
    private ArrayList<Card> guessedCards;
    private ArrayList<Card> people;
    private ArrayList<Card> location;
    private ArrayList<Card> weapon;
    private boolean canAccuse;

    public Computer()
    {
        computerPlayer = new Human();
        remainingCards = new ArrayList<>();
        guessedCards  = new ArrayList<>();
        myCards = new ArrayList<>();
        canAccuse = false;
    }

    public void setUp( int numPlayers, int index, ArrayList<Card> ppl,
                       ArrayList<Card> places, ArrayList<Card> weapons)
    {
        computerPlayer.setUp(numPlayers , index , ppl , places , weapons);
        remainingCards.addAll(ppl);
        remainingCards.addAll(places);
        remainingCards.addAll(weapons);
        people = ppl;
        location = places;
        weapon = weapons;
    }
    public void setCard (Card c)
    {
        myCards.add(c);
        remainingCards.remove(c);
    }
    public int getIndex(){ return computerPlayer.getIndex();}
    public Card canAnswer(Guess g, IPlayer ip)
    {

        ArrayList<Card> temp = new ArrayList<>();

        for(int i = 0 ; i < myCards.size() ; i++)
        {
            if (g.getCards().contains(myCards.get(i)))
            {
                temp.add(myCards.get(i));
            }
        }
        Collections.shuffle(temp);
        if(temp.size() != 0)
            return temp.get(0);

        return null;
    }

    public Guess getGuess()
    {
        if(!canAccuse) {
            Card card1 = retrieveCard(people.get(0).getType(), remainingCards);
            Card card2 = retrieveCard(location.get(0).getType(), remainingCards);
            Card card3 = retrieveCard(weapon.get(0).getType(), remainingCards);

            guessedCards.add(card1);
            guessedCards.add(card2);
            guessedCards.add(card3);

            if (remainingCards.size() > 3) {
                Guess g = new Guess(card1, card2, card3, false);
                System.out.println("Player : " + this.getIndex() + "  Suggestion : " + g.toString());
                return g;
            } else {
                Guess g = new Guess(card1, card2, card3, true);
                System.out.println("Player : " + this.getIndex() + "  Accusation : " + g.toString());
                return g;
            }
        }
        else
        {
            Guess g = new Guess(guessedCards.get(0), guessedCards.get(1), guessedCards.get(2), true);
            System.out.println("Player : " + this.getIndex() + "  Accusation : " + g.toString());
            return g;
        }
    }

    private Card retrieveCard(String type, ArrayList<Card> source)
    {

        Collections.shuffle(source);
        int i = 0;
        Card card = source.get(i);
        while(i < source.size())
        {
            i++;
            if(type.equals(card.getType()))
                return card;

            card = source.get(i);
        }
        return null;

    }
    public void receiveInfo(IPlayer ip, Card c)
    {
        if(ip != null && c != null) {
            remainingCards.remove(c);
            guessedCards = new ArrayList<>();
            canAccuse = false;
        }
            else
        {
            System.out.println("No one could answer the Guess !");
            canAccuse = true;
        }
    }
}
