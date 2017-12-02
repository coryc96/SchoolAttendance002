package cc.schoolattendance002;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.DrawableRes;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import static android.widget.ListPopupWindow.MATCH_PARENT;
import static android.widget.ListPopupWindow.WRAP_CONTENT;

public class EditActivity extends AppCompatActivity {

    public Dialog id_Dialog;
    public Dialog name_Dialog;
    public Dialog email_Dialog;

    public static int edit_id;
    public static String edit_name;
    public static String edit_email;

    final Student student = new Student();

    //public EditText editID;
    public EditText editName;
    public EditText editEmail;
    FloatingActionButton fab;

    final CardView[] studentsCard = new CardView[50];
    final TextView[] studentsText = new TextView[50];
    public int numStudents;

    DBHandler db;

    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        //editID = findViewById(R.id.editID);
        editName = findViewById(R.id.editName);
        editEmail = findViewById(R.id.editEmail);
        fab = findViewById(R.id.floatingActionButton);
        linearLayout = (LinearLayout) findViewById(R.id.linearLayout);

        db = new DBHandler(this);

        if (db.getStudentCount() != 0){if (db.getStudentCount() > 0){
            prepareFields();
            }
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

            ImageButton delete = new ImageButton(this);
            relativeLayout.addView(delete);
            RelativeLayout.LayoutParams btnParams = (RelativeLayout.LayoutParams)delete.getLayoutParams();
            btnParams.addRule(RelativeLayout.ALIGN_PARENT_END);
            btnParams.addRule(RelativeLayout.CENTER_VERTICAL);
            delete.setLayoutParams(btnParams);
            delete.setImageResource(R.drawable.ic_delete_forever_24dp);
            delete.setBackgroundColor(getResources().getColor(R.color.white));
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    alertPopup(current);
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

    public void goToIDPopup(View view){

        LayoutInflater inflater = getLayoutInflater();
        View alertLayout = inflater.inflate(R.layout.add_student_id, null);

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        //alert.getWindow().setBackgroundDrawable(new ColorDrawable(Color.argb(100, 0, 0, 0)));

        //alert.setTitle("ID");

        alert.setView(alertLayout);

        final EditText editID = alertLayout.findViewById(R.id.editID);
        //final Button btnID = alertLayout.findViewById(R.id.saveID);

        alert.setCancelable(false).setPositiveButton("Save ID", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                String tmp = editID.getText().toString();
                if(!(tmp.length() == 0 || tmp.equals("") || tmp == null)) {
                    edit_id = Integer.parseInt(tmp);
                }
                student.set_ID(edit_id);

                dialogInterface.dismiss();
                //goToNamePopup();
            }
        });

        //alert.setCancelable(false);

        AlertDialog alertDialog = alert.create();
        alertDialog.getWindow().setLayout(MATCH_PARENT, MATCH_PARENT);
        alertDialog.show();
    }

    public void goToNamePopup(View view){
        LayoutInflater inflater = getLayoutInflater();
        View alertLayout = inflater.inflate(R.layout.add_student_name, null);

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        //alert.getWindow().setBackgroundDrawable(new ColorDrawable(Color.argb(100, 0, 0, 0)));

        //alert.setTitle("ID");

        alert.setView(alertLayout);

        final EditText editName = alertLayout.findViewById(R.id.editName);
        //final Button btnID = alertLayout.findViewById(R.id.saveID);

        alert.setCancelable(false).setPositiveButton("Save Name", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                String tmp = editName.getText().toString();
                if(!(tmp.length() == 0 || tmp.equals("") || tmp == null)) {
                    edit_name = tmp;
                }
                student.set_Name(edit_name);

                dialogInterface.dismiss();
                goToEmailPopup();
            }
        });

        //alert.setCancelable(false);

        AlertDialog alertDialog = alert.create();
        alertDialog.getWindow().setLayout(MATCH_PARENT, MATCH_PARENT);
        alertDialog.show();
    }

    public void goToEmailPopup(){
        LayoutInflater inflater = getLayoutInflater();
        View alertLayout = inflater.inflate(R.layout.add_student_email, null);

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        //alert.getWindow().setBackgroundDrawable(new ColorDrawable(Color.argb(100, 0, 0, 0)));

        //alert.setTitle("ID");

        alert.setView(alertLayout);

        final EditText editEmail = alertLayout.findViewById(R.id.editEmail);
        //final Button btnID = alertLayout.findViewById(R.id.saveID);

        alert.setCancelable(false).setPositiveButton("Save Email", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                String tmp = editEmail.getText().toString();
                if(!(tmp.length() == 0 || tmp.equals("") || tmp == null)) {
                    edit_email = tmp;
                }
                student.set_Email(edit_email);

                dialogInterface.dismiss();
                saveStudent();
            }
        });

        //alert.setCancelable(false);

        AlertDialog alertDialog = alert.create();
        alertDialog.getWindow().setLayout(MATCH_PARENT, MATCH_PARENT);
        alertDialog.show();
    }

    public void saveStudent(){

        Log.d("Insert: ", "Inserting ..");
        db.addStudent(student);

        linearLayout.removeAllViews();
        prepareFields();
    }

    public void alertPopup(final Student student){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        String message = String.format("Delete info on %s? This action cannot be undone.", student.get_Name());
        builder.setMessage(message)
                .setTitle("Warning:");

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                db.deleteStudent(student);
                linearLayout.removeAllViews();
                prepareFields();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

}
