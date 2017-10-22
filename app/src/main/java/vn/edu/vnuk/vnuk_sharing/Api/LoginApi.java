package vn.edu.vnuk.vnuk_sharing.Api;

import java.util.ArrayList;

import vn.edu.vnuk.vnuk_sharing.DataStructure.User;

/**
 * Created by Quangngoc430 on 10/20/2017.
 */

public class LoginApi {
    public LoginApi(){
        super();
    }

    public int checkLogin(ArrayList<User> userArrayList, String username, String password){
        // -1 : login sai
        // trả về id user : login đúng

        for (int i = 0; i < userArrayList.size(); i++){
            if(userArrayList.get(i).getUsername().equals(username) && userArrayList.get(i).getPassword().equals(password)){
                return i;
            }
        }

        return -1;
    }
}
