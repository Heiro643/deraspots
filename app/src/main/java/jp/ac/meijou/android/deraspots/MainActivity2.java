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

        // EditTextの入力監視（人数が0より大きい場合のみボタンを有効化）
        binding.editPeople.addTextChangedListener(new android.text.TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    // 入力された文字列を整数に変換
                    int people = Integer.parseInt(s.toString());
                    // 人数が1以上ならボタンを有効化
                    binding.buttonSearch.setEnabled(people > 0);
                } catch (NumberFormatException e) {
                    // 数字に変換できない（空欄など）場合はボタンを無効化
                    binding.buttonSearch.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(android.text.Editable s) {}
        });

        // 検索ボタンがクリックされた時の処理
        binding.buttonSearch.setOnClickListener( view -> {
            // MainActivity3へのIntentを作成
            var intent = new Intent(this, MainActivity3.class);

            // 1. 人数を取得してIntentに追加
            int people = Integer.parseInt(binding.editPeople.getText().toString());
            intent.putExtra("people", people);

            // 2. 関係性を取得してIntentに追加
            // getSelectedItemPosition()は選択された項目のインデックス(0から始まる整数)を返す
            // (0:家族, 1:恋人, 2:友達)
            int relationship = binding.spinnerRelationship.getSelectedItemPosition();
            intent.putExtra("relationship", relationship);

            // 3. 時間帯を取得してIntentに追加
            // (0:朝から, 1:昼から, 2:夜から)
            int timezone = binding.spinnerTimezone.getSelectedItemPosition();
            intent.putExtra("timezone", timezone);

            // MainActivity3を開始
            startActivity(intent);
        });
    }
}
