package model;

public record FlipResult(
        // let's agree that termination is denoted by 'false', and the addition is denoted by 'true'
        boolean eventType,
        // Java doesn't have i64, so we use long to represent 64 bits
        long newState
) {
}
