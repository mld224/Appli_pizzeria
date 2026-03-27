package com.example.pizzeria;

import android.app.AlertDialog;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.ListFragment;

public class IngredientsFragment extends ListFragment {

    private final String[] ingredients = {
            "Mozzarella", "Gorgonzola", "Anchois", "Câpres",
            "Olives", "Artichauts", "Jambon cru", "Jambon cuit"
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_ingredients_list, container, false);


        Button btnValider = v.findViewById(R.id.btn_valider_ingredients);
        if (btnValider != null) {
            btnValider.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    valider();
                }
            });
        }

        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setListAdapter(new ArrayAdapter<>(requireActivity(), android.R.layout.simple_list_item_multiple_choice, ingredients));
        getListView().setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
    }

    private void valider() {
        StringBuilder sb = new StringBuilder();
        SparseBooleanArray checked = getListView().getCheckedItemPositions();

        int compteurIngredients = 0;

        // Boucle
        if (checked != null) {
            for (int i = 0; i < ingredients.length; i++) {
                // Si la case à la position i est cochée ?
                if (checked.get(i)) {
                    sb.append(ingredients[i].toLowerCase()).append(" + ");
                    compteurIngredients++;
                }
            }
        }

        // Obliger choix au moins 1
        if (compteurIngredients == 0) {
            Toast.makeText(requireContext(), "Attention : Veuillez choisir au moins un ingrédient !", Toast.LENGTH_SHORT).show();
            return;
        }


        String res = sb.toString();
        if (res.endsWith(" + ")) {
            res = res.substring(0, res.length() - 3);
        }


        final String resFinal = res;


        new AlertDialog.Builder(requireContext())
                .setTitle("Validation de la commande")
                .setMessage("Envoyer cette pizza en cuisine ?\n\nComposition : " + resFinal)


                .setPositiveButton("OUI", (dialog, which) -> {
                    MainActivity activity = (MainActivity) getActivity();
                    if (activity != null) {
                        activity.validerPizzaPerso(resFinal);
                    }
                })

                // Si modifier boite fermer et la personne coche autre chose
                .setNegativeButton("MODIFIER", null)
                .show();
    }
}