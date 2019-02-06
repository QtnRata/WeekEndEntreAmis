package fr.chalon.weekendentreamis.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import fr.chalon.weekendentreamis.database.DAO.PaiementDao;
import fr.chalon.weekendentreamis.database.DAO.ParticipantDao;
import fr.chalon.weekendentreamis.database.DAO.ParticipantSejourDao;
import fr.chalon.weekendentreamis.database.DAO.Participant_PosteDepenseDao;
import fr.chalon.weekendentreamis.database.DAO.PosteDepenseDao;
import fr.chalon.weekendentreamis.database.DAO.RemboursementDao;
import fr.chalon.weekendentreamis.database.DAO.SejourDao;
import fr.chalon.weekendentreamis.database.entities.DateConverter;
import fr.chalon.weekendentreamis.database.entities.Paiement;
import fr.chalon.weekendentreamis.database.entities.Participant;
import fr.chalon.weekendentreamis.database.entities.Participant_PosteDepense;
import fr.chalon.weekendentreamis.database.entities.Participant_Sejour;
import fr.chalon.weekendentreamis.database.entities.PosteDepense;
import fr.chalon.weekendentreamis.database.entities.Remboursement;
import fr.chalon.weekendentreamis.database.entities.Sejour;

@Database(entities = {Participant.class, Paiement.class, Participant_PosteDepense.class, Participant_Sejour.class,PosteDepense.class, Remboursement.class, Sejour.class}, version = 1)
@TypeConverters({DateConverter.class})
public abstract class WeekEndDatabase extends RoomDatabase {
    private static volatile WeekEndDatabase INSTANCE;
    //A faire pour chaque DAO
    public abstract ParticipantDao participantDao();
    public abstract SejourDao sejourDao();
    public abstract PosteDepenseDao posteDepenseDao();
    public abstract PaiementDao paiementDao();
    public abstract RemboursementDao remboursementDao();
    public abstract ParticipantSejourDao participantSejourDao();
    public abstract Participant_PosteDepenseDao participant_posteDepenseDao();

    //singleton of the instanciation of the database
    public static WeekEndDatabase getDatabase(final Context context){
        if(INSTANCE == null){
            synchronized (WeekEndDatabase.class){
                if(INSTANCE == null){
                    //création database
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), WeekEndDatabase.class, "WeekEndDatabase")
                            .addCallback(roomDatabaseCallback)
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback roomDatabaseCallback = new RoomDatabase.Callback(){
        @Override
        public void  onOpen(@NonNull SupportSQLiteDatabase db){
            super.onOpen(db);
            new PopulateDbAsync(INSTANCE).execute();
        }
    };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void>{
        private final ParticipantDao participantDao;
        private final SejourDao sejourDao;
        private final PosteDepenseDao posteDepenseDao;
        private final PaiementDao paiementDao;
        private final RemboursementDao remboursementDao;
        private final ParticipantSejourDao participantSejourDao;
        private final Participant_PosteDepenseDao participant_posteDepenseDao;

        PopulateDbAsync(WeekEndDatabase db){
            participantDao = db.participantDao();
            sejourDao = db.sejourDao();
            posteDepenseDao =db.posteDepenseDao();
            paiementDao =db.paiementDao();
            remboursementDao = db.remboursementDao();
            participantSejourDao = db.participantSejourDao();
            participant_posteDepenseDao = db.participant_posteDepenseDao();
        }

        @Override
        protected Void doInBackground(final Void... params){

                if(participantDao.getCountParticipant() <= 0){
                    //delete de toutes les données
                    participantDao.deleteAll();
                    sejourDao.deleteAll();
                    posteDepenseDao.deleteAll();
                    paiementDao.deleteAll();
                    remboursementDao.deleteAll();
                    participant_posteDepenseDao.deleteAll();

                    //ajout de participant
                    Participant p1 = new Participant("Gatefossey", "Dylan");
                    participantDao.insert(p1);
                    Participant p2 = new Participant("Ratajski", "Quentin");
                    participantDao.insert(p2);

                    //ajout de sejour
                    Sejour s1 = new Sejour("Séjour à la plage", DateConverter.getDate("02/03/2018").toString(), DateConverter.getDate("01/02/2018").toString() , 1);
                    sejourDao.insert(s1);
                    Sejour s2 = new Sejour("Séjour au ski", DateConverter.getDate("10/10/2018").toString() , DateConverter.getDate("17/10/2018").toString(),1);
                    sejourDao.insert(s2);

                    participantSejourDao.insert(new Participant_Sejour(2,2));

                    //ajout de poste de depense
                    PosteDepense posteDepense = new PosteDepense("Hotel","ceci est une photo", 200, 1);
                    posteDepenseDao.insert(posteDepense);
                    posteDepense = new PosteDepense("Essence","ceci est une photo", 100, 1);
                    posteDepenseDao.insert(posteDepense);
                    posteDepense = new PosteDepense("karting","ceci est une photo", 50, 2);
                    posteDepenseDao.insert(posteDepense);
                    posteDepense = new PosteDepense("Location de vélo","ceci est une photo", 100, 2);
                    posteDepenseDao.insert(posteDepense);

                    //ajout participant poste depense
                    Participant_PosteDepense participant_posteDepense = new Participant_PosteDepense(1,1);
                    participant_posteDepenseDao.insert(participant_posteDepense);
                    participant_posteDepense = new Participant_PosteDepense(2,1);
                    participant_posteDepenseDao.insert(participant_posteDepense);

                    //ajout de paiement
                /*Paiement paiement = new Paiement(1, 1,100);
                paiementDao.insert(paiement);
                paiement = new Paiement(2,1,50);
                paiementDao.insert(paiement);
                paiement = new Paiement(3 , 2, 25);
                paiementDao.insert(paiement);
                paiement = new Paiement(4, 1, 50);
                paiementDao.insert(paiement);
*/
                    //ajout de remboursement
               /* Remboursement remboursement = new Remboursement(1,2,50);
                remboursementDao.insert(remboursement);
                remboursement = new Remboursement(2,1, 50);
                remboursementDao.insert(remboursement);
*/
                }


                return null;
        }
    }

}
