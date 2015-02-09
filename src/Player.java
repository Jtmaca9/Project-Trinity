import org.newdawn.slick.Image;


public class Player extends Entity {

	 boolean moveRight = false;
	 boolean moveLeft = false;
	 boolean moveUp = false;
	 boolean moveDown = false;
	 
	 float speed;
	 int size = 32;
	 Image playerImage;

	Player(String n) {
		super(n);
		// TODO Auto-generated constructor stub
	}
	
	Boolean get_moveRight(){
		return moveRight;
	}
	Boolean get_moveLeft(){
		return moveLeft;
	}
	Boolean get_moveUp(){
		return moveUp;
	}
	Boolean get_moveDown(){
		return moveDown;
	}
	
	void set_moveRight(boolean move){
		moveRight = move;
	}
	void set_moveLeft(boolean move){
		moveLeft = move;
	}
	void set_moveUp(boolean move){
		moveUp = move;
	}
	void set_moveDown(boolean move){
		moveDown = move;
	}
	
	
	

}
