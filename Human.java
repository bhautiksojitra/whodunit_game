import java.util.*;

public class Human implements IPlayer {

    private int totalPlayers;
    private int index;
    private ArrayList<Card> myCards;
    private ArrayList<Card> remainingCards;
    private ArrayList<Card> guessedCards;

    private ArrayList<Card> people;
    private ArrayList<Card> location;
    private ArrayList<Card> weapons;


    public void setUp( int numPlayers, int index, ArrayList<Card> ppl,
                       ArrayList<Card> places, ArrayList<Card> weapons)
    {
        totalPlayers = numPlayers;
        this.index   = index;
        myCards = new ArrayList<Card>();
        remainingCards =  new ArrayList<Card>();

        remainingCards.addAll(ppl);
        remainingCards.addAll(places);
        remainingCards.addAll(weapons);

        people = ppl;
        location = places;
        this.weapons = weapons;

    }


    public void setCard (Card c)
    {
        myCards.add(c);
        remainingCards.remove(c);
    }
    public int getIndex()
    {
        return index;
    }

    public Card canAnswer(Guess g, IPlayer ip)
    {
        for(int i = 0 ; i < myCards.size() ; i++)
        {
            if(g.getCards().contains(myCards.get(i)))
            {
                return myCards.get(i);
            }
        }
        return null;
    }
    public Guess getGuess()
    {
        Scanner scan = new Scanner(System.in);

        Card card1 =  getGuessedCard(people , "Person" , scan);
        Card card2 =  getGuessedCard(weapons , "Weapon" , scan);
        Card card3 =  getGuessedCard(location , "location" , scan);

        boolean checkCondition = true;
        boolean isAccusation = false;
        while(checkCondition)
        {
            System.out.println("Is this Accusation : ");
            System.out.println("1 . YES ");
            System.out.println("2 .  NO ");

            int index = scan.nextInt();
            if(index == 1)
            {
                isAccusation = true;
                checkCondition = false;
            }
            else if(index == 2)
            {
                checkCondition = false;
            }
            else
            {
                System.out.println("FAIL ! Enter valid Input ");
            }
        }

        return new Guess(card1 , card2 , card3 , isAccusation);

    }

    private Card getGuessedCard(ArrayList<Card> source , String type , Scanner scan)
    {

        while(true)
        {
            System.out.println("Which "+ type + "  do you want to suggest : ");
            printList(source);
            int index = scan.nextInt();

            if (index >= 0 && index < source.size()) {
                return source.get(index);
            }
            else
            {
                System.out.println("FAIL ! Enter valid Input ");
            }
        }

    }

    private void printList(ArrayList<Card> source)
    {
        int i = 0;
        for(Card c : source)
        {
            System.out.println( i++ + ". " + c.getValue() );
        }
    }
    public void receiveInfo(IPlayer ip, Card c)
    {
        if(c != null & ip != null)
        {
            guessedCards.add(c);
            remainingCards.remove(c);
        }
    }
}
