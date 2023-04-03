package selenium.page.object.hm4.configuration;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

@Sources({
    "classpath:config.properties"
})
public interface StaticVeriables extends Config {

    @Key("user.login")
    String getLogin();

    @Key("user.pass")
    String getPass();

    @Key("URL")
    String getURL();

    @Key("destination")
    String getDestination();

    @Key("letterForMe")
    String getLetterForMe();

    @Key("subject")
    String getSubject();

    @Key("subjectForMe")
    String getSubjectForMe();

    @Key("bodyLetter")
    String getBodyLetter();
}
