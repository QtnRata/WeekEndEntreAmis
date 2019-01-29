package fr.chalon.weekendentreamis.database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.List;

@Entity(tableName="PosteDepense")
public class PosteDepense {
    @PrimaryKey
    @ColumnInfo(name="id")
    private int id;

    @ColumnInfo(name="libelle")
    private String libelle;

    @ColumnInfo(name="facture")
    private String facture;

    @ColumnInfo(name="montantTotal")
    private float montantTotal;

    @ColumnInfo(name="idSejour")
    private int idSejour;

    @ColumnInfo(name="participants")
    private List<Participant> participants;

    public PosteDepense(@NonNull String libelle, @NonNull String facture, @NonNull List<Participant> participants){
        this.libelle = libelle;
        this.facture = facture;
        this.participants =participants;
    }

}
