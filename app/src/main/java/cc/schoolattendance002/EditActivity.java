package cc.schoolattendance002;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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
import android.widget.LinearLayout;
import android.widget.TextView;

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
                goToNamePopup();
            }
        });

        //alert.setCancelable(false);

        AlertDialog alertDialog = alert.create();
        alertDialog.getWindow().setLayout(MATCH_PARENT, MATCH_PARENT);
        alertDialog.show();

/*

        id_Dialog = new Dialog(EditActivity.this, android.R.style.Theme_Material_Light_Dialog);
        final EditText editID = id_Dialog.findViewById(R.id.editID);
        id_Dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.argb(100, 0, 0, 0)));
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(id_Dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        id_Dialog.show();
        id_Dialog.getWindow().setAttributes(lp);
        id_Dialog.setContentView(R.layout.add_student_id);
        id_Dialog.setCancelable(true);
        id_Dialog.show();

        /*editID.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void afterTextChanged(Editable editable) {
                if(!(editID.length() == 0 || editID.equals("") || editID == null)) {
                    edit_id = Integer.parseInt(editID.toString());
                }
            }
        });
        String tmp = editID.getText().toString();
        if(!(tmp.length() == 0 || tmp.equals("") || tmp == null)) {
            edit_id = Integer.parseInt(tmp);
        }
        student.set_ID(edit_id);
*/
    }

    public void goToNamePopup(){
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

        linearLayout.removeView(findViewById(R.id.cardView));

        studentsCard[numStudents] = new CardView(this);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        studentsCard[numStudents].setLayoutParams(params);

        ViewGroup.MarginLayoutParams layoutParams =
                (ViewGroup.MarginLayoutParams) studentsCard[numStudents].getLayoutParams();
        layoutParams.setMargins(30,15,30,5);


        //if(numClasses > 0)
        //  params.addRule(RelativeLayout.BELOW, classesCard[numClasses-1].getId());

        //classesCard[numClasses].setLayoutParams(params);
        studentsCard[numStudents].requestLayout();
        studentsCard[numStudents].setRadius(15);
        studentsCard[numStudents].setPadding(25, 25, 25, 25);
        studentsCard[numStudents].setMaxCardElevation(30);
        studentsCard[numStudents].setMaxCardElevation(6);

        linearLayout.addView(studentsCard[numStudents]);

        studentsText[numStudents] = new TextView(this);
        studentsText[numStudents].setLayoutParams(params);
        studentsText[numStudents].setGravity(Gravity.CENTER);
        studentsText[numStudents].setTextSize(20);


        studentsCard[numStudents].addView(studentsText[numStudents]);

        String text = String.format("Name: %s\nID: %d\nEmail: %s", student.get_Name(), student.get_ID(), student.get_Email());
        studentsText[numStudents].setText(text);
        numStudents++;
    }

}
