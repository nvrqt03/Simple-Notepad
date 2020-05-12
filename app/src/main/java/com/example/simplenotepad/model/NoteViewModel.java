package com.example.simplenotepad.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.simplenotepad.persistence.NoteRepository;

import java.util.List;


// we are extending AndroidViewModel as its a subclass of ViewModel. in androidView model we get passed an application in the constructor.
// which we can use when application context is needed **we shouldn't save a reference to an activity in viewodel because viewmodel is meant
// to outlast an activity. saving a reference to an activity after its destroyed can cause a memory leak.
// however we need to pass a context to our repository, so we can instantiate our database instance. This is why we use AndroidViewModel - we get
// passed an application and can pass it down to our repository.

public class NoteViewModel extends AndroidViewModel {

    private NoteRepository repository;
    private LiveData<List<Note>> allNotes;

    public NoteViewModel(@NonNull Application application) {
        super(application);
        repository = new NoteRepository(application);
        allNotes = repository.getAllNotes();
    }

    public void insert(Note note) {
        repository.insert(note);
    }

    public void update(Note note) {
        repository.update(note);
    }

    public void delete(Note note) {
        repository.delete(note);
    }

    public void deleteAllNotes() {
        repository.deleteAllNotes();
    }

    public LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }
}
