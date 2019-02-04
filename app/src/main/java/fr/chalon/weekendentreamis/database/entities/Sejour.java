package fr.chalon.weekendentreamis.database.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import java.util.Date;
import java.util.List;

@Entity(tableName = "Sejour",
indices = {@Index(value={"id","nom","dateDebut","dateFin","statut"}, unique = true)})
public class Sejour {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="id")
    private long id;

    @ColumnInfo(name="nom")
    private String nom;

    @ColumnInfo(name="dateDebut")
    @TypeConverters(DateConverter.class)
    private String dateDebut;

    @ColumnInfo(name="dateFin")
    @TypeConverters(DateConverter.class)
    private String dateFin;

    @ColumnInfo(name="statut")
    private int statut;


    public Sejour(@NonNull String nom, @NonNull String dateDebut, @NonNull String dateFin, @NonNull int statut){
        this.nom =nom;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.statut = statut;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getStatut() {
        return statut;
    }

    public void setStatut(int statut) {
        this.statut = statut;
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
}
