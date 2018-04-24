package ie.rowingevent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Events extends AppCompatActivity {

    private FirebaseDatabase db;
    private DatabaseReference dbRef;
    private FirebaseUser user;
    private FirebaseAuth Auth;
    private Spinner eventSpinner;
    private Button eventBtn;
    private ArrayAdapter spinnerAdapter;
    private String myEvent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_events);
        Auth=FirebaseAuth.getInstance();
        user=Auth.getCurrentUser();
        db = FirebaseDatabase.getInstance();


        eventSpinner=findViewById(R.id.eventSpinner);
        eventBtn=findViewById(R.id.EnterEvent);
        spinnerAdapter=ArrayAdapter.createFromResource(Events.this, R.array.events, android.R.layout.simple_spinner_item);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        eventSpinner.setAdapter(spinnerAdapter);




        eventBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myEvent = eventSpinner.getSelectedItem().toString();
                dbRef = db.getReference().child(myEvent);
                Log.i("database",dbRef.toString());

                //dbRef.setValue("hello");

                startActivity(new Intent(Events.this,Enter.class));

            }
        });


    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu and show the items.
//        getMenuInflater().inflate(R.menu.menu_enter, menu);
//        return true;
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle menu clicks here.
        int id = item.getItemId();

        switch (id) {
            case R.id.menuReport:
                startActivity(new Intent( Events.this, EntryList.class));
                break;
            case R.id.menuEnter:
                startActivity(new Intent(Events.this, Enter.class));
                break;


            case R.id.menuLogout:
                if (Auth != null && user != null) {
                    Auth.signOut();
                }
                break;
        }return super.onOptionsItemSelected(item);
    }
}


