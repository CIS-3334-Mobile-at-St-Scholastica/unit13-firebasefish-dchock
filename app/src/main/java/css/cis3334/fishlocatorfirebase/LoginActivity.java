package css.cis3334.fishlocatorfirebase;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    EditText Email, Password;
    Button ButtonLogin, CreateUser;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Email = (EditText) findViewById(R.id.editTextEmail);
        Password = (EditText) findViewById(R.id.editTextPassword);
        ButtonLogin = (Button) findViewById(R.id.buttonLogin);
        CreateUser = (Button) findViewById(R.id.buttonNewUser);
        mAuth = FirebaseAuth.getInstance();  //declare object for Firebase

        ButtonLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("CIS3334", "Normal login ");
                signIn(Email.getText().toString(), Password.getText().toString());
            }
        });


        CreateUser.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("CIS3334", "Create New User ");
                createAccount(Email.getText().toString(), Password.getText().toString());
            }
        });
    }

        private void signIn(String Email, String Password) {
            //sign in the recurrent user with email and password previously created.
            mAuth.signInWithEmailAndPassword(Email, Password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() { //add to listener
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (!task.isSuccessful()) { //when failed
                        Toast.makeText(LoginActivity.this, "SignIn--Authentication failed.", Toast.LENGTH_LONG).show();
                    } else {
                        //return to MainActivity is login works
                        finish();
                    }
                }
            });
        }

        private void createAccount(String Email, String Password) {
            //create account for new users
            mAuth.createUserWithEmailAndPassword(Email, Password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {  //update listener.
                    if (!task.isSuccessful()) { //when failed
                        Toast.makeText(LoginActivity.this, "createAccount--Authentication failed.", Toast.LENGTH_LONG).show();
                    } else {
                        //return to MainActivity is login works
                        finish();
                    }
                }
            });
        }

}
