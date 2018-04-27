package ie.rowingevent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import adapters.AdapterList;
import models.Entries;

public class EntryList extends AppCompatActivity {

    private AdapterList adapterList;
    private List<Entries> entriesList;
    private RecyclerView recyclerView;
    private FirebaseAuth Auth;
    private FirebaseUser user;
    private FirebaseDatabase db;
    private DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry_list);
        Auth=FirebaseAuth.getInstance();
        user=Auth.getCurrentUser();
        db = FirebaseDatabase.getInstance();
        dbRef=db.getReference().child("Info");

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        entriesList = new ArrayList<>();
        adapterList = new AdapterList(this, entriesList);
        recyclerView.setAdapter(adapterList);
        adapterList.notifyDataSetChanged();//update the recycler view to the changes




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
                //startActivity(new Intent( Enter.this, EntryList.class));
                break;
            case R.id.menuEnter:
                startActivity(new Intent(EntryList.this, Enter.class));
                break;


            case R.id.menuLogout:
                if (Auth != null && user != null) {
                    Auth.signOut();
                }
                break;
        }return super.onOptionsItemSelected(item);
    }


}
