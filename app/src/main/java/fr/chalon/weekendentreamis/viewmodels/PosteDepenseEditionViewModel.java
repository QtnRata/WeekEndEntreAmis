package fr.chalon.weekendentreamis.viewmodels;

import android.arch.lifecycle.ViewModel;

import fr.chalon.weekendentreamis.database.entities.PosteDepense;

public class PosteDepenseEditionViewModel extends ViewModel {

    private PosteDepense posteDepense;

    public PosteDepense getPosteDepense(){return this.posteDepense;}
    public void setPosteDepense(PosteDepense posteDepense){this.posteDepense = posteDepense;}

}
