package managers;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.session.MediaController;
import android.media.session.MediaSession;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.SeekBar;

import com.lorenzo.germana.easydrive.EasyDrive;
import com.lorenzo.germana.easydrive.MainActivity;
import com.lorenzo.germana.easydrive.R;

/**
 * Created by loren on 18/05/2016.
 */
public class MusicManager {
	//verificare che MediaPlayer sia l'oggetto giusto da utilizzare
	AudioManager audio;
	public MusicManager(){
		audio = (AudioManager)EasyDrive.getContext().getSystemService(Context.AUDIO_SERVICE);
	}

	public void setVolumeBar(ViewGroup volumeBar){
		ImageButton button = (ImageButton)volumeBar.findViewById(R.id.volumeIcon);
		final SeekBar seekBar = (SeekBar)volumeBar.findViewById(R.id.volumeBar);
		button.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if (seekBar.getVisibility() == View.VISIBLE) seekBar.setVisibility(View.GONE);
				else seekBar.setVisibility(View.VISIBLE);
			}
		});
		seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
				setVolume(progress);
			}
			public void onStartTrackingTouch(SeekBar seekBar) {}
			public void onStopTrackingTouch(SeekBar seekBar) {}
		});
	}

	public void setVolume(int volume){
		if (volume<0)volume = 0;
		//verificare il volume massimo inseribile, da mettere al posto di 1
		if (volume>audio.getStreamMaxVolume(AudioManager.STREAM_MUSIC))volume = audio.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
		audio.setStreamVolume(AudioManager.STREAM_MUSIC,volume,AudioManager.FLAG_VIBRATE);
	}
	public int getMaxVolume(){return audio.getStreamMaxVolume(AudioManager.STREAM_MUSIC);}
	public int getCurrentVolume(){return audio.getStreamVolume(AudioManager.STREAM_MUSIC);}
}
