package fr.chalon.weekendentreamis.viewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

import fr.chalon.weekendentreamis.database.entities.Participant;
import fr.chalon.weekendentreamis.repository.ParticipantRepository;

public class ParticipantViewModel extends AndroidViewModel {
    public ParticipantRepository participantRepository;
    private LiveData<List<Participant>> allParticipants;

    public ParticipantViewModel(Application application) {
        super(application);
        participantRepository = new ParticipantRepository(application);
        allParticipants = participantRepository.getAllParticipants();

    }

    public LiveData<List<Participant>> getAllParticipants(){return allParticipants;}

    //public void insert(Participant participant){ participantRepository.insert(participant);}

}

