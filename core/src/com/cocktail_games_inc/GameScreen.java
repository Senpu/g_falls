package com.cocktail_games_inc;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.Timer;

import java.util.Iterator;
import java.util.TimerTask;
import java.util.logging.Handler;

import static com.badlogic.gdx.Input.Keys.R;

public class GameScreen implements Screen {

	final G_falls game;
	OrthographicCamera camera;
	SpriteBatch batch;

	Texture fireImage;
	Texture blastImage;
	Texture bg_img;
	Texture heroImage;

	Sound blastSound;
	Music bg_music;

	Rectangle rec_hero;
	Vector3 touchPos;
	Array<Rectangle> firedrops;

	long lastDropTime;
	int isDodged;


	public GameScreen(final G_falls gam) {

		this.game = gam;

		camera = new OrthographicCamera();
		camera.setToOrtho(false, 480, 600);

		batch = new SpriteBatch();

		touchPos = new Vector3();

		fireImage = new Texture("bomb.png");
		heroImage = new Texture("Crash.png");
		blastImage = new Texture("explosion.png");
		bg_img = new Texture("bg_image.jpg");


		blastSound = Gdx.audio.newSound(Gdx.files.internal("explosion.wav"));
		bg_music = Gdx.audio.newMusic(Gdx.files.internal("bg_music.mp3"));

		bg_music.setLooping(true);
		bg_music.play();

		rec_hero = new Rectangle();
		rec_hero.x = 480 / 2 - 60 / 2;
		rec_hero.y = 90;
		rec_hero.width = 60;
		rec_hero.height = 60;

		firedrops = new Array<Rectangle>();
		spawnFireDrop();

	}



	private void spawnFireDrop() {
		Rectangle fireDrop = new Rectangle();
		fireDrop.x = MathUtils.random(0, 480 - 50);
		fireDrop.y = 600;
		fireDrop.width = 50;
		fireDrop.height = 50;
		firedrops.add(fireDrop);
		lastDropTime = TimeUtils.nanoTime();
	}
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		camera.update();

		game.batch.setProjectionMatrix(camera.combined);
		game.batch.begin();
		game.batch.draw(bg_img, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		game.font.draw(game.batch, "Bombs dodged: " + isDodged, 5, 600);
		game.batch.draw(heroImage, rec_hero.x, rec_hero.y);
		for (Rectangle firedrop : firedrops) {
			game.batch.draw(fireImage, firedrop.x, firedrop.y);
		}
		game.batch.end();

		if (Gdx.input.isTouched()) {
			touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(touchPos);
			rec_hero.x = (int) (touchPos.x - 60 / 2);
		}

		if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) rec_hero.x -= 400 * Gdx.graphics.getDeltaTime();
		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) rec_hero.x += 400 * Gdx.graphics.getDeltaTime();

		if (rec_hero.x < 0) rec_hero.x = 0;
		if (rec_hero.x > 480 - 60) rec_hero.x = 480 - 60;

		if (TimeUtils.nanoTime() - lastDropTime > 500000000) spawnFireDrop();

		Iterator<Rectangle> iter = firedrops.iterator();
		while (iter.hasNext()) {
			Rectangle fireDrop = iter.next();
			fireDrop.y -= 200 * Gdx.graphics.getDeltaTime();
			if (fireDrop.y + 50 < 0) iter.remove();
			if (fireDrop.y + 50 < rec_hero.y) {
				isDodged++;
				iter.remove();
			}
			if (fireDrop.overlaps(rec_hero)) {
				isDodged = 0;
				blastSound.play();
				iter.remove();
				batch.begin();
				batch.draw(blastImage,  rec_hero.x, rec_hero.y);
				batch.end();
			}
		}
	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void hide() {

	}

	@Override
	public void dispose() {
		fireImage.dispose();
		heroImage.dispose();
		bg_music.dispose();
		blastSound.dispose();
		bg_img.dispose();
		blastImage.dispose();
	}
	@Override
	public void show() {
		bg_music.play();
	}
}