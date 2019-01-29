package fr.chalon.weekendentreamis.database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "Paiement")
public class Paiement {
    @PrimaryKey
    @ColumnInfo(name="id")
    private int id;

    @ColumnInfo(name="idPosteDepense")
    private int idPosteDepense;

    @ColumnInfo(name="idParticipant")
    private int idParticipant;

    @ColumnInfo(name="montant")
    private float montant;


}
