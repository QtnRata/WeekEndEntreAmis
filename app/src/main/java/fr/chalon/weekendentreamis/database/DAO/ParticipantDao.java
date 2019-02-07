package fr.chalon.weekendentreamis.database.DAO;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import fr.chalon.weekendentreamis.database.entities.Participant;
import fr.chalon.weekendentreamis.database.entities.Participant_PosteDepense;

@Dao
public interface ParticipantDao {
    @Insert
    void insert(Participant participant);

    @Update
    void update(Participant participant);

    @Delete
    void delete( Participant idParticipant);

    @Query("DELETE FROM Participant")
    void deleteAll();

    @Query("DELETE FROM Participant WHERE id = :idParticipant")
    void deleteParticipantById(long idParticipant);

    @Query("SELECT * FROM Participant ORDER BY nom ASC")
    LiveData<List<Participant>> getAllParticipants();

    @Query("SELECT * FROM Participant WHERE id = :idParticipant")
    LiveData<Participant> getParticipantById(long idParticipant);

    @Query("SELECT * FROM Participant INNER JOIN Participant_Sejour ON Participant.id = Participant_Sejour.idParticipant WHERE Participant_Sejour.idSejour = :idSejour")
    LiveData<List<Participant>> getParticipantBySejourId(long idSejour);

    @Query("SELECT COUNT(*) FROM Participant")
    int getCountParticipant();

    @Query("SELECT * FROM Participant INNER JOIN Participant_PosteDepense ON Participant.id = Participant_PosteDepense.idParticipant WHERE idPosteDepense = :idPosteDepense")
    LiveData<List<Participant>> getParticipantPosteDepense(long idPosteDepense);

}
