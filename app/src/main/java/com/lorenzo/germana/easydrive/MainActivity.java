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

    public static MapsViewManager mapViewManager;
    public static MusicViewManager musicViewManager;
    public static MessageViewManager messageViewManager;

    public static TopMenuManager topMenuManager;

	public static MapsManager mapsManager;
    public static MusicManager musicManager;

    IncomingCallReceiver phoneReceiver;

    Intent serviceIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        Log.i(MyTag, "I'm registering the calls receiver");
        phoneReceiver = new IncomingCallReceiver();
        registerReceiver(phoneReceiver, new IntentFilter("android.intent.action.PHONE_STATE"));

        Log.i(MyTag, "I'm creating the MapsViewManager");
	    mapsManager = new MapsManager();
        musicManager = new MusicManager();
	    LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        ViewGroup mapsView = (ViewGroup)inflater.inflate(R.layout.tabmaps, null);
        ViewGroup mapsmenu = (ViewGroup)inflater.inflate(R.layout.tabmapsmenu, null);
        mapViewManager = new MapsViewManager(mapsView, mapsmenu);

        Log.i(MyTag,"I'm creating the MusicViewManager");
        ViewGroup musicView = (ViewGroup)inflater.inflate(R.layout.tabmusic, null);
        ViewGroup musicmenu = (ViewGroup)inflater.inflate(R.layout.tabmusicmenu, null);
        ViewGroup musicbuttons = (ViewGroup)findViewById(R.id.ButtonContainer);
        ViewGroup volume = (ViewGroup)findViewById(R.id.Volume);
        musicViewManager = new MusicViewManager(musicView,musicmenu,musicbuttons,volume);

        Log.i(MyTag,"I'm creating the MessageViewManager");
        ViewGroup messagesView = (ViewGroup)inflater.inflate(R.layout.tabmessage, null);
        ViewGroup messagesmenu = (ViewGroup)inflater.inflate(R.layout.tabmessagemenu, null);
        messageViewManager = new MessageViewManager(messagesView,messagesmenu);

        Log.i(MyTag,"I'm creating the TopMenuManager");
        ViewGroup topmenucontainer = (ViewGroup)findViewById(R.id.TopMenuContainer);
        ViewGroup elementcontainer = (ViewGroup)findViewById(R.id.ElementContainer);
        ViewGroup menuelementscontainer = (ViewGroup)findViewById(R.id.OperationContainer);
        topMenuManager = new TopMenuManager(topmenucontainer,elementcontainer, menuelementscontainer);

        Log.i(MyTag, "Initializing the maps function (map creation)");
        //avvio le funzioni di google maps
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(mapsManager);

        new Runnable(){
            public void run() {
                Log.i(MyTag,"I'm starting the speech recognition service");
                serviceIntent = new Intent(EasyDrive.getContext(), SpeechRecognitionService.class);
                startService(serviceIntent);
            }
        }.run();
    }

    @Override
    public void onDestroy(){
        Log.i(MyTag,"I'm going to be stopped");
        super.onDestroy();
        stopService(serviceIntent);
        unregisterReceiver(phoneReceiver);
    }
}