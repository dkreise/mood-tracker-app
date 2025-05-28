package com.moodtracker.mood_tracker.controller;

import com.moodtracker.mood_tracker.model.Mood;
import com.moodtracker.mood_tracker.service.MoodService;
import com.moodtracker.mood_tracker.dto.MoodRequest;
import com.moodtracker.mood_tracker.dto.MoodResponse;
import com.moodtracker.mood_tracker.repository.MoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.stream.Collectors;
import java.time.LocalDate;

@RestController
@RequestMapping("/api/moods")
public class MoodController {
    // @Autowired
    // private MoodService moodService;
    private final MoodRepository moodRepository;
    private final ModelMapper modelMapper;

    public MoodController(MoodRepository moodRepository, ModelMapper modelMapper) {
        this.moodRepository = moodRepository;
        this.modelMapper = modelMapper;
    }

    // @PostMapping
    // public Mood addMood(@RequestBody Mood mood) {
    //     return moodService.saveMood(mood);
    // }

    // @GetMapping
    // public List<Mood> getAllMoods() {
    //     return moodService.getAllMoods();
    // }
    @PostMapping
    public ResponseEntity<MoodResponse> createMood(@RequestBody MoodRequest moodRequest) {
        Mood mood = modelMapper.map(moodRequest, Mood.class);
        mood.setDate(LocalDate.now());

        Mood savedMood = moodRepository.save(mood);
        MoodResponse response = modelMapper.map(savedMood, MoodResponse.class);

        return ResponseEntity.status(201).body(response);
    }

    @GetMapping
    public List<MoodResponse> getAllMoods() {
        return moodRepository.findAll().stream()
                .map(mood -> modelMapper.map(mood, MoodResponse.class))
                .collect(Collectors.toList());
    }

    // @GetMapping("/{id}")
    // public Mood getMoodById(@PathVariable Long id) {
    //     return moodService.getMoodById(id);
    // }

    // @DeleteMapping("/{id}")
    // public void deleteMood(@PathVariable Long id) {
    //     moodService.deleteMood(id);
    // }
}
