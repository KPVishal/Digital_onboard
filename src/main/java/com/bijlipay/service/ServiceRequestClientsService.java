package com.bijlipay.service;

import com.bijlipay.model.ServiceRequestClients;
import com.bijlipay.model.User;
import com.bijlipay.model.dto.request.UserRequest;

public interface ServiceRequestClientsService {
    //ServiceRequestClients createClient(UserRequest userRequest, User finalUser, boolean data);

    ServiceRequestClients getClientDetails(User user);

    boolean isClientExists(UserRequest userRequest);
}
