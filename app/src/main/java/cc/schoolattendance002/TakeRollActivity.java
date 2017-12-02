package cc.schoolattendance002;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class TakeRollActivity extends AppCompatActivity {


    final CardView[] studentsCard = new CardView[50];
    final TextView[] studentsText = new TextView[50];
    final CheckBox[] studentsCheck = new CheckBox[50];

    CheckBox checkAll;
    boolean checkedAll;

    final int[] wasChecked = new int[50];

    DBHandler db;

    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_roll);

        linearLayout = (LinearLayout) findViewById(R.id.linearLayout);

        checkAll = findViewById(R.id.checkAll);
        checkedAll = false;

        db = new DBHandler(this);

        if (db.getStudentCount() > 0){
            prepareFields();
        }
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

            studentsCheck[i] = new CheckBox(this);

            studentsCheck[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    CheckBox checkBox = (CheckBox)view;

                    if (checkBox.isChecked()){
                        current.set_isPresent(1);
                        db.updateStudent(current);
                        Toast.makeText(TakeRollActivity.this,
                                "Checked", Toast.LENGTH_LONG).show();
                    }
                    else{
                        current.set_isPresent(0);
                        db.updateStudent(current);
                        Toast.makeText(TakeRollActivity.this,
                                "Unchecked", Toast.LENGTH_LONG).show();
                    }
                    linearLayout.removeAllViews();
                    prepareFields();
                }
            });
            relativeLayout.addView(studentsCheck[i]);

            RelativeLayout.LayoutParams checkParams = (RelativeLayout.LayoutParams)studentsCheck[i].getLayoutParams();
            checkParams.addRule(RelativeLayout.ALIGN_PARENT_END);
            checkParams.addRule(RelativeLayout.CENTER_VERTICAL);
            studentsCheck[i].setLayoutParams(checkParams);

            studentsText[i] = new TextView(this);
            studentsText[i].setLayoutParams(params);
            studentsText[i].setGravity(Gravity.LEFT);
            studentsText[i].setTextSize(20);


            relativeLayout.addView(studentsText[i]);

            String present;
            if (current.get_isPresent() == 1){
                studentsCheck[i].setChecked(true);
                present = "Here";
            }
            else{
                studentsCheck[i].setChecked(false);
                present = "Absent";
            }

            String text = String.format("Name: %s\nEmail: %s\nPresent: %s", current.get_Name(), current.get_Email(), present);
            studentsText[i].setText(text);

        }
    }

    public void checkAllPress(View view){

        ArrayList<Student> studentList = db.getAllStudents();

        for(int i = 0; i < db.getStudentCount(); i++) {

            if (checkAll.isChecked()){
                studentsCheck[i].setChecked(true);
                final Student current = studentList.get(i);
                current.set_isPresent(1);
                db.updateStudent(current);
                Toast.makeText(TakeRollActivity.this,
                        "Checked", Toast.LENGTH_LONG).show();
            }

            else if (!checkAll.isChecked()){
                studentsCheck[i].setChecked(false);
                final Student current = studentList.get(i);
                current.set_isPresent(0);
                db.updateStudent(current);
                Toast.makeText(TakeRollActivity.this,
                        "Unchecked", Toast.LENGTH_LONG).show();
            }
            linearLayout.removeAllViews();
            prepareFields();
        }
    }

}


