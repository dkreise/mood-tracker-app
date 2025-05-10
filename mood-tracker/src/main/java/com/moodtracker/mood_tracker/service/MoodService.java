package com.moodtracker.mood_tracker.service;

import com.moodtracker.mood_tracker.model.Mood;
import com.moodtracker.mood_tracker.repository.MoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MoodService {
    @Autowired
    private MoodRepository moodRepository;

    public List<Mood> getAllMoods() {
        return moodRepository.findAll();
    }

    public Mood getMoodById(Long id) {
        return moodRepository.findById(id).orElse(null);
    }

    public Mood saveMood(Mood mood) {
        return moodRepository.save(mood);
    }

    public void deleteMood(Long id) {
        moodRepository.deleteById(id);
    }
}
