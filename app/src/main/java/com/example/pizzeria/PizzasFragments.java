package com.example.pizzeria;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class PizzasFragments extends Fragment implements View.OnClickListener {

    private Button napolitaine, royale, quatresfromages, agnarde;
    private Button raclette, hawai, tiramisu, pannacotta;

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


    public PizzasFragments() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_pizzas_fragments, container, false);

        napolitaine = v.findViewById(R.id.napolitaine);
        royale = v.findViewById(R.id.royale);
        quatresfromages = v.findViewById(R.id.quatrefromages);
        agnarde = v.findViewById(R.id.agnarde);
        raclette = v.findViewById(R.id.raclette);
        hawai = v.findViewById(R.id.hawai);
        pannacotta = v.findViewById(R.id.pannacotta);
        tiramisu = v.findViewById(R.id.tiramisu);

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

        return v;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        String codePlat = "";

        if (id == R.id.napolitaine) {
            cptNapolitaine++; napolitaine.setText("Napolitaine : " + cptNapolitaine); codePlat = "11";
        } else if (id == R.id.royale) {
            cptRoyale++; royale.setText("Royale : " + cptRoyale); codePlat = "05";
        } else if (id == R.id.quatrefromages) {
            cpt4Fromages++; quatresfromages.setText("Quatre Fromages : " + cpt4Fromages); codePlat = "14";
        } else if (id == R.id.agnarde) {
            cptAgnarde++; agnarde.setText("Montagnarde : " + cptAgnarde); codePlat = "18";
        } else if (id == R.id.raclette) {
            cptRaclette++; raclette.setText("Raclette : " + cptRaclette); codePlat = "20";
        } else if (id == R.id.hawai) {
            cptHawai++; hawai.setText("Hawai : " + cptHawai); codePlat = "06";
        } else if (id == R.id.pannacotta) {
            cptPannaCotta++; pannacotta.setText("Panna Cotta : " + cptPannaCotta); codePlat = "94";
        } else if (id == R.id.tiramisu) {
            cptTiramisu++; tiramisu.setText("Tiramisu : " + cptTiramisu); codePlat = "91";
        }

        if (!codePlat.isEmpty() && getActivity() != null) {
            ((MainActivity) getActivity()).envoyerCommande(codePlat);
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
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
}