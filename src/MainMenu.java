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
	String screen;
	Image title;
	Image startGame;
	Image mapEditor;
	Image selectionArrow;

	MainMenu(int mc, double w, double h) {
		mapCount = mc;
		width = w;
		height = h;
		init();
	}

	void init() {
		screen = "main";
		menuSelection = 0;

		try {
			title = new Image("Data/Images/Title.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
		try {
			startGame = new Image("Data/Images/StartGame.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
		try {
			mapEditor = new Image("Data/Images/MapEditor.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
		try {
			selectionArrow = new Image("Data/Images/SelectionArrow.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	void update() {

	}

	void render(Graphics g, GameContainer container) {

		if (screen == "main") {
			g.setColor(Color.white);
			g.fillRect((float) 0, (float) 0, (float) width, (float) height);
			g.drawImage(title, (float) (width / 2 - 266), 20);
			g.drawImage(startGame, (float) (width / 2) - 100, 200);
			g.drawImage(mapEditor, (float) (width / 2) - 100, 400);

			if (menuSelection == 0) {
				g.drawImage(selectionArrow, (float) (width / 2) + 100, 200);
			} else if (menuSelection == 1) {
				g.drawImage(selectionArrow, (float) (width / 2) + 100, 400);
			}

		}else if (screen == "levelSelect") {
			g.setColor(Color.white);
			g.fillRect(0, 0, (float) width,(float) height);
			g.setColor(Color.black);
			g.drawString("Select a Map!", 20, 20);
			if (mapCount < 20) {
				for (int i = 0; i < mapCount; i++) {
					g.drawString(ProjectTrinity.maps[i], 50,
							(float) (((height -200) / mapCount) * i) + 100 );
					if(menuSelection == i){
						g.drawImage(selectionArrow, 200, (float) (((height -200) / mapCount ) *i) + 80 );
					}
				}
			}

		}
	}

}
