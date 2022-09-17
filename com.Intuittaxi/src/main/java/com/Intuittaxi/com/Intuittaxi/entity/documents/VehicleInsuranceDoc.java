package com.Intuittaxi.com.Intuittaxi.entity.documents;

import com.Intuittaxi.com.Intuittaxi.entity.documents.DocItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.util.Date;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VehicleInsuranceDoc extends DocItem {
    private Date validfrom;
    private Date validtill;
    private String insurenceProvider;
}
