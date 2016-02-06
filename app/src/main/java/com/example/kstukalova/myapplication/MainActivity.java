package com.example.kstukalova.myapplication;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.app.Activity;
import android.widget.EditText;

import java.util.InputMismatchException;

public class MainActivity extends AppCompatActivity {
    ListView list;
    String[] exercise_type = {
            "Pull Ups",
            "Plank",
            "Walking",
            "Jogging",
            "Swimming",
            "Cycling",
            "Stair-Climbing",
            "Squats",
            "Sit Ups",
            "Leg Lifts",
            "Jumping Jacks",
            "Push Ups"
    } ;
    Integer[] minutes_reps_value = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 , 0};
    String[] minutes_reps_text = {
            " reps",
            " minutes",
            " minutes",
            " minutes",
            " minutes",
            " minutes",
            " minutes",
            " reps",
            " reps",
            " minutes",
            " minutes",
            " reps"
    };
    Integer[] imageId = {
            R.drawable.pullups,
            R.drawable.plank,
            R.drawable.walking,
            R.drawable.jogging,
            R.drawable.swimming,
            R.drawable.cycling,
            R.drawable.stairs,
            R.drawable.squats,
            R.drawable.situp,
            R.drawable.leglift,
            R.drawable.jumpingjacks,
            R.drawable.pushups
    };

    private RadioGroup radioGroup;
    private RadioButton minutes, reps; // unnecessary?
    private Boolean clicked = false;
    private String selected_exercise = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        radioGroup = (RadioGroup) findViewById(R.id.exerciseTypeRadioGroup);
//        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                if (checkedId == R.id.reps) {
//                    Toast.makeText(MainActivity.this, "hi", Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(MainActivity.this, "bye", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
        final CustomList adapter = new
                CustomList(MainActivity.this, exercise_type, imageId, minutes_reps_text, minutes_reps_value);
        list=(ListView)findViewById(R.id.list);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                clicked = true;
                selected_exercise = exercise_type[position];
                int calories = update_top(((EditText) findViewById(R.id.userInput)).getText());
                update_all(calories);
                adapter.setCurSelection(position);
                adapter.notifyDataSetChanged();

            }
        });
        EditText userInput = (EditText)findViewById(R.id.userInput);
        userInput.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!clicked) {
                    return;
                }
                int calories = update_top(s);
                update_all(calories);
                adapter.notifyDataSetChanged();
            }
        });
    }
    protected void update_all (int calories) {
        minutes_reps_value[0] = calories * 100/100;
        minutes_reps_value[1] = calories * 25/100;
        minutes_reps_value[2] = calories * 20/100;
        minutes_reps_value[3] = calories * 12/100;
        minutes_reps_value[4] = calories * 13/100;
        minutes_reps_value[5] = calories * 12/100;
        minutes_reps_value[6] = calories * 15/100;
        minutes_reps_value[7] = calories * 225/100;
        minutes_reps_value[8] = calories * 200/100;
        minutes_reps_value[9] = calories * 25/100;
        minutes_reps_value[10] = calories * 10/100;
        minutes_reps_value[11] = calories * 350/100;
    }
    protected int update_top(CharSequence s) {
        int calories = 0;
        String unit = " reps";
        int userInput;

        try {
            userInput = Integer.parseInt(s.toString());
        }
        catch (InputMismatchException | NumberFormatException e) {
            userInput = 0;
        }


        if (selected_exercise == "Push Ups") {
            calories = userInput * 100 / 350;
            unit = " reps";
        }

        else if (selected_exercise == "Sit Ups") {
            calories = userInput * 100 / 200;
            unit = " reps";
        }
        else if (selected_exercise == "Squats") {
            calories = userInput * 100 / 225;
            unit = " reps";
        }
        else if (selected_exercise == "Leg Lifts") {
            calories = userInput * 100 / 25;
            unit = " minutes";
        }
        else if (selected_exercise == "Plank") {
            calories = userInput * 100 / 25;
            unit = " minutes";
        }
        else if (selected_exercise == "Jumping Jacks") {
            calories = userInput * 100 / 10;
            unit = " minutes";
        }
        else if (selected_exercise == "Pull Ups") {
            calories = userInput * 100 / 100;
            unit = " reps";
        }
        else if (selected_exercise == "Cycling") {
            calories = userInput * 100 / 12;
            unit = " minutes";
        }
        else if (selected_exercise == "Walking") {
            calories = userInput * 100 / 20;
            unit = " minutes";
        }
        else if (selected_exercise == "Jogging") {
            calories = userInput * 100 / 12;
            unit = " minutes";
        }
        else if (selected_exercise == "Swimming") {
            calories = userInput * 100 / 13;
            unit = " minutes";
        }
        else if (selected_exercise == "Stair-Climbing") {
            calories = userInput * 100 / 15;
            unit = " minutes";
        }
        TextView new_calories = (TextView) findViewById(R.id.calories);
        new_calories.setText("" + calories + " calories");

        TextView new_unit = (TextView) findViewById(R.id.type_reps_min_top);
        new_unit.setText(unit);
        return calories;
    }
}

