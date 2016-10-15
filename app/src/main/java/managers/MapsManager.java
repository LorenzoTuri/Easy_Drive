package managers;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.android.PolyUtil;
import com.lorenzo.germana.easydrive.EasyDrive;
import com.lorenzo.germana.easydrive.R;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import dataContainer.step;

/**
 * Created by loren on 10/05/2016.
 */
public class MapsManager implements View.OnClickListener, OnMapReadyCallback {
	private String MyTag = "MapsManager";

	GoogleMap map;
	LocationManager lm;
	private boolean mapReady = false;
	EditText editText;

	/*TODO: capire che posizione iniziale vogliamo avere*/
	private float INITIAL_ZOOM = 15;
	private double Latitude = 46.50;
	private double Longitude = 11.33;
	private LatLng StartPointFago = new LatLng(Latitude, Longitude);

	public MapsManager(){
		lm = (LocationManager)EasyDrive.getContext().getSystemService(Context.LOCATION_SERVICE);
	}
	public void setDestinationEditText(EditText e){this.editText = e;}

	private void  askForRequest(final String origin, final String destination){
		new AsyncTask<Void,Void,Void>(){
			ArrayList<step> result = new ArrayList<step>();

			public Void doInBackground(Void... voids) {
				URL url = null;
				HttpURLConnection con = null;
				String tempurl;
				try {

					tempurl = "http://maps.googleapis.com/maps/api/directions/xml";
					tempurl += "?origin=" + origin;
					tempurl += "&destination=" + destination;
					tempurl += "&language=it";
					//tempurl += "&key="+EasyDrive.getContext().getResources().getString(R.string.google_maps_key);
					for (int i=0;i<tempurl.length();i++) if (tempurl.charAt(i)==' ')
						tempurl=tempurl.substring(0,i)+"%20"+tempurl.substring(i+1,tempurl.length());

					Log.i(MyTag,"Connecting to google servers..."+tempurl);
					url = new URL(tempurl);
					con = (HttpURLConnection) url.openConnection();
					con.connect();
					InputSource insource = new InputSource(con.getInputStream());

					Log.i(MyTag,"Parsing polyline...");
					XPath xPath = XPathFactory.newInstance().newXPath();

					Node source = (Node) xPath.evaluate("/DirectionsResponse[1]", insource, XPathConstants.NODE);
					NodeList dir = (NodeList) xPath.evaluate("//step", source, XPathConstants.NODESET);

					for (int i = 0; i < dir.getLength(); i++) {
						String poly = (String) xPath.evaluate("./polyline/points/text()", dir.item(i), XPathConstants.STRING);
						String htmlInstructions = (String)xPath.evaluate("./html_instruction/text()", dir.item(i), XPathConstants.STRING);
						LatLng startLocation = new LatLng(
								Float.parseFloat((String)xPath.evaluate("./start_location/lat/text()", dir.item(i), XPathConstants.STRING)),
								Float.parseFloat((String)xPath.evaluate("./start_location/lng/text()", dir.item(i), XPathConstants.STRING)));
						LatLng endLocation = new LatLng(
								Float.parseFloat((String)xPath.evaluate("./end_location/lat/text()", dir.item(i), XPathConstants.STRING)),
								Float.parseFloat((String)xPath.evaluate("./end_location/lng/text()", dir.item(i), XPathConstants.STRING)));
						int duration = Integer.parseInt((String) xPath.evaluate("./duration/value/text()", dir.item(i), XPathConstants.STRING));
						String distance = (String)xPath.evaluate("./distance/text/text()", dir.item(i), XPathConstants.STRING);

						step s = new step (poly, htmlInstructions, startLocation, endLocation, duration, distance);
						result.add(s);
					}

				} catch (MalformedURLException e) {
					System.err.println("Malformed URL Expression EXCEPTION:"+e.getMessage());
				}catch (IOException e) {
					System.err.println("IOException EXCEPTION:"+e.getMessage());
				}catch (XPathExpressionException e) {
					System.err.println("XPATH Expression EXCEPTION:"+e.getMessage()+",");
					e.printStackTrace();
				}finally {con.disconnect();}
				return null;
			}

			public void onPostExecute(Void v){
				map.clear();
				// aggiungiamo polyline alla mappa
				for (int i=0;i<result.size();i++){
					step s = result.get(i);
					PolylineOptions p = new PolylineOptions().addAll(PolyUtil.decode(s.getPoly()));
					p.color(EasyDrive.getContext().getResources().getColor(R.color.linecolor));
					map.addPolyline(p);
				}

				Log.i(MyTag,"Route added to map");
			}
		}.execute();
	}

	@Override
	public void onClick(View v) {
		if (ActivityCompat.checkSelfPermission(EasyDrive.getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
				ActivityCompat.checkSelfPermission(EasyDrive.getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
			return;
		}

		Location l = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
		if (l == null)
			l = lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

		Double lat = l.getLatitude();
		Double lon = l.getLongitude();
		String origin = lat.toString()+","+lon.toString();

		if (isMapReady()) askForRequest(origin, editText.getText().toString());
	}

	public void onMapReady(GoogleMap gMap) {
		map = gMap;

		if (ActivityCompat.checkSelfPermission(EasyDrive.getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
				ActivityCompat.checkSelfPermission(EasyDrive.getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
			return;
		}

		map.setMyLocationEnabled(true);
		Location l = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
		if (l == null)
			l = lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

		LatLng ll = null;

		if (l == null) ll = StartPointFago;
		else ll = new LatLng(l.getLatitude(), l.getLongitude());

		map.moveCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition.Builder().
				target(ll).
				zoom(INITIAL_ZOOM).
				build()));

		map.setOnMyLocationChangeListener(new GoogleMap.OnMyLocationChangeListener() {
			@Override
			public void onMyLocationChange(Location location) {
				float zoom = map.getCameraPosition().zoom;

				LatLng loc = new LatLng(location.getLatitude(), location.getLongitude());

				map.moveCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition.Builder().
						target(loc).
						zoom(zoom).
						build()));
			}
		});

		mapReady = true;
	}

	public boolean isMapReady(){
		return mapReady;
	}
}
