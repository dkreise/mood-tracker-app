package com.moodtracker.mood_tracker.controller;

import com.moodtracker.mood_tracker.model.Mood;
import com.moodtracker.mood_tracker.service.MoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/moods")
public class MoodControler {
    @Autowired
    private MoodService moodService;

    @PostMapping
    public Mood addMood(@RequestBody Mood mood) {
        return moodService.saveMood(mood);
    }

    @GetMapping
    public List<Mood> getAllMoods() {
        return moodService.getAllMoods();
    }

    @GetMapping("/{id}")
    public Mood getMoodById(@PathVariable Long id) {
        return moodService.getMoodById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteMood(@PathVariable Long id) {
        moodService.deleteMood(id);
    }
}
