package fr.chalon.weekendentreamis.database.DAO;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import fr.chalon.weekendentreamis.database.entities.Participant_PosteDepense;

@Dao
public interface Participant_PosteDepenseDao {

    @Insert
    void insert(Participant_PosteDepense participant_posteDepense);

    @Query("DELETE FROM Participant_PosteDepense")
    void deleteAll();


}
