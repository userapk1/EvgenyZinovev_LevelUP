package api.hm6.configuration;

import io.restassured.http.Header;
import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

@Sources({
    "classpath:configForHm6.properties"
})
public interface StaticVeriables extends Config {

    @Key("my.token")
    String getMyToken();

    /*@Key("user.id")
    String getUserId();*/


}
