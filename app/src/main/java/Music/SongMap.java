package Music;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Created by loren on 01/11/2016.
 */
public class SongMap extends TreeMap<String,SongList> {
	public Comparator comparator;
	public SongMap(Comparator comparator){
		this.comparator = comparator;
	}
}
