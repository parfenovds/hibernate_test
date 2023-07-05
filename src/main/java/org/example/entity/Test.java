package org.example.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.converter.BirthdayConverter;

@Entity
@Table(name = "test")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Test {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;
  private String text;
  @Column(name = "birth_date")
//  @Convert(converter = BirthdayConverter.class)
  private Birthday birthday;
}
