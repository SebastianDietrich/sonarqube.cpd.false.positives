package com.emovimento.sonarqube;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.querydsl.jpa.impl.JPAQueryFactory;

public class DuplicateCodeExample {

  /**
   * This method generates a list of books using the builder pattern.<br/>
   * It leads to duplicate (i.e. similar) code in sonarqube although it is perfectly clean code without duplications.
   * 
   * @return list of books in my library
   */
  public List<Book> myLibrary() {
    List<Book> myLibrary = new ArrayList<>();

    myLibrary
      .add(
        Book
          .builder()
          .author1FirstName("Erich")
          .author1LastName("Gamma")
          .author2FirstName("Richard")
          .author2LastName("Helm")
          .author3FirstName("Ralph")
          .author3LastName("Johnson")
          .author4FirstName("John")
          .author4LastName("Vlissides")
          .title("Design Patterns")
          .subtitle("Elements of Reusable Object-Oriented Software")
          .publisher("Addison-Wesley")
          .publisherCity("New York")
          .publicationDate(LocalDate.of(1994, 10, 31))
          .isbn("978-0201633610")
          .edition("1st")
          .language("english")
          .nrPages(395)
          .nrChapters(4)
          .build());

    myLibrary
      .add(
        Book
          .builder()
          .author1FirstName("Alan")
          .author1LastName("Shalloway")
          .author2FirstName("Scott")
          .author2LastName("Bain")
          .author3FirstName("Ken")
          .author3LastName("Pugh")
          .author4FirstName("Amir")
          .author4LastName("Kolski")
          .title("Essential Skills for the Agile Developer")
          .subtitle("A Guide to Better Programming and Design")
          .publisher("Addison-Wesley")
          .publisherCity("New York")
          .publicationDate(LocalDate.of(2013, 03, 04))
          .isbn("978-0321543738")
          .edition("1st")
          .language("english")
          .nrPages(395)
          .nrChapters(4)
          .build());

    return myLibrary;
  }

  /**
   * This method finds some books in my library.<br/>
   * It uses the java streaming api (i.e. "queries" on collections using functional programming) to find books according to given criteria.
   * Again duplicate (i.e. similar) code in sonarqube although it is perfectly clean code without duplications.
   * 
   * @return
   */
  public List<Book> someBooksFromList() {
    Book designPatternsEnglish = myLibrary()
      .stream()
      .filter(book -> book.getAuthor1FirstName().equals("Erich"))
      .filter(book -> book.getAuthor1LastName().equals("Gamma"))
      .filter(book -> book.getAuthor2FirstName().equals("Richard"))
      .filter(book -> book.getAuthor2LastName().equals("Helm"))
      .filter(book -> book.getAuthor3FirstName().equals("Ralph"))
      .filter(book -> book.getAuthor3LastName().equals("Johnson"))
      .filter(book -> book.getAuthor4FirstName().equals("John"))
      .filter(book -> book.getAuthor4LastName().equals("Vlissides"))
      .filter(book -> book.getTitle().contains("Design Patterns"))
      .filter(book -> book.getLanguage().equals("english"))
      .collect(Collectors.toList())
      .get(0);

    Book essentialSkills = myLibrary()
      .stream()
      .filter(book -> book.getAuthor1FirstName().equals("Alan"))
      .filter(book -> book.getAuthor1LastName().equals("Shalloway"))
      .filter(book -> book.getAuthor2FirstName().equals("Scott"))
      .filter(book -> book.getAuthor2LastName().equals("Bain"))
      .filter(book -> book.getAuthor3FirstName().equals("Ken"))
      .filter(book -> book.getAuthor3LastName().equals("Pugh"))
      .filter(book -> book.getAuthor4FirstName().equals("Amir"))
      .filter(book -> book.getAuthor4LastName().equals("Kolski"))
      .filter(book -> book.getTitle().contains("Essential Skills"))
      .filter(book -> book.getLanguage().equals("english"))
      .collect(Collectors.toList())
      .get(0);

    return Arrays.asList(designPatternsEnglish, essentialSkills);
  }

  @PersistenceContext
  private EntityManager entityManager;

  /**
   * This method queries some books from my database.<br/>
   * It uses query-dsl on jpa (http://www.querydsl.com/static/querydsl/4.4.0/reference/html_single/#jpa_integration) to find books using
   * sql-queries. Again duplicate (i.e. similar) code in sonarqube although it is perfectly clean code without duplications.
   * 
   * @return
   */
  public List<Book> someBooksFromDB() {
    JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);

    QBook book = QBook.book;

    Book designPatternsEnglish = queryFactory
      .selectFrom(book)
      .where(
        book.author1FirstName
          .eq("Erich")
          .and(book.author1LastName.eq("Gamma"))
          .and(book.author2FirstName.eq("Richard"))
          .and(book.author2LastName.eq("Helm"))
          .and(book.author3FirstName.eq("Ralph"))
          .and(book.author3LastName.eq("Johnson"))
          .and(book.author4FirstName.eq("John"))
          .and(book.author4LastName.eq("Vlissides"))
          .and(book.title.contains("Design Patterns"))
          .and(book.language.eq("english")))
      .fetch()
      .get(0);

    Book essentialSkills = queryFactory
      .selectFrom(book)
      .where(
        book.author1FirstName
          .eq("Alan")
          .and(book.author1LastName.eq("Shalloway"))
          .and(book.author2FirstName.eq("Scott"))
          .and(book.author2LastName.eq("Bain"))
          .and(book.author3FirstName.eq("Ken"))
          .and(book.author3LastName.eq("Pugh"))
          .and(book.author4FirstName.eq("Amir"))
          .and(book.author4LastName.eq("Kolski"))
          .and(book.title.contains("Essential Skills"))
          .and(book.language.eq("english")))
      .fetch()
      .get(0);

    return Arrays.asList(designPatternsEnglish, essentialSkills);

  }

}
