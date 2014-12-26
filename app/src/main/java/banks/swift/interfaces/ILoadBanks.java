package banks.swift.interfaces;

import banks.swift.model.Bank;

/**
 * Created by pedro.sousa on 26/12/2014.
 */
public interface ILoadBanks {

    public void onLoadedBanks(Bank[] banks);
}
