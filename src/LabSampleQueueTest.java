
import org.junit.jupiter.api.*;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

class LabSampleQueueTest {
    private LabSampleQueue queue;
    private LabSample sample1, sample2, sample3;

    @BeforeEach
    void setUp() {
        // Initialization before each test
        queue = new LabSampleQueue(2); // Queue with a maximum size of 2
        sample1 = new LabSample("S1", "blood", Duration.ofHours(2));
        sample2 = new LabSample("S2", "urine", Duration.ofHours(2));
        sample3 = new LabSample("S3", "PCR", Duration.ofHours(2));
    }

    @Test
    void testPut() {
        // Test adding a sample
        assertTrue(queue.put(sample1), "Sample should be added.");
        assertTrue(queue.put(sample2), "Sample should be added.");

        // Queue is full, no more samples should be added
        assertFalse(queue.put(sample3), "Sample should not be added because the queue is full.");
    }

    @Test
    void testGet() {
        // Test removing a sample
        queue.put(sample1);
        queue.put(sample2);

        LabSample removedSample = queue.get();
        assertEquals(sample1, removedSample, "The sample removed should be the first sample.");

        removedSample = queue.get();
        assertEquals(sample2, removedSample, "The sample removed should be the second sample.");
    }

    @Test
    void testQueueEmpty() {
        // Test removing from an empty queue
        assertNull(queue.get(), "null should be returned when the queue is empty.");
    }

    @Test
    void testQueueFull() {
        // Test the queue being full
        queue.put(sample1);
        queue.put(sample2);

        // Queue is full, no more samples can be added
        assertFalse(queue.put(sample3), "The queue should be full and not accept additional samples.");
    }

    @Test
    void testQueueWrapAround() {
        // Test the circular behavior of the queue
        queue.put(sample1);
        queue.put(sample2);
        queue.get(); // Remove the first sample
        queue.put(sample3); // Add another sample

        LabSample removedSample = queue.get();
        assertEquals(sample2, removedSample, "The sample removed should be the second sample.");

        removedSample = queue.get();
        assertEquals(sample3, removedSample, "The sample removed should be the third sample.");
    }
}