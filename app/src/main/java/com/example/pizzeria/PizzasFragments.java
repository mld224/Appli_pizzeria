package com.example.pizzeria;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class PizzasFragments extends Fragment implements View.OnClickListener {

    private Button btnNapo, btnRoyale, btnQuatre, btnAgnarde, btnRaclette, btnHawai, btnPanna, btnTira, btnPerso, btnReset;
    private int cN=0, cR=0, cQ=0, cA=0, cRac=0, cH=0, cP=0, cT=0, cPerso=0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_pizzas_fragments, container, false);

        btnNapo = v.findViewById(R.id.napolitaine);
        btnRoyale = v.findViewById(R.id.royale);
        btnQuatre = v.findViewById(R.id.quatrefromages);
        btnAgnarde = v.findViewById(R.id.agnarde);
        btnRaclette = v.findViewById(R.id.raclette);
        btnHawai = v.findViewById(R.id.hawai);
        btnPanna = v.findViewById(R.id.pannacotta);
        btnTira = v.findViewById(R.id.tiramisu);
        btnPerso = v.findViewById(R.id.btn_perso);
        btnReset = v.findViewById(R.id.btn_reset);

        Button[] bts = {btnNapo, btnRoyale, btnQuatre, btnAgnarde, btnRaclette, btnHawai, btnPanna, btnTira, btnPerso, btnReset};
        for(Button b : bts) if(b != null) b.setOnClickListener(this);

        if (savedInstanceState != null) {
            cN = savedInstanceState.getInt("CN");
            cR = savedInstanceState.getInt("CR");
            cQ = savedInstanceState.getInt("CQ");
            cA = savedInstanceState.getInt("CA");
            cRac = savedInstanceState.getInt("CRAC");
            cH = savedInstanceState.getInt("CH");
            cP = savedInstanceState.getInt("CP");
            cT = savedInstanceState.getInt("CT");
            cPerso = savedInstanceState.getInt("CPERSO");

            if(cN > 0) btnNapo.setText("Napolitaine : " + cN);
            if(cR > 0) btnRoyale.setText("Royale : " + cR);
            if(cQ > 0) btnQuatre.setText("Quatre Fromages : " + cQ);
            if(cA > 0) btnAgnarde.setText("Montagnarde : " + cA);
            if(cRac > 0) btnRaclette.setText("Raclette : " + cRac);
            if(cH > 0) btnHawai.setText("Hawai : " + cH);
            if(cP > 0) btnPanna.setText("Panna Cotta : " + cP);
            if(cT > 0) btnTira.setText("Tiramisu : " + cT);
            if(cPerso > 0) btnPerso.setText("Pizza Personnalisée : " + cPerso);
        }

        return v;
    }

    public void incrementerPerso() {
        cPerso++;
        btnPerso.setText("Pizza Personnalisée : " + cPerso);
    }

    @Override
    public void onClick(View v) {
        MainActivity activity = (MainActivity) getActivity();
        if (activity == null) return;

        int id = v.getId();
        String code = "";

        if (id == R.id.btn_reset) {
            cN=0; cR=0; cQ=0; cA=0; cRac=0; cH=0; cP=0; cT=0; cPerso=0;
            btnNapo.setText("Napolitaine");
            btnRoyale.setText("Royale");
            btnQuatre.setText("Quatre Fromages");
            btnAgnarde.setText("Montagnarde");
            btnRaclette.setText("Raclette");
            btnHawai.setText("Hawai");
            btnPanna.setText("Panna Cotta");
            btnTira.setText("Tiramisu");
            btnPerso.setText("Pizza Personnalisée");
            return;
        }
        else if (id == R.id.btn_perso) {
            activity.afficherIngredients();
            return;
        }

        if (id == R.id.napolitaine) { cN++; btnNapo.setText("Napolitaine : " + cN); code = "11"; }
        else if (id == R.id.royale) { cR++; btnRoyale.setText("Royale : " + cR); code = "05"; }
        else if (id == R.id.quatrefromages) { cQ++; btnQuatre.setText("Quatre Fromages : " + cQ); code = "14"; }
        else if (id == R.id.agnarde) { cA++; btnAgnarde.setText("Montagnarde : " + cA); code = "18"; }
        else if (id == R.id.raclette) { cRac++; btnRaclette.setText("Raclette : " + cRac); code = "20"; }
        else if (id == R.id.hawai) { cH++; btnHawai.setText("Hawai : " + cH); code = "06"; }
        else if (id == R.id.pannacotta) { cP++; btnPanna.setText("Panna Cotta : " + cP); code = "94"; }
        else if (id == R.id.tiramisu) { cT++; btnTira.setText("Tiramisu : " + cT); code = "91"; }

        if (!code.isEmpty()) {
            activity.envoyerCommande(code);
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("CN", cN);
        outState.putInt("CR", cR);
        outState.putInt("CQ", cQ);
        outState.putInt("CA", cA);
        outState.putInt("CRAC", cRac);
        outState.putInt("CH", cH);
        outState.putInt("CP", cP);
        outState.putInt("CT", cT);
        outState.putInt("CPERSO", cPerso);
    }
}