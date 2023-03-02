package ticket.test.hm2.suites;

import org.junit.platform.suite.api.IncludeClassNamePatterns;
import org.junit.platform.suite.api.IncludeTags;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

@Suite
@IncludeTags({"SuiteAll_Tag"})
@SelectPackages({"ticket.test.hm2"})
@IncludeClassNamePatterns(".*Test")
public class SuiteAll {
}
