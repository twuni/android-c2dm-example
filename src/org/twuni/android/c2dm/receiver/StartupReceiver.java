package org.twuni.android.c2dm.receiver;

import org.twuni.android.c2dm.service.MessageService;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class StartupReceiver extends BroadcastReceiver {

	@Override
	public void onReceive( Context context, Intent intent ) {
		Log.d( getClass().getSimpleName(), "Starting message service." );
		context.startService( new Intent( context, MessageService.class ) );
	}

}
