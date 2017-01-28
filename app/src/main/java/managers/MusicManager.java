package managers;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.media.AudioManager;
import android.net.Uri;
import android.provider.MediaStore;

import java.util.Collections;

import Interfaces.Destroyable;
import Music.AlbumComparator;
import Music.ArtistComparator;
import Music.Song;
import Music.SongList;
import Music.SongMap;

/**
 * Created by loren on 18/05/2016.
 */
public class MusicManager implements Destroyable{
	Context context;

	AudioManager audio;
	SongList songList;
	SongMap songByArtist;
	SongMap songByAlbum;

	public MusicManager(Context context){
		this.context = context;
		audio = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
		songList = new SongList();
		songByAlbum = new SongMap(new AlbumComparator());
		songByArtist = new SongMap(new ArtistComparator());

		initializeLists();
	}

	public void setVolume(int volume){
		volume = constrain(0, volume, getMaxVolume());
		audio.setStreamVolume(AudioManager.STREAM_MUSIC, volume, AudioManager.FLAG_VIBRATE);
	}
	public int getMaxVolume(){return audio.getStreamMaxVolume(AudioManager.STREAM_MUSIC);}
	public int getCurrentVolume(){return audio.getStreamVolume(AudioManager.STREAM_MUSIC);}

	public void onDestroy() {

	}

	private int constrain(int min, int volume, int max){
		if (volume < min) return min;
		else if(volume > max) return max;
		return volume;
	}

	public void initializeLists(){
		ContentResolver musicResolver = context.getContentResolver();
		Uri musicUri = android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
		Cursor musicCursor = musicResolver.query(musicUri, null, null, null, null);
		if(musicCursor!=null && musicCursor.moveToFirst()){
			//get columns
			int titleColumn = musicCursor.getColumnIndex(MediaStore.Audio.Media.TITLE);
			int idColumn = musicCursor.getColumnIndex(MediaStore.Audio.Media._ID);
			int artistColumn = musicCursor.getColumnIndex(MediaStore.Audio.Media.ARTIST);
			int albumColumn = musicCursor.getColumnIndex(MediaStore.Audio.Media.ALBUM);
			//add songs to list
			do {
				long thisId = musicCursor.getLong(idColumn);
				String thisTitle = musicCursor.getString(titleColumn);
				String thisArtist = musicCursor.getString(artistColumn);
				String thisAlbum = musicCursor.getString(albumColumn);
				songList.add(new Song(thisId, thisTitle, thisArtist,thisAlbum));
			}
			while (musicCursor.moveToNext());
		}
		for (Song song: songList) {
			if (songByAlbum.get(song.getAlbum()) == null) songByAlbum.put(song.getAlbum(),new SongList());
			if (songByArtist.get(song.getArtist()) == null) songByArtist.put(song.getArtist(),new SongList());
			songByArtist.get(song.getArtist()).add(song);
			songByAlbum.get(song.getAlbum()).add(song);
		}
		Collections.sort(songList,songList.comparator);
		for(Object key:songByAlbum.keySet()) Collections.sort(songByAlbum.get(key),songByAlbum.comparator);
		for(Object key:songByArtist.keySet()) Collections.sort(songByArtist.get(key),songByArtist.comparator);
	}

	public SongList getSongList(){
		return songList;
	}
}
