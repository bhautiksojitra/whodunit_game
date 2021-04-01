/*----------------------------------------------------------------
    Name       : Bhautik Sojitra
    Student No : 7900140
    File       : TheModel.java
    Course     : COMP 2150
    Assignment : 3

    Purpose    : This class Holds the all information related to the Game together and contains the main Game loop.
 -----------------------------------------------------------------*/

//some libraries to implement the data structure
import java.util.Collections;
import java.util.ArrayList;

public class TheModel {

    // different kind of cards stored in the ArrayList
    private ArrayList<Card> people;
    private ArrayList<Card> location;
    private ArrayList<Card> weapons;

    // ArrayList containing all the players in the game
    private ArrayList<IPlayer> players;

    private ArrayList<Card> result; // three solution cards
    private ArrayList<Card> remainingCards; // stores all the cards together except solution cards. Useful while assigning the cards.

    private IPlayer currentPlayer; // Pointer to the current player
    private IPlayer nextPlayer;    // Pointer to the next player (useful while asking for the suggestion

    private int currIndex; // Index of the current player.

    //Constructor - Initialise all the fields
    public TheModel(ArrayList<Card> ppl , ArrayList<Card> places , ArrayList<Card> weapons , ArrayList<IPlayer> players)
    {
        people = ppl;
        location = places;
        this.weapons = weapons;
        this.players = players;

        remainingCards = new ArrayList<>();
        result = new ArrayList<>();
        currentPlayer = players.get(0);
        currIndex = 0;
        nextPlayer = players.get(1);
    }

    //Will initialise the cards and the setUp all the players - show all required info on the console
    public void startGame()
    {


        //Printing all the cards on the console
        System.out.println("Showing you all the information : ");
        System.out.println("Here are the names of all the suspects : ");
        print(people);
        System.out.println("Here are the names of all the locations : ");
        print(location);
        System.out.println("Here are the names of all the weapons : ");
        print(weapons);

        //setup all the players based on the index number
        int index  = 0;
        for(IPlayer p : this.players)
            p.setUp(this.players.size(), index++, people, location, this.weapons);


        // Adding all the cards in one list.
        remainingCards.addAll(people);
        remainingCards.addAll(location);
        remainingCards.addAll(weapons);

        // Will shuffle the cards using shuffle method
        Collections.shuffle(people);
        Collections.shuffle(location);
        Collections.shuffle(weapons);

        //then add one card of each type in the solution
        result.add(people.get(0));
        remainingCards.remove(people.get(0));
        result.add(location.get(0));
        remainingCards.remove(location.get(0));
        result.add(weapons.get(0));
        remainingCards.remove(weapons.get(0));

        //shuffle again to add randomness in the play.
        Collections.shuffle(people);
        Collections.shuffle(location);
        Collections.shuffle(weapons);

        //Assign remainingCards to each player iteratively based on the index number
        Collections.shuffle(remainingCards);
        System.out.println("Dealing Cards...");
        for( int i = 0 ; i < remainingCards.size() ; i++)
            players.get(i % players.size()).setCard(remainingCards.get(i));
    }

    //Holds the main loop of the game
    public void gamePlay()
    {
        //deep copy of the players list - not to lose the players even after they are removed from the game.
        ArrayList<IPlayer> tempPlayers = new ArrayList<>();
        tempPlayers.addAll(players);

        //Loop condition - when playersList has only one
        while(players.size() != 1)
        {
            //Current player makes guess about the result
            Guess guess = currentPlayer.getGuess();

            // if that is accusation - that move is decisive for the current player whether he wins or loses.
            if(guess.checkAccusation())
            {
                //Display info..
                System.out.println("Player : " + currentPlayer.getIndex() + "  Accusation : " + guess.toString());

                //If the guess contains all three cards from the result - currPlayer wins
                if(guess.getCards().containsAll(result))
                {
                    System.out.println("Game is Won : Player : " + currentPlayer.getIndex() + " Won the Game.");
                    System.out.println("The answer is : ");
                    print(result);
                    return;
                }
                else
                {
                    players.remove(currentPlayer); // remove the player from the game

                    System.out.println("Player : " + currentPlayer.getIndex() + " made a bad accusation and was removed from the game ");
                    currIndex--;

                    // If only one player left then that -player wins th game
                    if(players.size() == 1) {
                        System.out.println("Game is Won : Player : " + players.get(0).getIndex() + " Won the Game.");
                        System.out.println("The answer is : ");
                        print(result);
                        return;
                    }

                }
            }
            else // if not the accusation then
            {
                //display info.
                System.out.println("Player : " + currentPlayer.getIndex() + "  Suggestion : " + guess.toString());
                Card theCard = null;
                // next players needs to be asked if that have the cards from the guess
                nextPlayer = tempPlayers.get((currentPlayer.getIndex() + 1) % tempPlayers.size());

                //Loop through all the players until currPlayer gets the answer.
                while(theCard == null && nextPlayer.getIndex() != currentPlayer.getIndex())
                {
                    System.out.println("Asking Player : " + nextPlayer.getIndex());

                    //checks if the nextPlayer can answer the guess
                    theCard = nextPlayer.canAnswer(guess , currentPlayer);

                    // if not move to the next player to ask
                    if(theCard == null )
                    {
                        System.out.println("Player : " + nextPlayer.getIndex() + " could not answer !");
                        nextPlayer = tempPlayers.get((nextPlayer.getIndex() + 1) % tempPlayers.size());
                    }
                    else
                        System.out.println("Player : " + nextPlayer.getIndex() + " answered !");
                }
                // receives the card info from another player and stores inside it.
                currentPlayer.receiveInfo(nextPlayer, theCard);
            }

            if(players.size() !=1)
                nextTurn(); // move to the next player
        }

    }

    // Will move to the next player
    private void nextTurn()
    {

        currIndex = (currIndex + 1) % players.size(); // from the last player it will go to the first one.
        currentPlayer = players.get(currIndex);
        System.out.println("Current Turn : Player " + currentPlayer.getIndex()); // output the info
    }

    // prints the elements of the array list . useful when printing the info on console
    private void print(ArrayList<Card> source)
    {
        int i = 0;
        System.out.print(" [ ");
        for( i = 0 ; i < source.size() - 1 ; i++)
            System.out.print(" " + source.get(i).getValue() + " ,");
        System.out.println(source.get(i).getValue() + " ]");
    }

}
