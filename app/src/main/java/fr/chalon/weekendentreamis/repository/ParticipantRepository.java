package fr.chalon.weekendentreamis.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.Executors;

import fr.chalon.weekendentreamis.database.DAO.ParticipantDao;
import fr.chalon.weekendentreamis.database.WeekEndDatabase;
import fr.chalon.weekendentreamis.database.entities.Participant;

public class ParticipantRepository {
    private Application app;
    private ParticipantDao participantDao;
    private java.util.concurrent.Executor executor;
    private LiveData<List<Participant>> allParticipants;

    public ParticipantRepository(Application application) {
        this.app = application;
        WeekEndDatabase db = WeekEndDatabase.getDatabase(application);
        participantDao = db.participantDao();
        allParticipants = participantDao.getAllParticipants();
        executor = Executors.newSingleThreadExecutor();
    }

    public LiveData<List<Participant>> getAllParticipants() {
        return allParticipants;
    }

    public void insert(Participant participant) {
        executor.execute(() -> {
            participantDao.insert(participant);
        });
    }

    public void update(Participant participant) {
        executor.execute(() -> {
            participantDao.update(participant);
        });
    }

    public LiveData<Participant> getParticipantById(long idParticipant) {
        return participantDao.getParticipantById(idParticipant);
    }

    public void deleteParticipantById(Long idParticipant) {
        executor.execute(() -> {
            this.participantDao.deleteParticipantById(idParticipant);
        });
    }
    public LiveData<List<Participant>> getParticipantPosteDepense(long idPosteDepense){ return participantDao.getParticipantPosteDepense(idPosteDepense);}
}
