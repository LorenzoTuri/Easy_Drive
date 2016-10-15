package incomingCall;

import android.app.Activity;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.lorenzo.germana.easydrive.R;

/**
 * Questa classe si occupa di gestire una chiamata, dopo che essa ha notificato di esser arrivata (vedi IncomingCallsReceiver).
 */
public class IncomingCallHandler extends Activity{
    private String MyTag = "IncomingCallHandler";
    tempPhoneStateListener listener;

    public void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.oncall_mainlayout);

        Log.i(MyTag,"Initializing received call activity");
        //imposto i dati dell'utente.
        //DA FARE: prendere nome e immagine dalla rubrica
        TextView contact = (TextView)findViewById(R.id.Contact_Info_Textview);
        String contactNumber = getIntent().getStringExtra("CONTACT_NUMBER");
        contact.setText(contactNumber);

        //imposto i clickhandler per i bottoni di risposta/rifiuto
        //DA FARE: trovare un modo per rispondere/rifutare le chiamate, possibilmente diverso dal trucco di
        //simulare un eventhandler sull'interfaccia di default. Se possibile nascondere l'interfaccia di default.
        Button answerButton = (Button)findViewById(R.id.ButtonAnswer);
        Button rejectButton = (Button)findViewById(R.id.ButtonReject);
        answerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //rispondi alla chiamata
            }
        });
        rejectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //rifiuta la chiamata
                //torna all'activity precedente
            }
        });

        //imposto i clickhandler per i bottoni di "utilita" durante la chiamata
        //DA FARE: trovare e inserire le funzioni adatte a farli funzionare
        Button tastieraButton = (Button)findViewById(R.id.ButtonTastieraNumerica);
        tastieraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //mostra la tastiera numerica
            }
        });
        Button vivavoceButton = (Button)findViewById(R.id.ButtonVivavoce);
        vivavoceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //attiva il vivavoce
            }
        });
        Button muteButton = (Button)findViewById(R.id.ButtonMuto);
        muteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //attiva il muto
            }
        });
        Button bluetoothDeviceButton = (Button)findViewById(R.id.ButtonBluetoothDevice);
        bluetoothDeviceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //attiva/disattiva il dispositivo bluetooth
            }
        });
        Button endCallButton = (Button)findViewById(R.id.ButtonEndCall);
        endCallButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //termina la chiamata
            }
        });

        //aggiungo un listener che permette di cambiare il layout di risposta

        Log.i(MyTag,"Inserting listener for call state");
        listener = new tempPhoneStateListener();
        ((TelephonyManager) getSystemService(TELEPHONY_SERVICE)).listen(listener,PhoneStateListener.LISTEN_CALL_STATE);
    }

    /**
     * questa funzione si occupa di visualizzare l'interfaccia con l'utilità, dopo che è stata regitrata
     * una risposta alla chiamata
     */
    void showCallInterface(){
        Log.i(MyTag,"Showing call interface");
        View previousMenu = findViewById(R.id.Call_Incoming_Menu);
        previousMenu.setVisibility(View.GONE);
        View nextMenu = findViewById(R.id.Call_Menu);
        nextMenu.setVisibility(View.VISIBLE);
        //creare un thread che gestisca il timer
    }

    /**
     * questa funzione si occupa di visualizzare l'interfaccia di fine chiamata, dopo che quest'ultima è terminata.
     */
    void showEndedInterface(){
        Log.i(MyTag,"Showing call_ended interface");
        //fermare il thread del timer
        View previousMenu = findViewById(R.id.Call_Menu);
        previousMenu.setVisibility(View.GONE);
        View nextMenu = findViewById(R.id.Call_Ended_Menu);
        nextMenu.setVisibility(View.VISIBLE);
        //creare un thread che dopo 2 secondi permetta di tornare all'activity precedente
    }

    /**
     * questa classe si occupa di ascoltare due particolari eventi che corrispondono alla chiamata risposta
     * e alla chiamata terminata. vengono poi chiamate fuzioni per cambiare il layout.
     */
    class tempPhoneStateListener extends PhoneStateListener{

        public void onCallStateChanged(int state, String incomingNumber){
            Log.i(MyTag,"Call state has changed");
            if (state == TelephonyManager.CALL_STATE_OFFHOOK){
                showCallInterface();
            }
            else if (state == TelephonyManager.CALL_STATE_IDLE){
                showEndedInterface();
                //faccio in modo che questo listener smetta di ascoltare
                ((TelephonyManager) getSystemService(TELEPHONY_SERVICE)).listen(listener,PhoneStateListener.LISTEN_NONE);
            }
        }
    }
}
