package fr.chalon.weekendentreamis.database.DAO;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import fr.chalon.weekendentreamis.database.entities.Participant_PosteDepense;
import fr.chalon.weekendentreamis.database.entities.PosteDepense;

@Dao
public interface PosteDepenseDao {
    @Insert
    void insert(PosteDepense posteDepense);

    @Update
    void update(PosteDepense posteDepense);

    @Delete
    void delete(PosteDepense posteDepense);

    @Query("DELETE FROM PosteDepense WHERE id = :idPoste")
    void deleteByPosteId(Long idPoste);

    @Query("DELETE FROM PosteDepense")
    void deleteAll();

    @Query("SELECT * FROM POSTEDEPENSE")
    LiveData<List<PosteDepense>> getAllPosteDepense();

    @Query("SELECT * " +
            "FROM POSTEDEPENSE " +
            "WHERE idSejour = :idSejour")
    LiveData<List<PosteDepense>> getPosteDepenseBySejour(long idSejour);

    @Query("SELECT * FROM PosteDepense WHERE id = :id")
    LiveData<PosteDepense> getPosteDepenseById(long id);

    @Query("SELECT idSejour FROM PosteDepense WHERE id = :id")
    LiveData<Long> getIdSejourPosteDepenseById(long id);





}
