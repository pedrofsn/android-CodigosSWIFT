package banks.swift;

import android.app.Application;

import com.amazon.device.ads.AdRegistration;

/**
 * Created by pedro.francisco on 04/04/2016.
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        AdRegistration.setAppKey("0123456789ABCDEF0123456789ABCDEF it's not my! :P");
    }
}
