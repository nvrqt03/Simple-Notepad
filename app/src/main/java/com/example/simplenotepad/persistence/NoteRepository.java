package com.example.simplenotepad.persistence;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.simplenotepad.async.DeleteAllAsyncTask;
import com.example.simplenotepad.async.DeleteAsyncTask;
import com.example.simplenotepad.async.InsertAsyncTask;
import com.example.simplenotepad.async.UpdateAsyncTask;
import com.example.simplenotepad.model.Note;

import java.util.List;

public class NoteRepository {

    private NoteDao noteDao;
    private LiveData<List<Note>> allNotes;

    public NoteRepository(Application application) {
        NoteDatabase database = NoteDatabase.getInstance(application);
        noteDao = database.noteDao();
        allNotes = noteDao.getAllNotes();
    }

    public void insert(Note note) {
        new InsertAsyncTask(noteDao).execute(note);
    }

    public void update(Note note) {
        new UpdateAsyncTask(noteDao).execute(note);
    }

    public void delete(Note note) {
       new DeleteAsyncTask(noteDao).execute(note);
    }

    public void deleteAllNotes() {
        new DeleteAllAsyncTask(noteDao).execute() ;
    }

    public LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }


}
