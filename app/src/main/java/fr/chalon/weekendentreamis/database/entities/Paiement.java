package fr.chalon.weekendentreamis.database.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "Paiement",
        indices = {@Index(value = {"idPosteDepense", "idParticipant"},unique = true)}/*,
        foreignKeys = {
        @ForeignKey(entity = PosteDepense.class, parentColumns = "id", childColumns = "idPosteDepense"),
        @ForeignKey(entity = Participant.class, parentColumns = "id", childColumns = "idParticipant")
}*/)

public class Paiement {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="id")
    private long id;

    @ColumnInfo(name="idPosteDepense")
    private long idPosteDepense;

    @ColumnInfo(name="idParticipant")
    private long idParticipant;

    @ColumnInfo(name="montant")
    private float montant;

    public Paiement(@NonNull long idPosteDepense, @NonNull long idParticipant, @NonNull float montant){
        this.idParticipant = idParticipant;
        this.idPosteDepense = idPosteDepense;
        this.montant = montant;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdPosteDepense() {
        return idPosteDepense;
    }

    public void setIdPosteDepense(long idPosteDepense) {
        this.idPosteDepense = idPosteDepense;
    }

    public long getIdParticipant() {
        return idParticipant;
    }

    public void setIdParticipant(long idParticipant) {
        this.idParticipant = idParticipant;
    }

    public float getMontant() {
        return montant;
    }

    public void setMontant(float montant) {
        this.montant = montant;
    }
}
