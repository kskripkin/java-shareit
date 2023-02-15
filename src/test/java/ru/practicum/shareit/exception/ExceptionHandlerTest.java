package ru.practicum.shareit.exception;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.practicum.shareit.exception.model.ConflictException;
import ru.practicum.shareit.exception.model.ErrorResponse;
import ru.practicum.shareit.exception.model.NotFoundException;
import ru.practicum.shareit.exception.model.ValidationException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ExceptionHandlerTest {

    @InjectMocks
    private ExceptionHandler exceptionHandler;

    private ErrorResponse errorResponse;

    @BeforeEach
    void setUp() {
        errorResponse = new ErrorResponse("FAIL");
    }

    @Test
    void handleValidationException() {
        assertEquals(exceptionHandler.handleValidationException(new ValidationException("FAIL")).getError(), errorResponse.getError());
    }

    @Test
    void handleUserNotFoundException() {
        assertEquals(exceptionHandler.handleUserNotFoundException(new NotFoundException("FAIL")).getError(), errorResponse.getError());
    }

    @Test
    void handleConflictException() {
        assertEquals(exceptionHandler.handleConflictException(new ConflictException("FAIL")).getError(), errorResponse.getError());
    }

    @Test
    void handleThrowable() {
        assertEquals(exceptionHandler.handleThrowable(new Throwable("FAIL")),
                ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Unexpected error: FAIL"));
    }
}