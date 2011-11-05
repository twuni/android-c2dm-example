package org.twuni.android.c2dm.activity;

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

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings.Secure;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate( Bundle savedInstanceState ) {
		super.onCreate( savedInstanceState );
		setContentView( R.layout.main );
	}

	@Override
	protected void onStop() {

		super.onStop();

		TextView userIdField = (TextView) findViewById( R.id.user_id );

		String userId = userIdField.getText().toString();

		new AsyncTask<String, Void, Void>() {

			@Override
			protected Void doInBackground( String... userIds ) {

				HttpClient client = new DefaultHttpClient();

				try {

					HttpPost post = new HttpPost( getString( R.string.c2dm_registration_url ) );

					post.setEntity( new UrlEncodedFormEntity( Arrays.asList( new NameValuePair [] {
					    new BasicNameValuePair( "user_id", userIds[0] ),
					    new BasicNameValuePair( "device_id", Secure.getString( getContentResolver(), Secure.ANDROID_ID ) )
					} ) ) );

					client.execute( post );

				} catch( ClientProtocolException exception ) {
				} catch( IOException exception ) {
				}

				return null;

			}

		}.execute( userId );

	};

}
