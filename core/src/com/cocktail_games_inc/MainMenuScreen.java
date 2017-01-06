package com.cocktail_games_inc;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

import static com.badlogic.gdx.utils.Align.center;

/**
 * Created by nepri_000 on 06.01.2017.
 */

public class MainMenuScreen implements Screen {
    Texture menu_bg_image;
    final G_falls game;
    OrthographicCamera camera;

    public MainMenuScreen (G_falls gam) {
        this.game = gam;
        menu_bg_image = new Texture("menu_bg_image.jpg");
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 480, 600);
    }
    @Override
    public void show() {

    }
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();

        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        game.batch.draw(menu_bg_image, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        game.font.draw(game.batch, "Dodge the bombs!", 180, 450);
        game.font.draw(game.batch, "Touch to start", 193, 400);
        game.batch.end();

        if (Gdx.input.isTouched()) {
            game.setScreen(new GameScreen(game));
            dispose();
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

    }
}
