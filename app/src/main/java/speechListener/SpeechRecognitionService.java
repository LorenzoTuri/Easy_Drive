package speechListener;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import com.lorenzo.germana.easydrive.EasyDrive;

import java.io.File;
import java.io.IOException;

import edu.cmu.pocketsphinx.Assets;
import edu.cmu.pocketsphinx.SpeechRecognizer;
import static edu.cmu.pocketsphinx.SpeechRecognizerSetup.defaultSetup;

public class SpeechRecognitionService extends Service {
	private String MyTag = "SpeechRecognitionService";

	SpeechRecognitionListener speechRecognitionListener;
	SpeechRecognizer speechRecognizer;

	public int onStartCommand (Intent intent, int flags, int startId) {
		new AsyncTask<Void, Void, Exception>() {
			@Override
			protected Exception doInBackground(Void... params) {
				try {
					Assets assets = new Assets(EasyDrive.getContext());
					File assetDir = assets.syncAssets();
					setupRecognizer(assetDir);
				} catch (IOException e) {
					return e;
				}
				return null;
			}
		}.execute();

		Log.i(MyTag,"Service has been created");
		return START_STICKY;
	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	private void setupRecognizer(File assetsDir) throws IOException {
		speechRecognizer = defaultSetup()
				.setAcousticModel(new File(assetsDir, "en-us-ptm"))
				.setDictionary(new File(assetsDir, "provadict.dict"))
				.setKeywordThreshold(1e-40f)
						// Use context-independent phonetic search, context-dependent is too slow for mobile
				.setBoolean("-allphone_ci", true)
				.getRecognizer();

		speechRecognitionListener = new SpeechRecognitionListener(this);
		speechRecognitionListener.setRecognizer(speechRecognizer);

		speechRecognizer.addListener(speechRecognitionListener);

		File menuGrammar = new File(assetsDir, "grammar/BasicCommand.gram");
		speechRecognizer.addGrammarSearch(speechRecognitionListener.BASIC_SEARCH, menuGrammar);

		speechRecognitionListener.switchSearch(speechRecognitionListener.BASIC_SEARCH);
		Log.i(MyTag,"Setup completed");
	}

	public void onDestroy(){
		if (speechRecognizer != null){
			speechRecognizer.cancel();
			speechRecognizer.shutdown();
		}
		Log.i(MyTag,"Service has been destroyed");
		super.onDestroy();
	}
}
