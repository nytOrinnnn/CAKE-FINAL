package com.example.cakev3;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class summary extends AppCompatActivity {

    ImageView backBtn, nextBtn;
    TextView total, sum;
    CakePreviewView cakePreview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.summary_page);

        backBtn = findViewById(R.id.backBtn);
        nextBtn = findViewById(R.id.nextBtn);
        total = findViewById(R.id.total);
        sum = findViewById(R.id.sum);
        cakePreview = findViewById(R.id.cakePreview);

        Intent in = getIntent();
        String flavor   = in.getStringExtra("FLAVOR");
        String size     = in.getStringExtra("SIZE");
        String icing    = in.getStringExtra("ICING");
        String topping  = in.getStringExtra("TOPPING");
        int totalNum    = in.getIntExtra("TOTALNUM", 0);

        String summaryText =
                "FLAVOR: " + flavor + "\n" +
                "SIZE: " + size + "\n" +
                "ICING: " + icing + "\n" +
                "TOPPING: " + topping;

        sum.setText(summaryText);
        total.setText("TOTAL: ₱" + totalNum);
        cakePreview.update(flavor, size, icing, topping);

        backBtn.setOnClickListener(v -> { startActivity(new Intent(this, ToppingPage.class)); finish(); });

        nextBtn.setOnClickListener(v -> {
            // Save to Room DB on background thread
            CakeOrder order = new CakeOrder(flavor, size, icing, topping, totalNum, System.currentTimeMillis());
            ExecutorService executor = Executors.newSingleThreadExecutor();
            executor.execute(() -> {
                CakeDatabase.getInstance(this).cakeOrderDao().insertOrder(order);
                runOnUiThread(() -> {
                    startActivity(new Intent(this, MainActivity.class));
                    finish();
                });
            });
            executor.shutdown();
        });
    }
}
