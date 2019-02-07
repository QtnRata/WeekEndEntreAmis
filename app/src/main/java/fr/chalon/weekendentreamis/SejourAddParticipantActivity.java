package fr.chalon.weekendentreamis;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.Map;
import java.util.stream.Collectors;

import fr.chalon.weekendentreamis.database.entities.Participant_Sejour;
import fr.chalon.weekendentreamis.recyclerviews.RecyclerViewAdapter;
import fr.chalon.weekendentreamis.recyclerviews.RecyclerViewHolderActions;
import fr.chalon.weekendentreamis.repository.ParticipantRepository;
import fr.chalon.weekendentreamis.repository.SejourRepository;

public class SejourAddParticipantActivity extends AppCompatActivity {

    private ParticipantRepository participantRepository;
    private SejourRepository sejourRepository;

    private RecyclerView participantRecyclerView;
    private RecyclerView.Adapter participantRecyclerViewAdapter;
    private RecyclerView.LayoutManager participantRecyclerViewLayoutManager;

    private Long idSejour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sejour_add_participant);
        this.idSejour = this.getIntent().getLongExtra("idSejour", 0);

        this.sejourRepository = new SejourRepository(this.getApplication());
        this.participantRepository = new ParticipantRepository(this.getApplication());
        this.prepareSelectableParticipantsRecyclerView();

        Button btnRetour = this.findViewById(R.id.btn_retour);
        btnRetour.setOnClickListener(v -> {
            super.onBackPressed();
        });
    }

    private void prepareSelectableParticipantsRecyclerView() {
        participantRecyclerView = findViewById(R.id.recycler_view_participant_sejour);
        participantRecyclerViewLayoutManager = new LinearLayoutManager(this);

        participantRecyclerViewAdapter = new RecyclerViewAdapter(this,
                new RecyclerViewHolderActions(
                (Long idParticipant) -> {
                    sejourRepository.addParticipantToSejour(idSejour, idParticipant);
                    })
                    // Si le participant est déjà selectionné, on le déselectionne et inversement.


                    // sejourRepository.isParticipantEngagedInSejour(idSejour, idParticipant) : bool
                    // sejourRepository.addParticipantToSejour(idSejour, idPartipant) : void
                    // sejourRepository.removeParticipantFromSejour(idSejour, idParticipant) : void
                    //this.participantRepository.deleteParticipantById(idParticipant);

        );

        participantRecyclerView.setAdapter(participantRecyclerViewAdapter);

        this.sejourRepository.getNonParticipantsSejour(idSejour).observe(this, participants -> {
            if (participants != null && participants.size() > 0)
            {
                Map<Long, String> data = participants.stream().collect(
                        Collectors.toMap(p -> p.getId(), p -> p.getNom().toUpperCase() + " " + p.getPrenom())
                );
                ((RecyclerViewAdapter)this.participantRecyclerViewAdapter).setData(data);
            }
        });

        participantRecyclerView.setLayoutManager(participantRecyclerViewLayoutManager);
    }
}
