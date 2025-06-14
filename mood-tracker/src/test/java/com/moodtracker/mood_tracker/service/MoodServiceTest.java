import com.moodtracker.mood_tracker.model.Mood;
import com.moodtracker.mood_tracker.repository.MoodRepository;
import com.moodtracker.mood_tracker.service.MoodService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MoodServiceTest {

    @Mock
    private MoodRepository moodRepository;

    @InjectMocks
    private MoodService moodService;

    private Mood mood;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        mood = new Mood();
        mood.setId(1L);
        mood.setMood("Happy");
        mood.setNote("Nice day");
        mood.setDate(LocalDate.now());
    }

    @Test
    void testCreateMood() {
        Mood newMood = new Mood();
        newMood.setMood("Happy");

        Mood savedMood = new Mood();
        savedMood.setId(1L);
        savedMood.setMood("Happy");
        savedMood.setDate(LocalDate.now());

        when(moodRepository.save(any(Mood.class))).thenReturn(savedMood);

        Mood result = moodService.createMood(newMood);

        assertNotNull(result);
        assertEquals("Happy", result.getMood());
        assertEquals(1L, result.getId());
        verify(moodRepository).save(newMood);
    }

    @Test
    void testGetAllMoods() {
        List<Mood> mockList = List.of(mood);
        when(moodRepository.findAll()).thenReturn(mockList);

        List<Mood> result = moodService.getAllMoods();

        assertEquals(1, result.size());
        assertEquals("Happy", result.get(0).getMood());
        verify(moodRepository).findAll();
    }

    @Test
    void testGetMoodByIdFound() {
        when(moodRepository.findById(1L)).thenReturn(Optional.of(mood));

        Mood result = moodService.getMoodById(1L);

        assertNotNull(result);
        assertEquals("Happy", result.getMood());
        verify(moodRepository).findById(1L);
    }

    @Test
    void testGetMoodByIdNotFound() {
        when(moodRepository.findById(2L)).thenReturn(Optional.empty());

        Mood result = moodService.getMoodById(2L);

        assertNull(result);
        verify(moodRepository).findById(2L);
    }

    @Test
    void testDeleteMood() {
        Long id = 1L;

        moodService.deleteMood(id);

        verify(moodRepository).deleteById(id);
    }
}
