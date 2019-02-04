package fr.chalon.weekendentreamis.viewmodels;

import android.arch.lifecycle.ViewModel;

import fr.chalon.weekendentreamis.database.entities.Participant;

public class ParticipantEditionViewModel extends ViewModel {

    private Participant participant;

    public Participant getParticipant() { return this.participant; }

    public void setParticipant(Participant participant) { this.participant = participant; }
}
