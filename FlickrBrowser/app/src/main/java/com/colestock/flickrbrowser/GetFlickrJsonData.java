package com.colestock.flickrbrowser;

import android.net.Uri;
import android.util.Log;

import java.util.List;

public class GetFlickrJsonData extends GetRawData {

    private String LOG_TAG = GetFlickrJsonData.class.getSimpleName();
    private List<Photo> photos;
    private Uri destinationUri = null;

    public GetFlickrJsonData(String searchCriteria, boolean matchAll) {
        super(null);
        createAndUpdateUri(searchCriteria, matchAll);
    }

    public boolean createAndUpdateUri(String searchCriteria, boolean matchAll) {
        final String BASE_URL = "https://api.flickr.com/services/feeds/photos_public.gne";
        final String TAGS_PARAM = "tags";
        final String TAGMODE_PARAM = "tagmode";
        final String FORMAT_PARAM = "format";
        final String NO_JSON_CALLBACK_PARAM = "nojsoncallback";

        this.destinationUri = null;

        this.destinationUri = Uri.parse(BASE_URL).buildUpon()
                .appendQueryParameter(TAGS_PARAM,searchCriteria)
                .appendQueryParameter(TAGMODE_PARAM, (matchAll ? "ALL" : "ANY") )
                .appendQueryParameter(FORMAT_PARAM, "json")
                .appendQueryParameter(NO_JSON_CALLBACK_PARAM,"1")
                .build();
        Log.d(LOG_TAG,"The following URI was generated: " + "\n" +  this.destinationUri.toString());

        return this.destinationUri != null;
    }

    public class downloadJsonData extends DownloadRawData {

    }
}
