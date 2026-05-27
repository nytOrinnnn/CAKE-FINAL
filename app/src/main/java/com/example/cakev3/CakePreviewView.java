package com.example.cakev3;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

public class CakePreviewView extends View {

    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    // Current selections
    private String flavor = "";
    private String size = "";
    private String icing = "";
    private String topping = "";

    public CakePreviewView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void update(String flavor, String size, String icing, String topping) {
        this.flavor = flavor != null ? flavor : "";
        this.size = size != null ? size : "";
        this.icing = icing != null ? icing : "";
        this.topping = topping != null ? topping : "";
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int w = getWidth();
        int h = getHeight();
        float cx = w / 2f;

        // Cake width based on size
        float cakeW;
        switch (size.toUpperCase()) {
            case "SMALL":   cakeW = w * 0.30f; break;
            case "MEDIUM":  cakeW = w * 0.40f; break;
            case "LARGE":   cakeW = w * 0.50f; break;
            case "EXTRA LARGE": cakeW = w * 0.60f; break;
            default:        cakeW = w * 0.35f; break;
        }

        float cakeH = cakeW * 0.55f;
        float bottomY = h * 0.80f;
        float topY = bottomY - cakeH;

        // ---- Plate ----
        paint.setColor(Color.parseColor("#E0E0E0"));
        canvas.drawOval(cx - cakeW * 0.7f, bottomY - 6, cx + cakeW * 0.7f, bottomY + 14, paint);

        // ---- Cake body (flavor color) ----
        int flavorColor;
        switch (flavor.toUpperCase()) {
            case "CHOCOLATE":  flavorColor = Color.parseColor("#6B3A2A"); break;
            case "VANILLA":    flavorColor = Color.parseColor("#F5E6C8"); break;
            case "STRAWBERRY": flavorColor = Color.parseColor("#F4A7B9"); break;
            case "MATCHA":     flavorColor = Color.parseColor("#7DBE8E"); break;
            default:           flavorColor = Color.parseColor("#D4B896"); break;
        }
        paint.setColor(flavorColor);
        RectF bodyRect = new RectF(cx - cakeW, topY + cakeH * 0.12f, cx + cakeW, bottomY);
        canvas.drawRoundRect(bodyRect, 12, 12, paint);

        // ---- Top ellipse (top face of cake) ----
        canvas.drawOval(cx - cakeW, topY, cx + cakeW, topY + cakeH * 0.25f, paint);

        // ---- Icing layer on top ----
        int icingColor;
        switch (icing.toUpperCase()) {
            case "CHOCO GANACHE":  icingColor = Color.parseColor("#3D1C02"); break;
            case "BUTTER CREAM":   icingColor = Color.parseColor("#FFF0B3"); break;
            case "CREAM CHEESE":   icingColor = Color.parseColor("#FFFFFF"); break;
            case "UBE":            icingColor = Color.parseColor("#9B59B6"); break;
            default:               icingColor = Color.parseColor("#F5F5F5"); break;
        }
        paint.setColor(icingColor);
        canvas.drawOval(cx - cakeW, topY, cx + cakeW, topY + cakeH * 0.25f, paint);

        // Icing drip effect on sides
        paint.setAlpha(200);
        float dripH = cakeH * 0.28f;
        RectF dripRect = new RectF(cx - cakeW + 4, topY + cakeH * 0.1f, cx + cakeW - 4, topY + dripH);
        canvas.drawRoundRect(dripRect, 8, 8, paint);
        paint.setAlpha(255);

        // ---- Toppings ----
        switch (topping.toUpperCase()) {
            case "SPRINKLES":
                drawSprinkles(canvas, cx, topY, cakeW); break;
            case "CHOCOLATE CHIPS":
                drawChocolateChips(canvas, cx, topY, cakeW); break;
            case "FRUITS":
                drawFruits(canvas, cx, topY, cakeW); break;
            case "CANDY":
                drawCandy(canvas, cx, topY, cakeW); break;
        }

        // ---- Candles (always 3 cute candles) ----
        drawCandles(canvas, cx, topY, cakeW);

        // ---- Label if nothing chosen ----
        if (flavor.isEmpty() && size.isEmpty() && icing.isEmpty()) {
            paint.setColor(Color.parseColor("#AAAAAA"));
            paint.setTextSize(28f);
            paint.setTextAlign(Paint.Align.CENTER);
            canvas.drawText("Build your cake!", cx, h * 0.92f, paint);
        }
    }

