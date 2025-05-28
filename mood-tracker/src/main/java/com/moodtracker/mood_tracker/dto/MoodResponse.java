package com.moodtracker.mood_tracker.dto;

import java.time.LocalDate;
// import jakarta.validation.constraints.NotBlank;
// import com.fasterxml.jackson.annotation.JsonProperty;

public class MoodResponse {
    private Long id;
    private String mood;
    private String note;
    private LocalDate date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}