package cz.stambrecht.mealmanager.web.errors;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;


@ResponseStatus(HttpStatus.NOT_FOUND)
public final class ResourceNotFoundException extends RuntimeException{
	//empty
}
