package viewManagers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
    ViewGroup buttons;
    ViewGroup volume;

    public MusicViewManager(final MainActivity activity){
        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        root = (ViewGroup)inflater.inflate(R.layout.tabmusic, null);
        menu = (ViewGroup)inflater.inflate(R.layout.tabmusicmenu, null);
        buttons = (ViewGroup)activity.findViewById(R.id.ButtonContainer);
        volume = (ViewGroup)activity.findViewById(R.id.Volume);

        View ButtonPlayPause = buttons.findViewById(R.id.playButton);
        View ButtonNext = buttons.findViewById(R.id.forwardButton);
        View ButtonPrevious = buttons.findViewById(R.id.backwardButton);
		View ButtonShuffle = buttons.findViewById(R.id.shuffleButton);
	    View ButtonRepeat = buttons.findViewById(R.id.replayButton);

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
	    volumeBar.setMax(activity.musicManager.getMaxVolume());
	    volumeBar.setProgress(activity.musicManager.getCurrentVolume());
	    volumeBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
		    @Override
		    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
			    activity.musicManager.setVolume(progress);
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