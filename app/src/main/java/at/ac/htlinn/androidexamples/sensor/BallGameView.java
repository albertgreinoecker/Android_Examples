package at.ac.htlinn.androidexamples.sensor;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class BallGameView extends View {
    Paint p = new Paint();
    private float cx;
    private float cy;
    private float[] orientation;
    public BallGameView(Context context) {
        super(context);
        cx = this.getWidth() / 2;
        cy = this.getHeight() / 2;
        orientation = new float[3];
    }

    public float getCx() {
        return cx;
    }

    public void setCx(float cx) {
        this.cx = cx;
    }

    public float getCy() {
        return cy;
    }

    public void setCy(float cy) {
        this.cy = cy;
    }

    public void setOrientation(float[] orientation) {
        this.orientation = orientation;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.BLACK);
        p.setColor(Color.RED);
        canvas.drawCircle(cx, cy, 30, p);
        p.setTextSize(40);
        canvas.drawText(String.format("%s:%.4f", "azimut", orientation[0]), 10,50,p);
        canvas.drawText(String.format("%s:%.4f", "pitch", orientation[1]), 10,100,p);
        canvas.drawText(String.format("%s:%.4f", "roll", orientation[2]), 10,150,p);
     }
}
