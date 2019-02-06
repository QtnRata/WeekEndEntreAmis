package fr.chalon.weekendentreamis;

import android.app.ActionBar;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import fr.chalon.weekendentreamis.database.entities.Participant;
import fr.chalon.weekendentreamis.databinding.ActivityParticipantEditionBinding;
import fr.chalon.weekendentreamis.helpers.FormHelper;
import fr.chalon.weekendentreamis.repository.ParticipantRepository;
import fr.chalon.weekendentreamis.viewmodels.ParticipantEditionViewModel;

public class ParticipantEditionActivity extends AppCompatActivity {

    private ActivityParticipantEditionBinding binding;
    private ParticipantEditionViewModel viewModel;
    private boolean isUpdate = false;
    private ParticipantRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_participant_edition);

        this.repository = new ParticipantRepository(this.getApplication());
        this.binding = DataBindingUtil.setContentView(this, R.layout.activity_participant_edition);
        this.viewModel = ViewModelProviders.of(this).get(ParticipantEditionViewModel.class);

        // Récupère l'id
        long id = this.getIntent().getLongExtra("id", 0);

        // On retire l'observer pour éviter que la modification soit exécutée en boucle.
        this.repository.getParticipantById(id).removeObservers(this);

        this.repository.getParticipantById(id).observe(this, participant -> {
            if (participant != null)
            {
                // Si l'identifiant est valide et que le participant existe, on modifie le modèle.
                this.viewModel.setParticipant(participant);
                this.isUpdate = true;
            }
            else
            {
                this.viewModel.setParticipant(new Participant("", ""));
            }

            binding.setViewModel(this.viewModel);
            binding.setEditClickListener(v -> onSubmit());
            binding.setBackClickListener(v -> back());
        });
    }

    private void onSubmit() {
        if (!this.isFormValid()) {
            Toast.makeText(this, R.string.edit_failed_error, Toast.LENGTH_SHORT).show();
           return;
        }

        if(this.isUpdate)
        {
            this.repository.update(this.viewModel.getParticipant());
        }
        else
        {
            // Créer un nouveau participant.
            this.repository.insert(this.viewModel.getParticipant());
        }

        Toast.makeText(this, R.string.edit_succeeded_error, Toast.LENGTH_SHORT).show();
    }

    private void back() {
        super.onBackPressed();
    }

    private boolean isFormValid() {
        EditText txtNom = this.findViewById(R.id.txt_participant_edition_nom);
        EditText txtPrenom = this.findViewById(R.id.txt_participant_edition_prenom);

        boolean isValid = !FormHelper.isEmpty(this, txtNom, R.string.nom_participant_error);

        // Si le prénom est vide, on lui donne juste une chaîne vide.
        if (FormHelper.isEmpty(txtPrenom))
        {
            txtPrenom.setText("");
        }

        return isValid;
    }
}
