package ticket.test.hm2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;
import ticket.test.hm2.annotation.NegativeTag;
import ticket.test.hm2.annotation.PositiveTag;

public class TicketTest extends BaseTicketTest {


    //POSITIVE

    @ParameterizedTest
    @MethodSource("ticket.test.hm2.TicketTestDataProvider#dataProviderTrue")
    @PositiveTag
     void isItHappyTrue(String input) {
        var expected = new String("true");
        var actual = ticket.sumNumb(input);

        Assertions.assertEquals(expected, actual);
    }

    @ParameterizedTest
    @MethodSource("ticket.test.hm2.TicketTestDataProvider#dataProviderFalse")
    @PositiveTag
    void isItHappyFalse(String input) {
        var expected = new String("false");
        var actual = ticket.sumNumb(input);

        Assertions.assertEquals(expected, actual);
    }


    //NEGATIVE

    @ParameterizedTest
    @NullSource
    @NegativeTag
    void isItHappyExc1(String input) {

        Assertions.assertThrows(NullPointerException.class,
            () -> ticket.sumNumb(input));
    }

    @ParameterizedTest
    @MethodSource("ticket.test.hm2.TicketTestDataProvider#dataProviderLessSix")
    @NegativeTag
    void isItHappyLessSix(String input) {
        var actual = ticket.sumNumb(input);

        Assertions.assertEquals(expected, actual);
    }

    @ParameterizedTest
    @MethodSource("ticket.test.hm2.TicketTestDataProvider#dataProviderMoreSix")
    @NegativeTag
    void isItHappyMoreSix(String input) {
        var actual = ticket.sumNumb(input);

        Assertions.assertEquals(expected, actual);
    }

    @ParameterizedTest
    @MethodSource("ticket.test.hm2.TicketTestDataProvider#dataProviderLetter")
    @NegativeTag
    void isItHappyLetterInput(String input) {
        var actual = ticket.sumNumb(input);

        Assertions.assertEquals(expected, actual);
    }

}
