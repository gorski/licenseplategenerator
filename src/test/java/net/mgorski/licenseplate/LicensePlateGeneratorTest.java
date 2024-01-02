package net.mgorski.licenseplate;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class LicensePlateGeneratorTest {

    private final LicensePlateGenerator generator = new LicensePlateGenerator();

    @ParameterizedTest
    @MethodSource("expectedPlateNumbers")
    public void licensePlateIsCorrectlyGenerated(String expected, long value) {
        Assertions.assertEquals(expected, generator.generateLicensePlate(value));
    }

    @Test(expected = RuntimeException.class)
    public void licensePlateTooLong() {
        generator.generateLicensePlate(501363136);
    }

    private static Stream<Arguments> expectedPlateNumbers() {
        return Stream.of(
                Arguments.of("000000", 0),
                Arguments.of("000001", 1),
                Arguments.of("999999", 999999),

                Arguments.of("00000A", 1000000),
                Arguments.of("00001A", 1000001),
                Arguments.of("00002A", 1000002),
                Arguments.of("99999A", 1099999),

                Arguments.of("99999Z", 3599999),
                Arguments.of("0000AA", 3600000),
                Arguments.of("0001AA", 3600001),
                Arguments.of("0002AA", 3600002),

                Arguments.of("9999AA", 3609999),
                Arguments.of("0000AB", 3610000),
                Arguments.of("0001AB", 3610001),

                Arguments.of("999ZZZ", 27935999),
                Arguments.of("00AAAA", 27936000),

                Arguments.of("9ZZZZZ", 192447359),
                Arguments.of("AAAAAA", 192447360),
                Arguments.of("AAAAAB", 192447361),
                Arguments.of("ZZZZZY", 501363134),
                Arguments.of("ZZZZZZ", 501363135)

        );
    }

//    @Test
//    public void runMe() {
//        long input = 501363134;
//        for (long i = input; i <= (input + 100); i++) {
//            System.out.println(i);
//            String s = generator.generateLicensePlate(i);
//            System.out.println(s);
//        }
//    }
}
