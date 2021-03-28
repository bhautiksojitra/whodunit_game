import java.util.*;

public class Main {

    public static void main(String[] args)
    {
        String[] nameOfPeople = new String[] { "John" , "Mike" , "Steve" , "Sam" , "Ben"} ;
        String[] nameOfLocation = new String[]{ "Kitchen" , "BedRoom" , "Hall" , "BathRoom" , "Balcony" };
        String[] nameOfWeapons = new String[] {"Knife" , "Stick" , "Gun" , "Poison" , "Pen" };

        ArrayList<Card> people = new ArrayList<>();
        ArrayList<Card> location = new ArrayList<>();
        ArrayList<Card> weapons = new ArrayList<>();

        ArrayList<IPlayer> players = new ArrayList<>();

        addCardsToList(people , nameOfPeople , "Person");
        addCardsToList(location , nameOfLocation , "Location");
        addCardsToList(weapons , nameOfWeapons , "Weapon");


        Scanner scan = new Scanner(System.in);
        System.out.println("New Game Starts...");
        System.out.println("How many players you want to play with  : ");
        int compPlayers = scan.nextInt();
        IPlayer newPlayer = new Human();
        players.add(newPlayer);

        for(int i  = 1 ; i <= compPlayers ; i++)
        {
            IPlayer computerPlayer = new Computer();
            players.add(computerPlayer);
        }

        TheModel simulator = new TheModel(people , location , weapons , players);
        simulator.startGame();
        simulator.gamePlay();

    }

    private static void addCardsToList(ArrayList<Card> destination , String[] listOfNames , String type)
    {
        for (String name : listOfNames) {
            Card card = new Card(type, name);
            destination.add(card);
        }

    }

}
