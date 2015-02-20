import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class Player extends Entity {

	boolean moveRight = false;
	boolean moveLeft = false;
	boolean moveUp = false;
	boolean moveDown = false;
	boolean activeAb = false;

	String movementQueue[];
	String noMove;
	
	String ability1;

	int size = 32;
	Image playerImage;
	
	///////////////
	//PlayerStats//
	//////////////
	String playerClass;
	int speed;
	int health;
	int energy;
	int attack;
	int magicAttack;
	int defence;
	

	Player(String n, String plc) {
		super(n);
		playerClass = plc;
		playerClass();
	}
	
	void playerClass(){
		if (playerClass == "Warrior"){
			health = 100;
			energy = 100;
			attack = 10;
			magicAttack = 0;
			defence = 5;
			ability1 = "healProjectile";
		}else if(playerClass == "Priest"){
			health = 100;
			energy = 100;
			attack = 0;
			magicAttack = 10;
			defence = 5;
			ability1 = "healProjectile";
		}
	}
	
	
	
	void render(Graphics g, GameContainer container){
		g.setColor(Color.red);
		g.fillRect(sxpos , sypos - 20, 32, 5);
		g.setColor(Color.green);
		g.fillRect(sxpos , sypos - 20, (health / 100) *32, 5);
		g.drawImage(playerImage, sxpos, sypos);
	}


	Boolean get_moveRight() {
		return moveRight;
	}

	Boolean get_moveLeft() {
		return moveLeft;
	}

	Boolean get_moveUp() {
		return moveUp;
	}

	Boolean get_moveDown() {
		return moveDown;
	}

	float get_speed() {
		return speed;
	}

	void set_moveRight(boolean move) {
		if (move) {
			for (int i = 0; i < 4; i++) {
				if (movementQueue[i] == noMove) {
					movementQueue[i] = "right";
					break;
				}
				
			}
		} else {
			for (int i=0; i<4; i++) {
				if (movementQueue[i] == "right") {
					while (i<3) {
						movementQueue[i] = movementQueue[i+1];
						i++;
					}
					movementQueue[3] = noMove;
				}
			}
		}

	}

	void set_moveLeft(boolean move) {
		if (move) {
			for (int i = 0; i < 4; i++) {
				if (movementQueue[i] == noMove) {
					movementQueue[i] = "left";
					break;
				}
			}
		} else {
			for (int i=0; i<4; i++) {
				if (movementQueue[i] == "left") {
					while (i<3) {
						movementQueue[i] = movementQueue[i+1];
						i++;
					}
					movementQueue[3] = noMove;
				}
			}
		}

	}

	void set_moveUp(boolean move) {
		if (move) {
			for (int i = 0; i < 4; i++) {
				if (movementQueue[i] == noMove) {
					movementQueue[i] = "up";
					break;
				}

			}
		} else {
			for (int i=0; i<4; i++) {
				if (movementQueue[i] == "up") {
					while (i<3) {
						movementQueue[i] = movementQueue[i+1];
						i++;
					}
					movementQueue[3] = noMove;
				}
			}
		}

	}

	void set_moveDown(boolean move) {
		if (move) {
			for (int i = 0; i < 4; i++) {
				if (movementQueue[i] == noMove) {
					movementQueue[i] = "down";
					break;
				}
				
			}
		} else {
			for (int i=0; i<4; i++) {
				if (movementQueue[i] == "down") {
					while (i<3) {
						movementQueue[i] = movementQueue[i+1];
						i++;
					}
					movementQueue[3] = noMove;
				}
			}
		}

	}

	void set_speed(int spd) {
		speed = spd;
	}

}
