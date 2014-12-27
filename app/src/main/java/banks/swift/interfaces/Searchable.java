package banks.swift.interfaces;

/**
 * Created by pedrofsn on 27/12/2014.
 */
public interface Searchable {

    public void onLoading();

    public void search(String query);

    public void restartSearch();

    public void showSearchResults(Object[] array);

}