    private void drawSprinkles(Canvas canvas, float cx, float topY, float cakeW) {
        int[] colors = {Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW, Color.MAGENTA};
        float[][] positions = {{-0.4f, 0.05f}, {0.1f, -0.02f}, {-0.1f, 0.06f}, {0.35f, 0.04f}, {0f, -0.04f}, {-0.25f, 0.03f}, {0.2f, 0.07f}};
        for (int i = 0; i < positions.length; i++) {
            paint.setColor(colors[i % colors.length]);
            float x = cx + cakeW * positions[i][0];
            float y = topY + 8 + positions[i][1] * 30;
            canvas.drawRect(x - 6, y - 3, x + 6, y + 3, paint);
        }
    }

    private void drawChocolateChips(Canvas canvas, float cx, float topY, float cakeW) {
        paint.setColor(Color.parseColor("#3D1C02"));
        float[][] pos = {{-0.35f, 0f}, {0.0f, -0.03f}, {0.3f, 0.03f}, {-0.15f, 0.05f}, {0.18f, -0.02f}};
        for (float[] p : pos) {
            canvas.drawCircle(cx + cakeW * p[0], topY + 10 + p[1] * 25, 7, paint);
        }
    }

    private void drawFruits(Canvas canvas, float cx, float topY, float cakeW) {
        // Red strawberries
        paint.setColor(Color.parseColor("#E74C3C"));
        canvas.drawCircle(cx - cakeW * 0.3f, topY + 5, 10, paint);
        canvas.drawCircle(cx + cakeW * 0.15f, topY + 3, 10, paint);
        // Green leaf hint
        paint.setColor(Color.parseColor("#27AE60"));
        canvas.drawCircle(cx - cakeW * 0.3f, topY - 4, 5, paint);
        canvas.drawCircle(cx + cakeW * 0.15f, topY - 6, 5, paint);
        // Yellow bit (banana/mango)
        paint.setColor(Color.parseColor("#F1C40F"));
        canvas.drawCircle(cx + cakeW * 0.35f, topY + 6, 8, paint);
    }

    private void drawCandy(Canvas canvas, float cx, float topY, float cakeW) {
        int[] cols = {Color.parseColor("#E91E63"), Color.parseColor("#2196F3"), Color.parseColor("#FF9800")};
        float[] xs = {cx - cakeW * 0.3f, cx, cx + cakeW * 0.3f};
        for (int i = 0; i < 3; i++) {
            paint.setColor(cols[i]);
            canvas.drawCircle(xs[i], topY + 6, 9, paint);
            paint.setColor(Color.WHITE);
            paint.setAlpha(120);
            canvas.drawCircle(xs[i] - 2, topY + 3, 4, paint);
            paint.setAlpha(255);
        }
    }

    private void drawCandles(Canvas canvas, float cx, float topY, float cakeW) {
        float[] candleX = {cx - cakeW * 0.25f, cx, cx + cakeW * 0.25f};
        int[] candleColors = {Color.parseColor("#E91E63"), Color.parseColor("#2196F3"), Color.parseColor("#FF9800")};
        for (int i = 0; i < 3; i++) {
            float x = candleX[i];
            float candleTop = topY - 32;
            // Candle body
            paint.setColor(candleColors[i]);
            canvas.drawRect(x - 5, candleTop, x + 5, topY + 2, paint);
            // Flame
            paint.setColor(Color.parseColor("#FFD700"));
            canvas.drawOval(x - 5, candleTop - 14, x + 5, candleTop + 2, paint);
            paint.setColor(Color.parseColor("#FF6B00"));
            canvas.drawOval(x - 3, candleTop - 10, x + 3, candleTop, paint);
        }
    }
}
