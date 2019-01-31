package fr.chalon.weekendentreamis.database.DAO;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import fr.chalon.weekendentreamis.database.entities.Sejour;

@Dao
public interface SejourDao {
    @Insert
    void insert(Sejour sejour);

    @Update
    void update(Sejour sejour);

    @Delete
    void delete(Sejour sejour);

    @Query("DELETE FROM sejour")
    void deleteAll();

    @Query("SELECT * FROM SEJOUR ORDER BY nom ASC")
    LiveData<List<Sejour>> getAllSejours();

    @Query("SELECT * FROM SEJOUR WHERE id = :idSejour")
    LiveData<Sejour> getSejourById(int idSejour);

}
