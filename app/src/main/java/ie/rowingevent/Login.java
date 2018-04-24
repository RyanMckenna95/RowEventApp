package ie.rowingevent;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Login extends AppCompatActivity {

    private FirebaseAuth rAuth;
    private FirebaseAuth.AuthStateListener rAuthListner;
    private FirebaseUser user;

    private EditText LoginEmail,LoginPassword;
    private Button btnLogin;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        LoginEmail = findViewById(R.id.loginEmail);
        LoginPassword= findViewById(R.id.loginPassword);
        btnLogin= findViewById(R.id.btnLogin);

        rAuth = FirebaseAuth.getInstance();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!TextUtils.isEmpty(LoginEmail.getText().toString())&&!TextUtils.isEmpty(LoginPassword.getText().toString())){
                    String Email=LoginEmail.getText().toString();
                    String Password=LoginPassword.getText().toString();
                    login(Email,Password);
                }
            }
        });
        rAuthListner= new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
            user=firebaseAuth.getCurrentUser();
            if(user !=null){
                Toast.makeText(Login.this, "Signed In", Toast.LENGTH_LONG).show();

            }else{
                Toast.makeText(Login.this, "Signed out", Toast.LENGTH_LONG).show();
            }

            }
        };

    }

    public void login (String Email, String Password ){
        rAuth.signInWithEmailAndPassword(Email,Password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Login.this, "Successfully signed in",Toast.LENGTH_LONG).show();
                            startActivity(new Intent(Login.this, Events.class));

                        }else {
                            Toast.makeText(Login.this, "Unsuccessful, incorrect details",Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    public void register(View v) {
        startActivity (new Intent(this, Register.class));
    }

    private void startHomeScreen() {
        Intent intent = new Intent(Login.this, Enter.class);
        Login.this.startActivity(intent);
    }
}
