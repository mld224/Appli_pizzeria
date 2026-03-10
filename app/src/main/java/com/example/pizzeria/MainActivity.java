package com.example.pizzeria;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    // Déclaration des vues
    private Button napolitaine, royale, quatresfromages, agnarde;
    private Button raclette, hawai, tiramisu, pannacotta;
    private TextView tvTitreTable;


    private int cptNapolitaine = 0, cptRoyale = 0, cpt4Fromages = 0, cptAgnarde = 0;
    private int cptRaclette = 0, cptHawai = 0, cptTiramisu = 0, cptPannaCotta = 0;


    private static final String KEY_NAPOLITAINE = "KEY_NAPOLITAINE";
    private static final String KEY_ROYALE = "KEY_ROYALE";
    private static final String KEY_QUATRESFROMAGES = "KEY_QUATRESFROMAGES";
    private static final String KEY_AGNARDE = "KEY_AGNARDE";
    private static final String KEY_RACLETTE = "KEY_RACLETTE";
    private static final String KEY_HAWAI = "KEY_HAWAI";
    private static final String KEY_PANNACOTTA = "KEY_PANNACOTTA";
    private static final String KEY_TIRAMISU = "KEY_TIRAMISU";


    private String tableActuelle = "01";
    private final Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Log.i("Lifecycle", "MainActivity - onCreate");

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        tvTitreTable = findViewById(R.id.tv_titre_table);
        napolitaine = findViewById(R.id.napolitaine);
        royale = findViewById(R.id.royale);
        quatresfromages = findViewById(R.id.quatrefromages);
        agnarde = findViewById(R.id.agnarde);
        raclette = findViewById(R.id.raclette);
        hawai = findViewById(R.id.hawai);
        pannacotta = findViewById(R.id.pannacotta);
        tiramisu = findViewById(R.id.tiramisu);


        napolitaine.setOnClickListener(this);
        royale.setOnClickListener(this);
        quatresfromages.setOnClickListener(this);
        agnarde.setOnClickListener(this);
        raclette.setOnClickListener(this);
        hawai.setOnClickListener(this);
        pannacotta.setOnClickListener(this);
        tiramisu.setOnClickListener(this);


        Intent intent = getIntent();
        String numTable = intent.getStringExtra("NUM_TABLE");
        if (numTable != null && !numTable.isEmpty()) {
            tableActuelle = numTable;
            tvTitreTable.setText("Commande de la table n°" + tableActuelle);
        }


        if (savedInstanceState != null) {
            cptNapolitaine = savedInstanceState.getInt(KEY_NAPOLITAINE);
            cptRoyale = savedInstanceState.getInt(KEY_ROYALE);
            cpt4Fromages = savedInstanceState.getInt(KEY_QUATRESFROMAGES);
            cptAgnarde = savedInstanceState.getInt(KEY_AGNARDE);
            cptRaclette = savedInstanceState.getInt(KEY_RACLETTE);
            cptHawai = savedInstanceState.getInt(KEY_HAWAI);
            cptPannaCotta = savedInstanceState.getInt(KEY_PANNACOTTA);
            cptTiramisu = savedInstanceState.getInt(KEY_TIRAMISU);

            napolitaine.setText("Napolitaine : " + cptNapolitaine);
            royale.setText("Royale : " + cptRoyale);
            quatresfromages.setText("Quatre Fromages : " + cpt4Fromages);
            agnarde.setText("Montagnarde : " + cptAgnarde);
            raclette.setText("Raclette : " + cptRaclette);
            hawai.setText("Hawai : " + cptHawai);
            pannacotta.setText("Panna Cotta : " + cptPannaCotta);
            tiramisu.setText("Tiramisu : " + cptTiramisu);
        }
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        String codePlat = "";

        if (id == R.id.napolitaine) {
            cptNapolitaine++;
            napolitaine.setText("Napolitaine : " + cptNapolitaine);
            codePlat = "11";
        } else if (id == R.id.royale) {
            cptRoyale++;
            royale.setText("Royale : " + cptRoyale);
            codePlat = "05";
        } else if (id == R.id.quatrefromages) {
            cpt4Fromages++;
            quatresfromages.setText("Quatre Fromages : " + cpt4Fromages);
            codePlat = "14";
        } else if (id == R.id.agnarde) {
            cptAgnarde++;
            agnarde.setText("Montagnarde : " + cptAgnarde);
            codePlat = "18";
        } else if (id == R.id.raclette) {
            cptRaclette++;
            raclette.setText("Raclette : " + cptRaclette);
            codePlat = "20";
        } else if (id == R.id.hawai) {
            cptHawai++;
            hawai.setText("Hawai : " + cptHawai);
            codePlat = "06";
        } else if (id == R.id.pannacotta) {
            cptPannaCotta++;
            pannacotta.setText("Panna Cotta : " + cptPannaCotta);
            codePlat = "94";
        } else if (id == R.id.tiramisu) {
            cptTiramisu++;
            tiramisu.setText("Tiramisu : " + cptTiramisu);
            codePlat = "91";
        }


        if (!codePlat.isEmpty()) {
            new CommandeThread(tableActuelle, codePlat).start();
        }
    }



    private class CommandeThread extends Thread {
        private String numTable;
        private String codePizza;

        public CommandeThread(String table, String pizza) {
            this.numTable = table;
            this.codePizza = pizza;
        }

        @Override
        public void run() {
            try {

                if (numTable.length() == 1) {
                    numTable = "0" + numTable;
                }

                String messageAEnvoyer = numTable + codePizza;


                Socket socket = new Socket("chadok.info", 9874);
                PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));


                writer.println(messageAEnvoyer);


                final String msg1 = reader.readLine();


                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        tvTitreTable.setText(msg1);
                    }
                });


                final String msg2 = reader.readLine();

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        tvTitreTable.setText(msg2);
                    }
                });


                socket.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_NAPOLITAINE, cptNapolitaine);
        outState.putInt(KEY_ROYALE, cptRoyale);
        outState.putInt(KEY_QUATRESFROMAGES, cpt4Fromages);
        outState.putInt(KEY_AGNARDE, cptAgnarde);
        outState.putInt(KEY_RACLETTE, cptRaclette);
        outState.putInt(KEY_HAWAI, cptHawai);
        outState.putInt(KEY_PANNACOTTA, cptPannaCotta);
        outState.putInt(KEY_TIRAMISU, cptTiramisu);
    }

    @Override
    protected void onStart() { super.onStart(); Log.i("Lifecycle", "MainActivity - onStart"); }
    @Override
    protected void onResume() { super.onResume(); Log.i("Lifecycle", "MainActivity - onResume"); }
    @Override
    protected void onPause() { super.onPause(); Log.i("Lifecycle", "MainActivity - onPause"); }
    @Override
    protected void onStop() { super.onStop(); Log.i("Lifecycle", "MainActivity - onStop"); }
    @Override
    protected void onDestroy() { super.onDestroy(); Log.i("Lifecycle", "MainActivity - onDestroy"); }
    @Override
    protected void onRestart() { super.onRestart(); Log.i("Lifecycle", "MainActivity - onRestart"); }
}