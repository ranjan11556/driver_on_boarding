package com.Intuittaxi.com.Intuittaxi.service.StateMachine;

import com.Intuittaxi.com.Intuittaxi.entity.Documents;
import com.Intuittaxi.com.Intuittaxi.entity.ProfileCurrentState;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
@Slf4j
@Service
public class TraningExecuterMachine implements StateMachine{
    private StateMachine nextStateMachine;
    @Override
    public void setNextState(StateMachine nextState) {
        this.nextStateMachine = nextState;
    }

    @Override
    public ProfileCurrentState processState(Documents driverID) {
        //TODO add process of device shipment
        // if failed return ProfileCurrentState.TRAINING_IN_PROGRESS
        // else
        return ProfileCurrentState.ONBOARDING_COMPLETED;
    }
}
