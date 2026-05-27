package com.example.cakev3;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class SizePage extends AppCompatActivity {

    ImageView backBtn, nextBtn, csmall, small, cmedium, medium, clarge, large, cxlarge, xlarge;
    CakePreviewView cakePreview;

    final int SMALL = 150, MEDIUM = 200, LARGE = 250, XLARGE = 300;
    int baseS = 0, totalInNumS = 0, totalUpS = 0;
    String flavor = "", size = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.size_page);

        backBtn = findViewById(R.id.backBtn);
        nextBtn = findViewById(R.id.nextBtn);
        csmall = findViewById(R.id.csmall); small = findViewById(R.id.small);
        cmedium = findViewById(R.id.cmedium); medium = findViewById(R.id.medium);
        clarge = findViewById(R.id.clarge); large = findViewById(R.id.large);
        cxlarge = findViewById(R.id.cxlarge); xlarge = findViewById(R.id.xlarge);
        cakePreview = findViewById(R.id.cakePreview);

        Intent in = getIntent();
        flavor = in.getStringExtra("FLAVOR");
        totalInNumS = in.getIntExtra("TOTALNUM", 0);
        cakePreview.update(flavor, "", "", "");

        backBtn.setOnClickListener(v -> { startActivity(new Intent(this, FlavorPage.class)); finish(); });

        nextBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, IcingPage.class);
            intent.putExtra("FLAVOR", flavor);
            intent.putExtra("SIZE", size);
            intent.putExtra("TOTALNUM", totalUpS);
            startActivity(intent);
        });

        small.setOnClickListener(v -> selectSize("SMALL", SMALL, small, csmall));
        medium.setOnClickListener(v -> selectSize("MEDIUM", MEDIUM, medium, cmedium));
        large.setOnClickListener(v -> selectSize("LARGE", LARGE, large, clarge));
        xlarge.setOnClickListener(v -> selectSize("EXTRA LARGE", XLARGE, xlarge, cxlarge));
    }

    void selectSize(String name, int price, ImageView normal, ImageView checked) {
        resetImages();
        size = name;
        baseS = price;
        totalUpS = totalInNumS + price;
        normal.setVisibility(View.GONE);
        checked.setVisibility(View.VISIBLE);
        cakePreview.update(flavor, size, "", "");
    }

    void resetImages() {
        small.setVisibility(View.VISIBLE);  csmall.setVisibility(View.GONE);
        medium.setVisibility(View.VISIBLE); cmedium.setVisibility(View.GONE);
        large.setVisibility(View.VISIBLE);  clarge.setVisibility(View.GONE);
        xlarge.setVisibility(View.VISIBLE); cxlarge.setVisibility(View.GONE);
    }
}
