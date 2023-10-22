package org.epam.grpctask.service;

import io.grpc.stub.StreamObserver;
import org.epam.grpctask.BookRequest;
import org.epam.grpctask.BookResponse;
import org.epam.grpctask.BookServiceGrpc;
import org.epam.grpctask.entity.Book;
import org.epam.grpctask.jpa.BookRepository;
import org.lognet.springboot.grpc.GRpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@GRpcService
public class BookService extends BookServiceGrpc.BookServiceImplBase {

    private final static Logger LOGGER = LoggerFactory.getLogger(BookService.class);

    @Autowired
    private BookRepository bookRepository;

    @Override
    public void getBook(BookRequest request, StreamObserver<BookResponse> responseObserver) {
        Optional<Book> bookOptional = bookRepository.findById((long) request.getId());
        Book book = bookOptional.orElseThrow(() -> new BookDoesNotExistException("The book doesn't exist"));
        LOGGER.info("Requested book: " + book);
        BookResponse.Builder bookResponseBuilder =
                BookResponse.newBuilder()
                        .setId((int) book.getId())
                        .setName(book.getName())
                        .setAuthor(book.getAuthor());
        BookResponse bookResponse = bookResponseBuilder.build();
        responseObserver.onNext(bookResponse);
        responseObserver.onCompleted();
    }
}
