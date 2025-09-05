package jp.ac.meijou.android.deraspots;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import jp.ac.meijou.android.deraspots.databinding.ActivityMain3Binding;

public class MainActivity3 extends AppCompatActivity {

    private ActivityMain3Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMain3Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        int people =getIntent().getIntExtra("people",0);

        if (people <= 2){
            binding.itemTextTitle.setText("名古屋PARCO");
            binding.itemTextExplain.setText("　地下鉄矢場町駅直結の約300店舗が集まる巨大な商業施設。" +
                    "最新のファッションから雑貨、グルメなどが楽しめます。");
            binding.itemImage.setImageResource(R.drawable.parco);
            binding.itemButton.setOnClickListener(view -> {
                var intent = new Intent(this,MainActivity4.class);
                startActivity(intent);
            });
        }
        else{
            binding.itemTextTitle.setText("名古屋港水族館");
            binding.itemTextExplain.setText("　日本最大級の水族館。" +
                    "シャチやイルカの迫力あるショーやペンギンたちに会える人気スポット。" +
                    "家族や友達と楽しめる名古屋の定番おでかけ先です。");
            binding.itemImage.setImageResource(R.drawable.nagoya_aquarium1);
            binding.itemButton.setOnClickListener(view -> {
                var intent = new Intent(this,MainActivity5.class);
                startActivity(intent);
            });
        }


    }
}