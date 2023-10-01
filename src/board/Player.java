package board;
import java.util.ArrayList;
import java.util.List;

import cards.*;

public class Player {
    private Hand h;
    private Display d;
    private int score;
    private int handlimit;
    private int sticks;
    public Player(){
        h = new Hand();
        d = new Display();
        Card c =new Pan();
        d.add(c);
        score = 0;
        handlimit = 8;
        sticks = 0;
    };
    public int getScore(){
        return score;
    }
    public int getHandLimit(){
        return handlimit;
    }
    public int getStickNumber(){
        return sticks;
    };
    public void addSticks(int i ){
        for(int k = 0;k<i;k++){
            Card stick = new Stick();
            d.add(stick);
        }
        sticks += i;
    };
    public void removeSticks(int i){
        sticks -= i ;
        List<Integer> list = new ArrayList<>();
        for(int j =d.size()-1;j>=0;j--){
            if (getDisplay().getElementAt(j).getType()==CardType.STICK){
                list.add(j);
            }
        }
        for(int n=0;n<i;n++){
            d.removeElement(list.get(n));
        }
    };
    public Hand getHand(){
        return h;
    };
    public Display getDisplay(){
        return d;
    };
    public void addCardtoHand(Card c){
        if(c.getType()==CardType.BASKET){
            addCardtoDisplay(c);
            handlimit+=2;
        }
        else{
            h.add(c);
        }
       
    };
    public void addCardtoDisplay(Card c){
        d.add(c);
    };

