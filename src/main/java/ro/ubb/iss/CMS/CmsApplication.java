package ro.ubb.iss.CMS;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ro.ubb.iss.CMS.Services.FileService;

import javax.annotation.Resource;
import org.springframework.web.client.RestTemplate;
import ro.ubb.iss.CMS.dto.MyTicketDto;

@SpringBootApplication
public class CmsApplication {

  public static void main(String[] args) {
    SpringApplication.run(CmsApplication.class, args);
  }

  @Bean
  public WebMvcConfigurer corsConfigurer() {
    return new WebMvcConfigurer() {
      @Override
      public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:4200")
                .allowedMethods("GET", "PUT", "POST", "DELETE")
                .allowedHeaders("*");
      }
    };
  }
}
