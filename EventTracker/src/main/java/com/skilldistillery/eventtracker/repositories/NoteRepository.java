package com.skilldistillery.eventtracker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.eventtracker.entities.Note;

public interface NoteRepository extends JpaRepository<Note, Integer> {

}
