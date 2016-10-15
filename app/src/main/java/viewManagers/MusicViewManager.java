package viewManagers;

import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.SeekBar;

import com.lorenzo.germana.easydrive.MainActivity;
import com.lorenzo.germana.easydrive.R;

import eventListeners.OnClickListenerPlayer;
import eventListeners.OnTouchDesignButton;

/**
 * Created by loren on 13/04/2016.
 */
public class MusicViewManager {
    ViewGroup root;
    ViewGroup menu;
    ViewGroup musicplayercommands;
    ViewGroup volume;

    public MusicViewManager(ViewGroup root, ViewGroup menu, ViewGroup musicplayercommands,ViewGroup volume){
        this.root = root;
        this.menu = menu;
        this.musicplayercommands = musicplayercommands;
        this.volume = volume;

        View ButtonPlayPause = musicplayercommands.findViewById(R.id.playButton);
        View ButtonNext = musicplayercommands.findViewById(R.id.forwardButton);
        View ButtonPrevious = musicplayercommands.findViewById(R.id.backwardButton);
		View ButtonShuffle = musicplayercommands.findViewById(R.id.shuffleButton);
	    View ButtonRepeat = musicplayercommands.findViewById(R.id.replayButton);

        ButtonPlayPause.setOnClickListener(new OnClickListenerPlayer("togglepause"));
        ButtonPlayPause.setOnTouchListener(new OnTouchDesignButton());

        ButtonNext.setOnClickListener(new OnClickListenerPlayer("next"));
        ButtonNext.setOnTouchListener(new OnTouchDesignButton());

        ButtonPrevious.setOnClickListener(new OnClickListenerPlayer("previous"));
        ButtonPrevious.setOnTouchListener(new OnTouchDesignButton());

	    ButtonShuffle.setOnClickListener(new OnClickListenerPlayer("shuffle"));
	    ButtonShuffle.setOnTouchListener(new OnTouchDesignButton());

	    ButtonRepeat.setOnClickListener(new OnClickListenerPlayer("repeat"));
	    ButtonRepeat.setOnTouchListener(new OnTouchDesignButton());

	    View volumeIcon = volume.findViewById(R.id.volumeIcon);
	    final SeekBar volumeBar = (SeekBar)volume.findViewById(R.id.volumeBar);

	    volumeIcon.setOnClickListener(new View.OnClickListener() {
		    public void onClick(View v) {
			    if (volumeBar.getVisibility() == View.VISIBLE) volumeBar.setVisibility(View.GONE);
			    else volumeBar.setVisibility(View.VISIBLE);
		    }
	    });
	    volumeBar.setMax(MainActivity.musicManager.getMaxVolume());
	    volumeBar.setProgress(MainActivity.musicManager.getCurrentVolume());
	    volumeBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
		    @Override
		    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
			    MainActivity.musicManager.setVolume(progress);
		    }
		    public void onStartTrackingTouch(SeekBar seekBar) {}
		    public void onStopTrackingTouch(SeekBar seekBar) {}
	    });
    }

    public View getView(){
        return root;
    }

    public View getMenu(){
        return menu;
    }
}