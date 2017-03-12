package com.vogella.android.mealcount;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;


public class setMaxMeals extends AppCompatActivity {

    EditText setMaxEditText;
    Button set;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_max_meals);

        set = (Button) findViewById(R.id.set);
        setMaxEditText = (EditText) findViewById(R.id.maxMealsEditText);

        /* Get the sharedPreferences from the MainActivity */
        sharedPreferences = getSharedPreferences(MainActivity.MyPREFERENCES, MODE_PRIVATE);

        /* Change max meals when the button is pressed */
        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String inputGiven = setMaxEditText.getText().toString();

                if(inputGiven.equals("")) {
                    Toast.makeText(setMaxMeals.this, "You have not entered a number", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    /* Set the maximum number of meals, reset and exit */
                    int maxMeals = Integer.valueOf(inputGiven);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putInt("maxMeals", maxMeals);
                    editor.putInt("total", 0);
                    editor.putInt("left", maxMeals);
                    editor.commit();
                    Toast.makeText(setMaxMeals.this, "Count has been reset", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }
}

