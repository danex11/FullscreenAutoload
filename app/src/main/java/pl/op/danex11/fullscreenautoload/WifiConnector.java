package pl.op.danex11.fullscreenautoload;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkRequest;
import android.net.wifi.WifiNetworkSpecifier;

import androidx.annotation.NonNull;


/**
 * A class that connects Android device to a specified wifi network.
 * Example code:
 * <pre>
 *         String networkSSID = "AndroidWifi";
 *         String networkPass = "";
 *         Context context = getApplicationContext();
 *         WifiConnector wificonnector = new WifiConnector(context);
 *         wificonnector.Connect(networkSSID, networkPass);
 *       </pre>
 *
 * @author dsz
 * @version 0.1
 */
public class WifiConnector {

    Context contextthis;

    /**
     * A constructor for method to connect Android device to a specified wifi network.
     * *      <p>
     * *      <b>Note:</b> A context is required.
     *
     * @param context context from witch class is called, ex. {@code Context context = getApplicationContext()};
     * @author dsz
     * @version 0.1
     */
    public WifiConnector(Context context) {
        this.contextthis = context;
    }

    /**
     * This is a method that attempts establish connection
     * TODO throw status info
     *
     * @param ssid     specific name of wifi network to connect to
     * @param password password to a given network
     */
    public void Connect(String ssid, String password) {
        try {
            System.out.println(" try ");
            WifiNetworkSpecifier.Builder wifiNetworkSpecifierBuilder = new WifiNetworkSpecifier.Builder();
            wifiNetworkSpecifierBuilder.setSsid(ssid);
            wifiNetworkSpecifierBuilder.setWpa2Passphrase(password);
            System.out.println(" wifiNetworkSpecifierBuilder OK ");

            WifiNetworkSpecifier wifiNetworkSpecifier = wifiNetworkSpecifierBuilder.build();
            System.out.println(" wifiNetworkSpecifier OK ");

            NetworkRequest.Builder networkRequestBuilder = new NetworkRequest.Builder();
            networkRequestBuilder.addTransportType(NetworkCapabilities.TRANSPORT_WIFI);
            //networkRequestBuilder.addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET);
            networkRequestBuilder.setNetworkSpecifier(wifiNetworkSpecifier);
            System.out.println(" networkRequestBuilder OK ");

            NetworkRequest networkRequest = networkRequestBuilder.build();
            System.out.println(" networkRequestBuilder OK ");

            // ConnectivityManager connectivityManager = (ConnectivityManager)android.app.Application.GetSystemService(Context.ConnectivityService);
            ConnectivityManager connectivityManager = (ConnectivityManager) contextthis.getSystemService(Context.CONNECTIVITY_SERVICE);
            System.out.println(" connectivityManager OK ");

            connectivityManager.requestNetwork(networkRequest, new NetworkCallback());
            System.out.println(" connectivityManager request OK ");

        } catch (Exception ex) {
            System.out.println("Thrown exception: " + ex.getMessage());
        }
    }


    class NetworkCallback extends ConnectivityManager.NetworkCallback {
        @Override
        public void onAvailable(@NonNull Network network) {
            super.onAvailable(network);
            System.out.println("OnAvailable(): " + network);

        }
    }

}

