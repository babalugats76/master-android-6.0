package com.colestock.flickrbrowser;

import android.net.Uri;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class GetFlickrJsonData extends GetRawData {

    private String LOG_TAG = GetFlickrJsonData.class.getSimpleName();
    private List<Photo> photos;
    private Uri destinationUri = null;

    public GetFlickrJsonData(String searchCriteria, boolean matchAll) {
        super(null);
        createAndUpdateUri(searchCriteria, matchAll);
        photos = new ArrayList<Photo>();
    }

    public void execute() {
        super.setDataUrl(this.destinationUri.toString());
        Log.v(LOG_TAG, "URI = " + this.destinationUri.toString());
        DownloadJsonData downloadJsonData = new DownloadJsonData();
        downloadJsonData.execute(this.destinationUri.toString());
    }

    public boolean createAndUpdateUri(String searchCriteria, boolean matchAll) {
        final String BASE_URL = "https://api.flickr.com/services/feeds/photos_public.gne";
        final String TAGS_PARAM = "tags";
        final String TAGMODE_PARAM = "tagmode";
        final String FORMAT_PARAM = "format";
        final String NO_JSON_CALLBACK_PARAM = "nojsoncallback";

        this.destinationUri = null;

        this.destinationUri = Uri.parse(BASE_URL).buildUpon()
                .appendQueryParameter(TAGS_PARAM, searchCriteria)
                .appendQueryParameter(TAGMODE_PARAM, (matchAll ? "ALL" : "ANY"))
                .appendQueryParameter(FORMAT_PARAM, "json")
                .appendQueryParameter(NO_JSON_CALLBACK_PARAM, "1")
                .build();
        Log.d(LOG_TAG, "The following URI was generated: " + "\n" + this.destinationUri.toString());

        return this.destinationUri != null;
    }

    protected void processResult() {
        if (getDownloadStatus() != DownloadStatus.OK) {
            Log.e(LOG_TAG, "Error download the raw data; unable to process");
            return;
        }

        final String FLICKR_ITEMS = "items";
        final String FLICKR_TITLE = "title";
        final String FLICKR_MEDIA = "media";
        final String FLICKR_PHOTO_URL = "m";
        final String FLICKR_AUTHOR = "author";
        final String FLICKR_AUTHOR_ID = "author_id";
        final String FLICKR_LINK = "link";
        final String FLICKR_TAGS = "tags";

        try {

            JSONObject jsonData = new JSONObject(getRawData());
            JSONArray itemsArray = jsonData.getJSONArray(FLICKR_ITEMS);

            for (int i = 0; i < itemsArray.length(); i++) { // For each item in the item array
                JSONObject photo = itemsArray.getJSONObject(i);
                String title = photo.getString(FLICKR_TITLE);
                String author = photo.getString(FLICKR_AUTHOR);
                String authorId = photo.getString(FLICKR_AUTHOR_ID);
                String link = photo.getString(FLICKR_LINK);
                String tags = photo.getString(FLICKR_TAGS);
                JSONObject media = photo.getJSONObject(FLICKR_MEDIA);
                String photoUrl = media.getString(FLICKR_PHOTO_URL);

                this.photos.add(new Photo(title, author, authorId, link, tags, photoUrl));

            }

        } catch (JSONException jsone) {
            jsone.printStackTrace();
            Log.e(LOG_TAG, "Error parsing JSON data");
        }

        for(Photo p : this.photos) {
            Log.v(LOG_TAG, p.toString());
        }
    }


    public class DownloadJsonData extends DownloadRawData {

        @Override
        protected String doInBackground(String... params) {
            return super.doInBackground(params);
        }

        @Override
        protected void onPostExecute(String data) {
            super.onPostExecute(data);
            processResult();
        }
    }
}
