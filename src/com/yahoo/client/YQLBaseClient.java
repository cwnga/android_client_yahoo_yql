package com.yahoo.client;

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
 */
public class YQLBaseClient extends AsyncTask<Void, Void, Void> {
	public JSONObject result;

	public void getWeather() {
		this.execute();

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
		String url = "http://query.yahooapis.com/v1/public/yql?q=SELECT%20*%20FROM%20google.books%20WHERE%20q%3D%22android%22%20AND%20maxResults%3D5%20AND%20startIndex%3D1&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys&callback=";

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
