package com.example.simplenotepad.async;

import android.os.AsyncTask;

import com.example.simplenotepad.model.Note;
import com.example.simplenotepad.persistence.NoteDao;

public class DeleteAsyncTask extends AsyncTask<Note, Void, Void> {

        private NoteDao noteDao;

        public DeleteAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.delete(notes[0]);
            return null;
        }
    }
