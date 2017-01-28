package eventListeners;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.lorenzo.germana.easydrive.EasyDrive;
import com.lorenzo.germana.easydrive.MainActivity;
import com.lorenzo.germana.easydrive.R;

/**
 * Created by loren on 13/04/2016.
 */
public class OnClickListenerPlayer implements View.OnClickListener {
	private String MyTag = "OnClickListenerPlayer";
    String command;
	MainActivity activity;

    public OnClickListenerPlayer(String command, MainActivity activity) {
        this.command = command;
	    this.activity = activity;
    }

    public void onClick(View v) {
	    //used to see if a layout change is needed
	    boolean isActive = false;
		switch (command){
			case "togglepause":
				activity.musicSrv.togglePause();
				if (activity.musicSrv.isPlaying()) isActive = true;
				break;
			case "next":
				activity.musicSrv.nextSong();
				break;
			case "previous":
				activity.musicSrv.previousSong();
				break;
			case "shuffle":
				if (activity.musicSrv.isShuffling()) isActive = true;
				activity.musicSrv.toggleShuffle();
				break;
			case "repeat":
				if (activity.musicSrv.isLooping()) isActive = true;
				activity.musicSrv.toggleRepeat();
				break;
		}
	    if (isActive){
		    v.setBackgroundDrawable((activity.getResources().getDrawable(R.drawable.roundshapeactive)));
	    }
    }
}