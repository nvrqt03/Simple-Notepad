package com.example.simplenotepad.persistence;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.simplenotepad.model.Note;

@Database(entities = {Note.class}, version = 3) // if you don't add fallbackToDestructiveMigration to Room.databaseBuilder, your app will crash
public abstract class NoteDatabase extends RoomDatabase {

    private static NoteDatabase instance;
    public abstract NoteDao noteDao();

    // in a multi thread environment, its possible that two different threads access this database instance at the same time. Synchronized avoids this.
    public static synchronized NoteDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    NoteDatabase.class, "note_db")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    // The following is to prepopulate the recyclerView with fake notes (3). probably not needed in production

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private NoteDao noteDao;
        private PopulateDbAsyncTask(NoteDatabase db) {
            noteDao = db.noteDao();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.insertNotes(new Note("Title 1", "Description 1", 1));
            noteDao.insertNotes(new Note("Title 2", "Description 2", 1));
            noteDao.insertNotes(new Note("Title 3", "Description 3", 1));
            return null;
        }
    }
}
