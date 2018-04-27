package ie.rowingevent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

import models.Entries;

public class Enter extends AppCompatActivity {
    private FirebaseDatabase db;
    private DatabaseReference dbRef;
    private FirebaseUser user;
    private FirebaseAuth Auth;
    private Spinner catSpinner;
    private Spinner boatSpinner;
    private TextView price;
    private ArrayAdapter spinnerAdapter;
    private Button enterBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        setContentView(R.layout.activity_enter);


        Auth=FirebaseAuth.getInstance();
        user=Auth.getCurrentUser();
        db = FirebaseDatabase.getInstance();
        Log.i("database",db.toString());
        Intent i=getIntent();
        String pickedEvent= i.getStringExtra("eventPicked");
        dbRef = db.getReference().child(pickedEvent);

        catSpinner=findViewById(R.id.catagories);
        boatSpinner=findViewById(R.id.boats);
        price=findViewById(R.id.priceOf);
        enterBtn=findViewById(R.id.entryButton);
        spinnerAdapter=ArrayAdapter.createFromResource(Enter.this, R.array.catagories, android.R.layout.simple_spinner_item);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        catSpinner.setAdapter(spinnerAdapter);

        spinnerAdapter=ArrayAdapter.createFromResource(Enter.this, R.array.boats, android.R.layout.simple_spinner_item);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        boatSpinner.setAdapter(spinnerAdapter);



        boatSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String boatPrice=boatSpinner.getSelectedItem().toString();
                switch(boatPrice){
                    case "1X":
                        price.setText("20");
                        break;
                    case "2X":
                        price.setText("40");
                        break;
                    case "2-":
                        price.setText("40");
                        break;
                    case "4X+":
                        price.setText("100");
                        break;
                    case "4X-":
                        price.setText("80");
                        break;
                    case "4-":
                        price.setText("80");
                        break;
                    case "4+":
                        price.setText("100");
                        break;
                    case "8+":
                        price.setText("180");
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        enterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String myBoats = boatSpinner.getSelectedItem().toString();
                String cats = catSpinner.getSelectedItem().toString();
                String pricey = price.getText().toString();

                Entries entryModel= new Entries(myBoats,cats,pricey);



                dbRef.push().setValue(entryModel);


                startActivity(new Intent(getApplicationContext(), EntryList.class));




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
                startActivity(new Intent( Enter.this, EntryList.class));
                break;
            case R.id.menuEnter:
                    //startActivity(new Intent( Enter.this, Enter.class));
                break;


            case R.id.menuLogout:
                if(Auth != null && user != null){
                    Auth.signOut();
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }


}




