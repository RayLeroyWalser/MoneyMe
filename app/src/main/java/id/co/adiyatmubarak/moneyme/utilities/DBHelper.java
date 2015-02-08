package id.co.adiyatmubarak.moneyme.utilities;

import android.content.Context;

import id.co.adiyatmubarak.moneyme.models.User;
import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

/**
 * Created by Administrator on 2015-02-08.
 */
public class DBHelper {

    private static DBHelper dbHelper;
    private static Realm realm;
    private static RealmQuery<User> userQuery;

    private DBHelper(){}

    public static DBHelper getInstance(Context ctx) {
        if (dbHelper == null) {
            // Create instance class
            dbHelper = new DBHelper();

            // Craete realm instance
            realm = Realm.getInstance(ctx);
            userQuery = realm.where(User.class);
        }
        return dbHelper;
    }

    /**
     * Boolean function to check whether the apps already have
     * PIN for login
     * @return boolean status if the data is not empty
     */
    public static boolean isExists() {
        RealmResults<User> results = userQuery.findAll();
        return !results.isEmpty();
    }

    /**
     * Boolean function to check if the pin valid / registered in database
     * @param pin String from user input
     * @return boolean status if selected pin exist in database
     */
    public static boolean isAuthenticated(String pin) {
        long result = userQuery.equalTo("pin", Integer.parseInt(pin)).count();
        if (result >= 1) {
            return true;
        }
        return false;
    }

    /**
     * Function to insert new pin.
     * @param pin
     */
    public static void createPin(int pin) {
        realm.beginTransaction();
        User user = realm.createObject(User.class);
        user.setPin(pin);
        realm.commitTransaction();
    }

    /**
     * Function to edit existing user pin.
     * @param user instance from User class
     * @param newPin new user pin
     */
    public static void updatePin(User user, int newPin) {
        // Get user by pin
        User realmUser = realm.where(User.class).equalTo("pin", user.getPin()).findFirst();

        // Update the pin
        realm.beginTransaction();
        realmUser.setPin(newPin);
        realm.commitTransaction();
    }

    /**
     * Function to reset database. All data will clear.
     */
    public static void resetDatabase() {
        realm.beginTransaction();
        RealmResults<User> results = userQuery.findAll();
        results.clear();
        realm.commitTransaction();
    }

}
