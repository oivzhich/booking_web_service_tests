package config;

import org.aeonbits.owner.Config;

public interface PropertiesConfiguration extends Config {
    @DefaultValue("http://localhost:9090")
    String baseUrl();
}
