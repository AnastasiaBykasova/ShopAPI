//package com.example.ShopAPI.configs;
//
//import org.springframework.http.HttpStatus;
//
//
//import java.util.HashMap;
//import java.util.Map;
//
//@RestControllerAdvice
//public class GlobalExceptionHandler {
//    @ExceptionHandler(ChangeSetPersister.NotFoundException.class)
//    public ResponseEntity<String> handleNotFoundException(ChangeSetPersister.NotFoundException ex) {
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
//    }
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
//        Map<String, String> errors = new HashMap<>();
//        ex.getBindingResult().getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
//    }
//    @ExceptionHandler(ResponseStatusException.class)
//    public ResponseEntity<String> handleResponseStatusException(ResponseStatusException ex) {
//        return ResponseEntity.status(ex.getStatusCode()).body(ex.getReason());
//    }
//}