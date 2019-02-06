package fr.chalon.weekendentreamis.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;

import fr.chalon.weekendentreamis.database.entities.PosteDepense;

public class PosteDepenseListViewModel extends ViewModel {

    private String libelle;
    private String facture;
    private float montantTotal;
    private long idSejour;





    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getFacture() {
        return facture;
    }

    public void setFacture(String facture) {
        this.facture = facture;
    }

    public float getMontantTotal() {
        return montantTotal;
    }

    public void setMontantTotal(float montantTotal) {
        this.montantTotal = montantTotal;
    }

    public long getIdSejour() {
        return idSejour;
    }

    public void setIdSejour(long idSejour) {
        this.idSejour = idSejour;
    }
}
