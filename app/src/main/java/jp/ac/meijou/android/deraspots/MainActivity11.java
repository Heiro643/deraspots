package jp.ac.meijou.android.deraspots;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import jp.ac.meijou.android.deraspots.databinding.ActivityRegisterPlaceBinding;

public class MainActivity11 extends AppCompatActivity {

    private ActivityRegisterPlaceBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityRegisterPlaceBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // キャンセル
        binding.buttonCancel.setOnClickListener(v -> finish());

        // 保存
        binding.buttonSave.setOnClickListener(v -> {
            String name = binding.editName.getText().toString().trim();
            String address = binding.editAddress.getText().toString().trim();
            String desc = binding.editDescription.getText().toString().trim();
            String latStr = binding.editLatitude.getText().toString().trim();
            String lngStr = binding.editLongitude.getText().toString().trim();

            if (name.isEmpty()) {
                Toast.makeText(this, "場所名を入力してください", Toast.LENGTH_SHORT).show();
                return;
            }

            Double lat = null, lng = null;
            try { if (!latStr.isEmpty()) lat = Double.parseDouble(latStr); } catch (NumberFormatException ignored) {}
            try { if (!lngStr.isEmpty()) lng = Double.parseDouble(lngStr); } catch (NumberFormatException ignored) {}

            Intent intent = new Intent(this, MainActivity3.class);
            intent.putExtra("new_spot_title", name);
            String combinedDesc = desc;
            if (!address.isEmpty()) {
                if (!combinedDesc.isEmpty()) combinedDesc = address + "\n" + combinedDesc;
                else combinedDesc = address;
            }
            intent.putExtra("new_spot_description", combinedDesc);
            if (lat != null) intent.putExtra("new_spot_latitude", lat);
            if (lng != null) intent.putExtra("new_spot_longitude", lng);

            startActivity(intent);
            finish();
        });
    }
}