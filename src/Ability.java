import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class Ability extends Entity {
	String type, direction;
	Image ability;

	int localOffsetX, localOffsetY;
	float initialScreenPanOffsetX;
	float initialScreenPanOffsetY;

	// ////////////////////
	// Ability Properties//
	// ///////////////////

	int size;
	int speed;
	int damageAmount;
	int healAmount;
	int coolDown;
	int abilityCost;

	Ability(String n, String t, String d, int xpos, int ypos, int sx, int sy) {
		super(n);
		type = t;
		direction = d;
		vxpos = xpos;
		vypos = ypos;
		sxpos = sx;
		sypos = sy;
		init();

	}

	void init() {
		localOffsetX = 0;
		localOffsetY = 0;
		initialScreenPanOffsetX = ProjectTrinity.screenOffsetX;
		initialScreenPanOffsetY = ProjectTrinity.screenOffsetY;
		if (type == "healProjectile") {
			ability = ProjectTrinity.healProjectileImage;
			size = 16;
			speed = 3;
			healAmount = 10;
			damageAmount = 0;
		} else if (type == "damageProjectile") {
			ability = ProjectTrinity.damageProjectileImage;
			size = 16;
			speed = 10;
			healAmount = 0;
			damageAmount = -10;
		}

	}

	void update() {
		if (type != "null") {
			localOffsetX = (int) (initialScreenPanOffsetX - ProjectTrinity.screenOffsetX);
			localOffsetY = (int) (initialScreenPanOffsetY - ProjectTrinity.screenOffsetY);
			if (type == "healProjectile" || type == "damageProjectile") {
				gridXpos = (int) (vxpos / 32);
				gridYpos = (int) (vypos / 32);
				projectile();
				gridXpos = (int) (vxpos / 32);
				gridYpos = (int) (vypos / 32);
				projectileCollision();
			}
		}
	}

	void projectile() {
		if (direction == "right") {
			vxpos += speed;
			sxpos += speed;
			sxpos += localOffsetX;
			sypos += localOffsetY;
			initialScreenPanOffsetX = ProjectTrinity.screenOffsetX;
			initialScreenPanOffsetY = ProjectTrinity.screenOffsetY;
		}
		if (direction == "left") {
			vxpos -= speed;
			sxpos -= speed;
			sxpos += localOffsetX;
			sypos += localOffsetY;
			initialScreenPanOffsetX = ProjectTrinity.screenOffsetX;
			initialScreenPanOffsetY = ProjectTrinity.screenOffsetY;
		}
		if (direction == "down") {
			vypos += speed;
			sypos += speed;
			sxpos += localOffsetX;
			sypos += localOffsetY;
			initialScreenPanOffsetX = ProjectTrinity.screenOffsetX;
			initialScreenPanOffsetY = ProjectTrinity.screenOffsetY;
		}
		if (direction == "up") {
			vypos -= speed;
			sypos -= speed;
			sxpos += localOffsetX;
			sypos += localOffsetY;
			initialScreenPanOffsetX = ProjectTrinity.screenOffsetX;
			initialScreenPanOffsetY = ProjectTrinity.screenOffsetY;
		}
	}

	void projectileCollision() {
		for (int i = gridXpos - 5; i < gridXpos + 5; i++) {
			for (int j = gridYpos - 5; j < gridYpos + 5; j++) {
				if (i >= 0 && j >= 0 && i < ProjectTrinity.currentMap.mapWidth
						&& j < ProjectTrinity.currentMap.mapHeight) {
					if ((vxpos + (16 - speed)) >= (ProjectTrinity.currentMap.tiles[i][j].xpos * 32)
							&& (vxpos) <= (ProjectTrinity.currentMap.tiles[i][j].xpos * 32) + (32 - speed)
							&& (vypos + (16 - speed)) >= (ProjectTrinity.currentMap.tiles[i][j].ypos * 32)
							&& (vypos) <= (ProjectTrinity.currentMap.tiles[i][j].ypos * 32) + (32 - speed)) {
						if (ProjectTrinity.currentMap.tiles[i][j].block) {
							type = "null";
						}

					}
				}
			}
		}

		if (vxpos < 0 || (vxpos + 10) >= ProjectTrinity.currentMap.mapWidthPx
				|| vypos < 0
				|| (vypos + 10) >= ProjectTrinity.currentMap.mapHeightPx) {
			type = "null";
		}

		if (ProjectTrinity.currentMap.playerCount == 1) {
			if ((vxpos + 10) >= (ProjectTrinity.currentMap.player1.vxpos)
					&& (vxpos) <= (ProjectTrinity.currentMap.player1.vxpos) + 28
					&& (vypos + 10) >= (ProjectTrinity.currentMap.player1.vypos)
					&& (vypos) <= (ProjectTrinity.currentMap.player1.vypos) + 28) {
				type = "null";
				ProjectTrinity.currentMap.player1.adjust_curHealth(healAmount);
				ProjectTrinity.currentMap.player1.adjust_curHealth(damageAmount);
			}
		} else if (ProjectTrinity.currentMap.playerCount == 2) {
			if ((vxpos + 10) >= (ProjectTrinity.currentMap.player1.vxpos)
					&& (vxpos) <= (ProjectTrinity.currentMap.player1.vxpos) + 28
					&& (vypos + 10) >= (ProjectTrinity.currentMap.player1.vypos)
					&& (vypos) <= (ProjectTrinity.currentMap.player1.vypos) + 28) {
				type = "null";
				ProjectTrinity.currentMap.player1.adjust_curHealth(healAmount);
				ProjectTrinity.currentMap.player1.adjust_curHealth(damageAmount);
			} else if ((vxpos + 10) >= (ProjectTrinity.currentMap.player2.vxpos)
					&& (vxpos) <= (ProjectTrinity.currentMap.player2.vxpos) + 28
					&& (vypos + 10) >= (ProjectTrinity.currentMap.player2.vypos)
					&& (vypos) <= (ProjectTrinity.currentMap.player2.vypos) + 28) {
				type = "null";
				ProjectTrinity.currentMap.player2.adjust_curHealth(healAmount);
				ProjectTrinity.currentMap.player2.adjust_curHealth(damageAmount);
			}
		}
	}

	void render(Graphics g, GameContainer container) {
		if (type != "null") {
			g.drawImage(ability, sxpos, sypos);
		}
	}

}
