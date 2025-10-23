package top.ksmdev.exam2025.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.micrometer.tracing.Tracer;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import top.ksmdev.exam2025.Application;
import top.ksmdev.exam2025.common.controller.ResultData;

import java.lang.reflect.Method;
import java.util.Objects;
import java.util.Optional;

@RestControllerAdvice
public class RestResponseBodyAdvice implements ResponseBodyAdvice<Object> {
    private static final Logger log = LoggerFactory.getLogger(RestResponseBodyAdvice.class);
    @Autowired
    private Tracer tracer;
    @Autowired
    private ObjectMapper objectMapper;

    @SneakyThrows
    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        if (Optional.ofNullable(methodParameter.getMethod()).isPresent()) {
            Method method = methodParameter.getMethod();
            Class<?> declaringClass = method.getDeclaringClass();
            String classPackageName = declaringClass.getPackage().getName();
            String searchPackageName = Application.class.getPackage().getName();
            return classPackageName.startsWith(searchPackageName);
        } else {
            return false;
        }
    }

    @SneakyThrows
    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType,
                                  Class<? extends HttpMessageConverter<?>> aClass,
                                  ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        String traceId = Objects.requireNonNull(tracer.currentSpan()).context().traceId();

        ResultData<?> resultData;
        if (o == null) {
            return null;
        } else if (o instanceof String) {
            resultData = ResultData.success(o);
            resultData.setTraceId(traceId);
            return objectMapper.writeValueAsString(resultData);
        } else if (o instanceof ResultData<?> rd) {
            rd.setTraceId(traceId);
            return rd;
        } else if (o.getClass().isArray()) {
            return o;
        } else {
            resultData = ResultData.success(o);
            resultData.setTraceId(traceId);
        }
        return resultData;
    }
}
