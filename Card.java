public class Card {

    private String typeCard;
    private String value;

    public Card(String theType , String theValue)
    {
        typeCard = theType;
        value = theValue;
    }

    public String getType()
    {
        return typeCard;
    }

    public String getValue()
    {
        return value;
    }


}
