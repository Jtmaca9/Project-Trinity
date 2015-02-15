import org.newdawn.slick.Image;

public class Player extends Entity {

	boolean moveRight = false;
	boolean moveLeft = false;
	boolean moveUp = false;
	boolean moveDown = false;

	String movementQueue[];
	String noMove;

	int speed;
	int size = 32;
	Image playerImage;

	Player(String n) {
		super(n);
		// TODO Auto-generated constructor stub
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
