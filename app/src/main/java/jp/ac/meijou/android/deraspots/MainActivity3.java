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
import java.util.Collections;
import java.util.Comparator;
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
        List<Spot> allSpots = new ArrayList<>();

        /**
         * 例）
         * allSpots.add(new Spot(
         *      "場所",
         *      "場所の説明(50~75文字程度)",
         *      R.drawable.画像名,
         *      MainActivity.class
         * ));
         */

        //他にも候補があればどんどん追加

        if (relationship == 1 && people <= 2 && (timezone == 1 || timezone == 2)) {
            allSpots.add(new Spot(
                    "名古屋PARCO",
                    "　地下鉄矢場町駅直結の約300店舗が集まる巨大な商業施設。最新のファッションから雑貨、グルメなどが楽しめます。",
                    R.drawable.parco,
                    MainActivity4.class
            ));
        }
      
        if (people >= 1) {
            allSpots.add(new Spot(
                    "名古屋港水族館",
                    "　名古屋にある、カラフルなレゴブロックの冒険の国。迫力ある乗り物から、自分の手で組み立てるワークショップまで遊びが盛りだくさん。お子様の創造力を刺激する、笑顔あふれるテーマパークです。",
                    R.drawable.nagoya_aquarium1,
                    MainActivity5.class
            ));
        }

        if (people >= 0) {
            allSpots.add(new Spot(
                    "レゴランド",
                    "　日本最大級の水族館。シャチやイルカの迫力あるショーやペンギンたちに会える人気スポット。家族や友達と楽しめる名古屋の定番おでかけ先です。",
                    R.drawable.lego,
                    MainActivity6.class
            ));
        }

        if (people >= 0) {
            allSpots.add(new Spot(
                    "名古屋城",
                    "　尾張徳川家の栄華を伝えるランドマーク。壮大な石垣や美しい庭園、重要文化財など見どころが満載。散策しながら歴史を楽しめる、名古屋の定番観光地です。",
                    R.drawable.nagoya_castle,
                    MainActivity7.class
            ));
        }

        if (people >= 0) {
            allSpots.add(new Spot(
                    "東山動植物園",
                    "　豊かな自然の中で、多種多様な生き物と触れ合える場所。話題の動物たちや季節の花々、秋の紅葉など魅力が満載。子供から大人まで楽しめる、名古屋の定番お出かけ先です。",
                    R.drawable.higashi,
                    MainActivity8.class
            ));
        }

        if (people >= 0) {
            allSpots.add(new Spot(
                    "名古屋市科学館",
                    "　ギネス記録を持つプラネタリウムを備える科学の殿堂。遊びながら学べる展示や実演が豊富で、一日中飽きさせません。家族連れやデートにもおすすめの、名古屋の定番観光地です。",
                    R.drawable.science,
                    MainActivity9.class
            ));
        }

        if (people >= 0) {
            allSpots.add(new Spot(
                    "ジブリパーク",
                    "　数々のジブリ作品の舞台を再現した夢のエリア。名作の世界観に包まれながら、展示を見たり、カフェでくつろいだりとゆったり過ごせます。子供から大人まで、ジブリの魔法を感じられる特別な場所です。",
                    R.drawable.ghibli,
                    MainActivity10.class
            ));
        }

        if (timezone == 2) {
            // spots.add(new Spot("オアシス21", "...", R.drawable.oasis21, MainActivity6.class));
        }
      
        //スコア計算
        for (Spot spot : allSpots) {
            calculateScore(spot, people, relationship, timezone);
        }        
        //スコアの高い順で並べ替え
        Collections.sort(allSpots, new Comparator<Spot>() {
            @Override
            public int compare(Spot s1, Spot s2) {
                // s2.score - s1.score で降順 (スコアの高い順) になる
                return s2.score - s1.score;
            }
        });

        //0より大きいものを返す
        List<Spot> rankedSpots = new ArrayList<>();
        for (Spot spot : allSpots) {
            if (spot.score > 0) {
                rankedSpots.add(spot);
            }
        }

        //もしどの条件にも一致しなかった場合
        if (rankedSpots.isEmpty()) {
            rankedSpots.add(new Spot(
                    "該当なし",
                    "条件に合うスポットが見つかりませんでした。",
                    R.drawable.deraspot_logo,
                    MainActivity2.class));
        }

        return rankedSpots;
    }

    /**
     * 各スポットのスコアを計算するメソッド
     * 例）
     * case "場所":
     *      if (条件) spot.addScore(数字+1~3か合わないものはマイナス10まで);
     *      条件は&&も用いて、さらに加点、減点なども作るとより正確になるかも
     *      ...
     *      break;
     */
    private void calculateScore(Spot spot, int people, int relationship, int timezone) {
        // spot.title を使って、どの観光地かを判定
        switch (spot.title) {
            case "名古屋PARCO":
                //恋人(1)と来たら +3点
                if (relationship == 1) spot.addScore(3);
                //友達(2)と来たら +1点
                if (relationship == 2) spot.addScore(1);
                //2人以下なら +2点
                if (people <= 2) spot.addScore(2);
                //昼(1)か夜(2)なら +2点
                if (timezone == 1 || timezone == 2) spot.addScore(2);

                //減点
                //大人数の家族はお買い物には大変
                if (relationship == 0 && people >= 5) {
                    spot.addScore(-5);
                }
                break;

            case "名古屋港水族館":
                //家族(0)と来たら +3点
                if (relationship == 0) spot.addScore(3);
                //友達(2)か恋人(1)と来たら +1点
                if (relationship == 1 || relationship == 2) spot.addScore(1);
                //3人以上なら +2点
                if (people >= 3) spot.addScore(2);
                //2人以下なら +1点
                if (people <= 2) spot.addScore(1);
                //朝(0)か昼(1)なら +2点
                if (timezone == 0 || timezone == 1) spot.addScore(2);

                //減点
                // 夜から(2)は閉館している可能性が高いので大きく減点
                if (timezone == 2) {
                    spot.addScore(-10);
                }
                break;

            // 他のスポットのスコアルールもここに追加

        }
    }
}