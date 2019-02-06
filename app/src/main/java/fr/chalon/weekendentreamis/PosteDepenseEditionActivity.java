package fr.chalon.weekendentreamis;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import fr.chalon.weekendentreamis.database.entities.PosteDepense;
import fr.chalon.weekendentreamis.databinding.ActivityPosteDepenseEditionBinding;
import fr.chalon.weekendentreamis.helpers.FormHelper;
import fr.chalon.weekendentreamis.repository.PosteDepenseRepository;
import fr.chalon.weekendentreamis.viewmodels.PosteDepenseEditionViewModel;

public class PosteDepenseEditionActivity extends AppCompatActivity {

    private PosteDepense posteDepense;
    private PosteDepenseRepository posteDepenseRepository;
    private PosteDepenseEditionViewModel posteDepenseEditionViewModel;

    private ActivityPosteDepenseEditionBinding binding;

    private boolean isUpdate =false;

    EditText txtLibellePosteDepense;
    EditText txtFacturePosteDepense;
    EditText txtMontantPosteDepense;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        binding = DataBindingUtil.setContentView(this,R.layout.activity_poste_depense_edition);
        posteDepenseRepository = new PosteDepenseRepository(this.getApplication());
        posteDepenseEditionViewModel = ViewModelProviders.of(this).get(PosteDepenseEditionViewModel.class);

        long idPosteDepense = getIntent().getLongExtra("id", 0 );
        long idSejour = getIntent().getLongExtra("idSejour", 0);

        posteDepenseRepository.getPosteDepenseById(idPosteDepense).observe(this, posteDepense ->{

            if(posteDepense != null){


                posteDepenseEditionViewModel.setPosteDepense(posteDepense);
                this.isUpdate = true;
            }
            else{
                posteDepenseEditionViewModel.setPosteDepense(new PosteDepense("","",0,idSejour));
            }
            binding.setViewModel(posteDepenseEditionViewModel);
            binding.setEditClickListener(v-> onSubmit());
        });

    }

    private void onSubmit(){
        if(noCheckForm()){
            Toast.makeText(this, R.string.edit_failed_error, Toast.LENGTH_SHORT).show();
            return;

        }

        if(this.isUpdate){
            this.posteDepenseRepository.update(this.posteDepenseEditionViewModel.getPosteDepense());

        }else{
            this.posteDepenseRepository.insert(this.posteDepenseEditionViewModel.getPosteDepense());

        }
        Toast t = Toast.makeText(this, R.string.edit_succeeded_error, Toast.LENGTH_SHORT);
        t.show();


    }

    boolean noCheckForm(){
        txtLibellePosteDepense = findViewById(R.id.txtAddPosteDepenseLibelle);
        txtFacturePosteDepense = findViewById(R.id.txtFacturePosteDepense);
        txtMontantPosteDepense = findViewById(R.id.txtMontantPosteDepense);

        if(FormHelper.isEmpty(this, txtLibellePosteDepense, R.string.error_missing_libelle)){
            txtLibellePosteDepense.setText("");
            return true;
        }
        if(FormHelper.isEmpty(this, txtFacturePosteDepense, R.string.error_missing_facture)){
            txtFacturePosteDepense.setText("");
            return true;
        }
        if(FormHelper.isEmpty(this, txtMontantPosteDepense, R.string.error_missing_montant)){
            txtMontantPosteDepense.setText("");
            return true;
        }

        return false;
    }
}
