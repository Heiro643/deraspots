package jp.ac.meijou.android.deraspots;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import jp.ac.meijou.android.deraspots.databinding.ActivityMain4Binding;
import jp.ac.meijou.android.deraspots.databinding.ActivityMain6Binding;
import jp.ac.meijou.android.deraspots.databinding.ActivityMain7Binding;
import jp.ac.meijou.android.deraspots.databinding.ActivityMain8Binding;

public class MainActivity8 extends AppCompatActivity {

    private ActivityMain8Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMain8Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        binding.mapButton.setOnClickListener(v -> {
            Uri gmmIntentUri = Uri.parse("geo:35.1565379,136.9818099?q=Higashiyama+Zoo+and+Botanical+Gardens");
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
            mapIntent.setPackage("com.google.android.apps.maps");
            startActivity(mapIntent);
        });

        binding.officialButton.setOnClickListener(v -> {
            var intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("https://www.higashiyama.city.nagoya.jp/"));
            startActivity(intent);
        });

        binding.backButton.setOnClickListener(v -> {
            finish();
        });
    }
}