package ie.rowingevent;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
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

public class Register extends Activity {

    private FirebaseAuth regAuth;

    private EditText RegEmail,RegPassword,RegClub,RegName;
    private Button btnRegister;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        regAuth = FirebaseAuth.getInstance();

        RegEmail=findViewById(R.id.registerEmail);
        RegPassword=findViewById(R.id.registerPassword);
        RegClub=findViewById(R.id.registerClub);
        RegName=findViewById(R.id.registerName);
        btnRegister=findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signUp();
            }
        });

    }

    public void signUp(){
        String email=RegEmail.getText().toString().trim();
        String password=RegPassword.getText().toString().trim();
        String club=RegClub.getText().toString().trim();
        String name=RegName.getText().toString().trim();


        if(!TextUtils.isEmpty(email)&&!TextUtils.isEmpty(password)&&!TextUtils.isEmpty(club)&&!TextUtils.isEmpty(name)){
            regAuth.createUserWithEmailAndPassword(email,password)
                    .addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                Toast.makeText(Register.this,"Could not sign up",Toast.LENGTH_LONG).show();
                            }else {
                                startActivity(new Intent(Register.this,Events.class));
                                finish();
                            }
                        }
                    });

        }
    }
}
