/*----------------------------------------------------------------
    Name       : Bhautik Sojitra
    Student No : 7900140
    File       : Main.java
    Course     : COMP 2150
    Assignment : 3

    Purpose    : Main Class with main method - plays the game (calls the methods)
 -----------------------------------------------------------------*/

// Import the required libraries
import java.util.*;

public class Main {

    public static void main(String[] args)
    {
        // names of the various values for the cards
        String[] nameOfPeople = new String[] { "John" , "Mike" , "Steve" , "Sam" , "Ben"} ;
        String[] nameOfLocation = new String[]{ "Kitchen" , "BedRoom" , "Hall" , "BathRoom" , "Balcony" };
        String[] nameOfWeapons = new String[] {"Knife" , "Stick" , "Gun" , "Poison" , "Pen" };

        //Initialise all the lists for various types
        ArrayList<Card> people = new ArrayList<>();
        ArrayList<Card> location = new ArrayList<>();
        ArrayList<Card> weapons = new ArrayList<>();

        //List of the players
        ArrayList<IPlayer> players = new ArrayList<>();

        // create cards with the values and add them to the List
        addCardsToList(people , nameOfPeople , "Person");
        addCardsToList(location , nameOfLocation , "Location");
        addCardsToList(weapons , nameOfWeapons , "Weapon");

        // gets user input to get the number of player he wants to play with
        Scanner scan = new Scanner(System.in);
        System.out.println("New Game Starts...");
        System.out.println("How many players you want to play with  : ");
        int compPlayers = -1;
        while(compPlayers < 1) {
            try {
                compPlayers = Integer.parseInt(scan.nextLine());
            } catch (NumberFormatException ne) {
                System.out.println("Enter a valid Integer Value !");
            } // Handles all the exception
            if(compPlayers < 1)
                System.out.println("Enter the value greater than 0 !");
        }

        //add player to the list
        IPlayer newPlayer = new Human();
        players.add(newPlayer);

        for(int i  = 1 ; i <= compPlayers ; i++)
        {
            IPlayer computerPlayer = new Computer();
            players.add(computerPlayer);
        }

        //Initialise the TheModel class
        TheModel simulator = new TheModel(people , location , weapons , players);

        simulator.startGame(); // start the game
        simulator.gamePlay(); // plays the game

    }

    // make the cards with the various values and types and add it to the respective lists.
    private static void addCardsToList(ArrayList<Card> destination , String[] listOfNames , String type)
    {
        for (String name : listOfNames) {
            Card card = new Card(type, name);
            destination.add(card);
        }

    }

}
