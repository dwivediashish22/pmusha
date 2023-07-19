package org.nic.pmusha.handler;


import org.nic.pmusha.utility.ReturnResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * GlobleExceptionHandler
 *
 * @author Dev Raj
 * @version 1.0
 */
@ControllerAdvice
public class GlobleExceptionHandler extends ResponseEntityExceptionHandler {
    /**
     * LOGGER
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobleExceptionHandler.class);

    /**
     * handleEntityException
     *
     * @param ex      EntityException
     * @param request WebRequest
     * @return ResponseEntity<ReturnResponse>
     */
    @ExceptionHandler(BadRequestException.class)
    public final ResponseEntity<ReturnResponse> handleEntityException(BadRequestException ex, WebRequest request) {
        LOGGER.error("Bad Entity exception : " + ex);
        ReturnResponse exceptionResponse = new ReturnResponse(HttpStatus.UNPROCESSABLE_ENTITY.value(), ex.getMessage());
        LOGGER.error("Bad Entity exception : " + exceptionResponse);
        return new ResponseEntity<>(exceptionResponse, HttpStatus.UNPROCESSABLE_ENTITY);
    }


}
