package com.Intuittaxi.com.Intuittaxi.service.StateMachine;

import com.Intuittaxi.com.Intuittaxi.entity.Documents;
import com.Intuittaxi.com.Intuittaxi.entity.ProfileCurrentState;
import com.Intuittaxi.com.Intuittaxi.entity.documents.DocStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
@Slf4j
@Service
public class BackGroundVerificationMachine implements StateMachine{
    private StateMachine nextStateMachine;
    @Override
    public void setNextState(StateMachine nextState) {
        this.nextStateMachine = nextState;
    }

    @Override
    public ProfileCurrentState processState(Documents documents) {
        //TODO add stategy to validate the doc
        //TODO add ML logics to validate the doc
        // if failed return ProfileCurrentState.VERIFICATION_IN_PROGRESS
        // else
        log.info("Moving processing step from BackgroundVerification to DeviceShipment");
        documents.getVehicleInsurance().setStatus(DocStatus.VERIFIED);
        documents.getDrivingLicence().setStatus(DocStatus.VERIFIED);

        return nextStateMachine.processState(documents);
    }
}
