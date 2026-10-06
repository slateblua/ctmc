import model.MoveResult;

import java.util.Random;

public class KineticMonteCarlo {
    private static final Random RANDOM = new Random();
    private static final int SITES = 64;

    public long execute(long initialState, int steps) {
        long state = initialState;

        for (int step = 0; step < steps; step++) {

            // Choose a random site
            int j = RANDOM.nextInt(SITES);

            // Choose one of the four event types
            Event type = randomEventType();

            switch (type) {

                case ADD -> {
                    // Addition is valid only if site is empty
                    if (Operations.get(state, j) == 0) {
                        state = Operations.flip(state, j).newState();
                    }
                }

                case REMOVE -> {
                    // Termination is valid only if site is occupied
                    if (Operations.get(state, j) == 1) {
                        state = Operations.flip(state, j).newState();
                    }
                }

                case MOVE_LEFT -> {
                    MoveResult result = Operations.movl(state, j);

                    if (result.wasMoved()) {
                        state = result.newState();
                    }
                }

                case MOVE_RIGHT -> {
                    MoveResult result = Operations.movr(state, j);

                    if (result.wasMoved()) {
                        state = result.newState();
                    }
                }
            }
        }

        return state;
    }

    private Event randomEventType() {
        int value = RANDOM.nextInt(4);

        return switch (value) {
            case 0 -> Event.ADD;
            case 1 -> Event.REMOVE;
            case 2 -> Event.MOVE_LEFT;
            case 3 -> Event.MOVE_RIGHT;
            default -> throw new IllegalStateException();
        };
    }
}
