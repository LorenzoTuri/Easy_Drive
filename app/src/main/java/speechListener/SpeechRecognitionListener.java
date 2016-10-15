package speechListener;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import edu.cmu.pocketsphinx.Hypothesis;
import edu.cmu.pocketsphinx.SpeechRecognizer;

/**
 * Created by loren on 02/05/2016.
 */
public class SpeechRecognitionListener implements edu.cmu.pocketsphinx.RecognitionListener {
	private String MyTag = "SpeechRecognitionListener";

	SpeechRecognizer speechRecognizer;
	Context context;

	String CurrentSearch;
	/* Named searches allow to quickly reconfigure the decoder */
	public final String BASIC_SEARCH = "top_menu_listening";

	public SpeechRecognitionListener(Context context){
		this.context = context;
		CurrentSearch = BASIC_SEARCH;
	}

	@Override
	public void onBeginningOfSpeech() {
		Log.i(MyTag,"Beginning of the speech found");
	}


	@Override
	public void onEndOfSpeech() {
		Log.i(MyTag,"End of the speech found");
	}

	@Override
	public void onPartialResult(Hypothesis hypothesis) {
		if (hypothesis == null || hypothesis.getHypstr().equals("")) return;

		String text = hypothesis.getHypstr();
		if(CurrentSearch.equals(BASIC_SEARCH)){
			if (text.contains("MapsCommand")){
				Log.i(MyTag, "Recognized: " + text + ", moving on maps tab");
				switchSearch(BASIC_SEARCH);
			}
			else if (text.contains("MusicCommand")){
				Log.i(MyTag, "Recognized: " + text + ", moving on music tab");
				switchSearch(BASIC_SEARCH);
			}
			else if (text.contains("MessageCommand")){
				Log.i(MyTag, "Recognized: " + text + ", moving on message tab");
				switchSearch(BASIC_SEARCH);
			}
			else if (text.contains("CallCommand")){
				Log.i(MyTag, "Recognized: " + text + ", moving on call tab");
				switchSearch(BASIC_SEARCH);
			}
			else if (text.contains("CancelCommand")){
				Log.i(MyTag, "Recognized: " + text + ", command rejected");
				switchSearch(BASIC_SEARCH);
			}
		}
	}

	@Override
	public void onError(Exception e) {Log.e(MyTag,e.getMessage());}

	@Override
	public void onResult(Hypothesis hypothesis) {
		if (hypothesis != null) {
			String text = hypothesis.getHypstr();
			Log.i(MyTag,"Final result:"+text);
		}
	}

	@Override
	public void onTimeout() {}

	public void switchSearch(String searchName) {
		speechRecognizer.stop();
		CurrentSearch = searchName;
		speechRecognizer.startListening(searchName);
	}

	public void setRecognizer(SpeechRecognizer speechRecognizer){this.speechRecognizer = speechRecognizer;}
}
