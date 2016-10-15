package dataContainer;

import com.lorenzo.germana.easydrive.R;

import java.util.HashMap;

/**
 * Created by loren on 18/05/2016.
 */
public class ImageIdContainer {
	HashMap<Integer,int[]> container = new HashMap<>();

	public final Integer COLOR_BLACK = 0;
	public final Integer COLOR_GREY = 1;
	public final Integer COLOR_WHITE = 2;

	public final Integer CALL_ICON = 0;
	public final Integer CALL_MADE = 1;
	public final Integer CALL_MISSED = 2;
	public final Integer CALL_RECEIVED = 3;
	public final Integer CONTACTS = 4;
	public final Integer DIRECTION_BUS = 5;
	public final Integer DIRECTION_CAR = 6;
	public final Integer DIRECTION_ICON = 7;
	public final Integer DIRECTION_WALK = 8;
	public final Integer DIRECTION_ARROW = 9;
	public final Integer MUSIC_FAST_FORWARD = 10;
	public final Integer MUSIC_FAST_REWIND = 11;
	public final Integer MUSIC_SKIP_NEXT = 12;
	public final Integer MUSIC_SKIP_PREVIOUS = 13;
	public final Integer MUSIC_VOLUME_DOWN = 14;
	public final Integer MUSIC_VOLUME_UP = 15;
	public final Integer MUSIC_VOLUME_MUTE = 16;
	public final Integer MUSIC_SHUFFLE = 17;
	public final Integer MUSIC_LOOP = 18;
	public final Integer MUSIC_REPEAT = 19;
	public final Integer MUSIC_REPEAT_ONE = 20;
	public final Integer MUSIC_PAUSE = 21;
	public final Integer MUSIC_PLAY = 22;
	public final Integer SEND = 23;
	public final Integer SENTIMENT_VERY_DISSATISFIED = 24;
	public final Integer THEME_ICON = 25;
	public final Integer MICROPHONE = 26;
	public ImageIdContainer(){
		int[] callicon = {R.drawable.call_icon_black,R.drawable.call_icon_grey,R.drawable.call_icon_white};
		int[] callmade = {R.drawable.call_made_black,R.drawable.call_made_grey,R.drawable.call_made_white};
		int[] callmissed = {R.drawable.call_missed_black,R.drawable.call_missed_grey,R.drawable.call_missed_white};
		int[] callreceived = {R.drawable.call_received_black,R.drawable.call_received_grey,R.drawable.call_received_white};
		int[] contacts = {R.drawable.contacts_black,R.drawable.contacts_grey,R.drawable.contacts_white};
		int[] direction_bus = {R.drawable.directions_bus_black,R.drawable.directions_bus_grey,R.drawable.directions_bus_white};
		int[] direction_car = {R.drawable.directions_car_black,R.drawable.directions_car_grey,R.drawable.directions_car_white};
		int[] direction_icon = {R.drawable.directions_icon_black,R.drawable.directions_icon_grey,R.drawable.directions_icon_white};
		int[] direction_walk = {R.drawable.directions_walk_black,R.drawable.directions_walk_grey,R.drawable.directions_walk_white};
		int[] fast_forward = {R.drawable.fast_forward_black,R.drawable.fast_forward_grey,R.drawable.fast_forward_white};
		int[] fast_rewind = {R.drawable.fast_rewind_black,R.drawable.fast_rewind_black,R.drawable.fast_rewind_black};
		int[] loop = {R.drawable.loop_black,R.drawable.loop_grey,R.drawable.loop_white};
		int[] navigation = {R.drawable.navigation_black,R.drawable.navigation_grey,R.drawable.navigation_white};
		int[] pause = {R.drawable.pause_black,R.drawable.pause_grey,R.drawable.pause_white};
		int[] play = {R.drawable.play_arrow_black,R.drawable.play_arrow_grey,R.drawable.play_arrow_white};
		int[] repeat = {R.drawable.repeat_icon_black,R.drawable.repeat_icon_grey,R.drawable.repeat_icon_white};
		int[] repeat_one = {R.drawable.repeat_one_black,R.drawable.repeat_one_grey,R.drawable.repeat_one_white};
		int[] send = {R.drawable.send_black,R.drawable.send_grey,R.drawable.send_white};
		int[] sentiment_very_dissatisfied = {R.drawable.sentiment_very_dissatisfied_black,R.drawable.sentiment_very_dissatisfied_grey,R.drawable.sentiment_very_dissatisfied_white};
		int[] shuffle = {R.drawable.shuffle_black,R.drawable.shuffle_grey,R.drawable.shuffle_white};
		int[] skip_next = {R.drawable.skip_next_black,R.drawable.skip_next_grey,R.drawable.skip_next_white};
		int[] skip_previous = {R.drawable.skip_previous_black,R.drawable.skip_previous_grey,R.drawable.skip_previous_white};
		int[] theme_icon = {R.drawable.theme_black,R.drawable.theme_grey,R.drawable.theme_white};
		int[] volume_down = {R.drawable.volume_down_black,R.drawable.volume_down_grey,R.drawable.volume_down_white};
		int[] volume_mute = {R.drawable.volume_mute_black,R.drawable.volume_mute_grey,R.drawable.volume_mute_white};
		int[] volume_up = {R.drawable.volume_up_black,R.drawable.volume_up_grey,R.drawable.volume_up_white};
		int[] microphone = {R.drawable.mic_black,R.drawable.mic_grey,R.drawable.mic_white};

		container.put( CALL_ICON , callicon);
		container.put( CALL_MADE , callmade);
		container.put( CALL_MISSED , callmissed);
		container.put( CALL_RECEIVED, callreceived);
		container.put( CONTACTS , contacts);
		container.put( DIRECTION_BUS , direction_bus);
		container.put( DIRECTION_CAR , direction_car);
		container.put( DIRECTION_ICON , direction_icon);
		container.put( DIRECTION_WALK , direction_walk);
		container.put( DIRECTION_ARROW , navigation);
		container.put( MUSIC_FAST_FORWARD , fast_forward);
		container.put( MUSIC_FAST_REWIND , fast_rewind);
		container.put( MUSIC_SKIP_NEXT , skip_next);
		container.put( MUSIC_SKIP_PREVIOUS , skip_previous);
		container.put( MUSIC_VOLUME_DOWN , volume_down);
		container.put( MUSIC_VOLUME_UP, volume_up);
		container.put( MUSIC_VOLUME_MUTE, volume_mute);
		container.put( MUSIC_SHUFFLE, shuffle);
		container.put( MUSIC_LOOP, loop);
		container.put( MUSIC_REPEAT, repeat);
		container.put( MUSIC_REPEAT_ONE, repeat_one);
		container.put( MUSIC_PAUSE, pause);
		container.put( MUSIC_PLAY, play);
		container.put( SEND, send);
		container.put( SENTIMENT_VERY_DISSATISFIED, sentiment_very_dissatisfied);
		container.put( THEME_ICON, theme_icon);
		container.put( MICROPHONE, microphone);
	}
	public int getImageID(int type, int color){
		if (color != 0 || color != 1 || color != 2){
			return container.get(type)[color];
		}
		else return -1;
	}
}
