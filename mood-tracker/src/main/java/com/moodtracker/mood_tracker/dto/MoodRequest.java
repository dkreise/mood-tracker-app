package com.moodtracker.mood_tracker.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

public class MoodRequest {
    @NotBlank(message = "Mood type must not be blank")
    @Size(max = 50, message = "Mood must not exceed 50 characters")
    @Schema(description = "Type of mood", example = "joyful", required = true)
    private String mood;

    @Size(max = 255, message = "Note must not exceed 255 characters")
    @Schema(description = "Optional note", example = "Spending time with my sister today!")
    private String note;

    public MoodRequest() {}

    public String getMood() {
        return mood;
    }

    public void setMood(String mood) {
        this.mood = mood;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "MoodRequest{mood='" + mood + "', note='" + note + "'}";
    }
}
