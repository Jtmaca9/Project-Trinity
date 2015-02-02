import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class ProjectTrinity extends BasicGame {

	static boolean p1_move_right = false;
	static boolean p1_move_left = false;
	static boolean p1_move_up = false;
	static boolean p1_move_down = false;
	static boolean p2_move_right = false;
	static boolean p2_move_left = false;
	static boolean p2_move_up = false;
	static boolean p2_move_down = false;



	public ProjectTrinity() {
		// Set the application window name
		super("ProjectTrinity");
	}

	public static void main(String[] arguments) {
		try {
			// Set up the screen and graphic options
			AppGameContainer app = new AppGameContainer(new ProjectTrinity());
			app.setDisplayMode(1920, 1080, true);
			app.setShowFPS(true);
			app.setVSync(true);
			app.start();

		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void init(GameContainer container) throws SlickException {



	}

	@Override
	public void update(GameContainer container, int delta)
			throws SlickException {


	}

	public void render(GameContainer container, Graphics g)
			throws SlickException {

	}

	public void keyPressed(int key, char c) {
		

		if (key == Input.KEY_D) {
			p1_move_right = true;
			p1_move_up = false;
			p1_move_down = false;
			p1_move_left = false;
		}

		if (key == Input.KEY_A) {
			p1_move_left = true;
			p1_move_up = false;
			p1_move_down = false;
			p1_move_right = false;
		}

		if (key == Input.KEY_W) {
			p1_move_up = true;
			p1_move_right = false;
			p1_move_down = false;
			p1_move_left = false;
		}

		if (key == Input.KEY_S) {
			p1_move_down = true;
			p1_move_up = false;
			p1_move_right = false;
			p1_move_left = false;
		}

		if (key == Input.KEY_RIGHT) {
			p2_move_right = true;
			p2_move_up = false;
			p2_move_down = false;
			p2_move_left = false;
		}

		if (key == Input.KEY_LEFT) {
			p2_move_left = true;
			p2_move_up = false;
			p2_move_down = false;
			p2_move_right = false;
		}

		if (key == Input.KEY_UP) {
			p2_move_up = true;
			p2_move_right = false;
			p2_move_down = false;
			p2_move_left = false;
		}

		if (key == Input.KEY_DOWN) {
			p2_move_down = true;
			p2_move_up = false;
			p2_move_right = false;
			p2_move_left = false;
		}

		if (key == Input.KEY_ESCAPE) {
			System.exit(0);
		}

	}

	public void keyReleased(int key, char c) {

		if (key == Input.KEY_D) {
			p1_move_right = false;

		}

		if (key == Input.KEY_A) {
			p1_move_left = false;

		}

		if (key == Input.KEY_W) {
			p1_move_up = false;

		}

		if (key == Input.KEY_S) {
			p1_move_down = false;

		}

		if (key == Input.KEY_RIGHT) {
			p2_move_right = false;

		}

		if (key == Input.KEY_LEFT) {
			p2_move_left = false;

		}

		if (key == Input.KEY_UP) {
			p2_move_up = false;

		}

		if (key == Input.KEY_DOWN) {
			p2_move_down = false;

		}

	}
	

}