package board;

import java.util.ArrayList;

import cards.Card;

public class Display implements Displayable {
    private ArrayList<Card> displayList = new ArrayList<>();

    public void add(Card c){
        displayList.add(c);
    }
    public int size(){
        return displayList.size();
    }
    public Card getElementAt(int index){
        return displayList.get(index);
    };
    public Card removeElement(int index){
        return displayList.remove(index);
    }
    
}
