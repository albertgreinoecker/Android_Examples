package at.ac.htlinn.androidexamples.simplecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

import at.ac.htlinn.androidexamples.R;

public class SimpleCalculatorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simple_calculator);
    }

    public void calculate(View view) {
        double res = 0;
        Spinner operSpinner = findViewById(R.id.oper);
        double op1 = Double.parseDouble(((TextView)findViewById(R.id.op1)).getText().toString());
        double op2 = Double.parseDouble(((TextView)findViewById(R.id.op2)).getText().toString());
        String  oper = (String)operSpinner.getSelectedItem();
        switch (oper)
        {
            case "+": res = op1 + op2; break;
            case "-": res = op1 - op2; break;
            case "*": res = op1 * op2; break;
            case "/": res = op1 / op2; break;
        }
        TextView resV = findViewById(R.id.result);
        resV.setText(res +"");

    }
}