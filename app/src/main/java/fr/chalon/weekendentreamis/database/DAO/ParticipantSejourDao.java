package fr.chalon.weekendentreamis.database.DAO;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import fr.chalon.weekendentreamis.database.entities.Participant;
import fr.chalon.weekendentreamis.database.entities.Participant_Sejour;

@Dao
public interface ParticipantSejourDao {
    @Insert
    void insert(Participant_Sejour participantSejour);

    @Query("SELECT * FROM participant_sejour WHERE idSejour =:idSejour AND idParticipant = :idParticipant")
    LiveData<Participant_Sejour> getParticipantSejour(Long idSejour, Long idParticipant);

    @Query("DELETE FROM participant_sejour WHERE idSejour =:idSejour AND idParticipant = :idParticipant")
    void deleteParticipantSejour(Long idSejour, Long idParticipant);

    @Query("SELECT * FROM participant p INNER JOIN participant_sejour ps ON p.id = ps.idParticipant WHERE ps.idSejour = :idSejour")
    LiveData<List<Participant>> getParticipantsSejour(Long idSejour);

    @Query("SELECT id, nom, prenom FROM participant EXCEPT SELECT id, nom, prenom FROM participant INNER JOIN participant_sejour ON participant.id = participant_sejour.idParticipant WHERE participant_sejour.idSejour = :idSejour")
    LiveData<List<Participant>> getNonParticipantsSejour(Long idSejour);

}
