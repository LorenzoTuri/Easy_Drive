package Music;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Created by loren on 01/11/2016.
 */
public class SongList extends ArrayList<Song> {
	public Comparator comparator = new Comparator() {
		@Override
		public int compare(Object lhs, Object rhs) {
			if (lhs==null || rhs==null) throw new NullPointerException();
			if (!(lhs instanceof Song) || !(rhs instanceof Song)) throw new ClassCastException();
			String s1 = ((Song) lhs).getTitle();
			String s2 = ((Song) rhs).getTitle();
			return s1.compareTo(s2);
		}
	};
}
