package fr.chalon.weekendentreamis.database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.List;

@Entity(tableName = "Participant")
public class Participant {
    @PrimaryKey
    @ColumnInfo(name="id")
    private int id;

    @ColumnInfo(name="nom")
    private String nom;

    @ColumnInfo(name="prenom")
    private String prenom;

    @ColumnInfo(name="sejours")
    private List<Sejour> sejours;

    @ColumnInfo(name = "PosteDepenses")
    private List<PosteDepense> posteDepenses;

    public Participant(@NonNull String nom, @NonNull List<Sejour> sejours, @NonNull List<PosteDepense> posteDepenses){
        this.nom = nom;
        this.sejours = sejours;
        this.posteDepenses = posteDepenses;
    }
}
