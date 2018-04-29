package ie.rowingevent;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;
import android.widget.ZoomControls;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class EventsMap extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private GoogleMap mMap;
    private static final LatLng DUBLIN = new LatLng(53.345942, -6.314689);
    private static final LatLng CHAMPS = new LatLng(51.893971, -8.759685);
    private static final LatLng CORK =  new LatLng(51.898085, -8.412675);
    private static final LatLng CLONMEL = new LatLng(52.350592, -7.715192);
    private static final LatLng LIMRICK = new LatLng(52.663058, -8.635162);
    private static final LatLng GALWAY = new LatLng(53.279251, -9.056821);
    private static final LatLng CASTLECONNELL = new LatLng(52.726550, -8.504422);

    private Marker dublin;
    private Marker cork;
    private Marker champs;
    private Marker limrick;
    private Marker clonmel;
    private Marker galway;
    private Marker castleconnell;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events_map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        Toast toast=Toast.makeText(getApplicationContext(), "MyToast Test", Toast.LENGTH_LONG);
        showMyToast(toast, 10000);

    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        List<Marker> markers = new ArrayList<>();

       dublin = mMap.addMarker(new MarkerOptions().position(DUBLIN).title("Dublin "));
       dublin.setTag(0);
       markers.add(dublin);

        cork = mMap.addMarker(new MarkerOptions().position(CORK).title("Cork"));
        cork.setTag(1);
        markers.add(cork);

        limrick = mMap.addMarker(new MarkerOptions().position(LIMRICK).title("Dublin"));
        limrick.setTag(2);
        markers.add(limrick);

        galway = mMap.addMarker(new MarkerOptions().position(GALWAY).title("Dublin"));
        galway.setTag(3);
        markers.add(galway);

        castleconnell = mMap.addMarker(new MarkerOptions().position(CASTLECONNELL).title("Dublin"));
        castleconnell.setTag(4);
        markers.add(castleconnell);

        champs = mMap.addMarker(new MarkerOptions().position(CHAMPS).title("Dublin"));
        champs.setTag(5);
        markers.add(champs);

        clonmel = mMap.addMarker(new MarkerOptions().position(CLONMEL).title("Dublin"));
        clonmel.setTag(6);
        markers.add(clonmel);

        mMap.setOnMarkerClickListener(this);

        for(Marker m : markers){
            LatLng latLng = new LatLng(m.getPosition().latitude, m.getPosition().longitude);
            mMap.addMarker(new MarkerOptions().position(latLng));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 8));
            m.showInfoWindow();

        }
    }



    public void showMyToast(final Toast toast, final int delay) {
        final Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                toast.show();
            }
        }, 0, 1000);
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                toast.cancel();
                timer.cancel();
            }
        }, delay);
    }



    @Override
    public boolean onMarkerClick(Marker marker) {
       if(marker.getTag().equals(0)){
           Toast.makeText(getApplicationContext(), "Hello",Toast.LENGTH_LONG ).show();
           Intent intent = new Intent(EventsMap.this, Enter.class);
           intent.putExtra("Marker", marker.getTitle());
           startActivity(intent);

       }else if(marker.getTag().equals(1)){
           Intent intent = new Intent(EventsMap.this, Enter.class);
           intent.putExtra("Marker", marker.getTitle());
       }
        return false;
    }

}
