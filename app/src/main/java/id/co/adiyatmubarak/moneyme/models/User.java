package id.co.adiyatmubarak.moneyme.models;

import io.realm.RealmObject;

/**
 * Created by Administrator on 2015-02-08.
 */
public class User extends RealmObject {

    private int pin;

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }
}
