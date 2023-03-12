package ticket.test.hm2;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TicketNegativeTest extends BaseTicketTest {

    //NEGATIVE

    @Test
    void isItHappyExcNull() {

        Assertions.assertThrows(NullPointerException.class,
            () -> ticket.sumNumb(null));
    }


    @Test
    void isItHappyLessSix() {
        var input = new String("121");
        var actual = ticket.sumNumb(input);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void isItHappyMoreSix() {
        var input = new String("12222133");
        var actual = ticket.sumNumb(input);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void isItHappyLetterInput() {
        var input = new String("r");
        var actual = ticket.sumNumb(input);

        Assertions.assertEquals(expected, actual);
    }

    @AfterEach
    void tearDown() {
        ticket = null;
    }
}
