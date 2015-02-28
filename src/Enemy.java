import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class Enemy extends Entity {
	String type;
	Image enemyImage;

	float initialScreenPanOffsetX;
	float initialScreenPanOffsetY;

	int size;
	int localOffsetX;
	int localOffsetY;
	PathFinder pathFinder;

	// Pathfinding vars//
	String target;
	boolean aggressive;
	Node nextStep;
	int targetGridX;
	int targetGridY;
	int mapWidth;
	int mapHeight;

	// Enemy Properties//
	float curHealth;
	int maxHealth;
	int speed;

	Enemy(String n, String t, int x, int y, int mw, int mh) {
		super(n);
		type = t;
		vxpos = x;
		vypos = y;
		mapWidth = mw;
		mapHeight = mh;
		sxpos = (int) (vxpos - ProjectTrinity.screenOffsetX);
		sypos = (int) (vypos - ProjectTrinity.screenOffsetY);
		init();

	}

	void init() {
		if (type != "null") {
			enemyImage = ProjectTrinity.monsterImage;
			initialScreenPanOffsetX = ProjectTrinity.screenOffsetX;
			initialScreenPanOffsetY = ProjectTrinity.screenOffsetY;
			speed = 2;
			target = "player1";
			gridXpos = (int) ((vxpos) / 32);
			gridYpos = (int) ((vypos) / 32);
			pathFinder = new PathFinder(mapWidth, mapHeight,gridXpos, gridYpos, target);
		

		}

	}

	void update() {
		if (type != "null") {
			localOffsetX = (int) (initialScreenPanOffsetX - ProjectTrinity.screenOffsetX);
			localOffsetY = (int) (initialScreenPanOffsetY - ProjectTrinity.screenOffsetY);
			gridXpos = (int) ((vxpos) / 32);
			gridYpos = (int) ((vypos) / 32);
			findPath();
			move();
			
			

		}
	}

	void render(Graphics g, GameContainer container) {
		if (type != "null") {
			g.drawImage(enemyImage, sxpos, sypos);
			g.drawString("." + pathFinder.parent.xpos + "," + pathFinder.parent.ypos, 20, 800);
		}
	}

	void move() {
		sxpos += localOffsetX;
		sypos += localOffsetY;
		initialScreenPanOffsetX = ProjectTrinity.screenOffsetX;
		initialScreenPanOffsetY = ProjectTrinity.screenOffsetY;

	}

	void findPath() {
		pathFinder.updateCurrenttLocation(gridXpos, gridYpos);
		pathFinder.updateTargetLocation(ProjectTrinity.currentMap.player1.get_gridXpos(), ProjectTrinity.currentMap.player1.get_gridYpos());
		pathFinder.findTarget();

	}
	
	void findNewPath(){
		//pathFinder.updateCurrenttLocation(gridXpos, gridYpos);
		//pathFinder.updateTargetLocation(ProjectTrinity.currentMap.player1.get_gridXpos(), ProjectTrinity.currentMap.player1.get_gridYpos());
		pathFinder.init();
		//pathFinder.findTarget();
	}

	void collision() {

	}
}
