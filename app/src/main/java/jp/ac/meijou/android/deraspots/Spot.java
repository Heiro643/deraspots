// Spot.java
package jp.ac.meijou.android.deraspots;

import android.content.Context;
import android.content.Intent;

public class Spot {
    String title;
    String description;
    int imageResource;
    Class<?> destinationActivity;

    public Spot(String title, String description, int imageResource, Class<?> destinationActivity) {
        this.title = title;
        this.description = description;
        this.imageResource = imageResource;
        this.destinationActivity = destinationActivity;
    }

    public void startDestinationActivity(Context context) {
        Intent intent = new Intent(context, destinationActivity);
        context.startActivity(intent);
    }
}
