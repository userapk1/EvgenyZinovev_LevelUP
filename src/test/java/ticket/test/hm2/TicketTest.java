package ticket.test.hm2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;
import ticket.test.hm2.annotation.NegativeTag;
import ticket.test.hm2.annotation.PositiveTag;
import ticket.test.hm2.annotation.SuiteAllTag;

public class TicketTest extends BaseTicketTest {


    //POSITIVE
    @ParameterizedTest
    @MethodSource("ticket.test.hm2.TicketTestDataProvider#dataProviderTrue")
    @PositiveTag
    @SuiteAllTag
     void isItHappyTrue(Integer input) {
        Assertions.assertTrue(ticket.sumNumb(input));
    }

    @ParameterizedTest
    @MethodSource("ticket.test.hm2.TicketTestDataProvider#dataProviderFalse")
    @PositiveTag
    @SuiteAllTag
    void isItHappyFalse(Integer input) {
        Assertions.assertFalse(ticket.sumNumb(input));
    }

    //NEGATIVE
    @ParameterizedTest
    @NullSource
    @NegativeTag
    @SuiteAllTag
    void isItHappyExc1(Integer input) {
        Assertions.assertThrows(NullPointerException.class, () -> ticket.sumNumb(input));
    }

    @ParameterizedTest
    @MethodSource("ticket.test.hm2.TicketTestDataProvider#dataProviderLessSix")
    @NegativeTag
    @SuiteAllTag
    void isItHappyLessSix(Integer input) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> ticket.sumNumb(input));
    }

    @ParameterizedTest
    @MethodSource("ticket.test.hm2.TicketTestDataProvider#dataProviderMoreSix")
    @NegativeTag
    @SuiteAllTag
    void isItHappyMoreSix(Integer input) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> ticket.sumNumb(input));
    }
}
