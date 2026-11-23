package com.bijlipay.service.implement;

import com.bijlipay.model.ServiceRequestClients;
import com.bijlipay.model.User;
import com.bijlipay.model.dto.request.UserRequest;
import com.bijlipay.repository.ServiceRequestClientsRepository;
import com.bijlipay.service.ServiceRequestClientsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceRequestClientsServiceImpl implements ServiceRequestClientsService {

    private static final Logger logger = LoggerFactory.getLogger(ServiceRequestClientsServiceImpl.class);
    private static final String TAG = "Service_Clients : ";


    @Autowired
    private ServiceRequestClientsRepository serviceReqClientsRepository;


 /*  @Override
    public ServiceRequestClients createClient(UserRequest userRequest, User finalUser, boolean data) {
        logger.info(TAG + "service client creation -- : ");
        ServiceRequestClients serviceReqClients = new ServiceRequestClients();
        serviceReqClients.setName(userRequest.getName());
        serviceReqClients.setSourceId(userRequest.getLeadSource());
        serviceReqClients.setAuthKey(userRequest.getServiceReqClients().getAuthKey());
        serviceReqClients.setAuthMethod(userRequest.getServiceReqClients().getAuthMethod());
        serviceReqClients.setUserId(finalUser);
        serviceReqClients.setAuthValue(userRequest.getServiceReqClients().getAuthValue());
        serviceReqClients.setCallBackUrl(userRequest.getServiceReqClients().getCallBackUrl());
        serviceReqClients.setClientType(data);

 true - internal && false - External


        return serviceReqClientsRepository.save(serviceReqClients);
    }
*/

    @Override
    public ServiceRequestClients getClientDetails(User user) {
        return serviceReqClientsRepository.findClientDetailsByUser(user);
    }

    @Override
    public boolean isClientExists(UserRequest userRequest) {
        return serviceReqClientsRepository.findClientByName(userRequest.getName()) > 0;
    }
}
