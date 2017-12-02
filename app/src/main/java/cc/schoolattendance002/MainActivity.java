package cc.schoolattendance002;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    public CardView editStudents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editStudents = findViewById(R.id.editStudents);
    }

    public void goToEditPage(View view){
        Intent intent = new Intent(this, EditActivity.class);
        startActivity(intent);
    }

    public void goToRollPage(View view){
        Intent intent = new Intent(this, TakeRollActivity.class);
        startActivity(intent);
    }

}
