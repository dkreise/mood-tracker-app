package com.moodtracker.mood_tracker.controller;

import com.moodtracker.mood_tracker.model.Mood;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.moodtracker.mood_tracker.dto.MoodRequest;
import com.moodtracker.mood_tracker.dto.MoodResponse;
import com.moodtracker.mood_tracker.repository.MoodRepository;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MoodController.class)
public class MoodControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MoodRepository moodRepository;

    @MockBean
    private ModelMapper modelMapper;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testCreateMood() throws Exception {
        MoodRequest request = new MoodRequest();
        request.setMood("Happy");

        Mood mood = new Mood();
        mood.setMood("Happy");
        mood.setDate(LocalDate.now());

        Mood savedMood = new Mood();
        savedMood.setId(1L);
        savedMood.setMood("Happy");
        savedMood.setDate(LocalDate.now());

        MoodResponse response = new MoodResponse();
        response.setId(1L);
        response.setMood("Happy");
        response.setDate(LocalDate.now());

        when(modelMapper.map(any(MoodRequest.class), eq(Mood.class))).thenReturn(mood);
        when(moodRepository.save(mood)).thenReturn(savedMood);
        when(modelMapper.map(savedMood, MoodResponse.class)).thenReturn(response);

        mockMvc.perform(post("/api/moods")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.mood").value("Happy"))
                .andExpect(jsonPath("$.date").value(LocalDate.now().toString()));
    }

    @Test
    public void testGetAllMoods() throws Exception {
        Mood mood1 = new Mood("Happy", "Feeling great!", LocalDate.now());
        Mood mood2 = new Mood();
        mood2.setId(2L);
        mood2.setMood("Sad");
        mood2.setNote("Feeling down.");
        mood2.setDate(LocalDate.now().minusDays(1));
        List<Mood> moods = List.of(mood1, mood2);

        MoodResponse response1 = new MoodResponse();
        response1.setId(1L);
        response1.setMood("Happy");
        response1.setNote("Feeling great!");
        response1.setDate(LocalDate.now());

        MoodResponse response2 = new MoodResponse();
        response2.setId(2L);
        response2.setMood("Sad");
        response2.setNote("Feeling down.");
        response2.setDate(LocalDate.now().minusDays(1));

        when(modelMapper.map(mood1, MoodResponse.class)).thenReturn(response1);
        when(modelMapper.map(mood2, MoodResponse.class)).thenReturn(response2);

        when(moodRepository.findAll()).thenReturn(moods);

        mockMvc.perform(get("/api/moods"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].mood").value("Happy"))
                .andExpect(jsonPath("$[0].note").value("Feeling great!"))
                .andExpect(jsonPath("$[1].mood").value("Sad"))
                .andExpect(jsonPath("$[1].note").value("Feeling down."));
    }
}