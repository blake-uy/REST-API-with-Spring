package poi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import poi.core.PoiManager;

import java.io.IOException;

@Configuration
public class BeanConfig {

    @Bean
    public PoiManager getPoiManager() {
        try {
            return new PoiManager("places.txt");
        } catch(IOException e) {
            return null;
        }
    }

}
