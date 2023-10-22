package org.epam.grpctask;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GrpcClient {

    private final static Logger LOGGER = LoggerFactory.getLogger(GrpcClient.class);

    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 6565)
                .usePlaintext()
                .build();

        BookServiceGrpc.BookServiceBlockingStub stub
                = BookServiceGrpc.newBlockingStub(channel);

        BookResponse bookResponse = stub.getBook(BookRequest.newBuilder()
                .setId(1)
                .build());
        LOGGER.info("Response: " + bookResponse);
    }
}
