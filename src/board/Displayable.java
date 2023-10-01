package board;
import cards.Card;

public interface Displayable {
    public void add(Card a);
    public int size();
    public Card getElementAt(int a);
    public Card removeElement(int a);
}
