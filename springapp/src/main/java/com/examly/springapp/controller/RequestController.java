package com.examly.springapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.examly.springapp.model.Request;
import com.examly.springapp.model.User;
import com.examly.springapp.service.RequestService;

@RestController
public class RequestController {
    @Autowired
    private RequestService requestService;

    @PostMapping("/api/request")
    public ResponseEntity<Request> createRequest(@RequestBody Request request) {
        Request createdRequest = requestService.createRequest(request);
        return new ResponseEntity<>(createdRequest, HttpStatus.CREATED);
    }

    // Get a request by ID
    @GetMapping("/api/request/{id}")
    public ResponseEntity<Request> getRequestById(@PathVariable("id") int id) {
        Request request = requestService.getRequestById(id);
        if (request != null) {
            return new ResponseEntity<>(request, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/api/request")
    public ResponseEntity<List<Request>> getAllRequests() {
        List<Request> requests = requestService.getAllRequests();
        return new ResponseEntity<>(requests, HttpStatus.OK);
    }

    // Update a request
    @PutMapping("/api/request/{id}")
    public ResponseEntity<String> updateRequest(@PathVariable int id) {
        String updated = requestService.updateRequest(id);
        if (updated!=null) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/api/requests/user/{userId}")
    public ResponseEntity<List<Request>> getRequestsByUser(@PathVariable int userId) {
        List<Request> requests = requestService.getRequestsByUserId(userId);
        return ResponseEntity.ok(requests);
    }

    @DeleteMapping("/request/{id}")
    public ResponseEntity<Void> deleteRequest(@PathVariable int id) {
        requestService.deleteRequest(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
