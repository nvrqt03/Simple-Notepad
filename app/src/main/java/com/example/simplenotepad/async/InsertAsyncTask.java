package com.example.simplenotepad.async;

import android.os.AsyncTask;

import com.example.simplenotepad.model.Note;
import com.example.simplenotepad.persistence.NoteDao;
import com.example.simplenotepad.persistence.NoteDatabase;


public class InsertAsyncTask extends AsyncTask<Note, Void, Void> {
    private NoteDao noteDao;

    public InsertAsyncTask(NoteDao noteDao) {
        this.noteDao = noteDao;
    }

    @Override
    protected Void doInBackground(Note... notes) {
        noteDao.insertNotes(notes[0]);
        return null;
    }
}

