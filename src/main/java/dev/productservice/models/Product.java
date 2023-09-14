package dev.ashish.productservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Builder
public class Product extends  BaseModel{
private String title;
private  String description;
private  String image;
@ManyToOne
private Category category;
private  double price;
}
