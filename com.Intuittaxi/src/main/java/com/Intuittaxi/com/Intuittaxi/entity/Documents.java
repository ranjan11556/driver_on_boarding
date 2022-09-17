package com.Intuittaxi.com.Intuittaxi.entity;

import com.Intuittaxi.com.Intuittaxi.entity.documents.DrivingLicenceDoc;
import com.Intuittaxi.com.Intuittaxi.entity.documents.ProfilePictureDoc;
import com.Intuittaxi.com.Intuittaxi.entity.documents.RegistrationCertificateDoc;
import com.Intuittaxi.com.Intuittaxi.entity.documents.VehicleInsuranceDoc;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Documents {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long documentId;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "vechileInsurenceId", referencedColumnName = "docItemId")
    private VehicleInsuranceDoc vehicleInsurance;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "profilePicId", referencedColumnName = "docItemId")
    private ProfilePictureDoc pofilePicture;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "drivingLicenceID", referencedColumnName = "docItemId")
    private DrivingLicenceDoc drivingLicence;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "RegistrationCertificateID", referencedColumnName = "docItemId")
    private RegistrationCertificateDoc registerationCertificate;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "PanCardId", referencedColumnName = "docItemId")
    private VehicleInsuranceDoc panCard;

}
