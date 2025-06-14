package com.moodtracker.mood_tracker.controller;

import com.moodtracker.mood_tracker.model.Mood;
import com.moodtracker.mood_tracker.service.MoodService;
import com.moodtracker.mood_tracker.dto.MoodRequest;
import com.moodtracker.mood_tracker.dto.MoodResponse;
import com.moodtracker.mood_tracker.repository.MoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

import java.util.List;
import java.util.stream.Collectors;
import java.time.LocalDate;

@RestController
@RequestMapping("/api/moods")
public class MoodController {
    // @Autowired
    private final MoodService moodService;
    private final ModelMapper modelMapper;

    public MoodController(MoodService moodService, ModelMapper modelMapper) {
        this.moodService = moodService;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    @Operation(summary = "Create a new mood entry", description = "Saves a new mood with optional note and current date.")
    public ResponseEntity<MoodResponse> createMood(
        @Parameter(description = "Mood details in JSON format", required = true)
        @RequestBody MoodRequest moodRequest) {

        Mood newMood = modelMapper.map(moodRequest, Mood.class);
        Mood savedMood = moodService.createMood(newMood);
        MoodResponse response = modelMapper.map(savedMood, MoodResponse.class);

        return ResponseEntity.status(201).body(response);
    }

    @GetMapping
    @Operation(summary = "Get all mood entries", description = "Retrieves a list of all mood entries with details.")
    public List<MoodResponse> getAllMoods() {
        return moodService.getAllMoods().stream()
                .map(mood -> modelMapper.map(mood, MoodResponse.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MoodResponse> getMoodById(
        @Parameter(description = "ID of the mood entry to retrieve", required = true)
        @PathVariable Long id) {
        Mood mood = moodService.getMoodById(id);
        if (mood == null) {
            return ResponseEntity.notFound().build();
        }
        MoodResponse response = modelMapper.map(mood, MoodResponse.class);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMoodById(@PathVariable Long id) {
        moodService.deleteMood(id);
    }
}
