package org.twuni.android.c2dm.service;

import org.twuni.android.c2dm.R;

import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class MessageService extends Service {

	@Override
	public IBinder onBind( Intent intent ) {
		return null;
	}

	@Override
	public void onCreate() {

		super.onCreate();

		Intent registration = new Intent( "com.google.android.c2dm.intent.REGISTER" );

		registration.putExtra( "app", PendingIntent.getBroadcast( this, 0, new Intent(), PendingIntent.FLAG_UPDATE_CURRENT ) );
		registration.putExtra( "sender", getString( R.string.c2dm_sender ) );

		startService( registration );

	}

	@Override
	public void onDestroy() {

		super.onDestroy();

		Intent unregister = new Intent( "com.google.android.c2dm.intent.UNREGISTER" );

		unregister.putExtra( "app", PendingIntent.getBroadcast( this, 0, new Intent(), PendingIntent.FLAG_UPDATE_CURRENT ) );

		startService( unregister );

	}

}
