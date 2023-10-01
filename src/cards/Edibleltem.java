package cards;

public class Edibleltem extends Card {
    protected int flavourPoints;

    public Edibleltem(CardType type,String cardName){
        super(type,cardName);
    };
    public int getFlavourPoints(){
        return flavourPoints;
    }
    
}
