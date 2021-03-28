import java.util.*;

public class Human implements IPlayer {

    private int totalPlayers;
    private int index;
    private ArrayList<Card> myCards;


    private ArrayList<Card> people;
    private ArrayList<Card> location;
    private ArrayList<Card> weapons;

    public Human() {}



    public void setUp( int numPlayers, int index, ArrayList<Card> ppl,
                       ArrayList<Card> places, ArrayList<Card> weapons)
    {
        totalPlayers = numPlayers;
        this.index   = index;
        myCards = new ArrayList<>();


        people = ppl;
        location = places;
        this.weapons = weapons;

    }


    public void setCard (Card c)
    {
        myCards.add(c);
        System.out.println( "you received the card : "+ c.getValue() );
    }
    public int getIndex()
    {
        return index;
    }

    public Card canAnswer(Guess g, IPlayer ip)
    {
        ArrayList<Card> commonCards = new ArrayList<>();

        for (int i = 0; i < myCards.size(); i++) {
            Card myCard = myCards.get(i);
            if (g.getCards().contains(myCard)) {
                commonCards.add(myCard);
            }
        }
        if(commonCards.isEmpty()) {
            System.out.println("Player " + ip.getIndex() + " asked you about [ " + g.toString() + " ] , but you couldn't answer.");
            return null;
        }
        else if(commonCards.size() == 1)
        {
            System.out.println( "Player " +  ip.getIndex() + " asked you about [ " + g.toString() + " ] you only have one card, " +
                                commonCards.get(0).getValue() + " showed it to them. ");
            return commonCards.get(0);
        }
        else
        {
            System.out.println( "Player " +  ip.getIndex() + " asked you about [ " + g.toString() +
                                " ] you only have multiple cards , which one do you show ?");
            printList(commonCards);
            Scanner scan = new Scanner(System.in);
            int input = scan.nextInt();
            while(input >= commonCards.size() || input < 0)
            {
                System.out.println("Invalid Input ! ");
                input = scan.nextInt();
            }
            return commonCards.get(input);
        }
    }
    public Guess getGuess()
    {
        System.out.println("It's your turn ! ");

        Scanner scan = new Scanner(System.in);

        Card card1 =  getGuessedCard(people , people.get(0).getType(), scan);
        Card card2 =  getGuessedCard(location , location.get(0).getType(), scan);
        Card card3 =  getGuessedCard(weapons , weapons.get(0).getType() , scan);


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

        Guess guess = new Guess(card1 , card2 , card3 , isAccusation);
        if(isAccusation)
        {
            System.out.println("Accusation : " + guess.toString());
        }
        else
        {
            System.out.println("Suggestion : " + guess.toString());
        }
        return guess;

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
        if(ip != null && c != null)
        {
            System.out.println("Player " + ip.getIndex() + " refuted your suggestion by showing you card " + c.getValue());
        }
        else
        {
            System.out.println("No one could refute your suggestion.");
        }

    }
}
