package com.example.quize2.Service;

import com.example.quize2.Model.Book;
import com.example.quize2.Model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class BookService {

    private final UserService userService;
    ArrayList<Book>books=new ArrayList<>();



    public ArrayList<Book> getAllBooks(){
        return books;
    }

    public void addBook(Book book){
        books.add(book);
    }

    public boolean update(String id, Book book){
        for (int i = 0; i <books.size() ; i++) {
            if(books.get(i).getID().equalsIgnoreCase(id)){
                books.set(i,book);
                return true;
            }
        }
return false;
    }



    public boolean delete(String id){
        for (Book b:books){
            if(b.getID().equalsIgnoreCase(id)){
                books.remove(b);
                return true;
            }
        }
        return false;
    }

 public Book getBookByName(String name){
        for (Book b:books){
            if (b.getName().equalsIgnoreCase(name)) {
                return b;
            }
        }
        return null;
 }


 public ArrayList<Book> getBooksByCategory(String category){
        ArrayList<Book>booksByCategory=new ArrayList<>();
        for (Book b:books){
            if(b.getCategory().equalsIgnoreCase(category))
                booksByCategory.add(b);
        }
        return booksByCategory;
 }




 public ArrayList<Book> getBooksByNumberOfPages(int pages){
        ArrayList<Book>booksByNumberOfPages=new ArrayList();
        for (Book b:books){
            if(b.getNumber_of_pages()>=pages){
                booksByNumberOfPages.add(b);
            }
        }
        return booksByNumberOfPages;
 }




    public String changeBookStatus(String UserId,String bookId){

        //check role
        boolean exist=false;
        for (User u: userService.users){
            if (u.getID().equalsIgnoreCase(UserId)){
                exist=true;
                if (!(u.getRole().equalsIgnoreCase("librarian")))
                    return "role doesn't have permeation to change status";
            }
        }
        if(!exist)return "user not found";

        //search for book
        for (Book b:books){
            if (b.getID().equalsIgnoreCase(bookId)){
                if(b.isAvailable()==false)return "book status already unavailable";
                b.setAvailable(false);
                return "book status changed successfully";
            }

        }
        return "Book not found";

    }




}
