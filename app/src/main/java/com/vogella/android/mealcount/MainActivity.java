package com.vogella.android.mealcount;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;
import android.content.Context;
import android.os.Vibrator;
import android.content.Intent;
import static android.widget.Toast.LENGTH_LONG;
import static java.lang.String.valueOf;

public class MainActivity extends AppCompatActivity {

    public static final String MyPREFERENCES = "MyPrefs";

    private int total, left;
    public int maxMeals;
    private TextView totalCurrentView;
    private Button countButton, resetButton, uncountButton, setMaximumButton;

    SharedPreferences sharedpreferences;

    Vibrator perfomVibrate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        totalCurrentView = (TextView)findViewById(R.id.totalCurrent);
        uncountButton = (Button)findViewById(R.id.uncount);
        countButton = (Button)findViewById(R.id.Count);
        resetButton = (Button)findViewById(R.id.reset);
        setMaximumButton = (Button) findViewById(R.id.setMaximum);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        perfomVibrate = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        maxMeals = sharedpreferences.getInt("maxMeals", 15);
        total = sharedpreferences.getInt("total", 0);
        left = sharedpreferences.getInt("left", 0);
        totalCurrentView.setText(valueOf(total));


        countButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               perfomVibrate.vibrate(40);
                total = (total + 1) % (maxMeals + 1);
                left = maxMeals - total;
                totalCurrentView.setText(valueOf(total));
                Toast.makeText(MainActivity.this,
                        "You have " + valueOf(left) + " meals left",
                        LENGTH_LONG).show();
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                total = 0;
                left = maxMeals;
                totalCurrentView.setText(valueOf(total));
                Toast.makeText(MainActivity.this,"You have "
                        + valueOf(left) + " meals left", LENGTH_LONG).show();

            }
            });

        uncountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                total--;

                if(total < 0) {
                    total = 0;
                }

                left = maxMeals - total;
                totalCurrentView.setText(valueOf(total));
                Toast.makeText(MainActivity.this,"You have "
                        + valueOf(left) + " meals left", LENGTH_LONG).show();

            }
        });

        /* Clicking the setMaximum button will open set Maximum activity :INTENTS */
        setMaximumButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent setMaximumMeals = new Intent(MainActivity.this, setMaxMeals.class);
                startActivity(setMaximumMeals);
            }
        });
        }

    @Override
    protected void onStop() {
        super.onStop();

        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putInt("total", total);
        editor.putInt("left", left);
        editor.commit();
    }



    public void changeMaxMeals(int num) {
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putInt("maxMeals", num);
    }


    }




