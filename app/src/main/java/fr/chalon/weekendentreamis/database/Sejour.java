package fr.chalon.weekendentreamis.database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.Date;
import java.util.List;

@Entity(tableName = "sejour")
public class Sejour {
    @PrimaryKey
    @ColumnInfo(name="id")
    private int id;

    @ColumnInfo(name="nom")
    private String nom;

    @ColumnInfo(name="dateDebut")
    private Date dateDebut;

    @ColumnInfo(name="dateFin")
    private Date dateFin;


    @ColumnInfo(name="statut")
    private int statut;

    @ColumnInfo(name="participants")
    private List<Participant> participants;

    public Sejour(@NonNull String nom, @NonNull Date dateDebut, @NonNull Date dateFin, @NonNull List<Participant> participants){
        this.nom =nom;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.participants = participants;

    }
}
