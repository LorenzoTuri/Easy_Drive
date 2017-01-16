package com.lorenzo.germana.easydrive;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.WindowManager;

import managers.MapsManager;
import managers.MusicManager;
import viewManagers.MapsViewManager;
import speechListener.SpeechRecognitionService;
import incomingCall.IncomingCallReceiver;
import viewManagers.MessageViewManager;
import viewManagers.MusicViewManager;
import viewManagers.TopMenuManager;

import com.google.android.gms.maps.SupportMapFragment;

public class MainActivity extends FragmentActivity {
    private String MyTag = "MainActivity";

    public MapsViewManager mapViewManager;
    public MusicViewManager musicViewManager;
    public MessageViewManager messageViewManager;
    public TopMenuManager topMenuManager;

    public MusicManager musicManager;

    IncomingCallReceiver phoneReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

	    Log.i(MyTag, "I'm creating the MusicManager");
	    musicManager = new MusicManager(this);
        /* MAYBE NOT NECESSARY ANYMORE?
        Log.i(MyTag, "I'm registering the calls receiver");
        phoneReceiver = new IncomingCallReceiver(this);
		*/

        Log.i(MyTag, "I'm creating the MapsViewManager");
        mapViewManager = new MapsViewManager(this);
        Log.i(MyTag,"I'm creating the MusicViewManager");
        musicViewManager = new MusicViewManager(this);
        Log.i(MyTag,"I'm creating the MessageViewManager");
        messageViewManager = new MessageViewManager(this);
        Log.i(MyTag,"I'm creating the TopMenuManager");
        topMenuManager = new TopMenuManager(this);


	    /* DEACTIVATED FOR THE TIME BEING
        new Runnable(){
            Intent serviceIntent;
            public void run() {
                Log.i(MyTag,"I'm starting the speech recognition service");
                serviceIntent = new Intent(EasyDrive.getContext(), SpeechRecognitionService.class);
                startService(serviceIntent);
            }
        }.run();*/
    }

    @Override
    public void onDestroy(){
	    super.onDestroy();

	    Log.i(MyTag, "I'm Stopping");
        //stopService(serviceIntent);
	    phoneReceiver.onDestroy();
	    mapViewManager.onDestroy();
	    musicManager.onDestroy();
    }
}