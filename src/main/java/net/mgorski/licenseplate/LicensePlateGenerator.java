package net.mgorski.licenseplate;

public class LicensePlateGenerator {
    private static final int NUMBERS_LENGTH = 10;
    private static final int PLATE_LENGTH = 6;
    private static final int LETTERS_ONLY_PERMUTATIONS = 308915776; //26^6
    private final BaseConverter baseConverter = new BaseConverter();

    public String generateLicensePlate(long input) {
        var numberCount = PLATE_LENGTH;
        var encodedWithLetters = 0;
        var numbersLeft = input;
        var letters = "";
        while (numberCount >= 0) {
            letters = generateLetters(encodedWithLetters);
            numberCount = PLATE_LENGTH - letters.length();
            long numberOfPermutations = calculateMaxPermutations(numberCount);
            if (numberOfPermutations > numbersLeft) {
                return fillWithZeroes(numbersLeft + letters);
            } else if (numberOfPermutations == 0) {
                if (numbersLeft >= LETTERS_ONLY_PERMUTATIONS) {
                    break;
                }
                letters = generateLetters(encodedWithLetters + numbersLeft);
                return fillWithZeroes(letters);
            }
            encodedWithLetters += 1;
            numbersLeft -= numberOfPermutations;
        }
        throw new RuntimeException("Number " + input + "too big to encode into license plate");
    }

    private String generateLetters(long toBeEncodedInLetters) {
        if (toBeEncodedInLetters > 0) {
            return baseConverter.toAlphabetic(toBeEncodedInLetters - 1);
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