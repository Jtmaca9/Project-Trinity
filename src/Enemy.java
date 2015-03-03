import org.newdawn.slick.Color;
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
	String dirCache;
	String direction;
	int cache = 0;

	// Pathfinding vars//
	String target;
	boolean aggressive;
	int targetGridX;
	int targetGridY;
	int mapWidth;
	int mapHeight;
	int pathcd;
	int nextStep;
	int vectorX1, vectorX2;
	int vectorY1, vectorY2;

	// Enemy Properties//
	float curHealth;
	int maxHealth;
	float healthPercent;
	int speed;

	Enemy(String n, String t, int x, int y, int mw, int mh, String targ) {
		super(n);
		type = t;
		vxpos = x;
		vypos = y;
		target = targ;
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
			aggressive = false;
			gridXpos = (int) ((vxpos) / 32);
			gridYpos = (int) ((vypos) / 32);
			pathFinder = new PathFinder(mapWidth, mapHeight, gridXpos, gridYpos, target);
			pathFinder.updateCurrenttLocation(gridXpos, gridYpos);
			if(target == "player1"){
			pathFinder.updateTargetLocation(ProjectTrinity.currentMap.player1.get_gridXpos(), ProjectTrinity.currentMap.player1.get_gridYpos());
			}else if(target == "player2"){
			pathFinder.updateTargetLocation(ProjectTrinity.currentMap.player2.get_gridXpos(), ProjectTrinity.currentMap.player2.get_gridYpos());
			}
		
		}
		if(type == "goblin"){
			speed = 4;
			curHealth = 100;
			maxHealth = 100;
		}

	}

	void update() {
		if (type != "null") {
			localOffsetX = (int) (initialScreenPanOffsetX - ProjectTrinity.screenOffsetX);
			localOffsetY = (int) (initialScreenPanOffsetY - ProjectTrinity.screenOffsetY);
			gridXpos = (int) ((vxpos) / 32);
			gridYpos = (int) ((vypos) / 32);
			
			//Aggro Checker///
			if(!aggressive){
			vectorX1 = Math.abs(ProjectTrinity.currentMap.player1.vxpos - vxpos);
			vectorY1 = Math.abs(ProjectTrinity.currentMap.player1.vypos - vypos);
			vectorX2 = Math.abs(ProjectTrinity.currentMap.player2.vxpos - vxpos);
			vectorY2 = Math.abs(ProjectTrinity.currentMap.player2.vypos - vypos);
			if(((vectorX1 < 20*32) && (vectorY1 < 20*32))){
				target = "player1";
				aggressive = true;
			}else if(((vectorX2 < 20 *32) && (vectorY2 < 20*32))){
				target = "player2";
				aggressive = true;
			}
			}
						
			// Health & Energy Checks//////////
			if (curHealth > maxHealth) {
				curHealth = maxHealth;
			}

			if (curHealth <= 0) {
				curHealth = 0;
				type = "null";
			}
			healthPercent = (float) ((float) curHealth / (float) maxHealth);
			
			///////////////////////////////////
			

	
			findPath();
			collision();
			move();
			
			

		}
	}

	void render(Graphics g, GameContainer container) {
		if (type != "null") {
			g.drawImage(enemyImage, sxpos, sypos);
			if(pathFinder.pathLength > 0){
			//g.drawString("." + pathFinder.path[pathFinder.pathLength-1].dir +"," + pathFinder.pathLength +"," + pathFinder.path[nextStep].dir+"," + nextStep, 20, 800);
			}
			g.setColor(Color.red);
			g.fillRect(sxpos, sypos - 20, 32, 5);
			g.setColor(Color.green);
			g.fillRect(sxpos, sypos - 20, (healthPercent) * 32, 5);
		}

	}

	void move() {
		if (aggressive){
		if(pathFinder.go){
			dirCache = pathFinder.path[nextStep].dir;
			System.out.println("start move");
			if(pathFinder.path[nextStep].dir == "up"){
				System.out.println("move up");
				if(vypos > pathFinder.path[nextStep].ypos *32){
					vypos -= speed;
					sypos -= speed;
					direction = "up";
				}else if( vypos == pathFinder.path[nextStep].ypos *32){
					nextStep--;
					pathFinder.init();
				}
			}else if(pathFinder.path[nextStep].dir == "right"){
				System.out.println("move right");
				if(vxpos < pathFinder.path[nextStep].xpos *32){
					vxpos += speed;
					sxpos += speed;
					direction = "right";
				}else if( vxpos == pathFinder.path[nextStep].xpos *32){
					nextStep--;
					pathFinder.init();
				}
			}else if(pathFinder.path[nextStep].dir == "down"){
				System.out.println("move down");
				if(vypos < pathFinder.path[nextStep].ypos *32){
					vypos += speed;
					sypos += speed;
					direction = "down";
				}else if( vypos == pathFinder.path[nextStep].ypos *32){
					nextStep--;
					pathFinder.init();
				}
			}else if(pathFinder.path[nextStep].dir == "left"){
				System.out.println("move left");
				if(vxpos > pathFinder.path[nextStep].xpos *32){
					vxpos -= speed;
					sxpos -= speed;
					direction = "left";
				}else if( vxpos == pathFinder.path[nextStep].xpos *32){
					nextStep--;
					pathFinder.init();
				}
				
			}	
		}else if(pathFinder.found == true){
		
			pathFinder.init();
		}
			
		}
			
		
		sxpos += localOffsetX;
		sypos += localOffsetY;
		initialScreenPanOffsetX = ProjectTrinity.screenOffsetX;
		initialScreenPanOffsetY = ProjectTrinity.screenOffsetY;
		if(nextStep < 0){
			nextStep = 0;
		}
		

	}

	void findPath() {
		pathFinder.updateCurrenttLocation(gridXpos, gridYpos);
		if(target == "player1"){
		pathFinder.updateTargetLocation(ProjectTrinity.currentMap.player1.get_gridXpos(), ProjectTrinity.currentMap.player1.get_gridYpos());
		}else if(target == "player2"){
		pathFinder.updateTargetLocation(ProjectTrinity.currentMap.player2.get_gridXpos(), ProjectTrinity.currentMap.player2.get_gridYpos());
		}
		pathFinder.findTarget();
		if(pathFinder.found){
		pathFinder.createPath();
		nextStep = pathFinder.pathLength -1;
		
		}
		
	
	}
		


	void collision() {
		if (ProjectTrinity.currentMap.playerCount == 2) {
			if ((vxpos + (32 - speed)) >= (ProjectTrinity.currentMap.player1.vxpos)
					&& (vxpos) <= (ProjectTrinity.currentMap.player1.vxpos) + (32 - speed)
					&& (vypos + (32 - speed)) >= (ProjectTrinity.currentMap.player1.vypos)
					&& (vypos) <= (ProjectTrinity.currentMap.player1.vypos) + (32 - speed)) {
				if (direction == "right") {// right
					vxpos -= speed;
					sxpos -= speed;

				} else if (direction == "left") {// left
					vxpos += speed;
					sxpos += speed;

				} else if (direction == "up") {// up
					vypos += speed;
					sypos += speed;

				} else if (direction == "down") {// down
					vypos -= speed;
					sypos -= speed;

				}
			}
				if ((vxpos + (32 - speed)) >= (ProjectTrinity.currentMap.player2.vxpos)
						&& (vxpos) <= (ProjectTrinity.currentMap.player2.vxpos) + (32 - speed)
						&& (vypos + (32 - speed)) >= (ProjectTrinity.currentMap.player2.vypos)
						&& (vypos) <= (ProjectTrinity.currentMap.player2.vypos) + (32 - speed)) {
					if (direction == "right") {// right
						vxpos -= speed;
						sxpos -= speed;

					} else if (direction == "left") {// left
						vxpos += speed;
						sxpos += speed;

					} else if (direction == "up") {// up
						vypos += speed;
						sypos += speed;

					} else if (direction == "down") {// down
						vypos -= speed;
						sypos -= speed;

					}
			}

		} else if (ProjectTrinity.currentMap.playerCount == 1) {
			if ((vxpos + (32 - speed)) >= (ProjectTrinity.currentMap.player1.vxpos)
					&& (vxpos) <= (ProjectTrinity.currentMap.player1.vxpos) + (32 - speed)
					&& (vypos + (32 - speed)) >= (ProjectTrinity.currentMap.player1.vypos)
					&& (vypos) <= (ProjectTrinity.currentMap.player1.vypos) + (32 - speed)) {
				if (direction == "right") {// right
					vxpos -= speed;
					sxpos -= speed;

				} else if (direction == "left") {// left
					vxpos += speed;
					sxpos += speed;

				} else if (direction == "up") {// up
					vypos += speed;
					sypos += speed;

				} else if (direction == "down") {// down
					vypos -= speed;
					sypos -= speed;

				}
			}
		}


	}
	
	float get_curHealth() {
		return curHealth;
	}

	int get_maxHealth() {
		return maxHealth;
	}

	void set_curHealth(int h) {
		curHealth = h;
	}

	void adjust_curHealth(int h) {
		curHealth += h;
	}
}
