package cc.schoolattendance002;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class TakeRollActivity extends AppCompatActivity {


    final CardView[] studentsCard = new CardView[50];
    final TextView[] studentsText = new TextView[50];

    DBHandler db;

    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_roll);
    }

    public void prepareFields(){

        linearLayout.removeView(findViewById(R.id.cardView));

        ArrayList<Student> studentList = db.getAllStudents();

        for(int i = 0; i < db.getStudentCount(); i++){

            final Student current = studentList.get(i);

            RelativeLayout relativeLayout = new RelativeLayout(this);

            studentsCard[i] = new CardView(this);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

            studentsCard[i].setLayoutParams(params);

            ViewGroup.MarginLayoutParams layoutParams =
                    (ViewGroup.MarginLayoutParams) studentsCard[i].getLayoutParams();
            layoutParams.setMargins(30,15,30,5);

            studentsCard[i].requestLayout();
            studentsCard[i].setRadius(15);
            studentsCard[i].setPadding(25, 25, 25, 25);
            studentsCard[i].setMaxCardElevation(30);
            studentsCard[i].setMaxCardElevation(6);

            linearLayout.addView(studentsCard[i]);
            studentsCard[i].addView(relativeLayout);

            CheckBox check = new CheckBox(this);
            relativeLayout.addView(check);
            RelativeLayout.LayoutParams checkParams = (RelativeLayout.LayoutParams)check.getLayoutParams();
            checkParams.addRule(RelativeLayout.ALIGN_PARENT_END);
            checkParams.addRule(RelativeLayout.CENTER_VERTICAL);
            check.setLayoutParams(checkParams);
            check.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    boolean checked = ((CheckBox)view).isChecked();
                }
            });



            studentsText[i] = new TextView(this);
            studentsText[i].setLayoutParams(params);
            studentsText[i].setGravity(Gravity.LEFT);
            studentsText[i].setTextSize(20);


            relativeLayout.addView(studentsText[i]);

            String text = String.format("Name: %s\nEmail: %s", current.get_Name(), current.get_Email());
            studentsText[i].setText(text);

        }
    }

    public void checkAll(View view){

    }

}


