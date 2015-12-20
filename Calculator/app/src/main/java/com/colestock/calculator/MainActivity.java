package com.colestock.calculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText operand1;
    private EditText operand2;
    private Button addButton;
    private Button subtractButton;
    private Button multiplyButton;
    private Button divideButton;
    private Button clearButton;
    private TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        operand1 = (EditText) findViewById(R.id.operand1);
        operand2 = (EditText) findViewById(R.id.operand2);
        addButton = (Button) findViewById(R.id.addButton);
        subtractButton = (Button) findViewById(R.id.subtractButton);
        multiplyButton = (Button) findViewById(R.id.multiplyButton);
        divideButton = (Button) findViewById(R.id.divideButton);
        clearButton = (Button) findViewById(R.id.clearButton);
        result = (TextView) findViewById(R.id.result);

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operand1.setText("");
                operand2.setText("");
                result.setText("0.00");
                operand1.requestFocus();
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((operand1.getText().length() > 0) && (operand2.getText().length() > 0)) {
                    double oper1 = Double.parseDouble(operand1.getText().toString());
                    double oper2 = Double.parseDouble(operand2.getText().toString());
                    double answer = oper1 + oper2;
                    result.setText(Double.toString(answer));
                } else {
                    Toast.makeText(MainActivity.this, "You must provide two values!", Toast.LENGTH_LONG).show();
                }
            }

        });

        subtractButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((operand1.getText().length() > 0) && (operand2.getText().length() > 0)) {
                    double oper1 = Double.parseDouble(operand1.getText().toString());
                    double oper2 = Double.parseDouble(operand2.getText().toString());
                    double answer = oper1 - oper2;
                    result.setText(Double.toString(answer));
                } else {
                    Toast.makeText(MainActivity.this, "You must provide two values!", Toast.LENGTH_LONG).show();
                }
            }

        });

        multiplyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((operand1.getText().length() > 0) && (operand2.getText().length() > 0)) {
                    double oper1 = Double.parseDouble(operand1.getText().toString());
                    double oper2 = Double.parseDouble(operand2.getText().toString());
                    double answer = oper1 * oper2;
                    result.setText(Double.toString(answer));
                } else {
                    Toast.makeText(MainActivity.this, "You must provide two values!", Toast.LENGTH_LONG).show();
                }
            }

        });

       divideButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((operand1.getText().length() > 0) && (operand2.getText().length() > 0)) {
                    double oper1 = Double.parseDouble(operand1.getText().toString());
                    double oper2 = Double.parseDouble(operand2.getText().toString());
                    double answer = oper1 / oper2;
                    result.setText(Double.toString(answer));
                } else {
                    Toast.makeText(MainActivity.this, "You must provide two values!", Toast.LENGTH_LONG).show();
                }
            }

        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
