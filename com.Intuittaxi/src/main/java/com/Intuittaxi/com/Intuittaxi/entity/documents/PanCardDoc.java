package com.Intuittaxi.com.Intuittaxi.entity.documents;

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
public class PanCardDoc extends DocItem{
    String panNo;
    private Date validfrom;
    private Date validtill;
}
