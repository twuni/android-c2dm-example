package org.twuni.android.c2dm.receiver;

import org.twuni.android.c2dm.service.MessageService;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class ShutdownReceiver extends BroadcastReceiver {

	@Override
	public void onReceive( Context context, Intent intent ) {
		Log.d( getClass().getSimpleName(), "Stopping message service." );
		context.stopService( new Intent( context, MessageService.class ) );
	}

}
