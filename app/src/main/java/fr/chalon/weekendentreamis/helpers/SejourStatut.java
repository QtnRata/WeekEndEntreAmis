package fr.chalon.weekendentreamis.helpers;

public class SejourStatut {
    public String getStatutLibelle(int idStatut){
        switch(idStatut){
            case 1: return "Valide";
            case 2: return "Clos";
            case 3: return "Annule";
        }
        return "Statut non valide";

    }
}
