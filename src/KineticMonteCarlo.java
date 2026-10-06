import model.FlipResult;
import model.MoveResult;

public class KineticMonteCarlo {
    // Ideally should be in a configuration file
    public static final float ADD_RATE = 0.1f;
    public static final float REMOVE_RATE = 0.4f;
    public static final float MOVE_RATE = 0.3f;

    public static void main(String[] args) {

    }

    // If we have 8 bits, 01100001
    // To know whether the 5-th bit is "occupied"
    // We bring it forward, and then & with 1
    int get(long s, int j) {
        return (int) (s >> j) & 1;
    }

    FlipResult flip(long s, int j) {
        long mask = 1L << j;

        boolean wasAdded = ((s >>> j) & 1L) == 0;

        long newState = s ^ mask;

        return new FlipResult(
                wasAdded,
                newState
        );
    }

    MoveResult movr(long s, int j) {
        // Is there anything to move?
        boolean hasWalker = get(s, j) == 1;

        // Figure out if j - 1 is available
        boolean hasSpace = get(s, j - 1) == 0;

        if (hasSpace && hasWalker) {
            long newState = s;

            // Remove walker from j
            newState &= ~(1L << j);

            // Add walker to j - 1
            newState |= (1L << (j - 1));

            return new MoveResult(
                    true,
                    newState
            );
        }

        return new MoveResult(
                false,
                s
        );
    }

    MoveResult movl(long s, int j) {
        // Is there anything to move?
        boolean hasWalker = get(s, j) == 1;

        // Figure out if j + 1 is available
        boolean hasSpace = get(s, j + 1) == 0;

        if (hasSpace && hasWalker) {
            long newState = s;

            // Remove walker from j
            newState &= ~(1L << j);

            // Add walker to j + 1
            newState |= (1L << (j + 1));

            return new MoveResult(
                    true,
                    newState
            );
        }

        return new MoveResult(
                false,
                s
        );
    }
}