import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class GUI {
	int playerCount;
	int GUIX;
	int GUIY;
	Image player1GUI;
	Image player2GUI;
	Image timerGUI;

	GUI(int pc) {
		playerCount = pc;
		init();
	}

	void init() {
		GUIX = 600;
		GUIY = 160;
		try {
			player1GUI = new Image("Data/Images/GUI-Player1.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
		try {
			player2GUI = new Image("Data/Images/GUI-Player2.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
		try {
			timerGUI = new Image("Data/Images/GUI-Timer.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}

	}

	void render(Graphics g, GameContainer container) {

		// Player 1 GUI Frame
		g.drawImage(player1GUI, 0, (float) (ProjectTrinity.height - GUIY));

		// Player 1 Health Bar
		g.setColor(Color.red);
		g.fillRect(20, (float) (ProjectTrinity.height - 140), 260, 40);
		g.setColor(Color.green);
		g.fillRect(20, (float) (ProjectTrinity.height - 140),
				(ProjectTrinity.currentMap.player1.get_healthPercent()) * 260,
				40);

		g.setColor(Color.white);
		g.drawString((int)ProjectTrinity.currentMap.player1.get_curHealth() + "/"
				+ ProjectTrinity.currentMap.player1.get_maxHealth(), 100,
				(float) (ProjectTrinity.height - 135));
		
		// Player 1 Energy Bar
				g.setColor(Color.red);
				g.fillRect(300, (float) (ProjectTrinity.height - 140), 260, 40);
				g.setColor(Color.yellow);
				g.fillRect(300, (float) (ProjectTrinity.height - 140),
						(ProjectTrinity.currentMap.player1.get_energyPercent()) * 260,
						40);

				g.setColor(Color.black);
				g.drawString((int)ProjectTrinity.currentMap.player1.get_curEnergy() + "/"
						+ ProjectTrinity.currentMap.player1.get_maxEnergy(), 380,
						(float) (ProjectTrinity.height - 135));

		// Player 1 Ability Bar
		drawAbilityIcon(g, container,
				ProjectTrinity.currentMap.player1.ability1, 20,
				(int) (ProjectTrinity.height - 85));
		
		if(ProjectTrinity.currentMap.player1.get_ability1CD() > 0){
			g.drawImage(ProjectTrinity.abilityCooldown, 20, (int) (ProjectTrinity.height - 85));
		}
		
		drawAbilityIcon(g, container,
				ProjectTrinity.currentMap.player1.ability2, 105,
				(int) (ProjectTrinity.height - 85));
		
		if(ProjectTrinity.currentMap.player1.get_ability2CD() > 0){
			g.drawImage(ProjectTrinity.abilityCooldown, 105, (int) (ProjectTrinity.height - 85));
		}

		if (playerCount == 2) {

			// Player 2 GUI Frame
			g.drawImage(player2GUI, (float) (ProjectTrinity.width - GUIX),
					(float) (ProjectTrinity.height - GUIY));

			// Player 2 Health Bar
			g.setColor(Color.red);
			g.fillRect((float) (ProjectTrinity.width - 580),
					(float) (ProjectTrinity.height - 140), 260, 40);
			g.setColor(Color.green);
			g.fillRect(
					(float) (ProjectTrinity.width - 580),
					(float) (ProjectTrinity.height - 140),
					(ProjectTrinity.currentMap.player2.get_healthPercent()) * 260,
					40);

			g.setColor(Color.white);
			g.drawString((int)ProjectTrinity.currentMap.player2.get_curHealth()
					+ "/" + ProjectTrinity.currentMap.player2.get_maxHealth(),
					(float) (ProjectTrinity.width - 500),
					(float) (ProjectTrinity.height - 135));
			
			// Player 2 Energy Bar
			g.setColor(Color.red);
			g.fillRect((float) (ProjectTrinity.width - 300), (float) (ProjectTrinity.height - 140), 260, 40);
			g.setColor(Color.yellow);
			g.fillRect((float) (ProjectTrinity.width - 300), (float) (ProjectTrinity.height - 140),
					(ProjectTrinity.currentMap.player2.get_energyPercent()) * 260,
					40);

			g.setColor(Color.black);
			g.drawString((int)ProjectTrinity.currentMap.player2.get_curEnergy() + "/"
					+ ProjectTrinity.currentMap.player2.get_maxEnergy(), (float) (ProjectTrinity.width - 220),
					(float) (ProjectTrinity.height - 135));


			// Player 2 Ability Bar
			drawAbilityIcon(g, container,
					ProjectTrinity.currentMap.player2.ability1,
					(int) (ProjectTrinity.width - 580),
					(int) (ProjectTrinity.height - 85));
			
			if(ProjectTrinity.currentMap.player2.get_ability1CD() > 0){
				g.drawImage(ProjectTrinity.abilityCooldown, (int) (ProjectTrinity.width - 580), (int) (ProjectTrinity.height - 85));
			}
			
			drawAbilityIcon(g, container,
					ProjectTrinity.currentMap.player2.ability2,
					(int) (ProjectTrinity.width - 495),
					(int) (ProjectTrinity.height - 85));
			
			if(ProjectTrinity.currentMap.player2.get_ability2CD() > 0){
				g.drawImage(ProjectTrinity.abilityCooldown, (int) (ProjectTrinity.width - 495), (int) (ProjectTrinity.height - 85));
			}
		}
	}

	void drawAbilityIcon(Graphics g, GameContainer container, String ability,
			int xpos, int ypos) {
		if (ability == "damageProjectile") {
			g.drawImage(ProjectTrinity.damageProjectileIcon, xpos, ypos);
		}
		if (ability == "healProjectile") {
			g.drawImage(ProjectTrinity.healProjectileIcon, xpos, ypos);
		}
	}
}
