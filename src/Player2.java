import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


public class Player2 extends Player {
	String direction;
	String moveThisWay;

	Player2(String n, int x, int y) {
		super(n);
		vxpos = x + 32;
		vypos = y + 32;
		init();
		// TODO Auto-generated constructor stub
	}

	
	void init(){
		noMove = "";
		movementQueue = new String[4];
		for (int i = 0; i < 4; i++) {
			movementQueue[i] = noMove;
		}
		direction = "up";
		speed = 4;
		sxpos = (int) (ProjectTrinity.width/2) + 32;
		sypos = (int) (ProjectTrinity.height/2)+ 32;
		
		try {
			playerImage = new Image("Data/Images/Healer.png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	void render(Graphics g, GameContainer container){
		g.drawImage(playerImage, sxpos, sypos);
	}
	
	void update(){
		
		
		
		gridXpos = (int) (vxpos / 32);
		gridYpos = (int) (vypos / 32);
		move();
		gridXpos = (int) (vxpos / 32);
		gridYpos = (int) (vypos / 32);
		collision();
		
		
		
	}
	
	void move(){
		moveThisWay = movementQueue[0];
		
		if(moveThisWay == "right"){	
			direction = "right";
			playerImage.setRotation(90f);
			vxpos += speed;
			sxpos += speed;

		}
		if(moveThisWay == "left"){
			direction = "left";
			playerImage.setRotation(270f);
			vxpos -= speed;
			sxpos -= speed;
		}
		if(moveThisWay == "up"){
			direction = "up";
			playerImage.setRotation(0f);
			vypos -= speed;
			sypos -= speed;

		}
		
		if(moveThisWay == "down"){
			direction = "down";			
			playerImage.setRotation(180f);
			vypos += speed;
			sypos += speed;

		}
	}
		
		
		
	
	void collision(){
		for(int i = gridXpos - 2; i < gridXpos + 2;  i++){
			for(int j = gridYpos - 2; j < gridYpos + 2; j++){
				if(i > 0 && j > 0 && i < ProjectTrinity.currentMap.mapWidth && j < ProjectTrinity.currentMap.mapHeight){
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
		if((vxpos + 28) >= (ProjectTrinity.player1.vxpos)  &&
				(vxpos) <= (ProjectTrinity.player1.vxpos) +  28 &&
				(vypos + 28) >= (ProjectTrinity.player1.vypos)  &&
				(vypos) <= (ProjectTrinity.player1.vypos) + 28  ){
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
}

