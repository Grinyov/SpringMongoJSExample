package rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletResponse;
/**
 * Created by Grinyov Vitaliy on 04.09.15.
 */
public class AbstractRestHandler {

    protected Logger log = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(Exception.class)
    public @ResponseBody RestResponse handleUncaughtException(Exception ex, WebRequest request, HttpServletResponse response) {
        log.info("Converting Uncaught exception to RestResponse : " + ex.getMessage());

        response.setHeader("Content-Type", "application/json");
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        return new RestResponse("Error occurred", -1);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public @ResponseBody RestResponse handleIllegalArgumentException(IllegalArgumentException ex, WebRequest request, HttpServletResponse response) {
        log.info("Converting IllegalArgumentException to RestResponse : " + ex.getMessage());

        response.setHeader("Content-Type", "application/json");
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        return new RestResponse("Error occurred", -1);
    }
}
