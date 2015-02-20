import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


public class MainMenu {
	int mapCount;
	double width;
	double height;
	int menuSelection;
	Image title;
	Image startGame;
	Image mapEditor;
	Image selectionArrow;
	
	
	MainMenu(int mc, double w, double h){
		mapCount = mc;
		width = w;
		height = h;
		init();
	}
	
	void init(){
		menuSelection = 1;
		
		try {
			title = new Image("Data/Images/Title.png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			startGame = new Image("Data/Images/StartGame.png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			mapEditor = new Image("Data/Images/MapEditor.png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			selectionArrow = new Image("Data/Images/SelectionArrow.png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	void update(){
		
	}
	
	void render(Graphics g, GameContainer container){
		g.setColor(Color.white);
		g.fillRect((float)0, (float)0, (float)width,(float) height);
		g.drawImage(title, (float) (width/2 - 266), 20);
		g.drawImage(startGame, (float) (width/2)- 100, 200);
		g.drawImage(mapEditor, (float) (width/2) -100, 400);
		
		if (menuSelection == 1){
			g.drawImage(selectionArrow, (float) (width/2)+ 100, 200);
		}else if(menuSelection == 2){
			g.drawImage(selectionArrow, (float) (width/2)+ 100, 400);
		}
	}

}
