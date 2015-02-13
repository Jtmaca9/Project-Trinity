import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


public class Player1 extends Player {

	Player1(String n, float x, float y) {
		super(n);
		vxpos = x;
		vypos = y;
		init();
		// TODO Auto-generated constructor stub
	}

	
	void init(){
		ProjectTrinity.screenOffsetX += vxpos;
		ProjectTrinity.screenOffsetX += vypos;
		speed = 10f;
		sxpos = (float) (ProjectTrinity.width/2);
		sypos = (float) (ProjectTrinity.height/2);
		
		try {
			playerImage = new Image("Data/Images/Tank.png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	void render(Graphics g, GameContainer container){
		g.drawImage(playerImage, sxpos, sypos);
	}
	
	void move(){
		if(moveRight){
			ProjectTrinity.screenOffsetX += speed;
			vxpos += speed;
			moveLeft = false;
			moveDown = false;
			moveUp = false;
		}
		if(moveLeft){
			ProjectTrinity.screenOffsetX -= speed;
			vxpos -= speed;
			moveRight = false;
			moveDown = false;
			moveUp = false;
		}
		if(moveUp){
			ProjectTrinity.screenOffsetY -= speed;
			vypos -= speed;
			moveLeft = false;
			moveDown = false;
			moveRight = false;
		}
		
		if(moveDown){
			ProjectTrinity.screenOffsetY += speed;
			vypos += speed;
			moveRight = false;
			moveLeft = false;
			moveUp = false;
		}
		
	}

}
