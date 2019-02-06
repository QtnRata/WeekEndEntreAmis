package fr.chalon.weekendentreamis.database.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.support.annotation.NonNull;

@Entity(tableName = "Participant_Sejour",
primaryKeys = {"idParticipant", "idSejour"},
        indices = {@Index( value = {"idParticipant", "idSejour"},unique = true)}/*,
foreignKeys = {@ForeignKey(entity = Participant.class, parentColumns = "id", childColumns = "idParticipant"),
                @ForeignKey(entity = Sejour.class, parentColumns = "id", childColumns = "idSejour")
}*/)
public class Participant_Sejour {

    @ColumnInfo(name="idParticipant")
    private long idParticipant;

    @ColumnInfo(name="idSejour")
    private long idSejour;

    public Participant_Sejour(@NonNull long idSejour,@NonNull long idParticipant) {
        this.idSejour = idSejour;
        this.idParticipant = idParticipant;
    }

    public long getIdParticipant() {
        return idParticipant;
    }

    public void setIdParticipant(long idParticipant) {
        this.idParticipant = idParticipant;
    }

    public long getIdSejour() {
        return idSejour;
    }

    public void setIdSejour(long idSejour) {
        this.idSejour = idSejour;
    }
}
