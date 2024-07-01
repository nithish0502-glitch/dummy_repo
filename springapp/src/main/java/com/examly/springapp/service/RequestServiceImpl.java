package com.examly.springapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examly.springapp.model.Request;
import com.examly.springapp.model.User;
import com.examly.springapp.repository.AgroChemicalRepo;
import com.examly.springapp.repository.CropRepo;
import com.examly.springapp.repository.RequestRepo;
import com.examly.springapp.repository.UserRepo;

@Service
public class RequestServiceImpl implements RequestService {
     @Autowired
    private RequestRepo requestRepository;

    @Autowired
    public CropRepo cropRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private AgroChemicalRepo agroChemicalRepo;

    @Override
    public Request createRequest(Request request) {
        request.setCrop(cropRepo.findById(request.getCrop().getCropId()).orElse(null));
        request.setUser(userRepo.findById(request.getUser().getUserId()).orElse(null));
        request.setAgroChemical(agroChemicalRepo.findById(request.getAgroChemical().getAgroChemicalId()).orElse(null));
        request.setStatus("Pending");
        return requestRepository.save(request);
    }

    @Override
    public Request getRequestById(int id) {
        return requestRepository.findById(id).orElseThrow(() -> new RuntimeException("Request not found"));
    }

    @Override
    public List<Request> getAllRequests() {
        return requestRepository.findAll();
    }

    // @Override
    // public Request updateRequest(int id, Request updatedRequest) {
    //     Request existingRequest = requestRepository.findById(id)
    //         .orElseThrow(() -> new RuntimeException("Request not found"));

    //     existingRequest.setAgroChemical(updatedRequest.getAgroChemical());
    //     existingRequest.setUser(updatedRequest.getUser());
    //     existingRequest.setCrop(updatedRequest.getCrop());
    //     existingRequest.setQuantity(updatedRequest.getQuantity());
    //     existingRequest.setStatus(updatedRequest.getStatus());
    //     existingRequest.setRequestDate(updatedRequest.getRequestDate());

    //     return requestRepository.save(existingRequest);
    // }

    @Override
    public String updateRequest(int id) {
        
            Request request = requestRepository.findById(id).orElse(null);
        if(request.getStatus().equals("Pending")){
        request.setStatus("Approved");
        return request.getStatus();
    }
        else{
         request.setStatus("Pending");
         return request.getStatus();
        }  
    } 
          
    @Override 
    public void deleteRequest(int id) {
        Request request = requestRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Request not found"));
        requestRepository.delete(request);
    }

    @Override
    public List<Request> getRequestsByUserId(int userId) {
        User user=userRepo.findById(userId).orElse(null);
        return requestRepository.findByUser(user);

    }
}
