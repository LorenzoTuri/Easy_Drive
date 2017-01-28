package com.lorenzo.germana.easydrive;

import android.app.Service;
import android.content.ContentUris;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;
import android.os.PowerManager;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.SeekBar;

import Music.Song;
import Music.SongList;

/**
 * Created by loren on 01/11/2016.
 */
public class MusicPlayerService extends Service implements
		MediaPlayer.OnPreparedListener, MediaPlayer.OnErrorListener,
		MediaPlayer.OnCompletionListener {
	private MediaPlayer player;
	private SongList songs;
	private int songPos;
	private boolean shuffle;
	private boolean loop;

	private final IBinder musicBind = new MusicBinder();

	@Override
	public void onCreate() {
		super.onCreate();
		songPos = 0;
		shuffle = false;
		loop = true;
		player = new MediaPlayer();
		initPlayer();
	}

	private void initPlayer(){
		player.setWakeMode(getApplicationContext(),PowerManager.PARTIAL_WAKE_LOCK);
		player.setAudioStreamType(AudioManager.STREAM_MUSIC);

		player.setOnPreparedListener(this);
		player.setOnCompletionListener(this);
		player.setOnErrorListener(this);
	}

	public void setSongList(SongList songs){
		this.songs = songs;
		songPos = 0;
	}

	public void setSong(int n){
		if (n < songs.size()) songPos = n;
	}

	public void nextSong(){
		onCompletion(player);
	}

	public void previousSong(){
		songPos--;
		playSong();
	}

	public void togglePause(){
		if (player.isPlaying()) player.pause();
		else player.start();
	}

	public void toggleShuffle(){
		shuffle = !shuffle;
	}

	public void toggleRepeat(){
		loop = !loop;
	}

	public boolean isShuffling(){ return shuffle; }

	public boolean isLooping(){ return loop; }

	public boolean isPlaying(){ return player.isPlaying(); }

	public void playSong(){
		player.reset();
		Song playSong = songs.get(songPos);
		long currSong = playSong.getId();

		Uri trackUri = ContentUris.withAppendedId(android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,currSong);
		try{
			player.setDataSource(getApplicationContext(), trackUri);
		}
		catch(Exception e){
			Log.e("MUSIC SERVICE", "Error setting data source", e);
			return;
		}
		player.prepareAsync();
	}

	public class MusicBinder extends Binder {
		MusicPlayerService getService() {
			return MusicPlayerService.this;
		}
	}
	@Nullable
	@Override
	public IBinder onBind(Intent intent) {
		return musicBind;
	}
	@Override
	public boolean onUnbind(Intent intent){
		player.stop();
		player.release();
		return false;
	}

	@Override
	public void onCompletion(MediaPlayer mp) {
		songPos++;
		if (shuffle) songPos = (int) Math.random()%(songs.size()-1);
		if (loop) {
			songPos = songPos%songs.size();
		} else if (songPos>=songs.size()) return;
		playSong();
	}

	@Override
	public boolean onError(MediaPlayer mp, int what, int extra) {
		String mess = "";
		switch (what){
			case MediaPlayer.MEDIA_ERROR_UNKNOWN: mess = "media error unknown"; break;
			case MediaPlayer.MEDIA_ERROR_SERVER_DIED: mess = "media error server died";
		}
		switch (extra){
			case MediaPlayer.MEDIA_ERROR_IO: mess+=" ,media error IO";break;
			case MediaPlayer.MEDIA_ERROR_MALFORMED: mess+=", media error malformed";break;
			case MediaPlayer.MEDIA_ERROR_UNSUPPORTED: mess+=", media error unsupported";break;
			case MediaPlayer.MEDIA_ERROR_TIMED_OUT: mess+=", media error timed out"; break;
		}
		Log.v("Service music",mess);
		return false;
	}

	@Override
	public void onPrepared(MediaPlayer mp) {
		mp.start();
	}
}
