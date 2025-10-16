package jp.ac.meijou.android.deraspots;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import jp.ac.meijou.android.deraspots.databinding.ActivityMain2Binding;

public class MainActivity2 extends AppCompatActivity {

    private ActivityMain2Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMain2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        
        // EditTextの入力監視
        binding.editPeople.addTextChangedListener(new android.text.TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    int people = Integer.parseInt(s.toString());
                    binding.buttonSearch.setEnabled(people > 0);
                } catch (NumberFormatException e) {
                    binding.buttonSearch.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(android.text.Editable s) {}
        });

        binding.buttonSearch.setOnClickListener( view -> {
            int people = Integer.parseInt(binding.editPeople.getText().toString());
            var intent = new Intent(this, MainActivity3.class);
            intent.putExtra("people",people);
            startActivity(intent);
        });
    }
}