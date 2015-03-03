import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;


public class MonsterSpawner extends Entity{
	Enemy enemy;
	int cooldown;
	int localOffsetX, localOffsetY, initialScreenPanOffsetX, initialScreenPanOffsetY;
	int width, height;
	
	MonsterSpawner(String n, int vx, int vy, int mw,int  mh) {
		super(n);
		vxpos = vx;
		vypos = vy;
		width = mw;
		height = mh;
		gridXpos = (int) ((vxpos) / 32);
		gridYpos = (int) ((vypos) / 32);
		sxpos = (int) (vxpos - ProjectTrinity.screenOffsetX);
		sypos = (int) (vypos - ProjectTrinity.screenOffsetY);
		init();
		// TODO Auto-generated constructor stub
	}
	
	void init(){
		enemy = new Enemy("null", "null", 0, 0, 0, 0, "null");
		if(enemy.type == "null"){
			enemy = new Enemy("goblin", "goblin", vxpos, vypos, width, height, "null");
		}
	}
	
	void update(){
		if(enemy.type == "null"){
			enemy = new Enemy("goblin", "goblin", vxpos, vypos,width, height, "null");
		}
		enemy.update();
			
			
	}
	void render(Graphics g, GameContainer container){
		enemy.render(g, container);
	}
	

}
