package viewManagers;

import android.Manifest;
import android.app.Fragment;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Camera;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ZoomButtonsController;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.lorenzo.germana.easydrive.EasyDrive;
import com.lorenzo.germana.easydrive.MainActivity;
import com.lorenzo.germana.easydrive.R;

import managers.MapsManager;


/**
 * Created by loren on 13/04/2016.
 */
public class MapsViewManager {
    ViewGroup root;
    ViewGroup menu;
    EditText et;

    public MapsViewManager(ViewGroup root, ViewGroup menu) {
        this.root = root;
        this.menu = menu;
        Button Start = (Button) menu.findViewById(R.id.StartNavigation);
        et = (EditText)menu.findViewById(R.id.InsertDestination);
	    MainActivity.mapsManager.setDestinationEditText(et);
        Start.setOnClickListener(MainActivity.mapsManager);
    }

    public View getView() {
        return root;
    }

    public View getMenu(){
        return menu;
    }
}