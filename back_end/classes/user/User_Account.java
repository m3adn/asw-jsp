/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package back_end.classes.user;

import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author epilif3sotnas
 */
public class User_Account {
    private int id;
    private String email;
    private String username;
    private String password;
    private String phoneNumber;
    private String referralCode;
    private float balance;
    private boolean admin;
    
    public User_Account (String _email,
                  String _username,
                  String _password,
                  String _phoneNumber,
                  String _referralCode,
                  String _admin) {
        
        if (_email != null && _username != null && _password != null &&
                _phoneNumber != null && _admin != null) {
            this.email = _email;
            this.username = _username;
            this.password = BCrypt.hashpw(_password, BCrypt.gensalt());
            this.phoneNumber = _phoneNumber;
            this.referralCode = _referralCode;
            
            if (_admin.equals("1")) this.admin = true;
            else if (_admin.equals("0")) this.admin = false;
        } else {
            throw new IllegalArgumentException("Data not valid!!");
        }
    }
    
    public User_Account (int _id,
            String _email,
            String _username,
            String _password,
            String _phoneNumber,
            float _balance,
            boolean _admin) {
        
        if (_id != 0 && _email != null && _username != null && _password != null &&
                _phoneNumber != null && _admin) {
            this.id = _id;
            this.email = _email;
            this.username = _username;
            this.password = _password;
            this.phoneNumber = _phoneNumber;
            this.balance = _balance;
            this.admin = _admin;
        } else {
            throw new IllegalArgumentException("Data not valid!!");
        }
    }
    
    public User_Account (int _id) {
        if (_id == 0) {
            this.id = _id;
        }
    }

    public String getEmail () {
        return email;
    }

    public String getUsername () {
        return username;
    }

    public String getPassword () {
        return password;
    }

    public String getPhoneNumber () {
        return phoneNumber;
    }

    public String getReferralCode () {
        return referralCode;
    }

    public float getBalance () {
        return balance;
    }

    public int getId () {
        return id;
    }

    public boolean isAdmin () {
        return admin;
    }

    public void setBalance (float balance) {
        this.balance = balance;
    }

    public void setId (int id) {
        this.id = id;
    }
    
    public boolean hasAtributes () {
        return this.id != 0 && this.email != null && this.username != null && this.password != null
                && this.phoneNumber != null && this.admin;
    }
}