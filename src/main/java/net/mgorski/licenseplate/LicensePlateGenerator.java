package net.mgorski.licenseplate;

public class LicensePlateGenerator {
    private static final int NUMBERS_LENGTH = 10;
    private static final int PLATE_LENGTH = 6;
    private final BaseConverter baseConverter = new BaseConverter();

    public String generateLicensePlate(long input) {
        var howManyNumbers = PLATE_LENGTH;
        var encodedWithLetters = 0;
        var numbersLeft = input;
        while (howManyNumbers >= 0) {
            var letters = "";
            letters = generateLetters(encodedWithLetters);
            howManyNumbers = PLATE_LENGTH - letters.length();
            long numberOfPermutations = calculateMaxPermutations(howManyNumbers);
            if (numberOfPermutations > numbersLeft) {
                return fillWithZeroes(numbersLeft + letters);
            } else {
                if (numberOfPermutations == 0) {
                    letters = generateLetters(encodedWithLetters + numbersLeft);
                    return fillWithZeroes(letters);
                }
                encodedWithLetters += 1;
                numbersLeft -= numberOfPermutations;
            }
        }
        return "";
    }

    private String generateLetters(long toBeEncodedInLetters) {
        if (toBeEncodedInLetters > 0) {
            var letters = baseConverter.toAlphabetic(toBeEncodedInLetters - 1);
            if (letters.length() > PLATE_LENGTH) {
                throw new RuntimeException("Number too big to encode into license plate");
            }
            return letters;
        }
        return "";
    }


    private long calculateMaxPermutations(int length) {
        return length <= 0 ? 0 : (long) Math.pow(NUMBERS_LENGTH, length);
    }

    private String fillWithZeroes(String numbersAsString) {
        return String.format("%1$" + PLATE_LENGTH + "s", numbersAsString).replace(' ', '0');
    }
}