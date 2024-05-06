package ru.mirea.ushakovaps.notebook;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void toFile(View view) {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            EditText editTextFile = findViewById(R.id.editTextFile);
            String fileName = editTextFile.getText().toString();
            EditText editTextQuote = findViewById(R.id.editTextQuote);
            String quote = editTextQuote.getText().toString();
            File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
            File file = new File(path, fileName);
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(file.getAbsoluteFile());
                OutputStreamWriter output = new OutputStreamWriter(fileOutputStream);
                output.write(quote);
                output.close();
                Log.d("writing", "success");
            } catch (IOException e) {
                Log.w("ExternalStorage", "Error writing " + file, e);
            }
        }
    }

    public void fromFile(View view) {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            File path = Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_DOCUMENTS);
            EditText editTextFile = findViewById(R.id.editTextFile);
            String fileName = editTextFile.getText().toString();
            File file = new File(path, fileName);
            try {
                FileInputStream fileInputStream = new FileInputStream(file.getAbsoluteFile());
                InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, StandardCharsets.UTF_8);
                List<String> lines = new ArrayList<String>();
                BufferedReader reader = new BufferedReader(inputStreamReader);
                String line = reader.readLine();
                while (line != null) {
                    lines.add(line);
                    line = reader.readLine();
                }
                String quote = lines.toString();
                Log.w("ExternalStorage", String.format("Read from file %s successful", quote));
                EditText editTextQuote = findViewById(R.id.editTextQuote);
                editTextQuote.setText(quote);
            } catch (Exception e) {
                Log.w("ExternalStorage", String.format("Read from file %s failed", e.getMessage()));
            }
        }
    }
}