package se.crisp.bowling;

class Parser {

    Frame parse(String pins) {
        return createFrames(pins, 0);
    }

    private Frame createFrames(String pins, int start) {
        if (pins.length() == 21 && start == 18) {
            return new LastFrame(pins.charAt(start),
                    pins.charAt(start + 1),
                    pins.charAt(start + 2));
        }
        if (start >= pins.length() - 1) {
            return null;
        }
        return new Frame(
                pins.charAt(start),
                pins.charAt(start + 1),
                createFrames(pins, start + 2));
    }
}
