package com.example.volleyjsonexample;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {
	TextView tView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tView = (TextView) findViewById(R.id.textView1);
        RequestQueue rQueue = Volley.newRequestQueue(this);
        //String url = "http://date.jsontest.com";
        //String url = "http://api.rottentomatoes.com/api/public/v1.0/lists/movies/in_theaters.json?apikey=2ey3pufpky8prmvuxrgf67y7";
        String url = "http://api.rottentomatoes.com/api/public/v1.0/lists/movies/in_theaters.json?apikey=2ey3pufpky8prmvuxrgf67y7&page_limit=50&page=1&country=us";
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

		@Override
		public void onResponse(JSONObject arg0) {
			// TODO Auto-generated method stub
			tView.setText("Response-> "+arg0.toString());
			Log.d("DEB","Volley success. Response is -"+arg0.toString());
			try {
			//	JSONObject mTotal = arg0.getJSONObject("total");
				int total = arg0.getInt("total");
				Log.d("DEB", "Total ->"+total);
				JSONArray mMovies = arg0.getJSONArray("movies");
				Log.d("DEB", "Total ->"+mMovies.length());
				for(int i=0;i<mMovies.length();i++){
					JSONObject obj = mMovies.getJSONObject(i);
					String title = obj.getString("title");
					Log.d("DEB", "Title-> "+title);
					int year = obj.getInt("year");
					Log.d("DEB", "Year-> "+year);
					JSONObject rlsDate = obj.getJSONObject("release_dates");
					String rDate = rlsDate.getString("theater");
					Log.d("DEB", "On Theater-> "+rDate);
					String synopsis = obj.getString("synopsis");
					Log.d("DEB", "Synopsis-> "+synopsis);
					JSONObject posterLink = obj.getJSONObject("posters");
					String posterURL = posterLink.getString("original");
					Log.d("DEB", "Poster Link-> "+posterURL);
				}
				//JSONObject object = arg0.getJSONObject("time");
			//	String str1 = arg0.getString("time");
			//	String str2 = arg0.getString("date");
			//	Log.d("DEB","JSON data arg1="+str1);
			//	Log.d("DEB","JSON data arg2="+str2);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				Log.d("DEB", "JSon exception ->"+e);
				e.printStackTrace();
			}
		}
	}, new Response.ErrorListener() {

		@Override
		public void onErrorResponse(VolleyError arg0) {
			// TODO Auto-generated method stub
			Log.d("DEB","Volley error. Error is -"+arg0);
		}
	});
       rQueue.add(jsonObjReq); 
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
