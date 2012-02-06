Background
----------

This application was originally written while reading the [documentation](http://code.google.com/android/c2dm/) for C2DM,
as a practical step-by-step to getting a working standalone implementation. My primary goal was to create a scenario
wherein I could tell an application server to send a message to *user X*, and it would broadcast that message to all of
that user's registered devices.


Configuring this application
----------------------------

Look in `res/values/config.xml` to adjust the C2DM sender address and your server's C2DM registration URL. The sender address you
configure must be [registered](http://code.google.com/android/c2dm/signup.html) for C2DM; it does not necessarily have to
be the same Google Account used to release the application to the Market. Requests to the C2DM registration URL will be via
the POST method, with the URL-encoded parameters `registration_id` and `device_id` (for C2DM) or `user_id` and `device_id`
(for correlation).

See the [c2dm-web](https://github.com/twuni/c2dm-web) project for a reference implementation of this server.


What this application does
--------------------------

This application does three things:

 1. It obtains a C2DM registration ID, which it then sends to your server at the configured C2DM registration URL along with the device ID.
 2. It contains an activity that demonstrates how one can register the device ID with a user ID for convenience.
 3. It displays a status bar notification whenever the device receives a C2DM message from your server.
