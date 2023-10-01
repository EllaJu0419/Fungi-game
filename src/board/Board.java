package board;
import java.util.ArrayList;

import cards.*;

public class Board {
    private static CardPile forestCardsPile;
    private static CardList forest;
    private static ArrayList<Card> decayPile;

    public static void initialisePiles(){
        forestCardsPile = new CardPile();
        forest = new CardList();
        decayPile = new ArrayList<>();
    };
    public static void setUpCards(){
        for(int i=0;i<10;i++){Card c =new HoneyFungus(CardType.DAYMUSHROOM);forestCardsPile.addCard(c);}
        for(int i=0;i<1;i++){Card c =new HoneyFungus(CardType.NIGHTMUSHROOM);forestCardsPile.addCard(c);}
        for(int i=0;i<8;i++){Card c =new TreeEar(CardType.DAYMUSHROOM);forestCardsPile.addCard(c);}
        for(int i=0;i<1;i++){Card c =new TreeEar(CardType.NIGHTMUSHROOM);forestCardsPile.addCard(c);}
        for(int i=0;i<6;i++){Card c =new LawyersWig(CardType.DAYMUSHROOM);forestCardsPile.addCard(c);}
        for(int i=0;i<1;i++){Card c =new LawyersWig(CardType.NIGHTMUSHROOM);forestCardsPile.addCard(c);}
        for(int i=0;i<5;i++){Card c =new Shiitake(CardType.DAYMUSHROOM);forestCardsPile.addCard(c);}
        for(int i=0;i<1;i++){Card c =new Shiitake(CardType.NIGHTMUSHROOM);forestCardsPile.addCard(c);}
        for(int i=0;i<5;i++){Card c =new HenOfWoods(CardType.DAYMUSHROOM);forestCardsPile.addCard(c);}
        for(int i=0;i<1;i++){Card c =new HenOfWoods(CardType.NIGHTMUSHROOM);forestCardsPile.addCard(c);}
        for(int i=0;i<4;i++){Card c =new BirchBolete(CardType.DAYMUSHROOM);forestCardsPile.addCard(c);}
        for(int i=0;i<1;i++){Card c =new BirchBolete(CardType.NIGHTMUSHROOM);forestCardsPile.addCard(c);}
        for(int i=0;i<4;i++){Card c =new Porcini(CardType.DAYMUSHROOM);forestCardsPile.addCard(c);}
        for(int i=0;i<1;i++){Card c =new Porcini(CardType.NIGHTMUSHROOM);forestCardsPile.addCard(c);}
        for(int i=0;i<4;i++){Card c =new Chanterelle(CardType.DAYMUSHROOM);forestCardsPile.addCard(c);}
        for(int i=0;i<1;i++){Card c =new Chanterelle(CardType.NIGHTMUSHROOM);forestCardsPile.addCard(c);}
        for(int i=0;i<3;i++){Card c =new Morel(CardType.DAYMUSHROOM);forestCardsPile.addCard(c);}
        for(int i=0;i<3;i++){Card c =new Butter();forestCardsPile.addCard(c);}
        for(int i=0;i<3;i++){Card c =new Cider();forestCardsPile.addCard(c);}
        for(int i=0;i<11;i++){Card c =new Pan();forestCardsPile.addCard(c);}
        for(int i=0;i<5;i++){Card c =new Basket();forestCardsPile.addCard(c);}
        
    }
    public static CardPile getForestCardsPile(){
        return forestCardsPile;
    }
    public static CardList getForest(){
        return forest;
    }
    public static ArrayList<Card> getDecayPile(){
        return decayPile;
    }
    public static void updateDecayPile(){
        if(decayPile.size()>=4){
            decayPile.clear();
        }
        decayPile.add(forest.removeCardAt(forest.size()-1));
        

    }
    
}
