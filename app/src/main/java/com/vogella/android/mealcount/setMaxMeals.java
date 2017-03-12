package com.vogella.android.mealcount;

import android.content.SharedPreferences;
import android.preference.Preference;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.content.Context;


public class setMaxMeals extends AppCompatActivity {
    EditText setMaxEditText;
    Button set;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_max_meals);



    /* Get the intent that started this activity */
        Intent intent = getIntent();
        sharedPreferences = getSharedPreferences(MainActivity.MyPREFERENCES, MODE_PRIVATE);
        set = (Button) findViewById(R.id.set);
        setMaxEditText = (EditText) findViewById(R.id.maxMealsEditText);


/* change max meals when the button is pressed */
        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* Is there a better way to do this? */
                int maxMeals = Integer.valueOf(setMaxEditText.getText().toString());
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("maxMeals", maxMeals);
                editor.commit();
            }
        });


    }


}

