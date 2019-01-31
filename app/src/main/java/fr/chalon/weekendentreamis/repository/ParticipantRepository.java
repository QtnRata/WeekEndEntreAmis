package fr.chalon.weekendentreamis.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.os.AsyncTask;

import java.util.List;

import fr.chalon.weekendentreamis.database.DAO.ParticipantDao;
import fr.chalon.weekendentreamis.database.WeekEndDatabase;
import fr.chalon.weekendentreamis.database.entities.Participant;

public class ParticipantRepository {
    private ParticipantDao participantDao;
    private LiveData<List<Participant>> allParticipants;

    public ParticipantRepository(Application application) {
        WeekEndDatabase db = WeekEndDatabase.getDatabase(application);
        participantDao = db.participantDao();
        allParticipants = participantDao.getAllParticipants();

    }

    public LiveData<List<Participant>> getAllParticipants() {
        return allParticipants;
    }


    /*public LiveData<Participant> getParticipantById(long idParticipant) {
        return participantDao.getParticipantById(idParticipant);
    }

    public LiveData<List<Participant>> getParticipantBySejourId(long idSejour) {
        return participantDao.getParticipantBySejourId(idSejour);
    }

    //Insert participant
    public void insert(Participant participant) {
        new insertAsyncTask(participantDao).execute(participant);
    }

    //methode d'insert
    private static class insertAsyncTask extends AsyncTask<Participant, Void, Void> {

        private ParticipantDao mAsyncTaskDao;

        insertAsyncTask(ParticipantDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Participant... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    public void delete(Long idParticipant) {
        DeleteAsyncTask task = new DeleteAsyncTask(participantDao);
        task.execute(idParticipant);

    }

    private static class DeleteAsyncTask extends AsyncTask<Long, Void, Void> {

        private ParticipantDao asyncTaskDao;

        DeleteAsyncTask(ParticipantDao dao) {
            asyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Long... params) {
            asyncTaskDao.delete(params[0]);
            return null;
        }
    }*/
}
