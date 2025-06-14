package com.moodtracker.mood_tracker.service;

import com.moodtracker.mood_tracker.model.Mood;
import com.moodtracker.mood_tracker.dto.MoodRequest;
import com.moodtracker.mood_tracker.dto.MoodResponse;
import com.moodtracker.mood_tracker.repository.MoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;
import java.time.LocalDate;
import java.util.List;

@Service
public class MoodService {
    // @Autowired
    private final MoodRepository moodRepository;

    public MoodService(MoodRepository moodRepository) {
        this.moodRepository = moodRepository;
    }

    public Mood createMood(Mood newMood) {
        newMood.setDate(LocalDate.now());
        return moodRepository.save(newMood);
    }

    public List<Mood> getAllMoods() {
        return moodRepository.findAll();
    }

    public Mood getMoodById(Long id) {
        return moodRepository.findById(id).orElse(null);
    }

    public void deleteMood(Long id) {
        moodRepository.deleteById(id);
    }
}
