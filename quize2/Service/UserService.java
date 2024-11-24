package com.example.quize2.Service;

import com.example.quize2.Model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService {
   ArrayList<User>users=new ArrayList<>();

   public ArrayList<User> getAllUser(){
       return users;
   }

   public void addUser(User user){
       users.add(user);
   }


   public boolean update(String id,User user){
       for (int i = 0; i <users.size() ; i++) {
           if(users.get(i).getID().equalsIgnoreCase(id)){
               users.set(i,user);
               return true;
           }
       }
       return false;
   }

   public boolean delete(String id){
       for (User u:users){
           if (u.getID().equalsIgnoreCase(id)){
               users.remove(u);
               return true;
           }
       }
       return false;
   }


   public ArrayList<User> getUsersByBalance(double balance){
       ArrayList<User>usersByBalance=new ArrayList<>();
       for (User u:users){
           if(u.getBalance()>=balance){
               usersByBalance.add(u);
           }
       }
       return usersByBalance;
   }


   public ArrayList<User> getUsersByAge(int age){
       ArrayList<User>usersByAge=new ArrayList<>();
       for (User u:users){
           if(u.getAge()>=age){
               usersByAge.add(u);
           }
       }
       return usersByAge;
   }

}
