package selenium.page.object.hm4.configuration;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

@Sources({
    "classpath:/config.properties"
})
public interface StaticVeriables extends Config {

    @Key("user.login")
    String getLogin();

    @Key("user.pass")
    String getPass();

}
