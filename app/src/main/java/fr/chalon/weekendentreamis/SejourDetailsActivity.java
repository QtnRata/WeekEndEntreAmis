package fr.chalon.weekendentreamis;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Map;
import java.util.stream.Collectors;

import fr.chalon.weekendentreamis.database.entities.Participant;
import fr.chalon.weekendentreamis.database.entities.Sejour;
import fr.chalon.weekendentreamis.databinding.ActivityDetailSejourBinding;
import fr.chalon.weekendentreamis.helpers.SejourStatut;
import fr.chalon.weekendentreamis.recyclerviews.RecyclerViewAdapter;
import fr.chalon.weekendentreamis.recyclerviews.RecyclerViewHolderActions;
import fr.chalon.weekendentreamis.repository.ParticipantRepository;
import fr.chalon.weekendentreamis.repository.PosteDepenseRepository;
import fr.chalon.weekendentreamis.repository.SejourRepository;
import fr.chalon.weekendentreamis.viewmodels.SejourListViewModel;

public class SejourDetailsActivity extends AppCompatActivity {

    private SejourRepository sejourRepository;
    private PosteDepenseRepository posteDepenseRepository;
    private ParticipantRepository participantRepository;

    private SejourListViewModel sejourListViewModel;
    private ActivityDetailSejourBinding binding;
    private SejourStatut sejourStatut;

    private RecyclerView posteDepenseRecyclerView;
    private RecyclerView.LayoutManager posteDepenseRecyclerLayoutManager;
    private RecyclerView.Adapter posteDepenseRecyclerViewAdapter;
    private RecyclerView participantRecyclerView;
    private RecyclerView.LayoutManager participantRecyclerViewLayoutManager;
    private RecyclerView.Adapter participantRecyclerViewAdapter;

    private RecyclerView recapitulatifRecyclerView;

    private RecyclerViewHolderActions actions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail_sejour);

        // instances repositories
        sejourRepository = new SejourRepository(this.getApplication());
        posteDepenseRepository = new PosteDepenseRepository(this.getApplication());
        participantRepository = new ParticipantRepository(this.getApplication());

        sejourListViewModel = ViewModelProviders.of(this).get(SejourListViewModel.class);
        sejourStatut = new SejourStatut();

        posteDepenseRepository = new PosteDepenseRepository(this.getApplication());

        long idSejour = getIntent().getLongExtra("id",0);

        sejourRepository.getSejourById(idSejour).observe(this, sejour -> {
            if(sejour != null){
                sejourListViewModel.setNom(sejour.getNom());
                sejourListViewModel.setDateDebut(sejour.getDateDebut());
                sejourListViewModel.setDateFin(sejour.getDateFin());
                int idStatut = sejour.getStatut();
                String libelleStatut = sejourStatut.getStatutLibelle(idStatut);
                sejourListViewModel.setStatut(libelleStatut);
            }

            Button fab = findViewById(R.id.btnAddPosteDepenseSejour);
            fab.setOnClickListener(view -> {
                Intent intent = new Intent(this, PosteDepenseEditionActivity.class);
                intent.putExtra("idSejour", sejour.getId());
                startActivity(intent);
            });
            binding.setViewModel(sejourListViewModel);
        });


        this.actions = new RecyclerViewHolderActions(
            PosteDepenseEditionActivity.class,PosteDepenseDetailsActivity.class,
                (Long idPosteDepense) ->{
                    posteDepenseRepository.deleteById(idPosteDepense);
                }
        );

        //recycler view poste de depense
        posteDepenseRecyclerView = findViewById(R.id.recycler_view_postedepense_sejour);
        posteDepenseRecyclerLayoutManager = new LinearLayoutManager(this);
        posteDepenseRecyclerView.setLayoutManager(posteDepenseRecyclerLayoutManager);
        posteDepenseRecyclerViewAdapter = new RecyclerViewAdapter(this,this.actions);
        posteDepenseRepository.getPosteDepenseBySejour(idSejour).observe(this, posteDepenses ->{
            Map<Long, String> dataWithIds = posteDepenses.stream().collect(Collectors.toMap(p-> p.getId(), p->p.getLibelle()));
            ((RecyclerViewAdapter)this.posteDepenseRecyclerViewAdapter).setData(dataWithIds);
            ((RecyclerViewAdapter)this.posteDepenseRecyclerViewAdapter).notifyDataSetChanged();
        });
        //ajout adapter
        this.posteDepenseRecyclerView.setAdapter(posteDepenseRecyclerViewAdapter);

        // participants
        Button addParticipantButton = (Button)this.findViewById(R.id.btnAddParticipantSejour);
        addParticipantButton.setOnClickListener(v -> {
            Intent i = new Intent(this.getApplicationContext(), SejourAddParticipantActivity.class);
            i.putExtra("idSejour", idSejour);
            startActivity(i);
        });
        this.prepareParticipantRecyclerView(idSejour);

       // recapitulatifRecyclerView = (RecyclerView)findViewById(R.id.recycler_view_recapitulatif);


        //recapitulatifRecyclerView.setNestedScrollingEnabled(false);



    }

    @Override
    public void onStart() {

        super.onStart();
    }

    private void onClickAddParticipant() {

    }

    private void prepareParticipantRecyclerView(Long idSejour) {
        participantRecyclerView = findViewById(R.id.recycler_view_participant_sejour);
        participantRecyclerViewLayoutManager = new LinearLayoutManager(this);
        participantRecyclerView.setLayoutManager(participantRecyclerViewLayoutManager);
        participantRecyclerViewAdapter = new RecyclerViewAdapter(this, new RecyclerViewHolderActions(
                ParticipantEditionActivity.class, ParticipantDetailsActivity.class,
                (Long idParticipant) ->{
                    sejourRepository.deleteParticipantSejour(idSejour, idParticipant);
                }
        ));

        this.sejourRepository.getParticipantsSejour(idSejour).observe(this, participants -> {
            if (participants != null && participants.size() > 0)
            {
                Map<Long, String> data = participants.stream().collect(
                        Collectors.toMap(p -> p.getId(), p -> p.getNom().toUpperCase() + " " + p.getPrenom())
                );
                ((RecyclerViewAdapter)this.participantRecyclerViewAdapter).setData(data);
            }
        });

        participantRecyclerView.setAdapter(participantRecyclerViewAdapter);
    }
}
