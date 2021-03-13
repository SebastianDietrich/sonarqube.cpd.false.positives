package com.emovimento.sonarqube;

import java.time.LocalDate;

import javax.persistence.Entity;

import lombok.Builder;
import lombok.Data;

/**
 * A class representing a typical book in a library (see e.g. https://en.wikipedia.org/wiki/Template:Cite_book).<br/>
 * Construction of such classes (having many attributes of same type) should be done using the builder pattern. The builder is added using
 * projectlombok (using the @Builder annotation - see https://projectlombok.org/features/Builder) - but it can as well be written by hand
 */
@Builder
@Data
@Entity
public class Book {
  private String author1FirstName;
  private String author1MiddleNames;
  private String author1LastName;
  private String author2FirstName;
  private String author2MiddleNames;
  private String author2LastName;
  private String author3FirstName;
  private String author3MiddleNames;
  private String author3LastName;
  private String author4FirstName;
  private String author4MiddleNames;
  private String author4LastName;
  private String title;
  private String subtitle;
  private String language;
  private int nrPages;
  private int nrChapters;
  private String publisher;
  private String publisherCity;
  private String editor;
  private String edition;
  private LocalDate publicationDate;
  private String isbn;
}
