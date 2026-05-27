package com.example.cakev3;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class ToppingPage extends AppCompatActivity {

    ImageView backBtn, nextBtn, csprinkles, sprinkles, cchocolatechips, chocolatechips, cfruits, fruits, ccandy, candy;
    CakePreviewView cakePreview;

    final int SPRINKLES = 40, CHOCOC = 50, FRUITS = 60, CANDY = 30;
    int baseT = 0, totalInNumT = 0, totalUpT = 0;
    String flavor = "", size = "", icing = "", topping = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.topping_page);

        backBtn = findViewById(R.id.backBtn);
        nextBtn = findViewById(R.id.nextBtn);
        csprinkles = findViewById(R.id.csprinkles); sprinkles = findViewById(R.id.sprinkles);
        cchocolatechips = findViewById(R.id.cchocolatechips); chocolatechips = findViewById(R.id.chocolatechips);
        cfruits = findViewById(R.id.cfruits); fruits = findViewById(R.id.fruits);
        ccandy = findViewById(R.id.ccandy); candy = findViewById(R.id.candy);
        cakePreview = findViewById(R.id.cakePreview);

        Intent in = getIntent();
        flavor = in.getStringExtra("FLAVOR");
        size = in.getStringExtra("SIZE");
        icing = in.getStringExtra("ICING");
        totalInNumT = in.getIntExtra("TOTALNUM", 0);
        cakePreview.update(flavor, size, icing, "");

        backBtn.setOnClickListener(v -> { startActivity(new Intent(this, IcingPage.class)); finish(); });

        nextBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, summary.class);
            intent.putExtra("FLAVOR", flavor);
            intent.putExtra("SIZE", size);
            intent.putExtra("ICING", icing);
            intent.putExtra("TOPPING", topping);
            intent.putExtra("TOTALNUM", totalUpT);
            startActivity(intent);
        });

        sprinkles.setOnClickListener(v -> selectTopping("SPRINKLES", SPRINKLES, sprinkles, csprinkles));
        chocolatechips.setOnClickListener(v -> selectTopping("CHOCOLATE CHIPS", CHOCOC, chocolatechips, cchocolatechips));
        fruits.setOnClickListener(v -> selectTopping("FRUITS", FRUITS, fruits, cfruits));
        candy.setOnClickListener(v -> selectTopping("CANDY", CANDY, candy, ccandy));
    }

    void selectTopping(String name, int price, ImageView normal, ImageView checked) {
        resetImages();
        topping = name;
        baseT = price;
        totalUpT = totalInNumT + price;
        normal.setVisibility(View.GONE);
        checked.setVisibility(View.VISIBLE);
        cakePreview.update(flavor, size, icing, topping);
    }

    void resetImages() {
        sprinkles.setVisibility(View.VISIBLE);      csprinkles.setVisibility(View.GONE);
        chocolatechips.setVisibility(View.VISIBLE); cchocolatechips.setVisibility(View.GONE);
        fruits.setVisibility(View.VISIBLE);         cfruits.setVisibility(View.GONE);
        candy.setVisibility(View.VISIBLE);          ccandy.setVisibility(View.GONE);
    }
}
