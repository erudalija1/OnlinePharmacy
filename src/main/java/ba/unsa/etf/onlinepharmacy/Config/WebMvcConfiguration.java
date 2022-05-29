package ba.unsa.etf.onlinepharmacy.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

    private final long MAX_AGE_SECS = 3600;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("HEAD", "OPTIONS", "GET", "POST", "PUT", "PATCH", "DELETE")
                .maxAge(MAX_AGE_SECS);
    }

        @Override
        public void addResourceHandlers(ResourceHandlerRegistry registry){
        Path medicamentUploadDir= Paths.get("./medicament-photos");
        String medicamentUploadPath=medicamentUploadDir.toFile().getAbsolutePath();
        registry.addResourceHandler("/medicament-photos/**").addResourceLocations("file:/"+medicamentUploadPath+"/");
    }


}
