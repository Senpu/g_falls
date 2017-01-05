package com.cocktail_games_inc.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.cocktail_games_inc.G_falls;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "G_Falls";
		config.width = 800;
		config.height = 480;
		new LwjglApplication(new G_falls(), config);
	}
}
