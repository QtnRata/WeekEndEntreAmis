package fr.chalon.weekendentreamis.database.DAO;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import fr.chalon.weekendentreamis.database.entities.Remboursement;

@Dao
public interface RemboursementDao {
    @Insert
    void insert(Remboursement remboursement);

    @Update
    void update(Remboursement remboursement);

    @Delete
    void delete(Remboursement remboursement);

    @Query("DELETE FROM Remboursement")
    void deleteAll();

    @Query("SELECT * FROM REMBOURSEMENT")
    LiveData<List<Remboursement>> getAllRemboursement();

    @Query("SELECT * FROM REMBOURSEMENT Where idParticipantReceveur = :idParticipantReceveur")
    LiveData<List<Remboursement>> getRemboursementRecuByParticipant(int idParticipantReceveur);

    @Query("SELECT * FROM Remboursement WHERE idParticipantEmetteur = :idParticipantEmetteur")
    LiveData<List<Remboursement>> getRemboursementEmisByParticipant(int idParticipantEmetteur);
}
