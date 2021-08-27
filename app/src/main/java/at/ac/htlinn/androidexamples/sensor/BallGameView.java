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

    public BallGameView(Context context) {
        super(context);
        cx = this.getWidth() / 2;
        cy = this.getHeight() / 2;
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


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.BLACK);
        p.setColor(Color.RED);
        canvas.drawCircle(cx, cy, 30, p);
    }
}
