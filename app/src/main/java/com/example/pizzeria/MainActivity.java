package com.example.pizzeria;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
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

public class MainActivity extends AppCompatActivity {

    private TextView tvTitreTable;
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

        Intent intent = getIntent();
        String numTable = intent.getStringExtra("NUM_TABLE");
        if (numTable != null && !numTable.isEmpty()) {
            tableActuelle = numTable;
            tvTitreTable.setText("Commande de la table n°" + tableActuelle);
        }


        // On ne l'ajoute que si c'est le tout premier lancement de l'activité
        if (savedInstanceState == null) {
            PizzasFragments frag = new PizzasFragments();

            // On utilise le FragmentManager pour ajouter le fragment dans notre FrameLayout
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, frag)
                    .commit();
        }
    }

    // Méthode appelée par le Fragment pour envoyer les données au serveur
    public void envoyerCommande(String codePlat) {
        new CommandeThread(tableActuelle, codePlat).start();
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

    // CYCLE DE VIE
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