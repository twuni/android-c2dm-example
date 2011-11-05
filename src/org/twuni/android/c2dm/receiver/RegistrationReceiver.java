package org.twuni.android.c2dm.receiver;

import java.io.IOException;
import java.util.Arrays;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.twuni.android.c2dm.R;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.provider.Settings.Secure;
import android.util.Log;

public class RegistrationReceiver extends BroadcastReceiver {

	private String registrationId;

	public boolean isRegistered() {
		return registrationId != null;
	}

	@Override
	public void onReceive( final Context context, Intent intent ) {

		final String registration = intent.getStringExtra( "registration_id" );

		Log.d( getClass().getSimpleName(), String.format( "Received C2DM Registration ID: %s", registration ) );

		if( intent.getStringExtra( "error" ) != null ) {
		} else if( intent.getStringExtra( "unregistered" ) != null ) {
		} else if( registration != null ) {

			new AsyncTask<String, Void, String>() {

				@Override
				protected String doInBackground( String... registrations ) {
					return register( context, registrations[0] );
				}

				@Override
				protected void onPostExecute( String registration ) {
					registrationId = registration;
				}

				private String register( Context context, String registration ) {

					HttpClient client = new DefaultHttpClient();

					try {

						HttpPost post = new HttpPost( context.getString( R.string.c2dm_registration_url ) );

						post.setEntity( new UrlEncodedFormEntity( Arrays.asList( new NameValuePair [] {
						    new BasicNameValuePair( "registration_id", registration ),
						    new BasicNameValuePair( "device_id", Secure.getString( context.getContentResolver(), Secure.ANDROID_ID ) )
						} ) ) );

						if( client.execute( post ).getStatusLine().getStatusCode() < 400 ) {
							return registration;
						}

					} catch( ClientProtocolException exception ) {
					} catch( IOException exception ) {
					}

					return null;

				}

			}.execute( registration );

		}

	}

}
