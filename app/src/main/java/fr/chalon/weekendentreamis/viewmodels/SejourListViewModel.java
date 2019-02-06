package fr.chalon.weekendentreamis.viewmodels;

import android.arch.lifecycle.ViewModel;

import fr.chalon.weekendentreamis.database.entities.Sejour;

public class SejourListViewModel extends ViewModel {
    // TODO: Implement the ViewModel

    private String nom;
    private String dateDebut;
    private String dateFin;
    private String statut;


    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(String dateDebut) {
        this.dateDebut = dateDebut;
    }

    public String getDateFin() {
        return dateFin;
    }

    public void setDateFin(String dateFin) {
        this.dateFin = dateFin;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

}
