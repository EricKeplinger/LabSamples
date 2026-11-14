
import java.time.*;

/**
 * Represents a laboratory sample collected from a patient.
 * <p>
 * Each sample has a unique identifier, a specific sample type,
 * and a maximum allowed age that determines when the sample expires.
 * The time of collection is automatically recorded when the object is created.
 * </p>
 */
public class LabSample {

    /**
     * Unique identifier of the lab sample.
     */
    private final String id;

    /**
     * Type of the sample (e.g., blood, urine, PCR swab).
     */
    private final String type;

    /**
     * Maximum stability duration of the sample before it expires.
     */
    private final Duration maxStability;

    /**
     * Timestamp when the sample was collected.
     */
    private final LocalDateTime collectedAt;

    /**
     * Creates a new {@code LabSample}.
     * <p>
     * The collection time is automatically set to the current system time.
     * </p>
     *
     * @param id     the unique identifier of the sample
     * @param type   the type of the sample (e.g., blood, urine, PCR swab)
     * @param maxStability the maximum stability duration of the sample before it expires
     */
    public LabSample(String id, String type, Duration maxStability) {
        this.id = id;
        this.type = type;
        this.maxStability = maxStability;
        this.collectedAt = LocalDateTime.now();
    }

    /**
     * Returns the maximum stability duration of this sample.
     *
     * @return the maximum stability duration
     */
    public Duration getMaxStability() {
        return maxStability;
    }

    /**
     * Returns the date and time when this sample was collected.
     *
     * @return the collection timestamp
     */
    public LocalDateTime getCollectedAt() {
        return collectedAt;
    }
}