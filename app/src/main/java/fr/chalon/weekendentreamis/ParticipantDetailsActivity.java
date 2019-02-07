package fr.chalon.weekendentreamis;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import fr.chalon.weekendentreamis.databinding.ActivityDetailSejourBinding;
import fr.chalon.weekendentreamis.repository.ParticipantRepository;
import fr.chalon.weekendentreamis.viewmodels.PosteDepenseListViewModel;

public class ParticipantDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_participant_details);


    }


}
