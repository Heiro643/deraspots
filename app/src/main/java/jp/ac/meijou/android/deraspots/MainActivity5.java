package jp.ac.meijou.android.deraspots;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import jp.ac.meijou.android.deraspots.databinding.ActivityMain5Binding;

public class MainActivity5 extends AppCompatActivity {

    private ActivityMain5Binding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMain5Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setContentView(R.layout.activity_main5);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        binding.mapButton2.setOnClickListener(v -> {
            Uri gmmIntentUri = Uri.parse("geo:35.1635,136.9086?q=名古屋港水族館");
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
            mapIntent.setPackage("com.google.android.apps.maps");
            startActivity(mapIntent);
        });

        binding.officialButton2.setOnClickListener(v -> {
            var intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("https://nagoyaaqua.jp/"));
            startActivity(intent);
        });

        binding.backButton2.setOnClickListener(v -> {
            var intent = new Intent(this, MainActivity3.class);
            startActivity(intent);
        });
    }//
}