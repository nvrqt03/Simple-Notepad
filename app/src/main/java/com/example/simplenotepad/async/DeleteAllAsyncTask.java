package com.example.simplenotepad.async;

import android.os.AsyncTask;

import com.example.simplenotepad.model.Note;
import com.example.simplenotepad.persistence.NoteDao;

public class DeleteAllAsyncTask extends AsyncTask<Void, Void, Void> {

        private NoteDao noteDao;

        public DeleteAllAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.deleteAllNotes();
            return null;
        }
    }

