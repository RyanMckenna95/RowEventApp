package ie.rowingevent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.List;

import adapters.AdapterList;
import adapters.EntriesViewHolder;
import models.Entries;

public class EntryList extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private FirebaseRecyclerAdapter<Entries, EntriesViewHolder> adapter;
    private List<Entries> entriesList;

    private FirebaseAuth Auth;
    private FirebaseUser user;
    private FirebaseDatabase db;
    private DatabaseReference dbRef;
    private EditText searchText;
    private Button searchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry_list);
        Auth=FirebaseAuth.getInstance();
        user=Auth.getCurrentUser();
        db = FirebaseDatabase.getInstance();
        dbRef=db.getReference().child("regattas");

        searchButton = findViewById(R.id.searchButton);
        searchText = findViewById(R.id.searchText);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        entriesList = new ArrayList<>();

        adapter = new FirebaseRecyclerAdapter<Entries, EntriesViewHolder>(
                Entries.class,
                R.layout.single_entry_list,
                EntriesViewHolder.class,
                dbRef) {
            @Override
            protected void populateViewHolder(EntriesViewHolder viewHolder, Entries model, final int position) {
                viewHolder.boatCat.setText("Boat Categry: " + model.getCatagory());
                viewHolder.ageCat.setText("Age Category: " +model.getBoatType());
                viewHolder.price.setText("Boat Price: " + model.getPrice());
                viewHolder.eventTitle.setText( model.getTitle());
                viewHolder.delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        adapter.getRef(position).removeValue();
                    }
                });
            }
        };
        recyclerView.setAdapter(adapter);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               String search = searchText.getText().toString();
                searchEvent(search);
            }
        });
    }

    public void searchEvent(String search){
        Query searchFirebase = dbRef.orderByChild("title").startAt(search).endAt(search + "\uf8ff");
        final FirebaseRecyclerAdapter myadapter = new FirebaseRecyclerAdapter<Entries, EntriesViewHolder>(
                Entries.class,
                R.layout.single_entry_list,
                EntriesViewHolder.class,
                searchFirebase) {
            @Override
            protected void populateViewHolder(EntriesViewHolder viewHolder, Entries model, final int position) {
                viewHolder.boatCat.setText("Boat Categry: " + model.getCatagory());
                viewHolder.ageCat.setText("Age Category: " +model.getBoatType());
                viewHolder.price.setText("Boat Price: " + model.getPrice());
                viewHolder.eventTitle.setText( model.getTitle());

            }
        };
        recyclerView.setAdapter(myadapter);
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
