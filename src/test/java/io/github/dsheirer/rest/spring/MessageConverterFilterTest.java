package io.github.dsheirer.rest.spring;

import org.jspecify.annotations.NonNull;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for MessageConverterFilter::postProcessAfterInitialization
 */
public class MessageConverterFilterTest {
    /** Verify that converters matching the exact class */
    @Test
    void removesMappingJackson2XmlConverters() {
        RequestMappingHandlerAdapter adapter = new RequestMappingHandlerAdapter();
        List<HttpMessageConverter<?>> converters = new ArrayList<>();
        converters.add(new MappingJackson2XmlHttpMessageConverter());
        converters.add(new MappingJackson2XmlHttpMessageConverterX());
        adapter.setMessageConverters(converters);

        MessageConverterFilter filter = new MessageConverterFilter();
        filter.postProcessAfterInitialization(adapter, "adapter");

        List<HttpMessageConverter<?>> result = adapter.getMessageConverters();
        assertEquals(1, result.size(), "Only the non-matching converter should remain");
        assertInstanceOf(MappingJackson2XmlHttpMessageConverterX.class, result.getFirst(), 
                "Remaining converter should be MappingJackson2XmlHttpMessageConverterX");
    }

    /** Verify that converters other than the exact match are left untouched */
    @Test
    void leavesNonMatchingConvertersUntouched() {
        RequestMappingHandlerAdapter adapter = new RequestMappingHandlerAdapter();
        List<HttpMessageConverter<?>> converters = new ArrayList<>();
        converters.add(new MappingJackson2XmlHttpMessageConverterX());
        adapter.setMessageConverters(converters);

        new MessageConverterFilter().postProcessAfterInitialization(adapter, "adapter");

        List<HttpMessageConverter<?>> result = adapter.getMessageConverters();
        assertEquals(1, result.size());
        assertInstanceOf(MappingJackson2XmlHttpMessageConverterX.class, result.getFirst());
    }

    // Minimal test implementations of HttpMessageConverter for non-matching class names
    
    /**
     * Dummy converter which should not be filtered.
     */
    static class MappingJackson2XmlHttpMessageConverterX implements HttpMessageConverter<Object> {
        @Override
        public boolean canRead(@NonNull Class<?> clazz, MediaType mediaType) {
            return false;
        }

        @Override
        public boolean canWrite(@NonNull Class<?> clazz, MediaType mediaType) {
            return false;
        }

        @Override
        public @NonNull List<MediaType> getSupportedMediaTypes() {
            return Collections.emptyList();
        }

        @Override
        public Object read(@NonNull Class<?> clazz, @NonNull HttpInputMessage inputMessage)
                throws UnsupportedOperationException {
            throw new UnsupportedOperationException();
        }

        @Override
        public void write(Object o, MediaType contentType, @NonNull HttpOutputMessage outputMessage)
                throws UnsupportedOperationException {
            throw new UnsupportedOperationException();
        }
    }
}
