package fr.chalon.weekendentreamis.database.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.List;

@Entity(tableName="PosteDepense",
        indices = {@Index(value = {"libelle","facture","montantTotal","id","idSejour"}, unique = true)})
public class PosteDepense {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="id")
    private long id;

    @ColumnInfo(name="libelle")
    private String libelle;

    @ColumnInfo(name="facture")
    private String facture;

    @ColumnInfo(name="montantTotal")
    private float montantTotal;

    @ColumnInfo(name="idSejour")
    private long idSejour;


    public PosteDepense(@NonNull String libelle, @NonNull String facture, @NonNull float montantTotal, @NonNull long idSejour){
        this.libelle = libelle;
        this.facture = facture;
        this.montantTotal = montantTotal;
        this.idSejour = idSejour;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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
