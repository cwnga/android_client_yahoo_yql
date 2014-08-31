package com.yahoo.client;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

/**
 * @author cwnga
 * 
 *         YQLBaseClient YQLBaseClient = new YQLBaseClient();
 *         YQLBaseClient.setListener(new CallBackListener() { public void
 *         callback(JSONObject result) {
 *         Log.d("callbackcallbackcallbackcallback", "12112");
 * 
 *         Log.d("ererer", result.toString());
 * 
 *         } });
 * 
 *         YQLBaseClient.execute();
 */
public class YQLBaseClient extends AsyncTask<Void, Void, Void> {
	public JSONObject result;
	public String yqlUrl = "https://query.yahooapis.com/v1/public/yql?q=";

	public void getWeather(String woeid) {
		if (woeid == "") {
			woeid = "2306179";

		}
		String yql = "select * from weather.forecast where woeid=" + woeid;
		yql = encodeUrl(yql);
		yqlUrl = yqlUrl + yql+"&format=json";
		this.execute();

	}

	/**
	 * 
	 */
	public void execute() {
		Log.d("YQLBaseClient", "url:" + this.yqlUrl);
		super.execute();
	}

	// select * from json where
	// url="http://search.twitter.com/search.json?q=puppy"

	/**
	 * @param string
	 * @return
	 */
	protected String encodeUrl(String string) {
		try {
			string = URLEncoder.encode(string, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return string;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.os.AsyncTask#onPreExecute()
	 */
	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		// Create a progressdialog

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.os.AsyncTask#doInBackground(Params[])
	 */
	@Override
	protected Void doInBackground(Void... params) {
		// Create the array
		ArrayList<HashMap<String, String>> arraylist = new ArrayList<HashMap<String, String>>();
		// YQL JSON URL
		String url = this.yqlUrl;
		try {
			// Retrive JSON Objects from the given URL in JSONfunctions.class
			JSONObject json_data = JSONfunctions.getJSONfromURL(url);
			this.result = json_data.getJSONObject("query");
			/*
			 * JSONObject json_query = json_data.getJSONObject("query");
			 * JSONObject json_results = json_query.getJSONObject("results");
			 * JSONObject json_json_result = json_results.getJSONObject("json");
			 * JSONArray json_result = json_json_result.getJSONArray("items");
			 * 
			 * for (int i = 0; i < json_result.length(); i++) { HashMap<String,
			 * String> map = new HashMap<String, String>(); JSONObject c =
			 * json_result.getJSONObject(i); JSONObject vo =
			 * c.getJSONObject("volumeInfo"); map.put("title",
			 * vo.optString("title")); map.put("description",
			 * vo.optString("description")); JSONObject il =
			 * vo.getJSONObject("imageLinks"); map.put("thumbnail",
			 * il.optString("thumbnail")); Log.d("test",
			 * il.optString("thumbnail")); arraylist.add(map); }
			 */

		} catch (JSONException e) {
			Log.e("Error", e.getMessage());
			e.printStackTrace();
			JSONObject JSONObject = new JSONObject();
			this.result = JSONObject;
			;
		}
		return null;
	}

	@Override
	protected void onPostExecute(Void args) {
		// onResult(this.result);
		if (mListener != null) {
			mListener.callback(this.result);
		}

		Log.d("YQLBaseClient", "result:" + this.result.toString());
	
		// Locate the listview in listview_main.xml
		// listview = (ListView) findViewById(R.id.listview);
		// Pass the results into ListViewAdapter.java
		// adapter = new ListViewAdapter(MainActivity.this, arraylist);
		// Binds the Adapter to the ListView
		// listview.setAdapter(adapter);
		// Close the progressdialog
		// mProgressDialog.dismiss();
	}

	CallBackListener mListener;

	public void setListener(CallBackListener listener) {
		mListener = listener;
	}

}
