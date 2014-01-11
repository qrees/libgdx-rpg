package info.plocharz.rpggame;

import java.io.IOException;
import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.XmlReader;
import com.badlogic.gdx.utils.XmlReader.Element;

public class Map implements Disposable {
	HashMap<String, Location> locations;
	private TiledMap tiled_map;
	private OrthogonalTiledMapRenderer renderer;
	
	public static Map from_file(FileHandle internal) throws IOException {
		Map map = new Map();
		if (internal.name().endsWith(".tmx")) {
			map.tiled_map = new TmxMapLoader().load(internal.path());
			map.renderer = new OrthogonalTiledMapRenderer(map.tiled_map, 1/16f);
			return map;
		}
		
		if(true)
			throw new Error("Not yet implemented");
		
		XmlReader reader = new XmlReader();
		Element root = reader.parse(internal);
		for(int i = 0; i < root.getChildCount(); i++){
			Element child = root.getChild(i);
			Gdx.app.log(RPGGame.TAG, child.getName());
			map.locations = Map.create_locations(child);
		}
		return map;
	}
	
	private static HashMap<String, Location> create_locations(Element locations_element) {
		HashMap<String, Location> locations = new HashMap<String, Location>();
		for(int i = 0; i < locations_element.getChildCount(); i++){
			Element location_element = locations_element.getChild(i);
			assert location_element.getName().equals("location");
			String name = location_element.get("name");
			Location location = Location.from_element(location_element);
			locations.put(name, location);
		}
		return locations;
	}
	
	public void draw(Camera camera){
		assert (camera instanceof OrthographicCamera);
		renderer.setView((OrthographicCamera) camera);
		renderer.render();
	}


	@Override
	public void dispose() {
		renderer.dispose();
		tiled_map.dispose();
	}
}
