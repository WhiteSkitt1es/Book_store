package com.example.bookstore.config;

import com.example.bookstore.database.entity.Product;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@Configuration
public class MethodDataRestConfig implements RepositoryRestConfigurer {

    @Value("${client.host}")
    private String clientUrl;

    @Override
    public void configureRepositoryRestConfiguration(
            RepositoryRestConfiguration config,
            CorsRegistry cors) {

        HttpMethod[] unsupportedActions = {
                // HttpMethod.POST,
                // HttpMethod.DELETE,
                // HttpMethod.PUT
        };

        config.exposeIdsFor(Product.class);

        disableHttpMethods(Product.class, config, unsupportedActions);

        cors.addMapping(config.getBasePath() + "/**")
                .allowedOrigins(clientUrl);
    }

    private void disableHttpMethods(
            Class<?> self,
            RepositoryRestConfiguration config,
            HttpMethod[] unsupportedActions) {
        config.getExposureConfiguration()
                .forDomainType(self)
                .withItemExposure(
                        (metadata, httpMethods) -> httpMethods.disable(unsupportedActions))
                .withCollectionExposure(
                        (metadata, httpMethods) -> httpMethods.disable(unsupportedActions));
    }
}
