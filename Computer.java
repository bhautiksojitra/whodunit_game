import java.util.ArrayList;

public class Computer implements IPlayer{

    public void setUp( int numPlayers, int index, ArrayList<Card> ppl,
                       ArrayList<Card> places, ArrayList<Card> weapons){}
    public void setCard (Card c){}
    public int getIndex(){ return -1;}
    public Card canAnswer(Guess g, IPlayer ip){return new Card("x" , "y");}
    Card card = new Card("!" ,"2");
    public Guess getGuess(){ return new Guess(card , card , card , false);}
    public void receiveInfo(IPlayer ip, Card c){}
}
