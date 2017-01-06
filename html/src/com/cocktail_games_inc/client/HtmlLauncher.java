package com.cocktail_games_inc.client;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;
import com.cocktail_games_inc.G_falls;

public class HtmlLauncher extends GwtApplication {

        @Override
        public GwtApplicationConfiguration getConfig () {
                return new GwtApplicationConfiguration(480, 600);
        }

        @Override
        public ApplicationListener createApplicationListener () {
                return new G_falls();
        }
}