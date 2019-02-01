package fr.chalon.weekendentreamis.viewmodels;

import android.arch.lifecycle.ViewModel;

public class SejourListViewModel extends ViewModel {
    // TODO: Implement the ViewModel

    private String nom;
    private String dateDebut;
    private String dateFin;
    private long statut;


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

    public long getStatut() {
        return statut;
    }

    public void setStatut(long statut) {
        this.statut = statut;
    }
}
