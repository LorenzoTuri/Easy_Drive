package incomingCall;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

import Interfaces.Destroyable;

/**
 * Questa classe si occupa di intercettare il segnale di chiamata e di visualizzare una particolare schermata.
 */
public class IncomingCallReceiver extends BroadcastReceiver implements Destroyable{
    private String MyTag = "IncomingCallReceiver";
    private Context context;

    public IncomingCallReceiver(Context context){
        this.context = context;
        context.registerReceiver(this, new IntentFilter("android.intent.action.PHONE_STATE"));
    }
    @Override
    public void onReceive(Context context, Intent intent)
    {
        String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);
        Log.i(MyTag,"Broadcast from call received. State: "+state);

        if (state.equals(TelephonyManager.EXTRA_STATE_RINGING)){
            //creo un intent e ne setto alcuni parametri. l'activity chiamata si occuperà di gestire le
            //varie funzioni della chiamata
            Intent newActivity = new Intent(context, IncomingCallHandler.class);
            newActivity.putExtra("CONTACT_NUMBER",intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER));
            newActivity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(newActivity);
        }
    }

	@Override
	public void onDestroy() {
		context.unregisterReceiver(this);
	}
}