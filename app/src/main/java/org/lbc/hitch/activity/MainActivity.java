package org.lbc.hitch.activity;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.lbc.hitch.R;


public class MainActivity extends AppCompatActivity implements OnMapReadyCallback, View.OnClickListener {
    private Firebase dbRef;
    private final float mapZoom = 9;
    private final int MAPS_PERMISSIONS_REQUEST = 1;
    private GoogleMap appMap;
    /**
     * Animations
     */
    private Boolean isFabOpen = false;
    private FloatingActionButton fab, requestFab, offerFab;
    private Animation fab_open, fab_close, rotate_forward, rotate_backward;

    private static final String TAG = MainActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // permissions
        getPermissionToAccessLocation();

        // set Firebase context and DB url
        Firebase.setAndroidContext(this);
        dbRef = new Firebase("https://popping-inferno-8016.firebaseio.com/");

        // get GoogleMap fragment
        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // get fab references
        // TODO: change FAB colors
        // TODO: consider changing to an action bar that slides in and out
        // https://www.google.com/design/spec/components/buttons-floating-action-button.html#buttons-floating-action-button-transitions
        fab = (FloatingActionButton) findViewById(R.id.fab);
        requestFab = (FloatingActionButton) findViewById(R.id.request_ride);
        //requestFab.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.DarkSeaGreen));
        offerFab = (FloatingActionButton) findViewById(R.id.offer_ride);
        //offerFab.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.PaleTurquoise));

        // get animations
        fab_open = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
        fab_close = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_close);
        rotate_backward = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_backward);
        rotate_forward = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_forward);

        fab.setOnClickListener(this);
        offerFab.setOnClickListener(this);
        requestFab.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // TODO: get user location and zoom into map
    @Override
    public void onMapReady(GoogleMap googleMap) {
        appMap = googleMap;
        // checks that the permissions were not changed at runtime prior to updating map
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MAPS_PERMISSIONS_REQUEST);
        } else {
            updateMap(appMap);
        }
    }

    private void updateMap(GoogleMap googleMap) {
        Log.i(TAG, "UPDATING map");
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // DO NOTHING, check is done in calling function
        }
        googleMap.setMyLocationEnabled(true);
        // get user's last location
        LocationManager locManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);

        String provider = LocationManager.GPS_PROVIDER;
        if (provider == null) {
            provider = LocationManager.NETWORK_PROVIDER;
        }

        Location lastLoc = locManager.getLastKnownLocation(provider);
        LatLng userPosition = new LatLng(lastLoc.getLatitude(), lastLoc.getLongitude());

        if (lastLoc != null) {
            CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(userPosition, mapZoom);
            googleMap.animateCamera(cameraUpdate);
        } else {
            // lastLoc should never be null
            googleMap.addMarker(new MarkerOptions()
                    .position(new LatLng(44.976140, -93.276930))
                    .title("Marker"));
        }
    }

    // TODO: this deals with the new permissions in Android M, versions below M should be asked when downloading the app (double check)
    @TargetApi(Build.VERSION_CODES.M)
    private void getPermissionToAccessLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MAPS_PERMISSIONS_REQUEST);

            // explain to user why we need perms
            if(shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION)) {
                Toast.makeText(this, "We need to access GPS location to better serve your ride/driving needs", Toast.LENGTH_SHORT).show();
            }
            // request perms
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    MAPS_PERMISSIONS_REQUEST);
        }
    }

    // Deals with runtime permission changes
    // Should a user update their permission through settings, we want to know
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MAPS_PERMISSIONS_REQUEST: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Location Permission granted at RunTime", Toast.LENGTH_SHORT).show();
                    updateMap(appMap);
                } else {
                    Toast.makeText(this, "Required permissions needed to access your location DENIED", Toast.LENGTH_SHORT).show();
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }
        }
    }

    /**
     * Makes animating the FAB much easier
     * TODO: change icons of the FABs and make them dissappear when scrolling map
     * @param v
     */
    @Override
    public void onClick(View v) {
        int fabId = v.getId();
        switch (fabId) {
            case R.id.fab:
                animateFAB();
                break;
            case R.id.request_ride:
                Toast.makeText(this, "Requesting ride", Toast.LENGTH_SHORT).show();
                break;
            case R.id.offer_ride:
                Toast.makeText(this, "Offering ride", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    /**
     * Animates FAB to appear as if the other request/offer ride FABs are closing/expanding
     * Enables and disables clickable state on request/offer ride FABs
     */
    private void animateFAB() {
        if(isFabOpen) {
            fab.startAnimation(rotate_backward);
            offerFab.startAnimation(fab_close);
            requestFab.startAnimation(fab_close);
            offerFab.setClickable(false);
            requestFab.setClickable(false);
            isFabOpen = false;
        }
        else {
            fab.startAnimation(rotate_forward);
            offerFab.startAnimation(fab_open);
            requestFab.startAnimation(fab_open);
            offerFab.setClickable(true);
            requestFab.setClickable(true);
            isFabOpen = true;
        }
    }
}
