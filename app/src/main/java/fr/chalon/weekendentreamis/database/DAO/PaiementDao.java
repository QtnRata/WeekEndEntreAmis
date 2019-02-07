package fr.chalon.weekendentreamis.database.DAO;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import fr.chalon.weekendentreamis.database.entities.Paiement;

@Dao
public interface PaiementDao {
    @Insert
    void insert(Paiement paiement);

    @Update
    void update(Paiement paiement);

    @Query("DELETE FROM Paiement")
    void deleteAll();

    @Query("SELECT * FROM Paiement WHERE idParticipant = :idParticipant")
    LiveData<List<Paiement>> getPaiementByParticipant(int idParticipant);

    @Query("SELECT * " +
            "FROM Paiement " +
            "INNER JOIN PosteDepense " +
            "ON Paiement.idPosteDepense = PosteDepense.id " +
            "INNER JOIN Sejour " +
            "ON Sejour.id = PosteDepense.idSejour " +
            "WHERE PosteDepense.id = :idSejour")
    LiveData<List<Paiement>> getPaiementBySejour(int idSejour);

    @Query("SELECT * FROM Paiement WHERE idPosteDepense = :idPosteDepense")
    LiveData<List<Paiement>> getPaiementByPosteDepense(int idPosteDepense);


}
