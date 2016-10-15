package com.lorenzo.germana.easydrive;

import android.app.Application;
import android.content.Context;

/**
 * Created by loren on 10/05/2016.
 */
public class EasyDrive extends Application {
	static private Context c;
	public void onCreate(){
		super.onCreate();
		c = getApplicationContext();
	}
	static public Context getContext(){
		return c;
	}
}
