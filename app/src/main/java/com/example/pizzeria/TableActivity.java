package com.example.pizzeria;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class TableActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);
        Log.i("Lifecycle", "TableActivity - onCreate");

        EditText editTable = findViewById(R.id.edit_table);
        Button btnValider = findViewById(R.id.btn_valider_table);

        btnValider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String numTable = String.valueOf(editTable.getText());


                Intent intent = new Intent(TableActivity.this, MainActivity.class);
                intent.putExtra("NUM_TABLE", numTable);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() { super.onStart(); Log.i("Lifecycle", "TableActivity - onStart"); }
    @Override
    protected void onResume() { super.onResume(); Log.i("Lifecycle", "TableActivity - onResume"); }
    @Override
    protected void onPause() { super.onPause(); Log.i("Lifecycle", "TableActivity - onPause"); }
    @Override
    protected void onStop() { super.onStop(); Log.i("Lifecycle", "TableActivity - onStop"); }
    @Override
    protected void onDestroy() { super.onDestroy(); Log.i("Lifecycle", "TableActivity - onDestroy"); }
    @Override
    protected void onRestart() { super.onRestart(); Log.i("Lifecycle", "TableActivity - onRestart"); }
}