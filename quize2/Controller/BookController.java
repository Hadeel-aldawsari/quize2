package com.example.quize2.Controller;

import com.example.quize2.ApiResponse.ApiResponse;
import com.example.quize2.Model.Book;
import com.example.quize2.Service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/book")
public class BookController {

    private final BookService bookService;

    @GetMapping("/get-all-book")
    public ResponseEntity getAllBooks() {
        return ResponseEntity.status(HttpStatus.OK).body(bookService.getAllBooks());
    }


    @PostMapping("/add-book")
    public ResponseEntity addBook(@RequestBody @Valid Book book, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors.getFieldError().getDefaultMessage());
        }
        bookService.addBook(book);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Book Added"));

    }


    @PutMapping("/update/{id}")
    public ResponseEntity update(@PathVariable String id,@RequestBody @Valid Book book,Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors.getFieldError().getDefaultMessage());
        }
        if(bookService.update(id,book)){
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Book updated successfully"));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("Book update Unsuccessfully"));

    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable String id){
        if(bookService.delete(id)){
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("book deleted successfully"));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("delete book Unsuccessfully"));
    }


    @GetMapping("/get-by-name/{name}")
    public ResponseEntity getBookByName(@PathVariable String name){
        if(bookService.getBookByName(name)==null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("Not found"));

       return  ResponseEntity.status(HttpStatus.OK).body(bookService.getBookByName(name));

    }


@GetMapping("/get-by-category/{category}")
    public ResponseEntity getBooksByCategory(@PathVariable String category){
      if(bookService.getBooksByCategory(category).size()==0)
          return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("not found books by this category"));
      return ResponseEntity.status(HttpStatus.OK).body(bookService.getBooksByCategory(category));

    }

    @GetMapping("/get-by-pages/{page}")
    public ResponseEntity getBooksByNumberOfPages(@PathVariable int page){
        if(bookService.getBooksByNumberOfPages(page).size()==0)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("Not Found book in this pages range "));
        return ResponseEntity.status(HttpStatus.OK).body(bookService.getBooksByNumberOfPages(page));
    }


    @PutMapping("/change-status/{userId}/{bookId}")
    public ResponseEntity changeBookStatus(@PathVariable String userId,@PathVariable String bookId){
       return ResponseEntity.status(HttpStatus.OK).body(bookService.changeBookStatus(userId,bookId));
    }









}
