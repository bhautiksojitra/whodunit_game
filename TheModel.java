import java.util.ArrayList;

public class TheModel {


    private ArrayList<Card> people ;
    private ArrayList<Card> location;
    private ArrayList<Card> weapons;

    private ArrayList<IPlayer> players;
    public TheModel(ArrayList<Card> ppl , ArrayList<Card> places , ArrayList<Card> weapons , ArrayList<IPlayer> players)
    {
        people = ppl;
        location = places;
        this.weapons = weapons;
        this.players = players;

    }
}
