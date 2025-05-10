package com.moodtracker.mood_tracker.repository;

import com.moodtracker.mood_tracker.model.Mood;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MoodRepository extends JpaRepository<Mood, Long> {
    // automatically get CRUD methods: save(), findById(), findAll(), deleteById()...
    // Custom query methods can be defined here if needed

}
