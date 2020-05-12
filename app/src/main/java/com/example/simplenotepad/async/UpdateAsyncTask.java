package com.example.simplenotepad.async;

import android.os.AsyncTask;

import androidx.room.Update;

import com.example.simplenotepad.model.Note;
import com.example.simplenotepad.persistence.NoteDao;

public class UpdateAsyncTask extends AsyncTask<Note, Void, Void> {

    private NoteDao noteDao;

    public UpdateAsyncTask(NoteDao noteDao) {
        this.noteDao = noteDao;
    }

    @Override
    protected Void doInBackground(Note... notes) {
        noteDao.update(notes[0]);
        return null;
    }
}
