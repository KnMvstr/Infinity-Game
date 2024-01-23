package HB_CAPE_MAK.hb_cape_makindu.advisor;


import HB_CAPE_MAK.hb_cape_makindu.custom_response.ResponseException;
import HB_CAPE_MAK.hb_cape_makindu.exception.NotFoundCapEntException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class NotFoundResponse {

    @ResponseBody
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY) // Modifie le code HTTP de la réponse
    @ExceptionHandler(NotFoundCapEntException.class) // L'exception qui doit être "catch"
    ResponseException notFoundResponseHandler(NotFoundCapEntException e) {
        return new ResponseException(
            HttpStatus.UNPROCESSABLE_ENTITY.value(),
            e.getType(),
            e.getField(),
            e.getValue(),
            e.getMessage()
        );
    }

}
