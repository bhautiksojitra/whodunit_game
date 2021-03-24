import java.util.*;

public class Main {

    public static void main(String[] args)
    {
        String[] nameOfPeople = new String[] { "John" , "Mike" , "Steve" , "Sam" , "Ben"} ;
        String[] nameOfLocation = new String[]{ "Kitchen" , "BedRoom" , "Hall" , "BathRoom" , "Balcony" };
        String[] nameOfWeapons = new String[] {"Knife" , "Stick" , "Gun" , "Poison" , "Pen" };

        ArrayList<Card> people = new ArrayList<Card>();
        ArrayList<Card> location = new ArrayList<Card>();
        ArrayList<Card> weapons = new ArrayList<Card>();

        ArrayList<IPlayer> players = new ArrayList<IPlayer>();

        addCardsToList(people , nameOfPeople , "Person");
        addCardsToList(location , nameOfLocation , "Location");
        addCardsToList(weapons , nameOfWeapons , "Weapon");


        Scanner scan = new Scanner(System.in);
        System.out.println("New Game Starts...");
        System.out.println("How many players you want to play with  : ");
        int totalPlayers = scan.nextInt() + 1;
        IPlayer newPlayer = new Human();
        players.add(newPlayer);
        newPlayer.setUp(totalPlayers , 0 , people , location , weapons);

        for(int i  = 1 ; i <= totalPlayers ; i++)
        {
            IPlayer computerPlayer = new Computer();
            players.add(computerPlayer);
            computerPlayer.setUp(totalPlayers , i , people , location , weapons);
        }

        TheModel simulator = new TheModel(people , location , weapons , players);





    }

    private static void addCardsToList(ArrayList<Card> destination , String[] listOfNames , String type)
    {
        for(int i = 0 ; i < listOfNames.length ; i++ )
        {
            destination.add(new Card(type , listOfNames[i]));
        }

    }

}
