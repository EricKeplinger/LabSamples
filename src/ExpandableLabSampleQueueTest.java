import org.junit.jupiter.api.*;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;


public class ExpandableLabSampleQueueTest {
    private ExpandableLabSampleQueue queue;
    private LabSample sample1, sample2, sample3;

    @BeforeEach
    void setUp() {
        // Initialization before each test
        queue = new ExpandableLabSampleQueue(2); // Queue with a start size of 2
        sample1 = new LabSample("S1", "blood", Duration.ofHours(2));
        sample2 = new LabSample("S2", "urine", Duration.ofHours(2));
        sample3 = new LabSample("S3", "PCR", Duration.ofHours(2));
    }

    @Test
    void testPut() {
        // Test adding a sample
        assertTrue(queue.put(sample1), "Sample should be added.");
        assertTrue(queue.put(sample2), "Sample should be added.");
        assertTrue(queue.put(sample3), "Sample should be added and the queue size should be double it´s previous length now.");
    }

    @Test
    void testGet() {
        // adding the samples
        queue.put(sample1);
        queue.put(sample2);
        queue.put(sample3);

        // Test removing a sample
        assertEquals(sample1, queue.get(), "The sample removed should be the first sample.");
        assertEquals(sample2, queue.get(), "The sample removed should be the second sample.");
        assertEquals(sample3, queue.get(), "The sample removed should be the third sample.");
    }

    @Test
    void testQueueEmpty() {
        // Test removing from an empty queue
        assertNull(queue.get(), "null should be returned when the queue is empty.");
    }

    @Test
    void testQueueWrapAround() {
        // Test the circular behavior of the queue
        queue.put(sample1);
        queue.put(sample2);
        queue.get(); // Remove the first sample
        queue.put(sample3); // Add another sample

        assertEquals(sample2, queue.get(), "The sample removed should be the second sample.");
        assertEquals(sample3, queue.get(), "The sample removed should be the third sample.");
    }

    @Test
    void testResizeArray() {
        // Test the resize amount of the resizearray method
        queue.put(sample1);
        queue.put(sample2);

        int length = queue.data.length;

        queue.put(sample3);
        assertEquals(queue.data.length, length * 2);
    }

}
