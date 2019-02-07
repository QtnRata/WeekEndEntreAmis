package fr.chalon.weekendentreamis;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import fr.chalon.weekendentreamis.databinding.ActivityDetailSejourBinding;
import fr.chalon.weekendentreamis.databinding.ActivityPosteDepenseDetailsBinding;
import fr.chalon.weekendentreamis.databinding.ActivityPosteDepenseEditionBinding;
import fr.chalon.weekendentreamis.repository.ParticipantRepository;
import fr.chalon.weekendentreamis.repository.PosteDepenseRepository;
import fr.chalon.weekendentreamis.viewmodels.PosteDepenseListViewModel;

public class PosteDepenseDetailsActivity extends AppCompatActivity {


    private ActivityPosteDepenseDetailsBinding binding;
    private ParticipantRepository participantRepository;
    private PosteDepenseListViewModel posteDepenseListViewModel;
    private PosteDepenseRepository posteDepenseRepository;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_poste_depense_details);



        posteDepenseRepository = new PosteDepenseRepository(this.getApplication());
        participantRepository = new ParticipantRepository(this.getApplication());
        posteDepenseListViewModel = ViewModelProviders.of(this).get(PosteDepenseListViewModel.class);

        long idPosteDepense = getIntent().getLongExtra("id", 0);


        posteDepenseRepository.getPosteDepenseById(idPosteDepense).observe(this, posteDepense -> {
            if(posteDepense != null){
                posteDepenseListViewModel.setLibelle(posteDepense.getLibelle());
                posteDepenseListViewModel.setFacture(posteDepense.getFacture());
                posteDepenseListViewModel.setMontantTotal(posteDepense.getMontantTotal());
            }else{

            }
            binding.setViewModel(posteDepenseListViewModel);
        });
    }
}
