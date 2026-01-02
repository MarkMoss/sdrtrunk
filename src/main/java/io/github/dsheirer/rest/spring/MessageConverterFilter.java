package io.github.dsheirer.rest.spring;

import org.jspecify.annotations.NonNull;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import java.util.List;

/**
 * Excludes the Jackson XML HttpMessageConverter so that the REST API cannot return XML responses.
 * <p>
 * SDRTrunk uses Jackson elsewhere for XML (de)serialization, but the REST API only supports
 * JSON for simplicity.
 */
@Component
public class MessageConverterFilter implements BeanPostProcessor {
    @Override
    public Object postProcessAfterInitialization(@NonNull Object bean, @NonNull String beanName) {
        if (bean instanceof RequestMappingHandlerAdapter adapter) {
            List<HttpMessageConverter<?>> converters = adapter.getMessageConverters();
            converters.removeIf(c -> c instanceof MappingJackson2XmlHttpMessageConverter);
        }
        return bean;
    }
}