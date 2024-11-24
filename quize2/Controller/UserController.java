package com.example.quize2.Controller;

import com.example.quize2.ApiResponse.ApiResponse;
import com.example.quize2.Model.Book;
import com.example.quize2.Model.User;
import com.example.quize2.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService userService;


    @GetMapping("/get-all-user")
    public ResponseEntity getAllBooks() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUser());
    }


    @PostMapping("/add-user")
    public ResponseEntity addBook(@RequestBody @Valid User user, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors.getFieldError().getDefaultMessage());
        }
        userService.addUser(user);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("user Added"));

    }


    @PutMapping("/update/{id}")
    public ResponseEntity update(@PathVariable String id,@RequestBody @Valid User user,Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors.getFieldError().getDefaultMessage());
        }
        if(userService.update(id,user)){
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("user updated successfully"));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("user update Unsuccessfully"));

    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable String id){
        if(userService.delete(id)){
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("user deleted successfully"));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("user book Unsuccessfully"));
    }

    @GetMapping("/get-user-by-balance/{balance}")
    public ResponseEntity getUsersByBalance(@PathVariable double balance){
        if(userService.getUsersByBalance(balance).size()==0)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("no users found"));
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUsersByBalance(balance));
    }

    @GetMapping("/get-user-by-age/{age}")
    public ResponseEntity getUsersByAge(@PathVariable int age){
        if(userService.getUsersByAge(age).size()==0)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("No user found in this age or above"));

        return ResponseEntity.status(HttpStatus.OK).body(userService.getUsersByAge(age));
    }

}
