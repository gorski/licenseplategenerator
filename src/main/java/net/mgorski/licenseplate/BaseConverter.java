package net.mgorski.licenseplate;

public class BaseConverter {

    public String toAlphabetic(long i) {
        if (i < 0) {
            return toAlphabetic(-i - 1);
        }

        long quot = i / 26;
        long rem = i % 26;
        char letter = (char) ((int) 'A' + rem);
        if (quot == 0) {
            return "" + letter;
        } else {
            return toAlphabetic(quot - 1) + letter;
        }
    }
}

