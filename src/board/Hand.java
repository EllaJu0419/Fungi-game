package board;
import java.util.ArrayList;
import cards.Card;

public class Hand implements Displayable{
    private ArrayList<Card> handList = new ArrayList<>();

    public void add(Card c){
        handList.add(c);
    }
    public int size(){
        return handList.size();
    }
    public Card getElementAt(int index){
        return handList.get(index);
    }
    public Card removeElement(int index){
        return handList.remove(index);
    }
}
