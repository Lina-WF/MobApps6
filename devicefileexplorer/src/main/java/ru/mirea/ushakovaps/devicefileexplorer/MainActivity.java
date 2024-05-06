package ru.mirea.ushakovaps.devicefileexplorer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText editTextGroupNum;
    EditText editTextListNum;
    EditText editTextFilm;
    SharedPreferences sharedPreferences;
    int groupNum;
    int listNum;
    String film;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextGroupNum = findViewById(R.id.editTexGroupNum);
        editTextListNum = findViewById(R.id.editTextListNum);
        editTextFilm = findViewById(R.id.editTextFilm);

        sharedPreferences = getSharedPreferences("SharedPreferences", Context.MODE_PRIVATE);
        groupNum = sharedPreferences.getInt("GroupNum", 0);
        listNum = sharedPreferences.getInt("ListNum", 0);
        film = sharedPreferences.getString("Film", "unknown");

        if(groupNum != 0)
        {
            editTextGroupNum.setText(String.valueOf(groupNum));
        }
        if(listNum != 0)
        {
            editTextListNum.setText(String.valueOf(listNum));
        }
        if(!film.equals("unknown"))
        {
            editTextFilm.setText(film);
        }
    }

    public void SavePreferences(View view)
    {
        SharedPreferences.Editor editor = sharedPreferences.edit();

        groupNum = Integer.parseInt(editTextGroupNum.getText().toString());
        editor.putInt("GroupNum", groupNum);
        listNum = Integer.parseInt(editTextListNum.getText().toString());
        editor.putInt("ListNum", listNum);
        film = editTextFilm.getText().toString();
        editor.putString("Film", film);

        editor.apply();
    }
}