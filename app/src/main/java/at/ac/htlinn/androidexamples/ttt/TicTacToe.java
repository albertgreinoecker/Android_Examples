package at.ac.htlinn.androidexamples.ttt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

import at.ac.htlinn.androidexamples.R;

/**
 * Viee for the Tic Tac Toe game
 * This demo shows how to cope with a programmed Tablelayout
 */
public class TicTacToe extends AppCompatActivity implements View.OnClickListener {
    TTTGame game;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tic_tac_toe);

        TableLayout playField = findViewById(R.id.playfield);
        TableRow tr1 = generateAndAddRows(new int[]{0,1,2});
        TableRow tr2 = generateAndAddRows(new int[]{3,4,5});
        TableRow tr3 = generateAndAddRows(new int[]{6,7,8});
        playField.addView(tr1);
        playField.addView(tr2);
        playField.addView(tr3);
        game = new TTTGame();
    }

    /**
     * Generate a row with nuttons according to <i>tags</i>
     * @param tags the tags of the buttons to be set
     * @return a fully prepared row already containing the buttons
     */
    private TableRow generateAndAddRows( int[] tags)
    {
        TableRow.LayoutParams tableRowParams=
                new TableRow.LayoutParams
                        (TableRow.LayoutParams.MATCH_PARENT,TableRow.LayoutParams.MATCH_PARENT);
        TableRow tr = new TableRow(this);
        tr.setLayoutParams(tableRowParams);
        for (int tag : tags) {
            tr.addView(generateButton(tag));
        }
        return tr;
    }

    /**
     * Generate a button with a certain tag
     * @param tag use this tag to identify the position of the button
     * @return the fully prepared ImageButton
     */
    private ImageButton generateButton(int tag)
    {
        ImageButton b = new ImageButton(this);
        b.setAdjustViewBounds(true);
        b.setAdjustViewBounds(true);
        b.setImageResource(R.drawable.b2);
        b.setTag(tag);
        b.setOnClickListener(this);
        return b;
    }

    /**
     * if any button is pressed....
     * @param view
     */
    @Override
    public void onClick(View view) {
        TextView feedback = findViewById(R.id.feedback);
        int tag = (int)view.getTag();
        boolean ok = game.play(tag);
        if (ok)
        {
            if (game.actPlayer == TTTGame.C.O)
            {
                ((ImageButton)view).setImageResource(R.drawable.x2);
            } else
            {
                ((ImageButton)view).setImageResource(R.drawable.o2);
            }
            if (game.won())
            {
                feedback.setText(R.string.ttt_won);
            } else if (game.full()){
                feedback.setText(R.string.ttt_draw);
            }
            game.changePlayer();
        } else
        {
            feedback.setText(R.string.ttt_illegal_move);
        }

    }
}
