package info.plocharz.rpggame;

import java.io.IOException;
import java.io.InputStream;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.Disposable;

public class Game implements InputProcessor, Disposable {
	private Map map = null;
	private int touch_start;
	private int last_x;
	private int last_y;
	private int current_x;
	private int current_y;
	private float scale;
	
	public Game(float scale) {
		this.scale = scale;
	}

	void load_level(FileHandle internal) throws IOException {
		map = Map.from_file(internal);
	}
	
	public void draw(Camera camera) {
		float delta_x = ((float)(-this.current_x+this.last_x)/this.scale);
		float delta_y = ((float)(this.current_y-this.last_y)/this.scale);
		if(delta_x != 0 || delta_y != 0)
			Gdx.app.log(RPGGame.TAG, "Move by" +  delta_x + delta_y);
		this.last_x = this.current_x;
		this.last_y = this.current_y;
		camera.translate(delta_x, delta_y, (float)0.0);
		map.draw(camera);
	}

	@Override
	public boolean keyDown(int keycode) {
		Gdx.app.log(RPGGame.TAG, "Key down " + keycode);
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		Gdx.app.log(RPGGame.TAG, "Key up " + keycode);
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		Gdx.app.log(RPGGame.TAG, "Key typed " + character);
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		this.last_x = screenX;
		this.last_y = screenY;
		this.current_x = screenX;
		this.current_y = screenY;
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
//		Gdx.app.log(RPGGame.TAG, String.format("drag %d %d %d", screenX, screenY, pointer));
		this.current_x = screenX;
		this.current_y = screenY;
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}

	@Override
	public void dispose() {
		if(map != null){
			map.dispose();
			map = null;
		}
	}
}
