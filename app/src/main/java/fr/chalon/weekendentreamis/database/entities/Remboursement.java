package fr.chalon.weekendentreamis.database.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "Remboursement",
indices = {@Index(value={"idParticipantReceveur", "idParticipantEmetteur"},unique = true)},
foreignKeys = {
        @ForeignKey(entity = Participant.class,parentColumns = "id", childColumns = "idParticipantReceveur"),
        @ForeignKey(entity= Participant.class, parentColumns = "id", childColumns = "idParticipantEmetteur")
})
public class Remboursement {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="id")
    private long id;

    @ColumnInfo(name = "idParticipantReceveur")
    private long idParticipantReceveur;

    @ColumnInfo(name = "idParticipantEmetteur")
    private long idParticipantEmetteur;

    @ColumnInfo(name="montant")
    private float montant;

    public Remboursement(@NonNull long idParticipantEmetteur, @NonNull long idParticipantReceveur, @NonNull float montant){
        this.idParticipantEmetteur = idParticipantEmetteur;
        this.idParticipantReceveur = idParticipantReceveur;
        this.montant = montant;
    }
    public long getIdParticipantReceveur() {
        return idParticipantReceveur;
    }

    public void setIdParticipantReceveur(long idParticipantReceveur) {
        this.idParticipantReceveur = idParticipantReceveur;
    }

    public float getMontant() {
        return montant;
    }

    public void setMontant(float montant) {
        this.montant = montant;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdParticipantEmetteur() {
        return idParticipantEmetteur;
    }

    public void setIdParticipantEmetteur(long idParticipantEmetteur) {
        this.idParticipantEmetteur = idParticipantEmetteur;
    }
}