    public boolean takeCardFromTheForest(int index){
        if (index<1 || index>8){
            return false;
        }
        else{
            if(h.size()>handlimit){
                return false;
            }
           
            else if (h.size()==handlimit) {
                if(Board.getForest().getElementAt(index).getType()==CardType.BASKET){
                    if(index>2){
                        if(getStickNumber()>=(index-2)){ 
                            removeSticks(index-2);
                            addCardtoHand(Board.getForest().removeCardAt(8-index));
                        }
                        else{
                            return false;
                        }
                       
                    }
                    else{
                        addCardtoHand(Board.getForest().removeCardAt(8-index));
                        return true;
                    }
        
                }
                return false;
                    
            } 
            else{
                if(index>2){
                    if(getStickNumber()>=(index-2)){ 
                        removeSticks(index-2);
                    }
                    else{
                        return false;
                    } 
                }
                addCardtoHand(Board.getForest().removeCardAt(8-index));
                return true;
            }
        }
    }
    public boolean takeFromDecay(){
        int count = 0;
        for(int i=0;i<Board.getDecayPile().size();i++){
            if(Board.getDecayPile().get(i).getType()==CardType.BASKET){
                count +=1;
            }
        }
        if((Board.getDecayPile().size()+getHand().size()>handlimit+3*count)||(Board.getDecayPile().isEmpty())){
            return false;
        }
        else{
            for(int i=0;i<Board.getDecayPile().size();i++){
                addCardtoHand(Board.getDecayPile().get(i));
            }
            Board.getDecayPile().clear();
            return true;
           
        }
        
       
    }
    public boolean cookMushrooms(ArrayList<Card> cooking){
        int panInDisplay = 0;
        int panInCooking = 0;
        int butterInCooking = 0;
        int ciderInCooking = 0;
        int nightMushroom =0;
        int dayMushroom = 0;
        List<Card> mushroom = new ArrayList<>();

        for(int i = 0;i<cooking.size();i++){
            if(cooking.get(i).getType()==CardType.PAN) panInCooking+=1;
            else if(cooking.get(i).getType()==CardType.BUTTER) butterInCooking+=1;
            else if(cooking.get(i).getType()==CardType.CIDER) ciderInCooking+=1;
            else if(cooking.get(i).getType()==CardType.DAYMUSHROOM){dayMushroom+=1;mushroom.add(cooking.get(i));}
            else if(cooking.get(i).getType()==CardType.NIGHTMUSHROOM){nightMushroom+=1;mushroom.add(cooking.get(i));}
            else{
                return false;
            }
        }
        //检查有pan在display或者arraylist
        for(int j =0;j<d.size();j++){
            if(d.getElementAt(j).getType()==CardType.PAN)panInDisplay+=1;
        }
        if((panInCooking==0&&panInDisplay==0)||(panInCooking>1))return false; 
        //检查是否有三个以上的相同的蘑菇 注意night是双倍
        for(int k=0;k<mushroom.size();k++){
            if(!mushroom.get(k).getName().equals(mushroom.get(0).getName()) )return false;
        }
        if((dayMushroom+2*nightMushroom)<3)return false;
        //检查有butter和cider时蘑菇数量是否够 butter四个蘑菇 cider五个蘑菇
        if((4*butterInCooking+5*ciderInCooking)>(dayMushroom+2*nightMushroom))return false;
        //当蘑菇烹饪时 更新score 
        score += 3*butterInCooking+5*ciderInCooking+(dayMushroom+2*nightMushroom)*(((Mushroom)mushroom.get(0)).getFlavourPoints());
        //将被烹饪的卡片移除 从display或hand中
          //从hand中移除
        if(panInCooking!=0){
            for(int w=h.size()-1;w>=0;w--){
                for(int e=cooking.size()-1;e>=0;e--){
                    if(h.getElementAt(w).getName()==cooking.get(e).getName()&&h.getElementAt(w).getType()==cooking.get(e).getType()){
                        h.removeElement(w);
                        cooking.remove(e);
                    }
                }
            }
        }
          //从display中移除(pan在display中)
        else{
            for(int w=h.size()-1;w>=0;w--){
                for(int e=cooking.size()-1;e>=0;e--){
                    if(h.getElementAt(w).getName()==cooking.get(e).getName()&&h.getElementAt(w).getType()==cooking.get(e).getType()){
                        h.removeElement(w);
                        cooking.remove(e);
                    }
                }
            }
            for(int s=0;s<d.size();s++){
                if(d.getElementAt(s).getType()==CardType.PAN){
                    d.removeElement(s);
                    break;
                }
            }
        }
        return true;
    };
    public boolean sellMushrooms(String name,int i){
        int numD = 0;
        int numN = 0;
        String rename = name.replaceAll("[^a-zA-Z0-9\\u4E00-\\u9FA5]", "").toLowerCase();
        List<Integer> listD = new ArrayList<>();
        List<Integer> listN = new ArrayList<>();
        for(int o =h.size()-1;o>=0;o--){
            if((h.getElementAt(o).getName().equals(rename))&&(h.getElementAt(o).getType()==CardType.DAYMUSHROOM)){
                numD+=1;
                listD.add(o);
            }
            if((h.getElementAt(o).getName().equals(rename))&&(h.getElementAt(o).getType()==CardType.NIGHTMUSHROOM)){
                numN+=1;
                listN.add(o);
            }
        }
        if(i>=2){
            if(numD+2*numN>=i)
            {
                if(numD>=i){
                    addSticks(i*((Mushroom)h.getElementAt(listD.get(0))).getSticksPerMushroom());
                    for(int n=0;n<i;n++){
                        h.removeElement(listD.get(n));
                    }
                }
                else {
                    addSticks(i*((Mushroom)h.getElementAt(listN.get(0))).getSticksPerMushroom());
                    h.removeElement(listN.get(0));
                    for(int q = 0;q<i-2;q++){
                        if(listD.get(q)>listN.get(0)){
                            h.removeElement(listD.get(q)-1);
                        }
                        else{
                            h.removeElement(listD.get(q));
                        }
                    }   
                }
                return true;
            }
            else{
                return false;
            }
        }
        else{
            return false;
        }
    }
    public boolean putPanDown(){
        for(int p = 0;p<h.size();p++){
            if(h.getElementAt(p).getType()==CardType.PAN){
                addCardtoDisplay(h.getElementAt(p));
                h.removeElement(p);
                return true;
            }
        }
        return false;
    }
}