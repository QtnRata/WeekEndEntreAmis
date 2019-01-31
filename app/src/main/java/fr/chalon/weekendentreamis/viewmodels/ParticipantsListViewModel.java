package fr.chalon.weekendentreamis.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import fr.chalon.weekendentreamis.database.entities.Participant;

public class ParticipantsListViewModel extends ViewModel {

    private MutableLiveData<List<Participant>> participants;

    public ParticipantsListViewModel()
    {
        this.participants = new MutableLiveData<List<Participant>>();
    }

    public LiveData<List<Participant>> getParticipants() {
        return this.participants;
    }

    public void setParticipants(List<Participant> participants)
    {
        this.participants.setValue(participants);
    }
}
