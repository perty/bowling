package se.crisp.bowling;

import java.util.Optional;

class Frame {

    static final char STRIKE = 'X';
    static final char SPARE = '/';

    private Frame next;
    char first;
    char second;

    Frame(char first, char second, Frame next) {
        this.first = first;
        this.second = second;
        this.next = next;
    }

    public int value() {
        if (isSpare()) {
            return nextBall().map(value -> 10 + value).orElse(0);
        }
        if (isStrike()) {
            return nextTwoBalls().map(value -> 10 + value).orElse(0);
        }
        return sumBoth();
    }

    private Optional<Integer> nextBall() {
        if (next == null) {
            return Optional.empty();
        }
        if (next.isStrike()) {
            return Optional.of(10);
        }
        return Optional.of(parse(next.first));
    }

    private Optional<Integer> nextTwoBalls() {
        if (next == null) {
            return Optional.empty();
        }
        if (next.isStrike()) {
            if (next.next == null) {
                return Optional.empty();
            }
            if (next.next.isStrike()) {
                return Optional.of(10 + 10);
            }
            return Optional.of(10 + parse(next.next.first));
        }
        if (next.isSpare()) {
            return Optional.of(10);
        }
        return Optional.of(next.value());
    }


    public int score() {
        if (next != null) {
            return value() + next.score();
        }
        return value();
    }

    int sumBoth() {
        return parse(first) + parse(second);
    }

    private boolean isSpare() {
        return second == SPARE;
    }

    private boolean isStrike() {
        return first == STRIKE;
    }

    int parse(int i) {
        char c = (char) i;
        switch (c) {
            case '-':
                return 0;
            case '1':
            case '2':
            case '3':
            case '4':
            case '5':
            case '6':
            case '7':
            case '8':
            case '9':
                return c - '0';
            case 'X':
                return 10;
        }
        return 0;
    }
}