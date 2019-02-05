package fr.chalon.weekendentreamis.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import fr.chalon.weekendentreamis.database.DAO.PosteDepenseDao;
import fr.chalon.weekendentreamis.database.WeekEndDatabase;
import fr.chalon.weekendentreamis.database.entities.PosteDepense;

public class PosteDepenseRepository {
    private Application app;
    private PosteDepenseDao posteDepenseDao;
    private LiveData<List<PosteDepense>> posteDepenseBySejour;
    private Executor executor;

    public PosteDepenseRepository(Application application){
        this.app = application;
        WeekEndDatabase db = WeekEndDatabase.getDatabase(application);
        posteDepenseDao = db.posteDepenseDao();
        executor = Executors.newSingleThreadExecutor();


    }

    public LiveData<List<PosteDepense>> getPosteDepenseBySejour(long idSejour){ return posteDepenseDao.getPosteDepenseBySejour(idSejour);}

    public void deleteById(Long idPosteDepense){ executor.execute(()-> posteDepenseDao.deleteByPosteId(idPosteDepense)); }
}
