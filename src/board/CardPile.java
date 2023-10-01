package board;
import java.util.Collections;
import java.util.Stack;
import cards.Card;

public class CardPile {
    private Stack<Card> cPile;

    public CardPile(){
        cPile = new Stack<Card>();
    };
    public void addCard(Card cardName){
        cPile.add(cardName);
    }
    public Card drawCard(){
        return cPile.pop();

    }
    public void shufflePile(){
        Collections.shuffle(cPile);
    }
    public int pileSize(){
        return cPile.size();
    }
    public boolean isEmpty(){
        return cPile.isEmpty();
    }
    
}
