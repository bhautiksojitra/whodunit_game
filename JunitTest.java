/*----------------------------------------------------------------
    Name       : Bhautik Sojitra
    Student No : 7900140
    File       : JunitTest.java
    Course     : COMP 2150
    Assignment : 3

    Purpose    : Implements Junit Tests
-----------------------------------------------------------------*/

// import the required junit libraries
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class JunitTest {

    // some required fields - common to each methods
    ArrayList<Card> people;
    ArrayList<Card> location;
    ArrayList<Card> weapons;


    Guess guess;
    Card card1;
    Card card2;
    Card card3;

    IPlayer computer;
    IPlayer player2;

    @BeforeEach
    public void InitMethod()
    {

        //Initialise all the required variables
        String[] nameOfPeople = new String[] { "John" , "Mike"} ;
        String[] nameOfLocation = new String[]{ "Kitchen" , "BedRoom" };
        String[] nameOfWeapons = new String[] {"Knife" , "Stick"};

        people = new ArrayList<>();
        location = new ArrayList<>();
        weapons = new ArrayList<>();

        for(int i = 0 ; i < nameOfPeople.length ; i++)
        {
           people.add(new Card ("person" ,nameOfPeople[i]));
           location.add(new Card("location" , nameOfLocation[i]));
           weapons.add(new Card("weapon" , nameOfWeapons[i]));
        }

        card1 = people.get(0);
        card2 = location.get(0);
        card3 = weapons.get(0);

        guess = new Guess(card1 , card2 , card3 , false);

        computer = new Computer();
        player2  = new Computer();

        computer.setUp(0 , 1 , people , location , weapons);
        player2.setUp(0 , 2 , people , location , weapons);
    }

    @Test
    public void test1()
    {

        // answer null when it has no cards in common with the guess
        assertEquals(null, computer.canAnswer(guess, player2));

        computer.setCard(people.get(1));
        computer.setCard(location.get(1));
        computer.setCard(weapons.get(1));

        assertEquals(null , computer.canAnswer(guess , player2));

    }

    @Test
    public void test2()
    {
        // return one card when the player has one card in common
        computer.setCard(people.get(0));
        computer.setCard(location.get(1));

        assertEquals(people.get(0) , computer.canAnswer(guess , player2));

        player2.setCard(location.get(0));

        assertEquals(location.get(0) , player2.canAnswer(guess , computer));
    }


    @Test
    public void test3()
    {
        // In the case of 2 cards in common it will return one of them randomly
        computer.setCard(people.get(0));
        computer.setCard(location.get(0));

        Card theCard = computer.canAnswer(guess , player2);

        if(!theCard.equals(people.get(0)))
            assertEquals(location.get(0) , theCard);
        else
            assertEquals(people.get(0) , theCard);

    }

    @Test
    public void test4()
    {
        computer.setCard(people.get(0));
        computer.setCard(location.get(0));


        ArrayList<Card> guessCards;

        // computer guess only those cards which it doesn't have on hands
        Guess newGuess = computer.getGuess();
        guessCards = newGuess.getCards();


        assertFalse(guessCards.contains(people.get(0)));
        assertFalse(guessCards.contains(location.get(0)));
        assertFalse(guess.checkAccusation()); // doesn't accuse in case of not sure

    }

    @Test
    public void test5()
    {
        computer.setCard(people.get(0));
        computer.setCard(location.get(0));
        computer.setCard(weapons.get(0));

        ArrayList<Card> guessCards;

        // when the computer is given all cards except 3 cards then
        Guess newGuess = computer.getGuess();
        guessCards = newGuess.getCards();

        assertTrue(newGuess.checkAccusation()); //computer will make accusation of the 3 remaining cards
        assertTrue(guessCards.contains(people.get(1)));
        assertTrue(guessCards.contains(location.get(1)));
        assertTrue(guessCards.contains(weapons.get(1)));

    }

    @Test
    public void test6()
    {
        computer.setCard(people.get(0));
        computer.setCard(location.get(0));

        ArrayList<Card> guessCards;

        // if the computer has 4 cards remained to be examined
        Guess newGuess = computer.getGuess();
        guessCards = newGuess.getCards();

        // make suggestion -by randomly picking 3 cards out of 4.
        assertFalse(guessCards.contains(people.get(0)));
        assertFalse(guessCards.contains(location.get(0)));
        assertFalse(guess.checkAccusation());

        // when he receives info regarding 1 card out of 4 from another player
        // In the next move he will make accusation on the three cards.
        computer.receiveInfo(player2 , weapons.get(0));


        newGuess = computer.getGuess();
        guessCards = newGuess.getCards();

        assertTrue(newGuess.checkAccusation());
        assertTrue(guessCards.contains(people.get(1)));
        assertTrue(guessCards.contains(location.get(1)));
        assertTrue(guessCards.contains(weapons.get(1)));


    }

    @Test
    public void test7()
    {
        IPlayer human = new Human();
        human.setUp(0 ,3, people , location , weapons);

        human.setCard(people.get(0));
        human.setCard(location.get(0));

        // if human has 2 cards in common with the guess - ask for user input - return card based on the input
        Card theCard = human.canAnswer(guess , computer);
        assertNotEquals(people.get(1) , theCard);
        assertNotEquals(location.get(1) , theCard);

        // out of 2 , it returns only one which is one of the 2 cards - checked by if condition
        if(!theCard.equals(people.get(0)))
            assertEquals(location.get(0) , theCard);
        else
            assertEquals(people.get(0) , theCard);

    }



}
