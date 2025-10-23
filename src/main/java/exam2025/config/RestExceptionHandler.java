package exam2025.config;


import io.sentry.Sentry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import exam2025.common.controller.ResultData;
import exam2025.common.controller.ReturnCode;

import java.io.PrintWriter;
import java.io.StringWriter;

@Slf4j
@RestControllerAdvice
public class RestExceptionHandler {

    @Autowired
    Environment env;

    /**
     * 默认全局系统异常处理。
     *
     * @param ex the e
     * @return ResultData
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResultData<String> exception(Exception ex) {
        switch (ex.getClass().getSimpleName()) {
            case "NoResourceFoundException": //过滤非关键异常
                break;
            default:
                log.error(ex.getMessage(), ex);
                Sentry.captureException(ex);
                break;
        }
        String stackTrack = env.acceptsProfiles(Profiles.of("prod")) ? null : getStackTraceAsString(ex);
        return ResultData.fail(ReturnCode.RC500.getCode(), ReturnCode.RC500.getMessage(), stackTrack);

    }


    private String getStackTraceAsString(Exception ex) {
        StringWriter sw = new StringWriter();
        ex.printStackTrace(new PrintWriter(sw));
        return sw.toString();
    }

}

