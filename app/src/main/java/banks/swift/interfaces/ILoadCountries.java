package banks.swift.interfaces;

import java.util.ArrayList;

import banks.swift.model.Bank;

/**
 * Created by pedro.sousa on 26/12/2014.
 */
public interface ILoadCountries {

    public void onLoadedCountries(ArrayList<String> countries);
}
