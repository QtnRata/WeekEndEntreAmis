package fr.chalon.weekendentreamis.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import fr.chalon.weekendentreamis.database.DAO.SejourDao;
import fr.chalon.weekendentreamis.database.WeekEndDatabase;
import fr.chalon.weekendentreamis.database.entities.Sejour;

public class SejourRepository {

    private SejourDao sejourDao;
    private LiveData<List<Sejour>> allSejours;
    private LiveData<Sejour> sejour;
    private Executor executor;

    public SejourRepository(Application application) {
        WeekEndDatabase db = WeekEndDatabase.getDatabase(application);
        sejourDao = db.sejourDao();
        allSejours = sejourDao.getAllSejours();
        executor = Executors.newSingleThreadExecutor();

    }

    public LiveData<List<Sejour>> getAllSejours() {
        return allSejours;
    }

    public LiveData<Sejour> getSejourById(Long idSejour){ return sejourDao.getSejourById(idSejour);}

    public void insert(Sejour sejour){
        executor.execute(()-> sejourDao.insert(sejour));
    }

    public void update(Sejour sejour){ executor.execute(()-> sejourDao.update(sejour));}

    public void deleteBySejourId(Long idSejour){executor.execute(()-> sejourDao.deleteBySejourId(idSejour));}

}
