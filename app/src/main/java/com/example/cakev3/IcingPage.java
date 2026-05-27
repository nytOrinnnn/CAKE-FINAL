package com.example.cakev3;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class IcingPage extends AppCompatActivity {

    ImageView backBtn, nextBtn, cchocog, chocog, ccheese, cheese, cube, ube, cbutter, butter;
    CakePreviewView cakePreview;

    final int CHOCOG = 110, BUTTER = 120, CHEESE = 120, UBE = 130;
    int baseI = 0, totalInNumI = 0, totalUpI = 0;
    String flavor = "", size = "", icing = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.icing_page);

        backBtn = findViewById(R.id.backBtn);
        nextBtn = findViewById(R.id.nextBtn);
        cbutter = findViewById(R.id.cbutter); butter = findViewById(R.id.butter);
        cchocog = findViewById(R.id.cchocog); chocog = findViewById(R.id.chocog);
        ccheese = findViewById(R.id.ccheese); cheese = findViewById(R.id.cheese);
        cube = findViewById(R.id.cube); ube = findViewById(R.id.ube);
        cakePreview = findViewById(R.id.cakePreview);

        Intent in = getIntent();
        flavor = in.getStringExtra("FLAVOR");
        size = in.getStringExtra("SIZE");
        totalInNumI = in.getIntExtra("TOTALNUM", 0);
        cakePreview.update(flavor, size, "", "");

        backBtn.setOnClickListener(v -> { startActivity(new Intent(this, SizePage.class)); finish(); });

        nextBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, ToppingPage.class);
            intent.putExtra("FLAVOR", flavor);
            intent.putExtra("SIZE", size);
            intent.putExtra("ICING", icing);
            intent.putExtra("TOTALNUM", totalUpI);
            startActivity(intent);
        });

        chocog.setOnClickListener(v -> selectIcing("CHOCO GANACHE", CHOCOG, chocog, cchocog));
        butter.setOnClickListener(v -> selectIcing("BUTTER CREAM", BUTTER, butter, cbutter));
        cheese.setOnClickListener(v -> selectIcing("CREAM CHEESE", CHEESE, cheese, ccheese));
        ube.setOnClickListener(v -> selectIcing("UBE", UBE, ube, cube));
    }

    void selectIcing(String name, int price, ImageView normal, ImageView checked) {
        resetImages();
        icing = name;
        baseI = price;
        totalUpI = totalInNumI + price;
        normal.setVisibility(View.GONE);
        checked.setVisibility(View.VISIBLE);
        cakePreview.update(flavor, size, icing, "");
    }

    void resetImages() {
        chocog.setVisibility(View.VISIBLE); cchocog.setVisibility(View.GONE);
        butter.setVisibility(View.VISIBLE); cbutter.setVisibility(View.GONE);
        cheese.setVisibility(View.VISIBLE); ccheese.setVisibility(View.GONE);
        ube.setVisibility(View.VISIBLE);    cube.setVisibility(View.GONE);
    }
}
