
package com.virtualcams.media;

import android.content.Context;
import android.net.Uri;

public class MediaInputManager {

    private Context context;
    private Uri mediaUri;

    public MediaInputManager(Context context) {
        this.context = context;
    }

    public void setMediaUri(Uri uri) {
        this.mediaUri = uri;
    }

    public Uri getMediaUri() {
        return mediaUri;
    }

    public boolean isVideo() {
        return mediaUri != null && mediaUri.toString().endsWith(".mp4");
    }
}
