package org.twuni.android.c2dm.receiver;

import org.twuni.android.c2dm.activity.MainActivity;

import android.R;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class MessageReceiver extends BroadcastReceiver {

	private void logExtras( Bundle extras ) {

		Log.d( getClass().getSimpleName(), "{" );

		for( String key : extras.keySet() ) {
			Log.d( getClass().getSimpleName(), String.format( "  \"%s\": \"%s\"", key, extras.get( key ) ) );
		}

		Log.d( getClass().getSimpleName(), "}" );

	}

	@Override
	public void onReceive( Context context, Intent intent ) {

		Bundle extras = intent.getExtras();

		logExtras( extras );

		showNotification( context, R.drawable.stat_notify_voicemail, "C2DM Test Message", extras.getString( "message" ), MainActivity.class );

	}

	public void showNotification( Context context, int iconResourceId, String title, String content, Class<? extends Activity> activityToLaunch ) {

		Notification notification = new Notification( iconResourceId, content, System.currentTimeMillis() );

		notification.flags |= Notification.FLAG_AUTO_CANCEL;
		notification.defaults |= Notification.DEFAULT_ALL;

		PendingIntent pendingIntent = PendingIntent.getActivity( context, 0, new Intent( context, activityToLaunch ), PendingIntent.FLAG_UPDATE_CURRENT );

		notification.setLatestEventInfo( context, title, content, pendingIntent );

		NotificationManager notificationManager = (NotificationManager) context.getSystemService( Context.NOTIFICATION_SERVICE );
		notificationManager.notify( 1, notification );

	}

}
