package com.lorenzo.germana.easydrive;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by loren on 01/11/2016.
 */
public class MusicPlayerService extends Service {
	@Nullable
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
}
