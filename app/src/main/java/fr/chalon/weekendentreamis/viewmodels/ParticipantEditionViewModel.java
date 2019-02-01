package fr.chalon.weekendentreamis.viewmodels;

import android.arch.lifecycle.ViewModel;

public class ParticipantEditionViewModel extends ViewModel {

    private String nom;
    private String prenom;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
}
