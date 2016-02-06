package com.example.kstukalova.myapplication;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kstukalova.myapplication.R;

public class CustomList extends ArrayAdapter<String>{

    private final Activity context;
    private final String[] exercise_type;
    private final Integer[] imageId;
    private Integer[] minutes_reps_value;
    private String[] minutes_reps_text;
    private int cur_selection = -1 ;

    public CustomList(Activity context,
                      String[] exercise_type, Integer[] imageId, String[] minutes_reps_text, Integer[] minutes_reps_value) {
        super(context, R.layout.list_single, exercise_type);
        this.context = context;
        this.exercise_type = exercise_type;
        this.imageId = imageId;
        this.minutes_reps_text = minutes_reps_text;
        this.minutes_reps_value = minutes_reps_value;
    }

    void setCurSelection(int pos) {
        cur_selection = pos;
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.list_single, null, true);
        TextView exercise = (TextView) rowView.findViewById(R.id.exercise);
        TextView unit = (TextView) rowView.findViewById(R.id.unit);
        TextView exercise_value = (TextView) rowView.findViewById(R.id.exercise_value);

        ImageView imageView = (ImageView) rowView.findViewById(R.id.img);
        exercise.setText(exercise_type[position]);
        unit.setText(minutes_reps_text[position]);
        exercise_value.setText("" + minutes_reps_value[position]);

        imageView.setImageResource(imageId[position]);

        if (cur_selection == position)
            rowView.setBackgroundColor(0xffffedf0);
        else
            rowView.setBackgroundColor(0xFFFFFFFF);

        return rowView;
    }
}