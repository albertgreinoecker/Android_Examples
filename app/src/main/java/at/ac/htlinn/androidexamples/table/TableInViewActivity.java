package at.ac.htlinn.androidexamples.table;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Point;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.google.android.material.snackbar.Snackbar;

import at.ac.htlinn.androidexamples.R;

/**
 * Demo for generating a table within an Activity
 * it shows:
 * * how to fill a Table with buttons
 * * Tags are a useful strategy to store non vivisble information for view (here: button)
 * * Usage of a Snackbar
 */
public class TableInViewActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_in_view);

        TableLayout table = findViewById(R.id.mytable);

        for (int i = 1; i <= 10;i++)
        {
            TableRow row = new TableRow(this);
            for (int j = 1; j <= 4; j++)
            {
                Button b = new Button(this);
                b.setGravity(Gravity.CENTER_HORIZONTAL);
                b.setTag(new Point(i,j));
                b.setText(String.format("(%d,%d)", i,j));
                b.setOnClickListener(this);
                row.addView(b);
            }
            table.addView(row);
        }
    }

    @Override
    public void onClick(View view) {
        Point p = (Point)view.getTag();
        String pStr = p.x + " , " + p.y;
        Snackbar.make(view,pStr, Snackbar.LENGTH_LONG).show();
    }
}