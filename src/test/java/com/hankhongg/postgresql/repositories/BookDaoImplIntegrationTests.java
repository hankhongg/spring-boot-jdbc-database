//package com.hankhongg.postgresql.dao.impl;
//
//import com.hankhongg.postgresql.TestDataUtil;
//import com.hankhongg.postgresql.domain.Author;
//import com.hankhongg.postgresql.domain.Book;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.annotation.DirtiesContext;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import java.util.List;
//import java.util.Optional;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@SpringBootTest
//@ExtendWith(SpringExtension.class)
//@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
//public class BookDaoImplIntegrationTests {
//    private BookDaoImpl bookDaoUnderTest;
//    private AuthorDaoImpl authorDaoImpl;
//
//    @Autowired
//    public BookDaoImplIntegrationTests(BookDaoImpl bookDaoUnderTest, AuthorDaoImpl authorDaoImpl) {
//        this.bookDaoUnderTest = bookDaoUnderTest;
//        this.authorDaoImpl = authorDaoImpl;
//    }
//    @Test
//    public void testBookCanBeCreatedAndRecalled(){
//        Author author = TestDataUtil.getTestAuthor1();
//        authorDaoImpl.create(author);
//        Book book = TestDataUtil.getTestBook1();
//        book.setAuthorId(author.getId());
//        bookDaoUnderTest.create(book);
//        Optional<Book> result = bookDaoUnderTest.findOne(book.getIsbn());
//        assertThat(result.isPresent());
//        assertThat(result.get()).isEqualTo(book);
//    }
//    @Test
//    public void testAllBooksCanBeCreatedAndRecalled(){
//        //get 3 author instances
//        Author author1 = TestDataUtil.getTestAuthor1();
//        Author author2 = TestDataUtil.getTestAuthor2();
//        Author author3 = TestDataUtil.getTestAuthor3();
//
//        //add to database 3 author instances
//        authorDaoImpl.create(author1);
//        authorDaoImpl.create(author2);
//        authorDaoImpl.create(author3);
//
//        //create 3 book instances
//        Book book1 = TestDataUtil.getTestBook1();
//        Book book2 = TestDataUtil.getTestBook2();
//        Book book3 = TestDataUtil.getTestBook3();
//
//        //set authorid for 3 books
//        book1.setAuthorId(author1.getId());
//        book2.setAuthorId(author2.getId());
//        book3.setAuthorId(author3.getId());
//
//        //add to database 3 book instances
//        bookDaoUnderTest.create(book1);
//        bookDaoUnderTest.create(book2);
//        bookDaoUnderTest.create(book3);
//
//        // test findAll
//        List<Book> books = bookDaoUnderTest.findAll();
//        assertThat(books).hasSize(3).containsExactly(book1, book2, book3);
//    }
//    @Test
//    public void testBookCanBeUpdatedAndRecalled(){
//        Author author1 = TestDataUtil.getTestAuthor1();
//        authorDaoImpl.create(author1);
//        Book book1 = TestDataUtil.getTestBook1();
//        book1.setAuthorId(author1.getId());
//        bookDaoUnderTest.create(book1);
//
//        // change the title but only to the instance NOT the db
//        book1.setTitle("time to pay back i guess");
//        // then update it based on the id
//        bookDaoUnderTest.update(book1.getIsbn(), book1);
//
//        Optional<Book> result = bookDaoUnderTest.findOne(book1.getIsbn());
//        assertThat(result).isPresent();
//        assertThat(result.get()).isEqualTo(book1);
//    }
//    @Test
//    public void testBookCanBeDeletedAndRecalled(){
//        Author author1 = TestDataUtil.getTestAuthor1();
//        authorDaoImpl.create(author1);
//        Book book1 = TestDataUtil.getTestBook1();
//        book1.setAuthorId(author1.getId());
//        bookDaoUnderTest.create(book1);
//
//        // try to delete
//        bookDaoUnderTest.delete(book1.getIsbn());
//        // save result
//        Optional<Book> result = bookDaoUnderTest.findOne(book1.getIsbn());
//        assertThat(result).isEmpty();
//    }
//}
