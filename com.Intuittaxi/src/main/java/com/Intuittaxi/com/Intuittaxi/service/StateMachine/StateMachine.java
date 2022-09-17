package com.Intuittaxi.com.Intuittaxi.service.StateMachine;

import com.Intuittaxi.com.Intuittaxi.entity.Documents;
import com.Intuittaxi.com.Intuittaxi.entity.ProfileCurrentState;
import com.Intuittaxi.com.Intuittaxi.entity.documents.DocStatus;
import org.springframework.stereotype.Service;

@Service
public interface StateMachine {
    public void setNextState(StateMachine nextState);
    public ProfileCurrentState processState(Documents driverID);
}
