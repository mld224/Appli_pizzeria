package com.example.pizzeria;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity2 extends AppCompatActivity implements View.OnClickListener {


    private Button napolitaine;
    private Button royale;
    private Button quatresfromages;
    private Button agnarde;
    private Button raclette;
    private Button hawai;
    private Button tiramisu;
    private Button pannacotta;

    private int cptNapolitaine = 0;
    private int cptRoyale = 0;
    private int cpt4Fromages = 0;
    private int cptAgnarde = 0;
    private int cptRaclette = 0;
    private int cptHawai = 0;
    private int cptTiramisu = 0;
    private int cptPannaCotta = 0;

    private static final String KEY_NAPOLITAINE = "KEY_NAPOLITAINE";
    private static final String KEY_ROYALE = "KEY_ROYALE";
    private static final String KEY_QUATRESFROMAGES = "KEY_QUATRESFROMAGES";
    private static final String KEY_AGNARDE = "KEY_AGNARDE";
    private static final String KEY_RACLETTE = "KEY_RACLETTE";
    private static final String KEY_HAWAI = "KEY_HAWAI";
    private static final String KEY_PANNACOTTA  = "KEY_PANNACOTTA";
    private static final String KEY_TIRAMISU = "KEY_TIRAMISU";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);


        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // On récupère les boutons du XML
        napolitaine = findViewById(R.id.napolitaine);
        royale = findViewById(R.id.royale);
        quatresfromages = findViewById(R.id.quatrefromages);
        agnarde = findViewById(R.id.agnarde);
        raclette = findViewById(R.id.raclette);
        hawai = findViewById(R.id.hawai);
        pannacotta = findViewById(R.id.pannacotta);
        tiramisu = findViewById(R.id.tiramisu);

        // On active l'écouteur
        napolitaine.setOnClickListener(this);
        royale.setOnClickListener(this);
        quatresfromages.setOnClickListener(this);
        agnarde.setOnClickListener(this);
        raclette.setOnClickListener(this);
        hawai.setOnClickListener(this);
        pannacotta.setOnClickListener(this);
        tiramisu.setOnClickListener(this);


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

    // Sauvegarde des données pour la rotation de l'ecran
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

    // Méthode appelée à chaque fois qu'un bouton est cliquée
    @Override
    public void onClick(View v) {
        int id = v.getId();

        if (id == R.id.napolitaine) {
            cptNapolitaine++;
            napolitaine.setText("Napolitaine : " + cptNapolitaine);
        }
        else if (id == R.id.royale) {
            cptRoyale++;
            royale.setText("Royale : " + cptRoyale);
        }
        else if (id == R.id.quatrefromages) {
            cpt4Fromages++;
            quatresfromages.setText("Quatre Fromages : " + cpt4Fromages);
        }
        else if (id == R.id.agnarde) {
            cptAgnarde++;
            agnarde.setText("Montagnarde : " + cptAgnarde);
        }
        else if (id == R.id.raclette) {
            cptRaclette++;
            raclette.setText("Raclette : " + cptRaclette);
        }
        else if (id == R.id.hawai) {
            cptHawai++;
            hawai.setText("Hawai : " + cptHawai);
        }
        else if (id == R.id.pannacotta) {
            cptPannaCotta++;
            pannacotta.setText("Panna Cotta : " + cptPannaCotta);
        }
        else if (id == R.id.tiramisu) {
            cptTiramisu++;
            tiramisu.setText("Tiramisu : " + cptTiramisu);
        }
    }
}