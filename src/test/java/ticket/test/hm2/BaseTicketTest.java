package ticket.test.hm2;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class BaseTicketTest {

    Ticket ticket;
    String expected;

    @BeforeEach
    void setUp() {
        //System.out.println(this.getClass().getName()+"before each");
        ticket = new TicketImpl();
        expected = new String("Номер билета должен состоять из 6 цифр");
    }

    @AfterEach
    void tearDown() {
        ticket = null;
    }


}
