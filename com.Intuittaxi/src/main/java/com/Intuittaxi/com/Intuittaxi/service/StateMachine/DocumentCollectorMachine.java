package com.Intuittaxi.com.Intuittaxi.service.StateMachine;

import com.Intuittaxi.com.Intuittaxi.entity.Documents;
import com.Intuittaxi.com.Intuittaxi.entity.ProfileCurrentState;
import com.Intuittaxi.com.Intuittaxi.entity.documents.DocStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Service
public class DocumentCollectorMachine implements StateMachine {

    private StateMachine nextStateMachine;
    @Override
    public void setNextState(StateMachine nextState) {
        this.nextStateMachine = nextState;
    }

    @Override
    public ProfileCurrentState processState(Documents documents) {
        if(documents.getVehicleInsurance() == null  || documents.getDrivingLicence() == null)
            return ProfileCurrentState.DOC_COLLECTION;

        documents.getVehicleInsurance().setStatus(DocStatus.VERIFICATION_IN_PROGRESS);
        documents.getDrivingLicence().setStatus(DocStatus.VERIFICATION_IN_PROGRESS);
        log.info("Moving processing step from DocumentCollectorMachine to BackGroundVerificationMachine");

        return nextStateMachine.processState(documents);
    }
}
