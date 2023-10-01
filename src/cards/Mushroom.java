package cards;

public class Mushroom extends Edibleltem{
    protected int sticksPerMushroom;

    public Mushroom(CardType type,String cardName){
        super(type,cardName);
    }
    public int getSticksPerMushroom(){
        return sticksPerMushroom;
    }
}
