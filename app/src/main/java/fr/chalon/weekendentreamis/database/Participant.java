package fr.chalon.weekendentreamis.database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "Participant")
public class Participant {
    @PrimaryKey
    @ColumnInfo(name="id")
    private int id;

    @ColumnInfo(name="nom")
    private String nom;

    @ColumnInfo(name="prenom")
    private String prenom;

    public Participant(@NonNull String nom){
        this.nom = nom;
    }
}
