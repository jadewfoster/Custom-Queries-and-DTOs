package com.qa.demo.persistence.domain;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(code = HttpStatus.CONFLICT,reason = "Person Already Exists with this ID")
public class PersonAlreadyExistsException extends Exception {

}