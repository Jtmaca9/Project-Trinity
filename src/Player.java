import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Player extends Entity {

	boolean moveRight = false;
	boolean moveLeft = false;
	boolean moveUp = false;
	boolean moveDown = false;

	String movementQueue[];
	String noMove;

	String ability1;
	String ability2;
	String ability3;
	String ability4;
	String direction;
	String moveThisWay;
	Ability[] activeAbilities;

	int activeAbilityNumber = 30;
	int ability1CD;
	int ability2CD;

	int size = 32;
	Image playerImage;

	// /////////////
	// PlayerStats//
	// ////////////
	String playerClass;
	int speed;
	float curHealth;
	int maxHealth;
	float healthPercent;
	float curEnergy;
	int maxEnergy;
	float energyPercent;
	int attack;
	int magicAttack;
	int defence;
	float energyRegen;
	float healthRegen;

	// ////////////////////////////
	// Ability coolDowns and cost//
	// ///////////////////////////

	// *Damage and Heal Projectile
	int dhProjCD;
	int dhProjCost;

	Player(String n, String plc, int x, int y) {
		super(n);
		playerClass = plc;
		vxpos = x;
		vypos = y;
		init();
	}

	void init() {
		playerClass();
		activeAbilities = new Ability[activeAbilityNumber];
		noMove = "";
		movementQueue = new String[4];
		for (int i = 0; i < 4; i++) {
			movementQueue[i] = noMove;
		}
		direction = "up";
		speed = 4;
		sxpos = (int) (ProjectTrinity.width / 2);
		sypos = (int) (ProjectTrinity.height / 2);
		ProjectTrinity.screenOffsetX = -sxpos;
		ProjectTrinity.screenOffsetY = -sypos;

		if (name == "player2") {
			sxpos += 32;
			sypos += 32;
			vxpos += 32;
			vypos += 32;

		}

		if (name == "player1") {
			try {
				playerImage = new Image("Data/Images/Tank.png");
			} catch (SlickException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (name == "player2") {
			try {
				playerImage = new Image("Data/Images/Healer.png");
			} catch (SlickException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		for (int i = 0; i < activeAbilityNumber; i++) {
			activeAbilities[i] = new Ability("null", "null", "null", 0, 0, 0, 0);
		}

		ability1CD = 0;
		ability2CD = 0;
		
		//Initiate Damage and Heal Projectile costs and cooldowns
		dhProjCD = 1;
		dhProjCost = 1;
	}

	void playerClass() {
		if (playerClass == "Warrior") {
			maxHealth = 100;
			curHealth = maxHealth;
			maxEnergy = 100;
			curEnergy = maxEnergy;
			attack = 10;
			magicAttack = 0;
			defence = 5;
			healthRegen = 0.1f;
			energyRegen = 0.2f;
			ability1 = "damageProjectile";
			ability2 = "healProjectile";
		} else if (playerClass == "Priest") {
			maxHealth = 100;
			curHealth = maxHealth;
			maxEnergy = 100;
			curEnergy = maxEnergy;
			attack = 0;
			magicAttack = 10;
			defence = 5;
			healthRegen = 0.1f;
			energyRegen = 0.2f;
			ability1 = "damageProjectile";
			ability2 = "healProjectile";
		}
	}

	void render(Graphics g, GameContainer container) {
		g.setColor(Color.red);
		g.fillRect(sxpos, sypos - 20, 32, 5);
		g.setColor(Color.green);
		g.fillRect(sxpos, sypos - 20, (healthPercent) * 32, 5);
		g.drawImage(playerImage, sxpos, sypos);
		for (int i = 0; i < activeAbilityNumber; i++) {
			activeAbilities[i].render(g, container);
		}
	}

	void update() {

		gridXpos = (int) (vxpos / 32);
		gridYpos = (int) (vypos / 32);
		move();
		gridXpos = (int) (vxpos / 32);
		gridYpos = (int) (vypos / 32);
		collision();

		for (int i = 0; i < activeAbilityNumber; i++) {
			activeAbilities[i].update();
		}
		
		//Ability CoolDown Management//////
		
		
		if(ability1CD > 0){
			ability1CD--;
		}
		if(ability2CD > 0){
			ability2CD--;
		}
		
		// Health & Energy Checks//////////
		if (curHealth > maxHealth) {
			curHealth = maxHealth;
		}

		if (curHealth <= 0) {
			curHealth = 0;
		}

		if (curEnergy > maxEnergy) {
			curEnergy = maxEnergy;
		}

		if (curEnergy <= 0) {
			curEnergy = 0;
		}
		if(curHealth < maxHealth){
			curHealth += healthRegen;
		}
		if(curEnergy < maxEnergy){
			curEnergy += energyRegen;
		}

		healthPercent = (float) ((float) curHealth / (float) maxHealth);
		energyPercent = (float) ((float) curEnergy / (float) maxEnergy);
		// /////////////////////////////////
	}

	void move() {

		moveThisWay = movementQueue[0];

		if (name == "player1") {

			if (moveThisWay == "right") {
				direction = "right";
				playerImage.setRotation(90f);
				vxpos += speed;
				if (sxpos <= (ProjectTrinity.width / 3) * 2) {
					sxpos += speed;
				} else {

					ProjectTrinity.screenOffsetX += speed;
					if (ProjectTrinity.currentMap.playerCount == 2) {
						ProjectTrinity.currentMap.player2.sxpos -= speed;
					}

				}

			}
			if (moveThisWay == "left") {
				direction = "left";
				playerImage.setRotation(270f);
				vxpos -= speed;
				if (sxpos >= (ProjectTrinity.width / 3)) {
					sxpos -= speed;
				} else {

					ProjectTrinity.screenOffsetX -= speed;
					if (ProjectTrinity.currentMap.playerCount == 2) {
						ProjectTrinity.currentMap.player2.sxpos += speed;

					}
				}

			}
			if (moveThisWay == "up") {
				direction = "up";
				playerImage.setRotation(0f);
				vypos -= speed;
				if (sypos >= (ProjectTrinity.height / 3)) {
					sypos -= speed;
				} else {

					ProjectTrinity.screenOffsetY -= speed;
					if (ProjectTrinity.currentMap.playerCount == 2) {
						ProjectTrinity.currentMap.player2.sypos += speed;
					}

				}

			}

			if (moveThisWay == "down") {
				direction = "down";
				playerImage.setRotation(180f);
				vypos += speed;
				if (sypos <= (ProjectTrinity.height / 3) * 2) {
					sypos += speed;
				} else {

					ProjectTrinity.screenOffsetY += speed;
					if (ProjectTrinity.currentMap.playerCount == 2) {
						ProjectTrinity.currentMap.player2.sypos -= speed;
					}

				}

			}
		} else if (name == "player2") {

			if (moveThisWay == "right") {
				direction = "right";
				playerImage.setRotation(90f);
				vxpos += speed;
				sxpos += speed;

			}
			if (moveThisWay == "left") {
				direction = "left";
				playerImage.setRotation(270f);
				vxpos -= speed;
				sxpos -= speed;
			}
			if (moveThisWay == "up") {
				direction = "up";
				playerImage.setRotation(0f);
				vypos -= speed;
				sypos -= speed;

			}

			if (moveThisWay == "down") {
				direction = "down";
				playerImage.setRotation(180f);
				vypos += speed;
				sypos += speed;

			}
		}
	}

	void collision() {
		for (int i = gridXpos - 2; i < gridXpos + 2; i++) {
			for (int j = gridYpos - 2; j < gridYpos + 2; j++) {
				if (i >= 0 && j >= 0 && i < ProjectTrinity.currentMap.mapWidth
						&& j < ProjectTrinity.currentMap.mapHeight) {
					if ((vxpos + 28) >= (ProjectTrinity.currentMap.tiles[i][j].xpos * 32)
							&& (vxpos) <= (ProjectTrinity.currentMap.tiles[i][j].xpos * 32) + 28
							&& (vypos + 28) >= (ProjectTrinity.currentMap.tiles[i][j].ypos * 32)
							&& (vypos) <= (ProjectTrinity.currentMap.tiles[i][j].ypos * 32) + 28) {

						if (ProjectTrinity.currentMap.tiles[i][j].block) {

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
			}
		}
		if (ProjectTrinity.currentMap.playerCount == 2 && name == "player1") {
			if ((vxpos + 28) >= (ProjectTrinity.currentMap.player2.vxpos)
					&& (vxpos) <= (ProjectTrinity.currentMap.player2.vxpos) + 28
					&& (vypos + 28) >= (ProjectTrinity.currentMap.player2.vypos)
					&& (vypos) <= (ProjectTrinity.currentMap.player2.vypos) + 28) {
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

		} else if (ProjectTrinity.currentMap.playerCount == 2
				&& name == "player2") {
			if ((vxpos + 28) >= (ProjectTrinity.currentMap.player1.vxpos)
					&& (vxpos) <= (ProjectTrinity.currentMap.player1.vxpos) + 28
					&& (vypos + 28) >= (ProjectTrinity.currentMap.player1.vypos)
					&& (vypos) <= (ProjectTrinity.currentMap.player1.vypos) + 28) {
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

		if (vxpos < 0 || (vxpos + 28) >= ProjectTrinity.currentMap.mapWidthPx
				|| vypos < 0
				|| (vypos + 28) >= ProjectTrinity.currentMap.mapHeightPx) {
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

	void ability(String a) {
		if (a == "healProjectile" || a == "damageProjectile") {
			if (curEnergy >= dhProjCost) {
				if (a == ability1 && ability1CD == 0) {
					for (int i = 0; i < activeAbilityNumber; i++) {
						if (activeAbilities[i].name == "null"
								|| activeAbilities[i].type == "null") {
							if (direction == "right") {
								activeAbilities[i] = new Ability(a, a,
										direction, vxpos + 32, vypos + 8,
										sxpos + 32, sypos + 8);
							} else if (direction == "left") {
								activeAbilities[i] = new Ability(a, a,
										direction, vxpos - 16, vypos + 8,
										sxpos - 16, sypos + 8);
							} else if (direction == "down") {
								activeAbilities[i] = new Ability(a, a,
										direction, vxpos + 8, vypos + 32,
										sxpos + 8, sypos + 32);
							} else if (direction == "up") {
								activeAbilities[i] = new Ability(a, a,
										direction, vxpos + 8, vypos - 16,
										sxpos + 8, sypos - 16);
							}
							curEnergy -= dhProjCost;
							ability1CD += dhProjCD;
							break;
						}
					}
				} else if (a == ability2 && ability2CD == 0) {
					for (int i = 0; i < activeAbilityNumber; i++) {
						if (activeAbilities[i].name == "null"
								|| activeAbilities[i].type == "null") {
							if (direction == "right") {
								activeAbilities[i] = new Ability(a, a,
										direction, vxpos + 32, vypos + 8,
										sxpos + 32, sypos + 8);
							} else if (direction == "left") {
								activeAbilities[i] = new Ability(a, a,
										direction, vxpos - 16, vypos + 8,
										sxpos - 16, sypos + 8);
							} else if (direction == "down") {
								activeAbilities[i] = new Ability(a, a,
										direction, vxpos + 8, vypos + 32,
										sxpos + 8, sypos + 32);
							} else if (direction == "up") {
								activeAbilities[i] = new Ability(a, a,
										direction, vxpos + 8, vypos - 16,
										sxpos + 8, sypos - 16);
							}
							curEnergy -= dhProjCost;
							ability2CD += dhProjCD;
							break;
						}
					}
				}
			}
		}
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
			for (int i = 0; i < 4; i++) {
				if (movementQueue[i] == "right") {
					while (i < 3) {
						movementQueue[i] = movementQueue[i + 1];
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
			for (int i = 0; i < 4; i++) {
				if (movementQueue[i] == "left") {
					while (i < 3) {
						movementQueue[i] = movementQueue[i + 1];
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
			for (int i = 0; i < 4; i++) {
				if (movementQueue[i] == "up") {
					while (i < 3) {
						movementQueue[i] = movementQueue[i + 1];
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
			for (int i = 0; i < 4; i++) {
				if (movementQueue[i] == "down") {
					while (i < 3) {
						movementQueue[i] = movementQueue[i + 1];
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

	float get_healthPercent() {
		return healthPercent;
	}

	float get_curEnergy() {
		return curEnergy;
	}

	int get_maxEnergy() {
		return maxEnergy;
	}

	void set_curEnergy(int e) {
		curEnergy = e;
	}

	void adjust_curEnergy(int e) {
		curEnergy += e;
	}

	float get_energyPercent() {
		return energyPercent;
	}

	String get_ability1() {
		return ability1;
	}

	String get_ability2() {
		return ability2;
	}
	
	int get_dhProjCD(){
		return dhProjCD;
	}
	
	int get_ability1CD(){
		return ability1CD;
	}
	
	int get_ability2CD(){
		return ability2CD;
	}

}
