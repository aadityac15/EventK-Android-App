package androidproject.chokshi.testeventk;

//Youtube: https://youtu.be/mQyXbM6XGtE (Android Fingerprint Authentication) was referred.

import android.content.Intent;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.widget.Toast;

@RequiresApi(api = Build.VERSION_CODES.M)
class AuthenticationHandler extends FingerprintManager.AuthenticationCallback {
    private SignInActivity signInActivity;

    public AuthenticationHandler(SignInActivity signInActivity) {
        this.signInActivity = signInActivity;
    }

    @Override
    public void onAuthenticationError(int errorCode, CharSequence errString) {
        super.onAuthenticationError(errorCode, errString);
        // Toast.makeText(signInActivity, "Authentication Error" + errString, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAuthenticationHelp(int helpCode, CharSequence helpString) {
        super.onAuthenticationHelp(helpCode, helpString);
        // Toast.makeText(signInActivity, "Authentication Help " + helpString, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {
        super.onAuthenticationSucceeded(result);
        Toast.makeText(signInActivity, "Authentication Success", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(signInActivity, WelcomePageActivity.class);
        signInActivity.startActivity(intent);
    }

    @Override
    public void onAuthenticationFailed() {
        super.onAuthenticationFailed();
        Toast.makeText(signInActivity, "Authentication Failed", Toast.LENGTH_SHORT).show();
    }

}
