package scenes;

import java.awt.Color;
import java.awt.Graphics;

import main.Game;

public class Settings extends GameScene implements SceneMethods{

	public Settings(Game game) {
		super(game);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void render(Graphics g) {

		g.setColor(Color.blue);
		g.fillRect(0, 0, 640, 640);
	}

}
