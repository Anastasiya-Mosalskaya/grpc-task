from __future__ import print_function

import grpc
import book_pb2
import book_pb2_grpc


if __name__ == "__main__":
    print("Will try to get book ...")
    with grpc.insecure_channel("localhost:6565") as channel:
        stub = book_pb2_grpc.BookServiceStub(channel)
        response = stub.getBook(book_pb2.BookRequest(id=1))
    print(f'{response}')