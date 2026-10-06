import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RejectionFreeKineticMonteCarlo {
    private static final Random RANDOM = new Random();
    private static final int SITES = 64;

    public long execute(long initialState, int steps) {

        long state = initialState;

        double time = 0.0;

        for (int step = 0; step < steps; step++) {

            // Find every event that can actually happen
            List<EventRecord> events = getValidEvents(state);

            // No possible events -> simulation stops
            if (events.isEmpty()) {
                break;
            }

            // Calculate total transition rate
            double totalRate = 0.0;

            for (EventRecord event : events) {
                totalRate += event.rate();
            }

            // Choose an event proportional to its rate
            EventRecord event = chooseEvent(events, totalRate);

            // Advance simulation time
            double u = RANDOM.nextDouble();

            double dt = -Math.log(u) / totalRate;

            time += dt;

            // Apply selected event
            state = applyEvent(state, event);
        }

        return state;
    }

    private List<EventRecord> getValidEvents(long state) {
        final List<EventRecord> events = new ArrayList<>();

        for (int j = 0; j < SITES; j++) {

            if (Operations.get(state, j) == 0) {

                // Empty site -> addition is possible
                events.add(
                        new EventRecord(
                                Event.ADD,
                                j,
                                Operations.ADD_RATE
                        )
                );

            } else {

                // Occupied site -> termination is possible
                events.add(
                        new EventRecord(
                                Event.REMOVE,
                                j,
                                Operations.REMOVE_RATE
                        )
                );

                // Walker can move to j + 1
                if (j < 63 && Operations.get(state, j + 1) == 0) {

                    events.add(
                            new EventRecord(
                                    Event.MOVE_LEFT,
                                    j,
                                    Operations.MOVE_RATE
                            )
                    );
                }

                // Walker can move to j - 1
                if (j > 0 && Operations.get(state, j - 1) == 0) {

                    events.add(
                            new EventRecord(
                                    Event.MOVE_RIGHT,
                                    j,
                                    Operations.MOVE_RATE
                            )
                    );
                }
            }
        }

        return events;
    }

    private EventRecord chooseEvent(
            List<EventRecord> events,
            double totalRate) {

        double r = RANDOM.nextDouble() * totalRate;

        double accumulated = 0.0;

        for (EventRecord event : events) {
            accumulated += event.rate();

            if (r < accumulated) {
                return event;
            }
        }

        return events.getLast();
    }

    private long applyEvent(long state, EventRecord event) {
        return switch (event.type) {
            case ADD, REMOVE ->
                    Operations.flip(state, event.position()).newState();

            case MOVE_LEFT ->
                    Operations.movl(state, event.position()).newState();

            case MOVE_RIGHT ->
                    Operations.movr(state, event.position()).newState();
        };
    }

    record EventRecord(Event type, int position, double rate) {}
}
