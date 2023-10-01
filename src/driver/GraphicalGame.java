package driver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import board.Board;
import board.Player;
import cards.Card;
import cards.CardType;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class GraphicalGame extends Application{
    private static Player p1;
    private static Player p2;
    private int turn = 1;  
    private int action;
    boolean successfulMove = false;
    ArrayList<Card> cookingMushrooms;
    private String sellName;
    private int sellNum;
    ArrayList<Integer> indexList;
    
    public void start(Stage stage) {
		try {
			GridPane gridpane = this.initializeGUI();					    
			Scene scene = new Scene(gridpane);
			scene.setFill(Color.BEIGE);
			stage.setTitle("Fungi Game");
			stage.setScene(scene);
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
    
    public GridPane initializeGUI() throws FileNotFoundException {
    	Board.initialisePiles();
		Board.setUpCards();
		Board.getForestCardsPile().shufflePile();
		
		for (int i=0; i<8; i++) {    				
			Board.getForest().add(Board.getForestCardsPile().drawCard());
		}
		
		p1 = new Player();
		p2 = new Player();
		
		p1.addCardtoHand(Board.getForestCardsPile().drawCard());
		p1.addCardtoHand(Board.getForestCardsPile().drawCard());
		p1.addCardtoHand(Board.getForestCardsPile().drawCard());
		p2.addCardtoHand(Board.getForestCardsPile().drawCard());
		p2.addCardtoHand(Board.getForestCardsPile().drawCard());
		p2.addCardtoHand(Board.getForestCardsPile().drawCard());		  		    
		
		GridPane gridpane = new GridPane();
		gridpane.setMinSize(1600, 800);
		gridpane.setHgap(22);
		gridpane.setVgap(20);
		gridpane.setPadding(new Insets(20, 20, 20, 20));   			   			
		
	    Text textDecay = new Text("Decay pile");
	    textDecay.setFont(Font.font(16));
		gridpane.add(textDecay, 10, 5);
		    			
		for(int i = 10; i < 14; i++) {
			Rectangle rectDecay = new Rectangle(); 
			rectDecay.setHeight(120);
			rectDecay.setWidth(70);
			rectDecay.setArcWidth(10); 
		    rectDecay.setArcHeight(10);
		    rectDecay.setFill(Color.BEIGE);
		    rectDecay.setStroke(Color.BLACK);
		    rectDecay.getStrokeDashArray().addAll(15d, 10d);
		    gridpane.add(rectDecay, i, 4);  
		}
	    
	    Text textP1 = new Text("Display Player 1");
	    textP1.setFont(Font.font(16));
		gridpane.add(textP1, 6, 2, 2, 1);

		Rectangle rectP1 = new Rectangle(); 
		rectP1.setHeight(120);
		rectP1.setWidth(1250);
		rectP1.setArcWidth(10); 
		rectP1.setArcHeight(10);
		rectP1.setFill(Color.BEIGE);
		rectP1.setStroke(Color.BLACK);
		rectP1.getStrokeDashArray().addAll(15d, 10d);
	    gridpane.add(rectP1, 1, 3, 14, 1);
	    
	    Text textP2 = new Text("Display Player 2");
	    textP2.setFont(Font.font(16));
		gridpane.add(textP2, 6, 7, 2, 1);

		Rectangle rectP2 = new Rectangle(); 
		rectP2.setHeight(120);
		rectP2.setWidth(1250);
		rectP2.setArcWidth(10); 
		rectP2.setArcHeight(10);
		rectP2.setFill(Color.BEIGE);
		rectP2.setStroke(Color.BLACK);
		rectP2.getStrokeDashArray().addAll(15d, 10d);
	    gridpane.add(rectP2, 1, 6, 14, 1);
	    
	    Text textP1Hand = new Text("Player 1 hand");
	    textP1Hand.setFont(Font.font(16));
		gridpane.add(textP1Hand, 6, 0, 2, 1);

		Rectangle rectP1Hand = new Rectangle(); 
		rectP1Hand.setHeight(120);
		rectP1Hand.setWidth(1250);
		rectP1Hand.setArcWidth(10); 
		rectP1Hand.setArcHeight(10);
		rectP1Hand.setFill(Color.LIGHTBLUE);
		rectP1Hand.setStroke(Color.BLACK);
		rectP1Hand.getStrokeDashArray().addAll(15d, 10d);
	    gridpane.add(rectP1Hand, 1, 1, 14, 1);
	    
	    Text textP2Hand = new Text("Player 2 hand");
	    textP2Hand.setFont(Font.font(16));
		gridpane.add(textP2Hand, 6, 9, 2, 1);

		Rectangle rectP2Hand = new Rectangle(); 
		rectP2Hand.setHeight(120);
		rectP2Hand.setWidth(1250);
		rectP2Hand.setArcWidth(10); 
		rectP2Hand.setArcHeight(10);
		rectP2Hand.setFill(Color.PINK);
		rectP2Hand.setStroke(Color.BLACK);
		rectP2Hand.getStrokeDashArray().addAll(15d, 10d);
	    gridpane.add(rectP2Hand, 1, 8, 14, 1);
	    
	    Text textP1Score = new Text("Player 1 score: " + p1.getScore());
	    textP1Score.setFont(Font.font(16));
	    textP1Score.setFill(Color.RED);
	    gridpane.add(textP1Score, 0, 1);
	    
	    Text textP2Score = new Text("Player 2 score: " + p2.getScore());
	    textP2Score.setFont(Font.font(16));
	    textP2Score.setFill(Color.RED);
	    gridpane.add(textP2Score, 0, 8);
	    
	    Text textP1Sticks = new Text("Player 1 sticks: " + p1.getStickNumber());
	    textP1Sticks.setFont(Font.font(16));
	    textP1Sticks.setFill(Color.BROWN);
	    gridpane.add(textP1Sticks, 0, 2);
	    
	    Text textP2Sticks = new Text("Player 2 sticks: " + p2.getStickNumber());
	    textP2Sticks.setFont(Font.font(16));
	    textP2Sticks.setFill(Color.BROWN);
	    gridpane.add(textP2Sticks, 0, 7);
	    
	    Text textP1HandLimit = new Text("Player 1 handlimit: " + p1.getHandLimit());
	    textP1HandLimit.setFont(Font.font(16));
	    textP1HandLimit.setFill(Color.BLUE);
	    gridpane.add(textP1HandLimit, 0, 3);
	    
	    Text textP2HandLimit = new Text("Player 2 handlimit: " + p2.getHandLimit());
	    textP2HandLimit.setFont(Font.font(16));
	    textP2HandLimit.setFill(Color.BLUE);
	    gridpane.add(textP2HandLimit, 0, 6);
	    
	    Text textTurn = new Text("Turn: Player " + turn);
	    textTurn.setFont(Font.font(20));
	    textTurn.setFill(Color.RED);
	    gridpane.add(textTurn, 6, 5, 2, 1);   	    
	      
	    for(int i = 1; i < 9; i++) {
	    	Rectangle rect = new Rectangle(); 
			rect.setHeight(120);
			rect.setWidth(70);
			rect.setArcWidth(10); 
		    rect.setArcHeight(10);
		    if(i > 6)
		    	rect.setFill(Color.YELLOW);
		    else rect.setFill(Color.WHITE);
		    rect.setStroke(Color.BLACK);
		    rect.getStrokeDashArray().addAll(15d, 10d);
		    gridpane.add(rect, i, 4);
	    } 	    	
	    
	    Button button1 = new Button("takeCardFromTheForest");
	    button1.setMinSize(160, 50);
	    button1.setOnAction(new EventHandler<ActionEvent>() {	    	
            @Override
            public void handle(ActionEvent event) {
            	action = 1;              	
            }
        });
	    gridpane.add(button1, 15, 2);
	    
	    Button button2 = new Button("takeFromDecay");
	    button2.setMinSize(160, 50);
	    button2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {    
            	action = 2;
            	if(turn == 1) {
            		if(p1.takeFromDecay()) {
            			refreshHandlimit(gridpane, turn);
            			try {
							refreshHand(gridpane, turn);
						} catch (FileNotFoundException e) {
							e.printStackTrace();
						}
            			try {
							refreshDisplay(gridpane, turn);
						} catch (FileNotFoundException e) {
							e.printStackTrace();
						}
            			successfulMove = true;
            			turn = 2;
            		} else action2ErrorDialog();           		
            	}else {
            		if(p2.takeFromDecay()) {
            			refreshHandlimit(gridpane, turn);
            			try {
							refreshHand(gridpane, turn);
						} catch (FileNotFoundException e) {
							e.printStackTrace();
						}
            			try {
							refreshDisplay(gridpane, turn);
						} catch (FileNotFoundException e) {
							e.printStackTrace();
						}
            			successfulMove = true;
            			turn = 1;
            		} else action2ErrorDialog();  
            	}
            	if(successfulMove) {
            		updateForestAndDecayPile();
            		try {
            			refreshForest(gridpane);
						refreshDecayPile(gridpane);
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
            		refreshTurn(gridpane);
            	}
            	successfulMove = false;
            }
        });
	    gridpane.add(button2, 15, 3);
	    
	    Button button3 = new Button("cookMushrooms");
	    button3.setMinSize(160, 50);
	    button3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	cookingMushrooms = new ArrayList<>();
            	indexList = new ArrayList<>();
            	action = 3;
            }});
	    gridpane.add(button3, 15, 4);
	    
	    Button button4 = new Button("sellMushrooms");
	    button4.setMinSize(160, 50);
	    button4.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	indexList = new ArrayList<>();
            	action = 4;
            }
        });
	    gridpane.add(button4, 15, 5);
	    
	    Button button5 = new Button("putPanDown");
	    button5.setMinSize(160, 50);
	    button5.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	if(turn == 1) {
            		if(p1.putPanDown()) {
            			try {
    						refreshHand(gridpane, turn);
    						refreshDisplay(gridpane, turn);
    					} catch (FileNotFoundException e) {
    						e.printStackTrace();
    					}
            			successfulMove = true;
            			turn = 2;
            		} else action5ErrorDialog();           		            		
            	} else {
            		if(p2.putPanDown()) {
            			try {
    						refreshHand(gridpane, turn);
    						refreshDisplay(gridpane, turn);
    					} catch (FileNotFoundException e) {
    						e.printStackTrace();
    					}
            			successfulMove = true;
            			turn = 1;
            		} else action5ErrorDialog();
            	}
            	if(successfulMove) {
            		updateForestAndDecayPile();
            		try {
            			refreshForest(gridpane);
						refreshDecayPile(gridpane);
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
            		refreshTurn(gridpane);
            	}
            	successfulMove = false;                       	
            }
        });
	    gridpane.add(button5, 15, 6);
	    
	    Button finishButton = new Button("finish choosing");
		finishButton.setMinSize(160, 50);
		finishButton.setOnAction(new EventHandler<ActionEvent>() {	    	
            @Override
            public void handle(ActionEvent event) {
            	if(action == 3) {
            		if(turn == 1) {
                		if(p1.cookMushrooms(cookingMushrooms)) {
                			refreshScore(gridpane, turn);
                			try {
    							refreshHand(gridpane, turn);
    							refreshDisplay(gridpane, turn);
    						} catch (FileNotFoundException e) {
    							e.printStackTrace();
    						}            			
                			successfulMove = true;
                			turn = 2;            			
                		}else action3ErrorDialog();
                	}else {
                		if(p2.cookMushrooms(cookingMushrooms)) {
                			refreshScore(gridpane, turn);
                			try {
    							refreshHand(gridpane, turn);
    							refreshDisplay(gridpane, turn);
    						} catch (FileNotFoundException e) {
    							e.printStackTrace();
    						}            			
                			successfulMove = true;
                			turn = 1;
                		}else action3ErrorDialog();
                	}
                	if(successfulMove) {
                		updateForestAndDecayPile();
                		try {
                			refreshForest(gridpane);
    						refreshDecayPile(gridpane);
    					} catch (FileNotFoundException e) {
    						e.printStackTrace();
    					}
                		refreshTurn(gridpane);
                	}
                	successfulMove = false;
                	cookingMushrooms.clear();
                	indexList.clear();               	            	                                    	
            	}
                else if(action == 4) {
                	if(turn == 1) {
                		if(p1.sellMushrooms(sellName, sellNum)) {
                			refreshSticks(gridpane, turn);
                			try {
								refreshHand(gridpane, turn);
								refreshDisplay(gridpane, turn);
							} catch (FileNotFoundException e) {
								e.printStackTrace();
							}
                			successfulMove = true;
                			turn = 2;
                		} else action4ErrorDialog();
                	}else {
                		if(p2.sellMushrooms(sellName, sellNum)) {
                			refreshSticks(gridpane, turn);
                			try {
								refreshHand(gridpane, turn);
								refreshDisplay(gridpane, turn);
							} catch (FileNotFoundException e) {
								e.printStackTrace();
							}
                			successfulMove = true;
                			turn = 1;
                		} else action4ErrorDialog();
                	}
                	if(successfulMove) {
                		updateForestAndDecayPile();
                		try {
                			refreshForest(gridpane);
    						refreshDecayPile(gridpane);
    					} catch (FileNotFoundException e) {
    						e.printStackTrace();
    					}
                		refreshTurn(gridpane);
                	}
                	successfulMove = false;
                	indexList.clear();
                	sellNum = 0;
                }
            	finishButton.setVisible(false);
            }});
	    gridpane.add(finishButton, 15, 8);
	    finishButton.setVisible(false);
	    
		gridpane.add(displayImage(Board.getForest().getElementAt(0).getName(), gridpane), 8, 4);
		gridpane.add(displayImage(Board.getForest().getElementAt(1).getName(), gridpane), 7, 4);
		gridpane.add(displayImage(Board.getForest().getElementAt(2).getName(), gridpane), 6, 4);
		gridpane.add(displayImage(Board.getForest().getElementAt(3).getName(), gridpane), 5, 4);
		gridpane.add(displayImage(Board.getForest().getElementAt(4).getName(), gridpane), 4, 4);
		gridpane.add(displayImage(Board.getForest().getElementAt(5).getName(), gridpane), 3, 4);
		gridpane.add(displayImage(Board.getForest().getElementAt(6).getName(), gridpane), 2, 4);
		gridpane.add(displayImage(Board.getForest().getElementAt(7).getName(), gridpane), 1, 4);
		    					
		gridpane.add(displayImage(p1.getHand().getElementAt(0).getName(), gridpane), 1, 1);
		gridpane.add(displayImage(p1.getHand().getElementAt(1).getName(), gridpane), 2, 1);
		gridpane.add(displayImage(p1.getHand().getElementAt(2).getName(), gridpane), 3, 1);
		
		gridpane.add(displayImage(p2.getHand().getElementAt(0).getName(), gridpane), 1, 8);
		gridpane.add(displayImage(p2.getHand().getElementAt(1).getName(), gridpane), 2, 8);
		gridpane.add(displayImage(p2.getHand().getElementAt(2).getName(), gridpane), 3, 8);				
		
		gridpane.add(displayImage(p1.getDisplay().getElementAt(0).getName(), gridpane), 1, 3);			
		gridpane.add(displayImage(p2.getDisplay().getElementAt(0).getName(), gridpane), 1, 6);
		return gridpane;
    }
    
    private void action1ErrorDialog() {
    	Alert alert = new Alert(AlertType.ERROR);
    	alert.setTitle("Error Dialog");
    	alert.setHeaderText("Action 1 Error");
    	alert.setContentText("You do not have enough sticks or you have reached your handlimit!");
    	alert.showAndWait();
    }
    
    private void action2ErrorDialog() {
    	Alert alert = new Alert(AlertType.ERROR);
    	alert.setTitle("Error Dialog");
    	alert.setHeaderText("Action 2 Error");
    	alert.setContentText("You have reached your handlimit or there is no card in the decay pile!");
    	alert.showAndWait();
    }
    
    private void action3ChooseErrorDialog() {
    	Alert alert = new Alert(AlertType.ERROR);
    	alert.setTitle("Error Dialog");
    	alert.setHeaderText("Action 3 Error");
    	alert.setContentText("Please choose the mushrooms of the same type of you!");
    	alert.showAndWait();
    }
    
    private void action3ErrorDialog() {
    	Alert alert = new Alert(AlertType.ERROR);
    	alert.setTitle("Error Dialog");
    	alert.setHeaderText("Action 3 Error");
    	alert.setContentText("You do not have a pan or your number of mushrooms is less than 3!");
    	alert.showAndWait();
    }
    
    private void action4ChooseErrorDialog() {
    	Alert alert = new Alert(AlertType.ERROR);
    	alert.setTitle("Error Dialog");
    	alert.setHeaderText("Action 4 Error");
    	alert.setContentText("Please choose the mushrooms of the same type of you!");
    	alert.showAndWait();
    }
    
    private void action4ErrorDialog() {
    	Alert alert = new Alert(AlertType.ERROR);
    	alert.setTitle("Error Dialog");
    	alert.setHeaderText("Action 4 Error");
    	alert.setContentText("Your number of mushrooms is less than 2!");
    	alert.showAndWait();
    }
    
    private void action5ErrorDialog() {
    	Alert alert = new Alert(AlertType.ERROR);
    	alert.setTitle("Error Dialog");
    	alert.setHeaderText("Action 5 Error");
    	alert.setContentText("You do not have a pan!");
    	alert.showAndWait();
    }
    
    private void chooseRepeatedCardDialog() {
    	Alert alert = new Alert(AlertType.WARNING);
    	alert.setTitle("Warning Dialog");
    	alert.setHeaderText("Reapted choosing");
    	alert.setContentText("You have chosen this card!");
    	alert.showAndWait();
    }
    
    private void gameOverDialog() {
    	Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setTitle("Result Dialog");
    	alert.setHeaderText("Game Over!");
    	if(p1.getScore() > p2.getScore())
    		alert.setContentText("Player 1 wins!");
    	else if (p2.getScore()>p1.getScore()) 
    		alert.setContentText("Player 2 wins!");		
		else alert.setContentText("There is a tie!");			
    	alert.showAndWait();
    }
    
    private void removeNodeByRowColumnIndex (final int column, final int row, GridPane gridPane) {
    	ObservableList<Node> childrens = gridPane.getChildren();
    	for(Node node : childrens) {   		
    	    if((node instanceof ImageView || node instanceof Text) 
    	    		&& gridPane.getRowIndex(node) == row && gridPane.getColumnIndex(node) == column) {    		   
    	       gridPane.getChildren().remove(node);
    	       break;
    	    }
    	}
    }
    
    private Node getNodeByRowColumnIndex (final int column, final int row, GridPane gridPane) {
    	ObservableList<Node> childrens = gridPane.getChildren();
    	Node result = null;
    	for(Node node : childrens) {    		
    	    if(gridPane.getRowIndex(node) == row && gridPane.getColumnIndex(node) == column) {    		   
    	       result = node;
    	       break;
    	    }
    	}
    	return result;
    }
    
    private void refreshTurn(GridPane gridPane) {
    	this.removeNodeByRowColumnIndex(6, 5, gridPane);
    	Text textTurn = new Text("Turn: Player " + turn);
	    textTurn.setFont(Font.font(20));
	    textTurn.setFill(Color.RED);
	    gridPane.add(textTurn, 6, 5, 2, 1);
	    action = 0;
    }
    
    private void refreshScore(GridPane gridPane, int player) {
    	if(player == 1) {
    		this.removeNodeByRowColumnIndex(0, 1, gridPane);
    		Text textP1Score = new Text("Player 1 score: " + p1.getScore());
    	    textP1Score.setFont(Font.font(16));
    	    textP1Score.setFill(Color.RED);
    	    gridPane.add(textP1Score, 0, 1);
    	} else {
    		this.removeNodeByRowColumnIndex(0, 8, gridPane);
    		Text textP2Score = new Text("Player 2 score: " + p2.getScore());
    	    textP2Score.setFont(Font.font(16));
    	    textP2Score.setFill(Color.RED);
    	    gridPane.add(textP2Score, 0, 1);
    	}    		
    }
    
    private void refreshSticks(GridPane gridPane, int player) {
    	if(player == 1) {
    		this.removeNodeByRowColumnIndex(0, 2, gridPane);
    		Text textP1Sticks = new Text("Player 1 sticks: " + p1.getStickNumber());
    	    textP1Sticks.setFont(Font.font(16));
    	    textP1Sticks.setFill(Color.BROWN);
    	    gridPane.add(textP1Sticks, 0, 2);
    	} else {
    		this.removeNodeByRowColumnIndex(0, 7, gridPane);
    		Text textP2Sticks = new Text("Player 2 sticks: " + p2.getStickNumber());
    	    textP2Sticks.setFont(Font.font(16));
    	    textP2Sticks.setFill(Color.BROWN);
    	    gridPane.add(textP2Sticks, 0, 7);
    	}        		    	    
    }
    
    private void refreshHandlimit(GridPane gridPane, int player) {
    	if(player == 1) {
    		this.removeNodeByRowColumnIndex(0, 3, gridPane);
    		Text textP1HandLimit = new Text("Player 1 handlimit: " + p1.getHandLimit());
    	    textP1HandLimit.setFont(Font.font(16));
    	    textP1HandLimit.setFill(Color.BLUE);
    	    gridPane.add(textP1HandLimit, 0, 3);
    	} else {
    		this.removeNodeByRowColumnIndex(0, 6, gridPane);
    		Text textP2HandLimit = new Text("Player 2 handlimit: " + p2.getHandLimit());
    	    textP2HandLimit.setFont(Font.font(16));
    	    textP2HandLimit.setFill(Color.BLUE);
    	    gridPane.add(textP2HandLimit, 0, 6);
    	}	    	    
    }
    
    private void refreshHand(GridPane gridPane, int player) throws FileNotFoundException {
    	if(player == 1) {
    		for(int i = 0; i < 14; i++) {
        		this.removeNodeByRowColumnIndex(i+1, 1, gridPane);
            	if(i < p1.getHand().size())
        			gridPane.add(displayImage(p1.getHand().getElementAt(i).getName(), gridPane), i+1, 1);
        	}  
    	} else {
    		for(int i = 0; i < 14; i++) {
        		this.removeNodeByRowColumnIndex(i+1, 8, gridPane);
            	if(i < p2.getHand().size())
        			gridPane.add(displayImage(p2.getHand().getElementAt(i).getName(), gridPane), i+1, 8);
        	} 
    	}	
    }
    
    private void refreshDisplay(GridPane gridPane, int player) throws FileNotFoundException {
    	if(player == 1) {
    		for(int i = 0; i < 14; i++) {
        		this.removeNodeByRowColumnIndex(i+1, 3, gridPane);
            	if(i < p1.getDisplay().size())
        			gridPane.add(displayImage(p1.getDisplay().getElementAt(i).getName(), gridPane), i+1, 3);
        	}  
    	} else {
    		for(int i = 0; i < 14; i++) {
        		this.removeNodeByRowColumnIndex(i+1, 6, gridPane);
            	if(i < p2.getDisplay().size())
        			gridPane.add(displayImage(p2.getDisplay().getElementAt(i).getName(), gridPane), i+1, 6);
        	} 
    	}
    }
    
    private void refreshForest(GridPane gridPane) throws FileNotFoundException {
    	if(Board.getForest().getElementAt(0) == null)
    		gameOverDialog();
    	for(int i = 1; i <= 8; i++) {
    		this.removeNodeByRowColumnIndex(i, 4, gridPane);
        	if(Board.getForest().getElementAt(i-1) != null)
    			gridPane.add(displayImage(Board.getForest().getElementAt(i-1).getName(), gridPane), i, 4);
    	}    		
    	
    }    
    
    private void refreshDecayPile(GridPane gridPane) throws FileNotFoundException {
    	for(int i = 0; i < 4; i++)
    		this.removeNodeByRowColumnIndex(13-i, 4, gridPane);
    	for(int i = 0; i < Board.getDecayPile().size(); i++)    		
    		gridPane.add(displayImage(Board.getDecayPile().get(i).getName(), gridPane), 13-i, 4);
    	
    }
    
    private void updateForestAndDecayPile() {
    	if (Board.getForest().size()>0) {
			Board.updateDecayPile();
		}
		if (Board.getForestCardsPile().pileSize()>0) {
			Board.getForest().add(Board.getForestCardsPile().drawCard());
		}
    }   
    
    
    private ImageView displayImage(String name, GridPane gridpane) throws FileNotFoundException {
    	ImageView imageView = new ImageView(loadImage(name));
    	imageView.setFitWidth(70);
    	imageView.setPreserveRatio(true);
    	imageView.setOnMouseClicked((new EventHandler<MouseEvent>() { 
            public void handle(MouseEvent event) {
            	if(action == 1) {
            		int index = (9 - (int)(imageView.getLayoutX()/100));
            		if(turn == 1) {
                		if(p1.takeCardFromTheForest(index)) {
                			refreshSticks(gridpane, turn);
                    		refreshHandlimit(gridpane, turn);
                    		try {
								refreshHand(gridpane, turn);
							} catch (FileNotFoundException e) {
								e.printStackTrace();
							}
                    		try {
								refreshDisplay(gridpane, turn);
							} catch (FileNotFoundException e) {
								e.printStackTrace();
							}
                    		turn = 2;
                    		successfulMove = true;
                		} else action1ErrorDialog();                		            		
                	}else {
                		if(p2.takeCardFromTheForest(index)) {
                			refreshSticks(gridpane, turn);
                    		refreshHandlimit(gridpane, turn);
                    		try {
								refreshHand(gridpane, turn);
							} catch (FileNotFoundException e) {
								e.printStackTrace();
							}
                    		try {
								refreshDisplay(gridpane, turn);
							} catch (FileNotFoundException e) {
								e.printStackTrace();
							}
                    		turn = 1;
                    		successfulMove = true;
                		}else action1ErrorDialog();                		           		
                	}
                	if(successfulMove) {
                		if (Board.getForestCardsPile().pileSize()>0) {
        					Board.getForest().add(Board.getForestCardsPile().drawCard());
        				}
                    	updateForestAndDecayPile();
                    	try {
        					refreshForest(gridpane);
        					refreshDecayPile(gridpane);
        				} catch (FileNotFoundException e) {
        					e.printStackTrace();
        				}           	
                    	refreshTurn(gridpane);
                	}      
                	successfulMove = false;                
            	} else if(action == 3) {
            		boolean flag = true;
            		int count = 0;
                	if(turn == 1) {
                		int index = (int)(imageView.getLayoutX()/100);
                		if(!indexList.contains(index)) {
                			if((int)(imageView.getLayoutY()/100) == 0) {
                    			if(!cookingMushrooms.isEmpty()) {
                    				for(Card c: cookingMushrooms) {
        	            				if(c.getType() == CardType.BUTTER)
        	            					count = count + 4;
        	            				else if(c.getType() == CardType.CIDER)
        	            					count = count + 5;
        	            			}
                    			}	            			
    	            			if(name.equals("butter") && cookingMushrooms.size() - count < 4
    	            					|| name.equals("cider") && cookingMushrooms.size() - count < 5)
    	            				flag = false;
    	                		if((cookingMushrooms.size() == 0 || cookingMushrooms.get(0).getName().equals(name)) && flag) {	                			
    	                			cookingMushrooms.add(p1.getHand().getElementAt(index-1));
    	                		} else action3ChooseErrorDialog();
                    		}else action3ChooseErrorDialog();
                		}else chooseRepeatedCardDialog();                		
                	} else {
                		int index = (int)(imageView.getLayoutX()/100);
                		if(!indexList.contains(index)) {
                			if((int)(imageView.getLayoutY()/100) == 8) {
                    			if(!cookingMushrooms.isEmpty()) {
    		            			for(Card c: cookingMushrooms) {
    		            				if(c.getType() == CardType.BUTTER)
    		            					count = count + 4;
    		            				else if(c.getType() == CardType.CIDER)
    		            					count = count + 5;
    		            			}
                    			}
    	            			if(name.equals("butter") && cookingMushrooms.size() - count < 4
    	            					|| name.equals("cider") && cookingMushrooms.size() - count < 5)
    	            				flag = false;
    	                		if((cookingMushrooms.size() == 0 || cookingMushrooms.get(0).getName().equals(name)) && flag) {	                			
    	                			cookingMushrooms.add(p2.getHand().getElementAt(index-1));
    	                			} else action3ChooseErrorDialog();
    	                		} else action3ChooseErrorDialog(); 
                		}                		           		                	                		
                	}
                	getNodeByRowColumnIndex(15, 8, gridpane).setVisible(true);
            	} else if(action == 4) {

            			if(turn == 1 && (int)(imageView.getLayoutY()/100) == 0) {
                    		int index = (int)(imageView.getLayoutX()/100);
                    		if(!indexList.contains(index)) {
                    			if(sellNum == 0) {
                        			sellName = name;
                        			sellNum++;
                        		}            			
                        		else if(sellName.equals(name))
                        			sellNum++;
                        		else action4ChooseErrorDialog();
                    		} else chooseRepeatedCardDialog();                			
                		}else {
                			if((int)(imageView.getLayoutY()/100) == 8) {
                				int index = (int)(imageView.getLayoutX()/100);
                        		if(!indexList.contains(index)) {
                        			if(sellNum == 0) {
                            			sellName = name;
                            			sellNum++;
                            		}            			
                            		else if(sellName.equals(name))
                            			sellNum++;
                            		else action4ChooseErrorDialog();
                        		} else chooseRepeatedCardDialog();                   			
                			}            		            			
                		}            		            		
            		getNodeByRowColumnIndex(15, 8, gridpane).setVisible(true);
        		} 
            }})); 
    	return imageView;
    }
    
    private Image loadImage(String name) throws FileNotFoundException {
    	String path = "";
    	if(name.equals("basket"))
    		path = "./img/basket.jpg";
    	else if(name.equals("birchbolete"))
    		path = "./img/birchbolete.jpg";
    	else if(name.equals("butter"))
    		path = "./img/butter.jpg";
    	else if(name.equals("chanterelle"))
    		path = "./img/chanterelle.jpg";
    	else if(name.equals("cider"))
    		path = "./img/cider.jpg";
    	else if(name.equals("flyagaric"))
    		path = "./img/flyagaric.jpg";
    	else if(name.equals("henofwoods"))
    		path = "./img/henofwoods.jpg";
    	else if(name.equals("honeyfungus"))
    		path = "./img/honeyfungus.jpg";
    	else if(name.equals("lawyerswig"))
    		path = "./img/lawyerswig.jpg";
    	else if(name.equals("morel"))
    		path = "./img/morel.jpg";
    	else if(name.equals("pan"))
    		path = "./img/pan.jpg";
    	else if(name.equals("porcini"))
    		path = "./img/porcini.jpg";
    	else if(name.equals("shiitake"))
    		path = "./img/shiitake.jpg";
    	else if(name.equals("treeear"))
    		path = "./img/treeear.jpg";
    	else if(name.equals("stick"))
    		path = "./img/stick.jpg";
    		
    	Image image = new Image(new FileInputStream(path));
    	return image;
    }        
	
	public static void main(String[] args) {
	     Application.launch(args);
	 }
    
}
