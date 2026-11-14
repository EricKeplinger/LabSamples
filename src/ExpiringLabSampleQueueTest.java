import org.junit.jupiter.api.*;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;


public class ExpiringLabSampleQueueTest {
    private ExpiringLabSampleQueue queue;
    private LabSample sample1, sample2, sample3, sample4;

    @BeforeEach
    void setUp() {
        // Initialization before each test
        queue = new ExpiringLabSampleQueue(2); // Queue with a start size of 2
        sample1 = new LabSample("S1", "blood", Duration.ZERO);
        sample2 = new LabSample("S2", "urine", Duration.ofHours(2));
        sample3 = new LabSample("S3", "urine", Duration.ZERO);
        sample4 = new LabSample("S4", "urine", Duration.ZERO);

    }

    @Test
    void testGet() {
        // Test adding a sample
        assertTrue(queue.put(sample1), "Sample should be added.");
        assertTrue(queue.put(sample2), "Sample should be added.");
        assertEquals(sample2, queue.get(), "Only the second sample ain´t expired");
    }

    @Test
    void testOnlyExpired(){
        assertTrue(queue.put(sample1), "Sample should be added.");
        assertTrue(queue.put(sample3), "Sample should be added.");
        assertTrue(queue.put(sample4), "Sample should be added.");

        assertNull(queue.get(), "All samples are expired so queue.get() should return Null");
    }

    @Test
    void testEmptyQueue(){
        assertNull(queue.get(), "Queue is empty so queue.get() should return null");
    }


}
