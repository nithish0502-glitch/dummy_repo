package com.examly.springapp.service;

import java.util.List;

import com.examly.springapp.model.Request;

public interface RequestService {
    Request createRequest(Request request);
    Request getRequestById(int id);
    List<Request> getAllRequests();
    Request updateRequest(int id, Request request);
    void deleteRequest(int id);
}
