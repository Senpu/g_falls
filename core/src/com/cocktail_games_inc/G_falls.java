package com.cocktail_games_inc;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by nepri_000 on 06.01.2017.
 */

public class G_falls extends Game {
    SpriteBatch batch;
    BitmapFont font;
    @Override
    public void create() {
        batch = new SpriteBatch();
        font = new BitmapFont();
        this.setScreen(new MainMenuScreen(this));

    }
    @Override
    public void render() {
        super.render();
    }
    @Override
    public void dispose() {
        super.dispose();
        batch.dispose();
        font.dispose();
    }
}
