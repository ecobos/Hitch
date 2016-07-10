package org.lbc.hitch.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.facebook.AccessToken;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;

public class SeedActivity extends AppCompatActivity {

    final static public String TAG = "SeedActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);



        if(AccessToken.getCurrentAccessToken() != null)
        {
            AccessToken.refreshCurrentAccessTokenAsync();
            // Initialize Firebase Auth
            FirebaseAuth mAuth = FirebaseAuth.getInstance();
            AuthCredential credential = FacebookAuthProvider.getCredential(AccessToken.getCurrentAccessToken().getToken());
            mAuth.signInWithCredential(credential).addOnCompleteListener(this, new LoginCompleteListener());
        }
        else
        {
            Intent newActivity = new Intent(this, LoginActivity.class);
            newActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(newActivity);
            finish();
        }
    }

    private class LoginCompleteListener implements OnCompleteListener<AuthResult>{

        @Override
        public void onComplete(@NonNull Task<AuthResult> task) {
            Class<? extends Activity> activityClass = MainActivity.class;

            Log.d(TAG, "signInWithCredential:onComplete:" + task.isSuccessful());

            // If sign in fails, display a message to the user. If sign in succeeds
            // the auth state listener will be notified and logic to handle the
            // signed in user can be handled in the listener.
            if (!task.isSuccessful()) {
                Log.w(TAG, "signInWithCredential", task.getException());
                activityClass = LoginActivity.class;
            }

            Intent intent = new Intent(getApplicationContext(), activityClass);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        }
    }
}
