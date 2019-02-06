package fr.chalon.weekendentreamis.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import fr.chalon.weekendentreamis.database.DAO.ParticipantSejourDao;
import fr.chalon.weekendentreamis.database.DAO.SejourDao;
import fr.chalon.weekendentreamis.database.WeekEndDatabase;
import fr.chalon.weekendentreamis.database.entities.Participant;
import fr.chalon.weekendentreamis.database.entities.Participant_Sejour;
import fr.chalon.weekendentreamis.database.entities.Sejour;

public class SejourRepository {

    private SejourDao sejourDao;
    private ParticipantSejourDao participantSejourDao;
    private LiveData<List<Sejour>> allSejours;
    private LiveData<Sejour> sejour;
    private Executor executor;

    public SejourRepository(Application application) {
        WeekEndDatabase db = WeekEndDatabase.getDatabase(application);
        sejourDao = db.sejourDao();
        participantSejourDao = db.participantSejourDao();
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

    public void deleteBySejourId(Long idSejour){executor.execute(()-> sejourDao.deleteBySejourId(idSejour));}

    public void deleteParticipantSejour(Long idSejour, Long idParticipant) {
        executor.execute(() -> participantSejourDao.deleteParticipantSejour(idSejour, idParticipant));
    }

    public void addParticipantToSejour(Long idSejour, Long idParticipant)
    {
        executor.execute(() -> participantSejourDao.insert(new Participant_Sejour(idSejour, idParticipant)));
    }

    public LiveData<Participant_Sejour> getParticipantSejour(Long idSejour, Long idParticipant)
    {
        return participantSejourDao.getParticipantSejour(idSejour, idParticipant);
    }

    public LiveData<List<Participant>> getParticipantsSejour(Long idSejour)
    {
        return participantSejourDao.getParticipantsSejour(idSejour);
    }

    public LiveData<List<Participant>> getNonParticipantsSejour(Long idSejour) {
        return participantSejourDao.getNonParticipantsSejour(idSejour);
    }
}
