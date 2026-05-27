package com.example.cakev3;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class FlavorPage extends AppCompatActivity {

    ImageView backBtn, nextBtn, cchocolate, chocolate, cvanilla, vanilla, cmatcha, matcha, cstrawberry, strawberry;
    CakePreviewView cakePreview;

    final int CHOCOLATE = 100, VANILLA = 100, STRAWBERRY = 120, MATCHA = 120;
    int base = 0;
    int totalInNum = 0;
    String flavor = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flavor_page);

        backBtn = findViewById(R.id.backBtn);
        nextBtn = findViewById(R.id.nextBtn);
        cchocolate = findViewById(R.id.cchocolate);
        chocolate = findViewById(R.id.chocolate);
        cvanilla = findViewById(R.id.cvanilla);
        vanilla = findViewById(R.id.vanilla);
        cmatcha = findViewById(R.id.cmatcha);
        matcha = findViewById(R.id.matcha);
        cstrawberry = findViewById(R.id.cstrawberry);
        strawberry = findViewById(R.id.strawberry);
        cakePreview = findViewById(R.id.cakePreview);

        backBtn.setOnClickListener(v -> {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        });

        nextBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, SizePage.class);
            intent.putExtra("FLAVOR", flavor);
            intent.putExtra("TOTALNUM", totalInNum);
            startActivity(intent);
        });

        chocolate.setOnClickListener(v -> selectFlavor("CHOCOLATE", CHOCOLATE, chocolate, cchocolate));
        vanilla.setOnClickListener(v -> selectFlavor("VANILLA", VANILLA, vanilla, cvanilla));
        strawberry.setOnClickListener(v -> selectFlavor("STRAWBERRY", STRAWBERRY, strawberry, cstrawberry));
        matcha.setOnClickListener(v -> selectFlavor("MATCHA", MATCHA, matcha, cmatcha));
    }

    void selectFlavor(String name, int price, ImageView normal, ImageView checked) {
        resetImages();
        flavor = name;
        base = price;
        totalInNum = price;
        normal.setVisibility(View.GONE);
        checked.setVisibility(View.VISIBLE);
        cakePreview.update(flavor, "", "", "");
    }

    void resetImages() {
        chocolate.setVisibility(View.VISIBLE); cchocolate.setVisibility(View.GONE);
        vanilla.setVisibility(View.VISIBLE);   cvanilla.setVisibility(View.GONE);
        strawberry.setVisibility(View.VISIBLE);cstrawberry.setVisibility(View.GONE);
        matcha.setVisibility(View.VISIBLE);    cmatcha.setVisibility(View.GONE);
    }
}
