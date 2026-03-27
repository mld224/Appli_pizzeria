package com.example.pizzeria;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {

    private TextView tvTitreTable;
    private String tableActuelle = "01";
    private final Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvTitreTable = findViewById(R.id.tv_titre_table);

        // Récupèrationdu numéro de table
        Intent intent = getIntent();
        String numTable = intent.getStringExtra("NUM_TABLE");
        if (numTable != null && !numTable.isEmpty()) {
            tableActuelle = numTable;
            tvTitreTable.setText("Commande de la table n°" + tableActuelle);
        }


        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, new PizzasFragments())
                    .commit();
        }
    }


    public void envoyerCommande(String codePlat) {
        new CommandeThread(tableActuelle, codePlat).start();
    }


    public void afficherIngredients() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new IngredientsFragment())
                .addToBackStack(null)
                .commit();
    }

    //
    public void validerPizzaPerso(String listeIngredients) {
        new CommandeThread(tableActuelle, "50" + listeIngredients).start();
        getSupportFragmentManager().popBackStack(); // Retour au fragment précédent

        //incrémenter le compteur
        getSupportFragmentManager().executePendingTransactions();
        Fragment f = getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        if (f instanceof PizzasFragments) {
            ((PizzasFragments) f).incrementerPerso();
        }
    }


    private class CommandeThread extends Thread {
        private String numTable, codePizza;
        public CommandeThread(String t, String p) { this.numTable = t; this.codePizza = p; }

        @Override
        public void run() {
            try {

                if (numTable.length() == 1) numTable = "0" + numTable;

                // Connexion
                Socket s = new Socket("chadok.info", 9874);
                PrintWriter out = new PrintWriter(s.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));

                out.println(numTable + codePizza);


                final String m1 = in.readLine();
                final String m2 = in.readLine();

                // Affiche la confirmation
                handler.post(() -> {
                    Toast.makeText(MainActivity.this, m1 + "\n" + m2, Toast.LENGTH_LONG).show();
                });

                s.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}