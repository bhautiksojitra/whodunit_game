import java.util.ArrayList;

public class Main {

    public static void main(String[] args)
    {
        Human one = new Human();
        ArrayList<Card> first = new ArrayList<Card>();
        ArrayList<Card> second = new ArrayList<Card>();
        ArrayList<Card> third = new ArrayList<Card>();
        first.add(new Card("one" , "1"));
        first.add(new Card("one" , "2"));
        first.add(new Card("one" , "3"));
        second.add(new Card("two" , "4"));
        second.add(new Card("two" , "5"));
        second.add(new Card("two" , "6"));
        third.add(new Card("three" , "7"));
        third.add(new Card("three" , "8"));
        one.setUp(10 , 1 ,first , second , third);
        Guess g1 = one.getGuess();


    }


}
