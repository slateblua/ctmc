import model.FlipResult;
import model.MoveResult;

public class KineticMonteCarlo {
    // Ideally should be in a configuration file
    public static final float ADD_RATE = 0.1f;
    public static final float REMOVE_RATE = 0.4f;
    public static final float MOVE_RATE = 0.3f;

    public static void main(String[] args) {

    }

    int get(long s, int j) {
        // Placeholder
        return -1;
    }

    FlipResult flip(long s, int j) {
        // Placeholder
        return new FlipResult(
                false,
                0
        );
    }

    MoveResult movr(long s, int j) {
        // Placeholder
        return new MoveResult(
                false,
                0
        );
    }

    MoveResult movl(long s, int j) {
        // Placeholder
        return new MoveResult(
                false,
                0
        );
    }
}