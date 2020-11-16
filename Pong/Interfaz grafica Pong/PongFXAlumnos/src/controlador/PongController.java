package controlador;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class PongController {

	@FXML private GridPane paneToolBar;
	@FXML private AnchorPane paneMenuNavigationDrawer;
	@FXML private AnchorPane paneMenuToolbar;
	@FXML private Circle bola;
	
	@FXML private Rectangle tabla1;
	@FXML private Rectangle tabla2;
	
	@FXML private AnimationTimer animationball;
	@FXML private AnimationTimer animationPlayers;
	
	@FXML private int velocidadX = 3;
	@FXML private int velocidadY = 2;
	
	@FXML private int posX = 0;
	@FXML private int posY = 0;
	
	private final int Start = 1;
	private final int Reset = 2;
	
	public int estado = Start;
	
	private int posY_jug1 = 261;
	private int posY_jug2 = 461;
	
	private int vel_jug1 = 2;
	private int vel_jug2 = 2;
	
	private double p1 = 0;
	private double p2 = 0;
	
	@FXML
	void initialize() {

		//Cargar los 2 menús
		try {			
			VBox menuNavigationDrawer = FXMLLoader.load(getClass().getResource("/vista/MenuNavigationDrawer.fxml"));
			paneMenuNavigationDrawer.getChildren().add(menuNavigationDrawer);

			VBox menuToolbar = FXMLLoader.load(getClass().getResource("/vista/MenuToolbar.fxml"));
			paneMenuToolbar.getChildren().add(menuToolbar);

		} catch (IOException ex) {
			Logger.getLogger(PongController.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		//Al iniciar la aplicación, no se muestran los paneles
		paneMenuNavigationDrawer.setVisible(false);
		paneMenuToolbar.setVisible(false);
		   
		//Bola 
		
		animationball = new AnimationTimer() {
			
			
			public void handle(long now) {
				posX=posX+velocidadX;
				posY=posY-velocidadY;
				bola.setCenterX(posX);
				bola.setCenterY(posY);
				Shape shapeCollision1 = Shape.intersect(tabla1, bola);
				boolean colisionVacia1 = shapeCollision1.getBoundsInLocal().isEmpty();
				
				Shape shapeCollision2 = Shape.intersect(tabla2, bola);
				boolean colisionVacia2 = shapeCollision2.getBoundsInLocal().isEmpty();
				
				if(!colisionVacia1) {
					velocidadX=velocidadX*-1;
				}
				
				if(!colisionVacia2) {
					velocidadX=velocidadX*-1;
				}
				
				if (posX<=-280 || posX>=280) {
					velocidadX=velocidadX*-1;
				}
			
				if(posY<=-120 || posY>=120) {
					velocidadY=velocidadY*-1;
				}
			}
		};
		animationball.start();
	 
    //Puntuacion
	/*	if(posX > 270) {
		p1++;
		p1.setProgress((double)p1);
		p1.setText(""+p1);
		}*/
		
	//Jugadores
	animationPlayers = new AnimationTimer() {
		@Override
		 public void handle(long now) {
		 posY_jug1 += vel_jug1;
		 posY_jug2 += vel_jug2;
		 //TODO que no se salga del cuadro
		 tabla1.setY(posY_jug1);
		 tabla2.setY(posY_jug2);
		 
		
		 Scene scene= tabla1.getScene();
		 scene.setOnKeyPressed(e ->{
		 
		if(e.getCode() == KeyCode.W){ 
			vel_jug1 = -5; 
		}
		 
		if(e.getCode() == KeyCode.S){ 
			vel_jug1 = +5; 
		 }
		 
		 if(e.getCode() == KeyCode.P){ 
			 vel_jug2 += -5; 
		 }
		
		 if(e.getCode() == KeyCode.K){ 
			 vel_jug2 = +5; 
		 }
		});
		
		
		 scene.setOnKeyReleased(e ->{
		 if ( (e.getCode() == KeyCode.W) || (e.getCode() == KeyCode.S) ){
		 vel_jug1 = 0;
		 }
		 if ( (e.getCode() == KeyCode.UP) || (e.getCode() == KeyCode.DOWN) ){
		 vel_jug2 = 0;
		 }
		 });
		 animationPlayers.start(); 
		}
	
	};
	
	
	
	}
	
	//**************************************************************************
	// Acciones para los menús
	//**************************************************************************
	@FXML
	private void onMouseExitedPaneNavigationDrawer(MouseEvent event) {
		paneMenuNavigationDrawer.setVisible(false);
		
	}

	@FXML
	private void onMouseExitedPaneToolbarMenu(MouseEvent event) {
		paneMenuToolbar.setVisible(false);
		
	}

	@FXML
	private void onMouseClickedMenuNavigationDrawer(MouseEvent event) {
		paneMenuNavigationDrawer.setVisible(!paneMenuNavigationDrawer.isVisible());
		paneMenuToolbar.setVisible(false);
		
	}

	@FXML
	private void onMouseClickedMenuToolbar(MouseEvent event) {
		paneMenuToolbar.setVisible(!paneMenuToolbar.isVisible());
		paneMenuNavigationDrawer.setVisible(false);
		
	}
	
	//**************************************************************************
	// Otras funciones
	//**************************************************************************
	
}	


