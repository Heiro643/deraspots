// Spot.java
package jp.ac.meijou.android.deraspots;

import android.content.Context;
import android.content.Intent;

public class Spot {
    String title;
    String description;
    int imageResource;
    Class<?> destinationActivity;
    int score = 0;

    public Spot(String title, String description, int imageResource, Class<?> destinationActivity) {
        this.title = title;
        this.description = description;
        this.imageResource = imageResource;
        this.destinationActivity = destinationActivity;
    }

    // スコアを加算するメソッド
    public void addScore(int points) {
        this.score += points;
    }

    public void startDestinationActivity(Context context) {
        Intent intent = new Intent(context, destinationActivity);
        context.startActivity(intent);
    }
}
