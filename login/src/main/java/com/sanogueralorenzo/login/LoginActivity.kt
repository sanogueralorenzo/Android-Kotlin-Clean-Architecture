package com.sanogueralorenzo.login

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

class LoginActivity : AppCompatActivity() {

    private val auth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }
    private val googleSignInClient: GoogleSignInClient by lazy {
        GoogleSignIn.getClient(this, googleSignInOptions)
    }
    private val googleSignInOptions: GoogleSignInOptions by lazy {
        GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        if (savedInstanceState == null) {
            auth.currentUser?.let {
                signInSuccess()
            } ?: signIn()
        }
    }

    private fun signIn() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, SIGN_IN)
    }

    private fun signInSuccess() = setResult(RESULT_OK).also { finish() }

    private fun signInFail() =
        Toast.makeText(this, getString(R.string.login_error), Toast.LENGTH_SHORT).show()
            .also {
                setResult(RESULT_CANCELED)
                finish()
            }

    override fun onBackPressed() {
        setResult(RESULT_CANCELED)
        super.onBackPressed()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)
                account?.let { firebaseAuthWithGoogle(it) }
            } catch (e: ApiException) {
                // Google Sign In failed
                onBackPressed()
            }
        }
    }

    private fun firebaseAuthWithGoogle(acct: GoogleSignInAccount) =
        GoogleAuthProvider.getCredential(acct.idToken, null)
            .also {
                auth.signInWithCredential(it)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            signInSuccess()
                        } else {
                            // If sign in fails, display a message to the user.
                            signInFail()
                        }
                    }
            }

    companion object {
        private const val SIGN_IN = 101
    }
}
