import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;


public class Ability extends Entity {
	String type, direction;
	Image ability;
	int size;
	int speed;
	int localOffsetX, localOffsetY;
	float initialScreenPanOffsetX;
	float initialScreenPanOffsetY;

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
	
	
	void init(){
		localOffsetX = 0;
		localOffsetY = 0;
		initialScreenPanOffsetX = ProjectTrinity.screenOffsetX;
		initialScreenPanOffsetY = ProjectTrinity.screenOffsetY;
		if(type == "healProjectile"){
			ability = ProjectTrinity.healProjectileImage;
			size = 16;
			speed = 6;
		}
		
	}
	void update(){
		localOffsetX = (int) (initialScreenPanOffsetX - ProjectTrinity.screenOffsetX);
		localOffsetY = (int) (initialScreenPanOffsetY - ProjectTrinity.screenOffsetY);
		if(type == "healProjectile"){
			healProjectile();
		}
	}
	
	
	void healProjectile(){
		if(direction == "right"){
			vxpos += speed;
			sxpos += speed;
			sxpos += localOffsetX;
			sypos += localOffsetY;
			initialScreenPanOffsetX = ProjectTrinity.screenOffsetX;
			initialScreenPanOffsetY = ProjectTrinity.screenOffsetY;
		}
		if(direction == "left"){
			vxpos -= speed;
			sxpos -= speed;
			sxpos += localOffsetX;
			sypos += localOffsetY;
			initialScreenPanOffsetX = ProjectTrinity.screenOffsetX;
			initialScreenPanOffsetY = ProjectTrinity.screenOffsetY;
		}
		if(direction == "down"){
			vypos += speed;
			sypos += speed;
			sxpos += localOffsetX;
			sypos += localOffsetY;
			initialScreenPanOffsetX = ProjectTrinity.screenOffsetX;
			initialScreenPanOffsetY = ProjectTrinity.screenOffsetY;
		}
		if(direction == "up"){
			vypos -= speed;
			sypos -= speed;
			sxpos += localOffsetX;
			sypos += localOffsetY;
			initialScreenPanOffsetX = ProjectTrinity.screenOffsetX;
			initialScreenPanOffsetY = ProjectTrinity.screenOffsetY;
		}
	}
	
	void render(Graphics g, GameContainer container){
		g.drawImage(ability, sxpos, sypos);
	}
	

}
