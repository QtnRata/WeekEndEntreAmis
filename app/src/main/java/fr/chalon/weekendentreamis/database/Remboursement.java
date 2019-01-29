package fr.chalon.weekendentreamis.database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "Remboursement")
public class Remboursement {
    @PrimaryKey
    @ColumnInfo(name="id")
    private int id;

    @ColumnInfo(name = "idParticipantReceveur")
    private int idParticipantReceveur;

    @ColumnInfo(name = "idParticipantEmetteur")
    private int idParticipantEmetteur;

    @ColumnInfo(name="montant")
    private float montant;
}
