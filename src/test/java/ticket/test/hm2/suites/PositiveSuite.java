package ticket.test.hm2.suites;

import org.junit.platform.suite.api.IncludeTags;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import ticket.test.hm2.TicketPositiveTest;

@Suite
//@IncludeTags({"PositiveTag"})
@SelectClasses({TicketPositiveTest.class})
public class PositiveSuite {
}
