package info.plocharz.rpggame;

import java.util.HashMap;

import info.plocharz.rpggame.map.tiles.Tile;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.XmlReader.Element;

public class Location {
	private SparseArray<Tile> tiles;
	private int width;
	private int height;
	
	public Location(){
		tiles = new SparseArray<Tile>();
	}
	
	private void setTile(int x, int y, Tile tile){
		tiles.setValueAt(y+width+x, tile);

		Gdx.app.log(RPGGame.TAG, String.format("Set tile %d, %d", x, y));
	}
	
	public static Location from_element(Element location_element) {
		Location loc = new Location();
		
		String tiles = location_element.getText();
		int width = location_element.getInt("width");
		int height = location_element.getInt("height");
		
		loc.width = width;
		loc.height = height;
		
		String [] rows = tiles.split("\n");
		assert rows.length == height;

		int cur_row = 0;
		for(String row: rows){
			assert row.length() == width;
			for(int cur_column = 0; cur_column < row.length(); cur_column++){
				char c = row.charAt(cur_column);
				
				Tile tile = null;
				try {
					tile = (Tile) Tile.TILE_TYTPES.get(String.valueOf(c)).newInstance();
				} catch (InstantiationException e) {
					e.printStackTrace();
					System.exit(1);
				} catch (IllegalAccessException e) {
					e.printStackTrace();
					System.exit(1);
				}
				loc.setTile(cur_column, cur_row, tile);
			}
			cur_row += 1;
		}
		return loc;
	}
}
