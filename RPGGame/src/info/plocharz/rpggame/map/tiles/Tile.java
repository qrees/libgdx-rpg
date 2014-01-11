package info.plocharz.rpggame.map.tiles;


import java.util.HashMap;
import java.util.List;

public class Tile {
	public static HashMap<String, Class> TILE_TYTPES;
	
	static
    {
        TILE_TYTPES = new HashMap<String, Class>();
        TILE_TYTPES.put("W", Water.class);
        TILE_TYTPES.put("D", Desert.class);
    }
	
}
