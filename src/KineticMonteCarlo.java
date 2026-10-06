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