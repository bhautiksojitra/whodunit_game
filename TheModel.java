import java.util.Collections;
import java.util.ArrayList;

public class TheModel {


    private ArrayList<Card> people;
    private ArrayList<Card> location;
    private ArrayList<Card> weapons;
    private ArrayList<IPlayer> players;

    private ArrayList<Card> remainingCards;
    private ArrayList<Card> result;
    private IPlayer currentPlayer;
    private IPlayer nextPlayer;
    private int currIndex;

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

    public void startGame()
    {
        System.out.println("Showing you all the information : ");
        System.out.println("Here are the names of all the suspects : ");
        print(people);
        System.out.println("Here are the names of all the locations : ");
        print(location);
        System.out.println("Here are the names of all the weapons : ");
        print(weapons);

        int index  = 0;
        for(IPlayer p : this.players)
            p.setUp(this.players.size(), index++, people, location, this.weapons);


        remainingCards.addAll(people);
        remainingCards.addAll(location);
        remainingCards.addAll(weapons);

        Collections.shuffle(people);
        Collections.shuffle(location);
        Collections.shuffle(weapons);

        result.add(people.get(0));
        remainingCards.remove(people.get(0));
        result.add(location.get(0));
        remainingCards.remove(location.get(0));
        result.add(weapons.get(0));
        remainingCards.remove(weapons.get(0));

        Collections.shuffle(people);
        Collections.shuffle(location);
        Collections.shuffle(weapons);

        Collections.shuffle(remainingCards);
        System.out.println("Dealing Cards...");
        for( int i = 0 ; i < remainingCards.size() ; i++)
            players.get(i % players.size()).setCard(remainingCards.get(i));
    }

    public void gamePlay()
    {
       /* printPlayers(players);
        players.remove(players.get(0));
        printPlayers(players);
       */

        ArrayList<IPlayer> tempPlayers = new ArrayList<>();
        tempPlayers.addAll(players);
        while(players.size() != 1)
        {
            Guess guess = currentPlayer.getGuess();

            if(guess.checkAccusation())
            {
                if(guess.getCards().containsAll(result))
                {
                    System.out.println("Player : " + currentPlayer.getIndex() + "Won the Game.");
                    return;
                }
                else
                {
                    players.remove(currentPlayer);
                    currIndex--;
                    if(players.size() == 1) {
                        System.out.println("Player : " + players.get(0).getIndex() + " Won the Game.");
                        return;
                    }

                }
            }
            else
            {
                Card theCard = null;
                nextPlayer = tempPlayers.get((currentPlayer.getIndex() + 1) % tempPlayers.size());
                while(theCard == null && nextPlayer.getIndex() != currentPlayer.getIndex())
                {
                    System.out.println("Asking Player : " + nextPlayer.getIndex());
                    theCard = nextPlayer.canAnswer(guess , currentPlayer);
                    if(theCard == null )
                        nextPlayer = tempPlayers.get((nextPlayer.getIndex() + 1) % tempPlayers.size());
                }

                if(theCard != null)
                {
                    currentPlayer.receiveInfo(nextPlayer , theCard);
                }
                else
                {
                    System.out.println("No one could answer the suggestion.");
                }
            }
            if(players.size() !=1)
                nextTurn();
        }

    }


    private void nextTurn()
    {
        currIndex = (currIndex + 1) % players.size();
        currentPlayer = players.get(currIndex);


    }


    private void print(ArrayList<Card> source)
    {
        int i = 0;
        System.out.print(" [ ");
        for( i = 0 ; i < source.size() - 1 ; i++)
            System.out.print(" " + source.get(i).getValue() + " ,");
        System.out.println(source.get(i).getValue() + " ]");
    }

    private void printPlayers(ArrayList<IPlayer> source)
    {
        int i = 0;
        System.out.print(" [ ");
        for( i = 0 ; i < source.size() - 1 ; i++)
            System.out.print(" " + source.get(i).getIndex() + " ,");
        System.out.println(source.get(i).getIndex() + " ]");
    }


}
