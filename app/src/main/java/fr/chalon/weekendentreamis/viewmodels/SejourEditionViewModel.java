package fr.chalon.weekendentreamis.viewmodels;

import android.arch.lifecycle.ViewModel;

import fr.chalon.weekendentreamis.database.entities.Sejour;

public class SejourEditionViewModel extends ViewModel {

    private Sejour sejour;

    public Sejour getSejour() { return this.sejour;}
    public void setSejour(Sejour sejour) {  this.sejour = sejour;}
}
