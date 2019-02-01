package fr.chalon.weekendentreamis;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import fr.chalon.weekendentreamis.database.entities.Participant;
import fr.chalon.weekendentreamis.databinding.ActivityParticipantEditionBinding;
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
        this.viewModel = new ParticipantEditionViewModel();
        binding.setViewModel(this.viewModel);
        binding.setOnClickListener(v -> onSubmit());
    }

    private void onSubmit() {
        if(this.isUpdate)
        {
            long id = getIntent().getLongExtra("PARAM_PARTICIPANT_ID", 0);
            Log.d("_DEBUG", "L'id du participant à modifier est " + Long.toString(id));

            this.repository.getParticipantById(id).observe(this, participant -> {

                if (participant != null && participant.getId() > 0)
                {
                    Log.d("_DEBUG", "Le participant non modifié : " + participant.getNom().toUpperCase() + " " + participant.getPrenom());

                    participant.setNom(this.viewModel.getNom());
                    participant.setPrenom(this.viewModel.getPrenom());

                    Log.d("_DEBUG", "Le participant modifié (pas mis à jour dans la bdd) : " + participant.getNom().toUpperCase() + " " + participant.getPrenom());

                    this.repository.update(participant);

                    this.repository.getParticipantById(id).observe(this, p -> {
                        Log.d("_DEBUG", "Le participant modifié (mis à jour) : " + p.getNom().toUpperCase() + " " + p.getPrenom());
                    });
                }
            });
        }
        else
        {
            Participant participant = new Participant(this.viewModel.getNom(), this.viewModel.getPrenom());
            this.repository.insert(participant);
        }
    }
}
