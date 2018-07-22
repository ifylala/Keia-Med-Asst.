package com.clevmania.keia.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.clevmania.keia.FirebaseUtils;
import com.clevmania.keia.MainActivity;
import com.clevmania.keia.R;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class RegisterActivity extends AppCompatActivity {
    private TextView headerText, or;
    private Button google, facebook;
    private GoogleSignInOptions gso;
    private GoogleApiClient googleApiClient;
    private static final int RC_SIGN_IN = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        headerText = findViewById(R.id.tv_header);
        or = findViewById(R.id.tv_or);
        facebook = findViewById(R.id.btn_facebook);
        google = findViewById(R.id.btn_google);

        String customFont = "Nunito-SemiBold.ttf";
        Typeface header = Typeface.createFromAsset(getAssets(),customFont);
        headerText.setTypeface(header);
        or.setTypeface(header);

        configureGoogleSignIn();

        google.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                signInWithGoogle();
            }
        });

    }

    private void configureGoogleSignIn(){
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        googleApiClient = new GoogleApiClient.Builder(RegisterActivity.this)
                .enableAutoManage(RegisterActivity.this, new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

                    }
                })
                .addApi(Auth.GOOGLE_SIGN_IN_API,gso).build();
    }

    // Fire up the Google sign in api
    private void signInWithGoogle() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    // retrieve the result from api call and authenticate user
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);

        if (requestCode == RC_SIGN_IN){
            GoogleSignInResult googleSignInResult = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (googleSignInResult.isSuccess()){
                GoogleSignInAccount googleSignInAccount = googleSignInResult.getSignInAccount();
                firebaseAuthWithGoogle(googleSignInAccount);
            }else{
                Log.e(RegisterActivity.class.getSimpleName(), "Login Unsuccessful");
                Snackbar.make(google,"Login Unsuccessful",Snackbar.LENGTH_LONG).show();
//                Toast.makeText(this, “Login Unsuccessful”, Toast.LENGTH_SHORT).show();

            }

        }

    }

    // retrieve google account details of user
    // and signs user into firebase
    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);

        FirebaseUtils.getAuthenticationReference().signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
//                            Log.d(TAG, "signInWithCredential:success");
//                            FirebaseUser user = FirebaseUtils.getAuthenticationReference().getCurrentUser();

//                            Log.i("userUidFromTask", task.getResult().getUser().getUid());
//                            if(user.getUid() != null){
//                                Log.i("userUidForFBUser",user.getUid());
//                            }
//                            updateUserProfile(user);
                            startActivity(new Intent(RegisterActivity.this,MainActivity.class));
                            finish();
                        } else {
                            // If sign in fails, display a message to the user.
//                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Snackbar.make(google,"Authentication Failed",Snackbar.LENGTH_LONG).show();
//                            updateUserProfile(null);
                        }

                    }
                });
    }

    public void signOutUser(Context context) {
        Auth.GoogleSignInApi.signOut(googleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(@NonNull Status status) {

                    }
                });

        FirebaseUtils.getAuthenticationReference().signOut();

        Intent logOutIntent = new Intent(context, RegisterActivity.class);
        logOutIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(logOutIntent);

    }

}
