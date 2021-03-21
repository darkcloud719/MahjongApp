package com.example.mahjonggamev1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mahjonggamev1.helper.MyDatabaseHelper;

public class AddGameRuleActivity extends AppCompatActivity {

    EditText gameTitleTextView, diTextView, taiTextView;
    Button add_button;
    MyDatabaseHelper myDB;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_gamerule);

        gameTitleTextView = findViewById(R.id.gameTitleTextView);
        diTextView = findViewById(R.id.diTextView);
        taiTextView = findViewById(R.id.taiTextView);
        add_button = findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                myDB = new MyDatabaseHelper(AddGameRuleActivity.this);
                myDB.addGameRule(gameTitleTextView.getText().toString().trim(), Integer.parseInt(diTextView.getText().toString().trim()), Integer.parseInt(taiTextView.getText().toString().trim()));
                setResult(RESULT_OK, getIntent());
                finish();
            }
        });
    }

}
