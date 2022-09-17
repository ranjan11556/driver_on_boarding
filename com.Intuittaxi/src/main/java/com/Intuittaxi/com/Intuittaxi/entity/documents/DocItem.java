package com.Intuittaxi.com.Intuittaxi.entity.documents;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
public class DocItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long docItemId;
    String url;
    DocStatus status;
}
