// MainActivity3.java
package jp.ac.meijou.android.deraspots;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

import jp.ac.meijou.android.deraspots.databinding.ActivityMain3Binding;

public class MainActivity3 extends AppCompatActivity {

    private ActivityMain3Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMain3Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.backButtonMain3.setOnClickListener(v -> {
            finish();
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        int people = getIntent().getIntExtra("people", 2);
        int relationship = getIntent().getIntExtra("relationship", 2);
        int timezone = getIntent().getIntExtra("timezone", 1);

        List<Spot> spotList = createSpotList(people, relationship, timezone);

        SpotAdapter adapter = new SpotAdapter(spotList);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setAdapter(adapter);
    }


    /**
     * 表示する観光地のリストを作成するメソッド
     */
    private List<Spot> createSpotList(int people, int relationship, int timezone) {
        List<Spot> spots = new ArrayList<>();

        if (relationship == 1 && people <= 2 && (timezone == 1 || timezone == 2)) {
            spots.add(new Spot(
                    "名古屋PARCO",
                    "　地下鉄矢場町駅直結の約300店舗が集まる巨大な商業施設。最新のファッションから雑貨、グルメなどが楽しめます。",
                    R.drawable.parco,
                    MainActivity4.class
            ));
        }

        if (people >= 1) {
            spots.add(new Spot(
                    "名古屋港水族館",
                    "　名古屋にある、カラフルなレゴブロックの冒険の国。迫力ある乗り物から、自分の手で組み立てるワークショップまで遊びが盛りだくさん。お子様の創造力を刺激する、笑顔あふれるテーマパークです。",
                    R.drawable.nagoya_aquarium1,
                    MainActivity5.class
            ));
        }

        if (people >= 0) {
            spots.add(new Spot(
                    "レゴランド",
                    "　日本最大級の水族館。シャチやイルカの迫力あるショーやペンギンたちに会える人気スポット。家族や友達と楽しめる名古屋の定番おでかけ先です。",
                    R.drawable.lego,
                    MainActivity6.class
            ));
        }

        if (people >= 0) {
            spots.add(new Spot(
                    "名古屋城",
                    "　尾張徳川家の栄華を伝えるランドマーク。壮大な石垣や美しい庭園、重要文化財など見どころが満載。散策しながら歴史を楽しめる、名古屋の定番観光地です。",
                    R.drawable.nagoya_castle,
                    MainActivity7.class
            ));
        }

        if (people >= 0) {
            spots.add(new Spot(
                    "東山動植物園",
                    "　豊かな自然の中で、多種多様な生き物と触れ合える場所。話題の動物たちや季節の花々、秋の紅葉など魅力が満載。子供から大人まで楽しめる、名古屋の定番お出かけ先です。",
                    R.drawable.higashi,
                    MainActivity8.class
            ));
        }

        if (people >= 0) {
            spots.add(new Spot(
                    "名古屋市科学館",
                    "　ギネス記録を持つプラネタリウムを備える科学の殿堂。遊びながら学べる展示や実演が豊富で、一日中飽きさせません。家族連れやデートにもおすすめの、名古屋の定番観光地です。",
                    R.drawable.science,
                    MainActivity9.class
            ));
        }

        if (people >= 0) {
            spots.add(new Spot(
                    "ジブリパーク",
                    "　数々のジブリ作品の舞台を再現した夢のエリア。名作の世界観に包まれながら、展示を見たり、カフェでくつろいだりとゆったり過ごせます。子供から大人まで、ジブリの魔法を感じられる特別な場所です。",
                    R.drawable.ghibli,
                    MainActivity10.class
            ));
        }

        if (timezone == 2) {
            // spots.add(new Spot("オアシス21", "...", R.drawable.oasis21, MainActivity6.class));
        }

        if (spots.isEmpty()) {
            spots.add(new Spot(
                    "該当なし",
                    "条件に合うスポットが見つかりませんでした。",
                    R.drawable.deraspot_logo,
                    MainActivity2.class));
        }

        return spots;
    }
}