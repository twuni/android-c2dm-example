package org.twuni.android.c2dm.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class MessageReceiver extends BroadcastReceiver {

	@Override
	public void onReceive( Context context, Intent intent ) {
		Log.d( getClass().getSimpleName(), "Received C2DM Message." );
		Bundle extras = intent.getExtras();
		Log.d( getClass().getSimpleName(), "{" );
		for( String key : extras.keySet() ) {
			Log.d( getClass().getSimpleName(), String.format( "  \"%s\": \"%s\"", key, extras.get( key ) ) );
		}
		Log.d( getClass().getSimpleName(), "}" );
	}

}
