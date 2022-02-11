/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package back_end.model;

import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.time.LocalDateTime;

import io.github.cdimascio.dotenv.Dotenv;
import org.mindrot.jbcrypt.BCrypt;

import back_end.classes.request.Request_Coins;
import back_end.classes.user.User_Account;
import back_end.classes.transaction.Transaction;

/**
 *name
 * @author epilif3sotnas
 */
public class DAO {
    private Connection con = null;
    private final static String TABLE_USERS = "\"Users\"";
    private final static String TABLE_TRANSACTIONS = "\"Transactions\"";
    private final static String TABLE_TRANSACTIONS_DONE = "\"Transactions-Done\"";
    
    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {}
    }
    
    // db connection
    public boolean connect () {
        try {
            Dotenv dotenv = Dotenv.configure()
                    .directory("/home/epilif3sotnas/NetBeansProjects/ASW/.env")
                    .ignoreIfMalformed()
                    .load();
            con = DriverManager.getConnection(dotenv.get("url"),
                                              dotenv.get("username"),
                                              dotenv.get("password"));
        } catch (Exception ex) {
            return false;
        }
        return true;
    }
    
    public void disconnect () {
        if (con != null) {
            try {
                con.close();
            } catch (Exception ex) {}
        }
    }
    
    // Table Users
    public boolean insertUser (User_Account user) {
        if (connect()) {
            String sql = "insert into " + TABLE_USERS + " (\"Email\", \"Username\", \"Password\","
                    + " \"PhoneNumber\", \"ReferralCode\", \"Admin\") values (?,?,?,?,?,?);";
            try {
                PreparedStatement stm = con.prepareStatement(sql);
                stm.setString(1, user.getEmail());
                stm.setString(2, user.getUsername());
                stm.setString(3, user.getPassword());
                stm.setString(4, user.getPhoneNumber());
                stm.setString(5, user.getReferralCode());
                stm.setBoolean(6, user.isAdmin());
                if (stm.executeUpdate() > 0) {
                    disconnect();
                    return true;
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        disconnect();
        return false;
    }
    
    public User_Account getUserData (String ID) {
        if (connect()) {
            String sql = "select * from " + TABLE_USERS + " where \"ID\"='" + ID + "';";
            try {
                PreparedStatement stm = con.prepareStatement(sql);
                ResultSet data = stm.executeQuery();
                if (data.next()) {
                    disconnect();
                    return new User_Account(data.getInt("ID"),
                            data.getString("Email"),
                            data.getString("Username"),
                            data.getString("Password"),
                            data.getString("PhoneNumber"),
                            data.getFloat("Balance"),
                            data.getBoolean("Admin"));
                }
            } catch (SQLException ex) {}
        }
        disconnect();
        return new User_Account(0);
    }
    
    public boolean updateUserData (User_Account user, String ID) {
        if (connect()) {
            String sql = "update " + TABLE_USERS + " set \"Email\"=?, \"Username\"=?, "
                    + "\"Password\"=?, \"PhoneNumber\"=? where \"ID\"='" + ID + "';";
            try {
                PreparedStatement stm = con.prepareStatement(sql);
                stm.setString(1, user.getEmail());
                stm.setString(2, user.getUsername());
                stm.setString(3, user.getPassword());
                stm.setString(4, user.getPhoneNumber());
                if (stm.executeUpdate() > 0) {
                    disconnect();
                    return true;
                }
            } catch (SQLException ex) {}
        }
        disconnect();
        return false;
    }
    
    public boolean changeBalance (int ID, float change) {
        if (connect()) {
            String sql = "update " + TABLE_USERS + " set \"Balance\"= \"Balance\" + ? "
                    + "where \"ID\"='" + ID + "';";
            try {
                PreparedStatement stm = con.prepareStatement(sql);
                stm.setFloat(1, change);
                if (stm.executeUpdate() > 0) {
                    disconnect();
                    return true;
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        disconnect();
        return false;
    }
    
    public boolean deleteUser (String ID) {
        if (connect()) {
            String sql = "delete from " + TABLE_USERS + " where \"ID\"='" + ID + "';";
            try {
                PreparedStatement stm = con.prepareStatement(sql);
                if (stm.executeUpdate() > 0) {
                    disconnect();
                    return true;
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        disconnect();
        return false;
    }
    
    public boolean verifyUser (String email, String password) {
        if (connect()) {
            String sql = "select * from " + TABLE_USERS +
                    " where \"Email\"='" + email + "';";
            try {
                PreparedStatement stm = con.prepareStatement(sql);
                ResultSet data = stm.executeQuery();
                if (data.next()) {
                    disconnect();
                    return BCrypt.checkpw(password, data.getString("Password"));
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        disconnect();
        return false;
    }
    
    public String getID (String email) {
        if (connect()) {
            String sql = "select * from " + TABLE_USERS + " where \"Email\"='" + email + "';";
            try {
                PreparedStatement stm = con.prepareStatement(sql);
                ResultSet data = stm.executeQuery();
                if (data.next()) {
                    disconnect();
                    return data.getString("ID");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        disconnect();
        return null;
    }
    
    public boolean existsUser (String email) {
        if (connect()) {
            String sql = "select * from " + TABLE_USERS + " where \"Email\"='" + email + "';";
            try {
                PreparedStatement stm = con.prepareStatement(sql);
                ResultSet data = stm.executeQuery();
                if (data.next()) {
                    disconnect();
                    return true;
                }
            } catch (SQLException ex) {}
        }
        disconnect();
        return false;
    }
    
    public boolean deposit (String ID, float deposit) {
        if (connect()) {
            String sql = "update " + TABLE_USERS + " set \"Balance\"=\"Balance\" + " + deposit
                    + " where \"ID\"='" + ID + "';";
            try {
                PreparedStatement stm = con.prepareStatement(sql);
                if (stm.executeUpdate() > 0) {
                    disconnect();
                    return true;
                }
            } catch (SQLException ex) {}
        }
        disconnect();
        return false;
    }

    public boolean withdraw (String ID, float withdraw) {
        if (connect()) {
            String sql = "update " + TABLE_USERS + " set \"Balance\"=\"Balance\" - " + withdraw
                    + " where \"ID\"='" + ID + "';";
            try {
                PreparedStatement stm = con.prepareStatement(sql);
                if (stm.executeUpdate() > 0) {
                    disconnect();
                    return true;
                }
            } catch (SQLException ex) {}
        }
        disconnect();
        return false;
    }

    // Table Transactions-Done
    public boolean insertTransactionDone (Transaction transaction) {
        if (connect()) {
            String sql = "insert into " + TABLE_TRANSACTIONS_DONE + " (\"Seller\", \"Buyer\", \"DateTime\","
                    + "\"Coin\", \"Units\") values (?,?,?,?,?);";
            
            try {
                PreparedStatement stm = con.prepareStatement(sql);
                stm.setInt(1, transaction.getSeller());
                stm.setInt(2, transaction.getBuyer());
                stm.setString(3, transaction.getDateTime());
                stm.setString(4, transaction.getCoin());
                stm.setFloat(5, transaction.getUnits());
                if (stm.executeUpdate() > 0) {
                    disconnect();
                    return true;
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        disconnect();
        return false;
    }
    
    // Table Transactions
    public ArrayList<Transaction> getPortfolio (String ID) {
        ArrayList<Transaction> portfolio = new ArrayList<>();
        if (connect()) {
            String sql = "select * from " + TABLE_TRANSACTIONS_DONE + " where \"Buyer\"='" + ID + "';";
            try {
                PreparedStatement stm = con.prepareStatement(sql);
                ResultSet data = stm.executeQuery();
                while (data.next()) {
                    Transaction item = new Transaction(data.getInt("ID"),
                            data.getString("DateTime"),
                            data.getString("Coin"),
                            data.getFloat("Units"));

                    portfolio.add(item);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        disconnect();
        return portfolio;
    }

    public Transaction getTransaction (String ID) {
        if (connect()) {
            String sql = "select * from " + TABLE_TRANSACTIONS + " where \"ID\"='" + ID + "';";
            try {
                PreparedStatement stm = con.prepareStatement(sql);
                ResultSet data = stm.executeQuery();
                if (data.next()) {
                    disconnect();
                    return new Transaction(data.getInt("ID"),
                            data.getInt("Seller"),
                            data.getInt("Buyer"),
                            data.getString("DateTime"),
                            data.getString("Coin"),
                            data.getFloat("Units"));
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        disconnect();
        return new Transaction(0);
    }
    
    public boolean deleteTransaction (String ID) {
        if (connect()) {
            String sql = "delete from " + TABLE_TRANSACTIONS + " where \"ID\"='" + ID + "';";
            
            try {
                PreparedStatement stm = con.prepareStatement(sql);
                if (stm.executeUpdate() > 0) {
                    disconnect();
                    return true;
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        disconnect();
        return false;
    }
    
    public ArrayList<Transaction> getTransactions () {
        ArrayList<Transaction> transactions = new ArrayList<>();
        if (connect()) {
            String sql = "select * from " + TABLE_TRANSACTIONS + ";";
            try {
                PreparedStatement stm = con.prepareStatement(sql);
                ResultSet data = stm.executeQuery();
                while (data.next()) {
                    disconnect();
                    Transaction transaction = new Transaction(
                        data.getInt("ID"),
                        data.getInt("Seller"),
                        0,
                        data.getString("DateTime"),
                        data.getString("Coin"),
                        data.getFloat("Units"));
                    transactions.add(transaction);
                }
            } catch (SQLException ex) {}
        }
        disconnect();
        return transactions;
    }
    
    public boolean sellCoins (String ID, Request_Coins reqCoins) {
        if (connect()) {
            String sql = "insert into " + TABLE_TRANSACTIONS + " (\"Seller\", \"DateTime\","
                    + "\"Coin\", \"Units\") values (?,?,?,?);";
            try {
                PreparedStatement stm = con.prepareStatement(sql);
                stm.setInt(1, Integer.parseInt(ID));
                stm.setString(2, LocalDateTime.now().toString());
                stm.setString(3, reqCoins.getCoin());
                stm.setFloat(4, reqCoins.getUnits());
                if (stm.executeUpdate() > 0) {
                    disconnect();
                    return true;
                }
            } catch (SQLException ex) {}
        }
        disconnect();
        return false;
    }
    
    // Admin
    public boolean verifyDeposit (String depositID) {
        return true;
    }
    
    public boolean verifyWithdraw (String withdrawID) {
        return true;
    }

    public boolean verifyTransaction (String transactionID) {
        return true;
    }
}