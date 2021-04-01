/*----------------------------------------------------------------
    Name       : Bhautik Sojitra
    Student No : 7900140
    File       : Card.java
    Course     : COMP 2150
    Assignment : 3

    Purpose    : Holds the info related to the cards
 -----------------------------------------------------------------*/
public class Card {

    private String typeCard; // type of the card
    private String value;  // value of the card

    //declares all the fields
    public Card(String theType , String theValue)
    {
        typeCard = theType;
        value = theValue;
    }

    //some getter methods
    public String getType()
    {
        return typeCard;
    }

    public String getValue()
    {
        return value;
    }


}
