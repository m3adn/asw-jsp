/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package back_end.classes.transaction;

/**
 *
 * @author epilif3sotnas
 */
public class Transaction {
    private int id;
    private int seller;
    private int buyer;
    private String dateTime;
    private String coin;
    private float units;

    public Transaction (int _id, int _seller, int _buyer, String _dateTime, String _coin, float _units) {
        if (_id != 0 && _seller != 0 && _dateTime != null &&
                _coin != null && _units != 0.0f) {
            this.id = _id;
            this.seller = _seller;
            this.dateTime = _dateTime;
            this.coin = _coin;
            this.units = _units;
        }
    }
    
    public Transaction (int _id, String _dateTime, String _coin, float _units) {
        if (_id != 0 && _dateTime != null && _coin != null && _units != 0.0f) {
            this.id = _id;
            this.dateTime = _dateTime;
            this.coin = _coin;
            this.units = _units;
        }
    }
    
    public Transaction (int _id) {
        if (_id == 0) {
            this.id = _id;
        }
    }

    public int getID () {
        return id;
    }

    public int getSeller () {
        return seller;
    }

    public int getBuyer () {
        return buyer;
    }

    public String getDateTime () {
        return dateTime;
    }

    public String getCoin () {
        return coin;
    }

    public float getUnits () {
        return units;
    }

    public void setBuyer(int buyer) {
        this.buyer = buyer;
    }
}