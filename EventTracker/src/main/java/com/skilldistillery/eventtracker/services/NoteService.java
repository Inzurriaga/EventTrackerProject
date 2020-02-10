package com.skilldistillery.eventtracker.services;

import java.util.List;

import com.skilldistillery.eventtracker.entities.Note;

public interface NoteService {
	public Note getNoteById(int id);
	public List<Note> getAllNotes();
	public Note addNote(Note note);
	public Note updateNote(Note note, int id);
	public boolean deleteNote(int id);
}
