package com.qa.demo.persistence.domain;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code= HttpStatus.NOT_FOUND,reason="Person doesn't exist with this id")
public class PersonNotFoundException extends Exception {

}