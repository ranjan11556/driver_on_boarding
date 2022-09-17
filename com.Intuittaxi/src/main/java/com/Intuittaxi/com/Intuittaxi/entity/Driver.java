package com.Intuittaxi.com.Intuittaxi.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long driverId;

    @NotBlank(message = "Please Add Name")
    private String firstName;
    @NotBlank(message = "Please Last Name")
    private String lastName;
    @NotBlank(message = "Please Add address")
    private String address;
    @NotBlank(message = "Please Add email")
    private String email;
    @NotBlank(message = "Please Add mobileno.")
    private String mobileNo;
    @NotBlank(message = "Please Add Location where you will drive")
    private String driveArea;

    private ProfileCurrentState profileCurrentState = ProfileCurrentState.DOC_COLLECTION;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "documentId", referencedColumnName = "documentId")
    private Documents document;
}
