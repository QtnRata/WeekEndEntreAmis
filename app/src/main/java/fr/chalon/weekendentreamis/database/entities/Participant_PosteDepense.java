package fr.chalon.weekendentreamis.database.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.support.annotation.NonNull;

@Entity(tableName = "Participant_PosteDepense",
primaryKeys = {"idParticipant","idPosteDepense"},
        indices = {@Index(value={"idParticipant","idPosteDepense"},unique = true)}/*,
foreignKeys = {@ForeignKey(entity = Participant.class, parentColumns = "id", childColumns = "idParticipant"),
               @ForeignKey(entity = PosteDepense.class , parentColumns = "id", childColumns = "idPosteDepense")
}*/)

public class Participant_PosteDepense {

    @ColumnInfo(name="idParticipant")
    private long idParticipant;

    @ColumnInfo(name="idPosteDepense")
    private long idPosteDepense;

    public Participant_PosteDepense(@NonNull long idParticipant, @NonNull long idPosteDepense){
        this.idParticipant = idParticipant;
        this.idPosteDepense = idPosteDepense;
    }


    public long getIdParticipant() {
        return idParticipant;
    }

    public void setIdParticipant(long idParticipant) {
        this.idParticipant = idParticipant;
    }

    public long getIdPosteDepense() {
        return idPosteDepense;
    }

    public void setIdPosteDepense(long idPosteDepense) {
        this.idPosteDepense = idPosteDepense;
    }
}
