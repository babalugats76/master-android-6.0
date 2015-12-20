package com.colestock.top10downloader;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    String fileContents;
    private Button btnParse;
    private ListView listApps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnParse = (Button) findViewById(R.id.btnParse);
        btnParse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseApplications parseApplications = new ParseApplications(fileContents);
                parseApplications.process();
                ArrayAdapter<Application> arrayAdapter = new ArrayAdapter<Application>(
                        MainActivity.this, R.layout.list_item, parseApplications.getApplications());
                        listApps.setAdapter(arrayAdapter);

            }
        });
        listApps = (ListView) findViewById(R.id.xmlListView);
        DownloadData downloadData = new DownloadData();
        downloadData.execute("http://ax.itunes.apple.com/WebObjects/MZStoreServices.woa/ws/RSS/topfreeapplications/limit=10/xml");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
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

    private class DownloadData extends AsyncTask<String, Void, String> {

        /**
         * AsyncTask needs to be subclassed, i.e., extended, and the doInBackground method must implemented/overridden
         * @param params variable number of parameters accepted
         * @return String representing the file's contents
         */
        @Override
        protected String doInBackground(String... params) {
            /* Assume that the first param is the urlPath (pass to downloadXMLFile method */
            fileContents = downloadXMLFile(params[0]);
            /* If file's contents are returned as null, log the error */
            if (fileContents == null) {
                Log.d("DownloadData", "Error downloading");
            }
            /* Otherwise, return the file's contents to the caller */
            return fileContents;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            Log.d("DownloadData", "Result was " + result);
        }

        /**
         *
         * @param urlPath fully-qualified path (as a String) to the resource to download
         * @return String representing the file's contents
         */
        private String downloadXMLFile(String urlPath) {
            /* buffer used in building string from resource downloaded */
            StringBuilder tmpBuffer = new StringBuilder();
            try {
                /* URL object with fully-qualified path to resource */
                URL url = new URL(urlPath);
                /* Connection used to obtain bytes from resource */
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                /* int to store response code, e.g., 404, 200, etc. */
                int response = connection.getResponseCode();
                Log.d("DownloadData", "The response code was " + response);
                /* create an InputStream for reading directly from the connection object instantiated above */
                InputStream is = connection.getInputStream();
                /* pass it to the InputStreamReader */
                InputStreamReader isr = new InputStreamReader(is);

                /* stores number of characters read using char[] buffer */
                int charRead;
                /* 500-byte character array to use for reading */
                char[] inputBuffer = new char[500];

                /* Unless we break out of the loop, continue reading 500 bytes at a time until nothing is left */
                while (true) {
                    /* read up to 500 bytes at a time using the stream reader and char array */
                    charRead = isr.read(inputBuffer);
                    /* if no (more) bytes to read, break out  of while loop */
                    if (charRead <= 0) {
                        break;
                    }
                    /* Otherwise, append what was read to the StringBuilder (this will ultimately be returned as a String to the caller */
                    tmpBuffer.append(String.copyValueOf(inputBuffer, 0, charRead));
                }

                /* Return StringBuilder buffer as a String to the caller */
                return tmpBuffer.toString();

            /* if an IOException occurs, log to the console */
            } catch (IOException e) {
                Log.d("DownloadData", "IO Exception reading data:" + e.getMessage());
                e.printStackTrace();
            } catch (SecurityException e) {
                Log.d("DownloadData", "Security exception. Needs permissions? " + e.getMessage());
            }

            return null;

        }
    }

}
