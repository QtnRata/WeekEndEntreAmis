package fr.chalon.weekendentreamis;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;

import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import fr.chalon.weekendentreamis.database.entities.Sejour;
import fr.chalon.weekendentreamis.databinding.ActivityDetailSejourBinding;
import fr.chalon.weekendentreamis.helpers.SejourStatut;
import fr.chalon.weekendentreamis.recyclerviews.RecyclerViewAdapter;
import fr.chalon.weekendentreamis.recyclerviews.RecyclerViewHolderActions;
import fr.chalon.weekendentreamis.repository.PosteDepenseRepository;
import fr.chalon.weekendentreamis.repository.SejourRepository;
import fr.chalon.weekendentreamis.viewmodels.SejourListViewModel;

public class SejourDetailsActivity extends AppCompatActivity {

    private SejourRepository sejourRepository;
    private PosteDepenseRepository posteDepenseRepository;
    private SejourListViewModel sejourListViewModel;
    private ActivityDetailSejourBinding binding;
    private SejourStatut sejourStatut;

    private RecyclerView posteDepenseRecyclerView;
    private RecyclerView.LayoutManager posteDepenseRecyclerLayoutManager;
    private RecyclerView.Adapter posteDepenseRecyclerViewAdapter;

    private RecyclerView participantRecyclerView;



    private RecyclerView recapitulatifRecyclerView;

    private RecyclerViewHolderActions actions;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail_sejour);

        sejourRepository = new SejourRepository(this.getApplication());
        sejourListViewModel = ViewModelProviders.of(this).get(SejourListViewModel.class);
        sejourStatut = new SejourStatut();

        posteDepenseRepository = new PosteDepenseRepository(this.getApplication());

        long idSejour = getIntent().getLongExtra("id",0);

        Log.d("idSejour", Long.toString(idSejour));
        sejourRepository.getSejourById(idSejour).observe(this, sejour -> {
            if(sejour != null){
                sejourListViewModel.setNom(sejour.getNom());
                sejourListViewModel.setDateDebut(sejour.getDateDebut());
                sejourListViewModel.setDateFin(sejour.getDateFin());
                int idStatut = sejour.getStatut();
                String libelleStatut = sejourStatut.getStatutLibelle(idStatut);
                sejourListViewModel.setStatut(libelleStatut);
            }


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

        participantRecyclerView = (RecyclerView)findViewById(R.id.recycler_view_participant_sejour);
       // recapitulatifRecyclerView = (RecyclerView)findViewById(R.id.recycler_view_recapitulatif);


        //recapitulatifRecyclerView.setNestedScrollingEnabled(false);



    }

    @Override
    public void onStart() {

        super.onStart();
    }
}
