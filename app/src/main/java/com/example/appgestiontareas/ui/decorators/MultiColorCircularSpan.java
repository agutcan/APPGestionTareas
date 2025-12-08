package com.example.appgestiontareas.ui.decorators;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.text.style.LineBackgroundSpan;

public class MultiColorCircularSpan implements LineBackgroundSpan {

    private final int color1;
    private final float percentage1;

    private final Integer color2;
    private final Float percentage2;

    public MultiColorCircularSpan(int color1, float percentage1) {
        this.color1 = color1;
        this.percentage1 = percentage1;
        this.color2 = null;
        this.percentage2 = null;
    }

    public MultiColorCircularSpan(int color1, float percentage1, int color2, float percentage2) {
        this.color1 = color1;
        this.percentage1 = percentage1;
        this.color2 = color2;
        this.percentage2 = percentage2;
    }

    @Override
    public void drawBackground(Canvas c, Paint p, int left, int right, int top,
                               int baseline, int bottom, CharSequence text,
                               int start, int end, int lnum) {

        float width = right - left;
        float height = bottom - top;
        float size = Math.min(width, height);

        float cx = left + width / 2;
        float cy = top + height / 2;
        float radius = size;

        RectF oval = new RectF(
                cx - radius,
                cy - radius,
                cx + radius,
                cy + radius
        );

        int originalColor = p.getColor();
        Paint.Style originalStyle = p.getStyle();
        boolean originalAA = p.isAntiAlias();

        p.setStyle(Paint.Style.FILL);
        p.setAntiAlias(true);

        float startAngle = -90f;

        // Primer sector
        p.setColor(color1);
        float sweep1 = 360f * percentage1;
        c.drawArc(oval, startAngle, sweep1, true, p);

        // Segundo sector (si existe)
        if (color2 != null && percentage2 != null && percentage2 > 0f) {
            p.setColor(color2);
            float sweep2 = 360f * percentage2;
            c.drawArc(oval, startAngle + sweep1, sweep2, true, p);
        }

        p.setColor(originalColor);
        p.setStyle(originalStyle);
        p.setAntiAlias(originalAA);
    }
}