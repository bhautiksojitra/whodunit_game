/*----------------------------------------------------------------
    Name       : Bhautik Sojitra
    Student No : 7900140
    File       : Human.java
    Course     : COMP 2150
    Assignment : 3

    Purpose    : Holds the info related to the human player
 -----------------------------------------------------------------*/
import java.util.*;

public class Human implements IPlayer {

    private int totalPlayers; // total players in the game
    private int index; // index of the human player
    private ArrayList<Card> myCards; // player's card

    // poniters to all the cards
    private ArrayList<Card> people;
    private ArrayList<Card> location;
    private ArrayList<Card> weapons;

    //constructor - not helpful
    public Human() {}


    //Initialise all the fields
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

    // assigning the card to the player - prompt to the user
    public void setCard (Card c)
    {
        myCards.add(c);
        System.out.println( "you received the card : "+ c.getValue() );
    }

    //returns the index
    public int getIndex()
    {
        return index;
    }

    // chceks if he can answer the question of another players guess
    public Card canAnswer(Guess g, IPlayer ip)
    {
        ArrayList<Card> commonCards = new ArrayList<>(); // temp list

        // add all the common cards from the guess and myCard list
        for(int i = 0; i < myCards.size(); i++) {
            Card myCard = myCards.get(i);
            if (g.getCards().contains(myCard)) {
                commonCards.add(myCard);
            }
        }

        // Output Based on the number of cards th player has to answer from the guess
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
                                " ] you have multiple cards , which one do you show ?");
            printList(commonCards);

            // In case of having multiple cards - asks for input from the user to choose one card to show.
            Scanner scan = new Scanner(System.in);
            int input = -1;
            while(input >= commonCards.size() || input < 0) // loops untill you enters the valid input
            {
                try
                {
                    input = Integer.parseInt(scan.nextLine());
                }
                catch(NumberFormatException ne)
                {
                    System.out.println("Enter valid Integer Value  ! ");
                }// handles exceptions
            }
            return commonCards.get(input); // returns the card
        }
    }

    // make the guess by making random choice - depends on the user (no smart work like computer).
    public Guess getGuess()
    {
        //Everything is based on the user input
        System.out.println("It's your turn ! ");

        Scanner scan = new Scanner(System.in);

        // selects the cards for the guess through user input
        Card card1 =  getGuessedCard(people , people.get(0).getType(), scan);
        Card card2 =  getGuessedCard(location , location.get(0).getType(), scan);
        Card card3 =  getGuessedCard(weapons , weapons.get(0).getType() , scan);

        // asks if user want to make accusation or not.
        char index = ' ';
        boolean isAccusation = false;
        while(index != 'y' && index != 'n' && index != 'Y' && index != 'N')
        {
            System.out.println("Is this an accusation (Y/[N])?");

            index = scan.next().charAt(0);
            if(index == 'y' || index == 'Y')
            {
                isAccusation = true;
            }
            else if(index != 'n' && index != 'N')
            {
                System.out.println("FAIL ! Enter valid Input : Y , N ");
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

    // Helps to get the card for the user
    private Card getGuessedCard(ArrayList<Card> source , String type , Scanner scan)
    {

        int index = -1;
        while(index < 0 || index >= source.size()) // loops until valid input
        {
            System.out.println("Which "+ type + "  do you want to suggest : ");
            printList(source);
            try {
                index = Integer.parseInt(scan.nextLine());
            }catch(NumberFormatException e)
            {}//Handled exceptions

            if (index >= 0 && index < source.size()) {
                return source.get(index);
            }
            else
            {
                System.out.println("FAIL ! Enter valid Input ");
            }
        }
        return null;
    }

    // Prints the entire list of cards - useful for prompts
    private void printList(ArrayList<Card> source)
    {
        int i = 0;
        for(Card c : source)
        {
            System.out.println( i++ + ". " + c.getValue() );
        }
    }

    // Only showing some output to the user - (No smart work like computer)
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
