package com.colestock.flickrbrowser;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

enum DownloadStatus {IDLE, PROCESSING, NOT_INITIALIZED, FAILED_OR_EMPTY, OK};

public class GetRawData extends AsyncTask<String, Void, String> {
    private String LOG_TAG = GetRawData.class.getSimpleName();
    private String dataUrl;
    private String data;
    private DownloadStatus downloadStatus;

    public GetRawData(String dataUrl) {
        this.dataUrl = dataUrl;
        this.downloadStatus = DownloadStatus.IDLE;
    }

    public void reset() {
        this.downloadStatus = DownloadStatus.IDLE;
        this.data = null;
        this.dataUrl = null;
    }

    public void execute() {
        this.execute(this.dataUrl);
    }

    public String getData() {
        return this.data;
    }

    public DownloadStatus getDownloadStatus() {
        return this.downloadStatus;
    }

    @Override
    protected String doInBackground(String... params) {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;

        if (params == null) {
            return null;
        }

        try { // Try to get the data

            // URL object based upon passed URL parameter
            URL url = new URL(params[0]);
            // Open connection casting to a HttpURLConnection object
            urlConnection = (HttpURLConnection) url.openConnection();
            // Set request method
            urlConnection.setRequestMethod("GET");
            // Open the connections
            urlConnection.connect();

            // Get InputStream from previously established connection/GET of web resource
            InputStream inputStream = urlConnection.getInputStream();

            // If no data was returned from the request, then return null
            if (inputStream == null) {
                return null;
            }

            // Create a StringBuffer used to process data return from the InputStream
            StringBuffer buffer = new StringBuffer();

            // Create a Buffered Reader used in reading
            reader = new BufferedReader(new InputStreamReader(inputStream));

            // Temporary variable used to store each line during loop processing
            String line;

            while ((line = reader.readLine()) != null) { // As long as the BufferedReader has lines to process
                buffer.append(line + "\n"); // "store up" the data
            } // continue on with the processing

            return buffer.toString(); // return buffers content cast to a String

        } catch (IOException e) { // if an IOException occurs
            Log.e(LOG_TAG, "Error", e); // Log it as an error, using the label defined earlier
            return null;
        } finally { // once completed
            if (urlConnection != null) { // if socket open
                urlConnection.disconnect(); // close it
            }

            if(reader != null) { // if reader not at end
                try{
                    reader.close(); // close it
                } catch(IOException e) { // if this cannot be done successfully
                    Log.e(LOG_TAG, "Error closing stream", e);  // log the error
                }
            }
        }
    }

    @Override
    protected void onPostExecute(String data) {
        Log.v(LOG_TAG, "Data returned was " + data);
        this.data = data;
        if(data == null) {
            if(this.dataUrl == null) {
                this.downloadStatus = DownloadStatus.NOT_INITIALIZED;
            } else {
                this.downloadStatus = DownloadStatus.FAILED_OR_EMPTY;
            }
        } else {
            this.downloadStatus = DownloadStatus.OK;
        }
    }
}
