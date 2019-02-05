package fr.chalon.weekendentreamis.viewmodels;

import android.arch.lifecycle.ViewModel;

public class PosteDepenseListViewModel extends ViewModel {

    private String libelle;
    private String facture;
    private float motantTotal;

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

    public float getMotantTotal() {
        return motantTotal;
    }

    public void setMotantTotal(float motantTotal) {
        this.motantTotal = motantTotal;
    }
}
