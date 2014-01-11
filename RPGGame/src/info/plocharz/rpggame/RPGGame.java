package info.plocharz.rpggame;

import java.io.IOException;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class RPGGame implements ApplicationListener {
	public static final String TAG = "RPG";
	
	private OrthographicCamera camera;
//	private SpriteBatch batch;
//	private Texture texture;
//	private Sprite sprite;
	
	private Game game;

	private OrthoCamController cameraController;
	private ShapeRenderer sr;
	
	@Override
	public void create() {		
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		
		float scale = w/30f;
		camera = new OrthographicCamera(30, 30 * h/w);
		
        cameraController = new OrthoCamController(camera);
//        Gdx.input.setInputProcessor(cameraController);
//		batch = new SpriteBatch();
		
//		texture = new Texture(Gdx.files.internal("data/libgdx.png"));
//		texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
//		
//		TextureRegion region = new TextureRegion(texture, 0, 0, 512, 275);
//		
//		sprite = new Sprite(region);
//		sprite.setSize(0.9f, 0.9f * sprite.getHeight() / sprite.getWidth());
//		sprite.setOrigin(sprite.getWidth()/2, sprite.getHeight()/2);
//		sprite.setPosition(-sprite.getWidth()/2, -sprite.getHeight()/2);
        
        sr = new ShapeRenderer();
        
		game = new Game(scale);
		Gdx.input.setInputProcessor(game);
		try {
			game.load_level(Gdx.files.internal("map/test01.tmx"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void dispose() {
//		batch.dispose();
//		texture.dispose();
	}

	public void circle(float x, float y, float radius){
		sr.circle(x, y, radius, (int) (radius*10));
	}
	
	@Override
	public void render() {		
		camera.update();
		Gdx.gl20.glClearColor(1, 1, 1, 1);
		Gdx.gl20.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		game.draw(camera);

//		Gdx.gl20.glEnable(GL20.GL_BLEND);
//		Gdx.gl20.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
//		sr.setProjectionMatrix(camera.combined);
//		sr.begin(ShapeType.Filled);
//		sr.setColor(0, 1, 1, 0.5f);
//		this.circle(0, 0, 10f);
//		sr.end();
		
//		batch.setProjectionMatrix(camera.combined);
//		batch.begin();
//		sprite.draw(batch);
//		batch.end();
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
}
