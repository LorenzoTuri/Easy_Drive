package eventListeners;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.lorenzo.germana.easydrive.EasyDrive;

/**
 * Created by loren on 13/04/2016.
 */
public class OnClickListenerPlayer implements View.OnClickListener {
	private String MyTag = "OnClickListenerPlayer";
    String command;

    public OnClickListenerPlayer(String command) {
        this.command = command;
    }

    public void onClick(View v) {
	    Log.i(MyTag,"Received a click. Sending intent with "+command);
	    Intent i = new Intent("com.android.music.musicservicecommand");
        i.putExtra("command", command);
        EasyDrive.getContext().sendBroadcast(i);
    }
	/* comandi possibili
		public static final String SERVICECMD = "com.android.music.musicservicecommand";
		public static final String CMDNAME = "command";
		public static final String CMDTOGGLEPAUSE = "togglepause";
		public static final String CMDSTOP = "stop";
		public static final String CMDPAUSE = "pause";
		public static final String CMDPLAY = "play";
		public static final String CMDPREVIOUS = "previous";
		public static final String CMDNEXT = "next";
		*/
}