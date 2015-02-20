import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


public class Player1 extends Player {
	String direction;
	String moveThisWay;
	Ability activeAbility;

	Player1(String n, String plc, int x, int y) {
		super(n, plc);
		vxpos = x;
		vypos = y;
		init();
	}

	
	void init(){
		noMove = "";
		movementQueue = new String[4];
		for (int i = 0; i < 4; i++) {
			movementQueue[i] = noMove;
		}
		direction = "up";
		speed = 4;
		sxpos = (int) (ProjectTrinity.width/2);
		sypos = (int) (ProjectTrinity.height/2);
		ProjectTrinity.screenOffsetX -= sxpos;
		ProjectTrinity.screenOffsetY -= sypos;
		
		try {
			playerImage = new Image("Data/Images/Tank.png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	void render(Graphics g, GameContainer container){
		g.setColor(Color.red);
		g.fillRect(sxpos , sypos - 20, 32, 5);
		g.setColor(Color.green);
		g.fillRect(sxpos , sypos - 20, (health / 100) *32, 5);
		g.drawImage(playerImage, sxpos, sypos);
		if(activeAb){
			activeAbility.render(g, container);
		}
	}
	
	void update(){
		
		gridXpos = (int) (vxpos / 32);
		gridYpos = (int) (vypos / 32);
		move();
		gridXpos = (int) (vxpos / 32);
		gridYpos = (int) (vypos / 32);
		collision();
		if(activeAb){
			activeAbility.update();
		}
	}
	
	void move(){
		
		moveThisWay = movementQueue[0];
		
		if(moveThisWay == "right"){	
			direction = "right";
			playerImage.setRotation(90f);
			vxpos += speed;
			if(sxpos <= (ProjectTrinity.width/3) *2){
				sxpos += speed;
			}else{
				ProjectTrinity.screenOffsetX += speed;
				if(ProjectTrinity.currentMap.playerCount == 2){
				ProjectTrinity.currentMap.player2.sxpos -=speed;
				}
			}

		}
		if(moveThisWay == "left"){
			direction = "left";
			playerImage.setRotation(270f);
			vxpos -= speed;
			if(sxpos >= (ProjectTrinity.width/3)){
				sxpos -= speed;
			}else{
				ProjectTrinity.screenOffsetX -= speed;
				if(ProjectTrinity.currentMap.playerCount == 2){
				ProjectTrinity.currentMap.player2.sxpos +=speed;
				}
			}

		}
		if(moveThisWay == "up"){
			direction = "up";
			playerImage.setRotation(0f);
			vypos -= speed;
			if(sypos >= (ProjectTrinity.height/3)){
				sypos -= speed;
			}else{
				ProjectTrinity.screenOffsetY -= speed;
				if(ProjectTrinity.currentMap.playerCount == 2){
				ProjectTrinity.currentMap.player2.sypos +=speed;
				}
			}

		}
		
		if(moveThisWay == "down"){
			direction = "down";			
			playerImage.setRotation(180f);
			vypos += speed;
			if(sypos <= (ProjectTrinity.height/3) *2){
				sypos += speed;
			}else{
				ProjectTrinity.screenOffsetY += speed;
				if(ProjectTrinity.currentMap.playerCount == 2){
				ProjectTrinity.currentMap.player2.sypos -=speed;
				}
			}

		}
	}
		
		
		
	
	void collision(){
		for(int i = gridXpos - 2; i < gridXpos + 2;  i++){
			for(int j = gridYpos - 2; j < gridYpos + 2; j++){
				if(i >= 0 && j >= 0 && i < ProjectTrinity.currentMap.mapWidth && j < ProjectTrinity.currentMap.mapHeight){
					if((vxpos + 28) >= (ProjectTrinity.currentMap.tiles[i][j].xpos * 32)  &&
							(vxpos) <= (ProjectTrinity.currentMap.tiles[i][j].xpos * 32) +  28 &&
							(vypos + 28) >= (ProjectTrinity.currentMap.tiles[i][j].ypos * 32)  &&
							(vypos) <= (ProjectTrinity.currentMap.tiles[i][j].ypos * 32) + 28  ){
						
						if(ProjectTrinity.currentMap.tiles[i][j].block){
			
						if (direction == "right"){//right
							vxpos -=speed;
							sxpos -= speed;

						}else if (direction == "left"){//left
							vxpos +=speed;
							sxpos += speed;

						}else if (direction == "up"){//up
							vypos +=speed;
							sypos += speed;

						}else if (direction == "down"){//down
							vypos -=speed;
							sypos -= speed;

						}
						}

					}
				}
			}
		}
		if(ProjectTrinity.currentMap.playerCount == 2){
		if((vxpos + 28) >= (ProjectTrinity.currentMap.player2.vxpos)  &&
				(vxpos) <= (ProjectTrinity.currentMap.player2.vxpos) +  28 &&
				(vypos + 28) >= (ProjectTrinity.currentMap.player2.vypos)  &&
				(vypos) <= (ProjectTrinity.currentMap.player2.vypos) + 28  ){
			if (direction == "right"){//right
				vxpos -=speed;
				sxpos -= speed;

			}else if (direction == "left"){//left
				vxpos +=speed;
				sxpos += speed;

			}else if (direction == "up"){//up
				vypos +=speed;
				sypos += speed;

			}else if (direction == "down"){//down
				vypos -=speed;
				sypos -= speed;

			}
		}
			
		}
		
		if(vxpos < 0 || (vxpos - 28) >= ProjectTrinity.currentMap.mapWidthPx || vypos < 0 || (vypos - 28) >= ProjectTrinity.currentMap.mapHeightPx ){
			if (direction == "right"){//right
				vxpos -=speed;
				sxpos -= speed;

			}else if (direction == "left"){//left
				vxpos +=speed;
				sxpos += speed;

			}else if (direction == "up"){//up
				vypos +=speed;
				sypos += speed;

			}else if (direction == "down"){//down
				vypos -=speed;
				sypos -= speed;

			}
			
		}
	}
	
	void ability1(String a){
			activeAb = true;
			if(direction == "right"){
				activeAbility = new Ability(a, a, direction, vxpos + 32, vypos + 8, sxpos + 32, sypos + 8);
			}else if(direction == "left"){
				activeAbility = new Ability(a, a, direction, vxpos - 16, vypos + 8, sxpos -16, sypos + 8);
			}else if(direction == "down"){
				activeAbility = new Ability(a, a, direction, vxpos + 8, vypos + 32, sxpos + 8, sypos + 32);
			}else if(direction == "up"){
				activeAbility = new Ability(a, a, direction, vxpos + 8, vypos - 16, sxpos + 8, sypos -16);
			}
	}
}

