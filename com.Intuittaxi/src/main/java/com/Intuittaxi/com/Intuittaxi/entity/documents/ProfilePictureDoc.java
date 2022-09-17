package com.Intuittaxi.com.Intuittaxi.entity.documents;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfilePictureDoc extends DocItem{
    String quality;

}
