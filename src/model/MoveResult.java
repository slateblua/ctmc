package model;

public record MoveResult(
        boolean wasMoved,
        // Java doesn't have i64, so we use long to represent 64 bits
        // New state is exactly the same, if there was no walker
        long newState
) {

}
