package masterung.androidthai.in.th.ungshop.utility;

import masterung.androidthai.in.th.ungshop.R;

public class MasterConstant {

    //    About Array
    private int[] iconInts = new int[]{
            R.drawable.ic_action_name,
            R.drawable.ic_action_user,
            R.drawable.ic_action_password,
            R.drawable.ic_action_exit};

    private String[] titleStrings = new String[]{
            "Title1",
            "Title2",
            "Title3",
            "Exit"};

//    About URL
    private String urlAddUserString = "http://androidthai.in.th/tod/addDataMaster.php";
    private String urlGetAllUserString = "http://androidthai.in.th/tod/getAllUserMaster.php";
    private String urlGetAllFoodString = "http://androidthai.in.th/tod/getAllFoodMaster.php";

    public int[] getIconInts() {
        return iconInts;
    }

    public String[] getTitleStrings() {
        return titleStrings;
    }

    public String getUrlGetAllFoodString() {
        return urlGetAllFoodString;
    }

    public String getUrlGetAllUserString() {
        return urlGetAllUserString;
    }

    public String getUrlAddUserString() {
        return urlAddUserString;
    }
}
