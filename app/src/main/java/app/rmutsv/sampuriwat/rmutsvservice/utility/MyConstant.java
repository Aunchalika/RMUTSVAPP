package app.rmutsv.sampuriwat.rmutsvservice.utility;

/**
 * Created by samPuriwat on 8/11/2560.
 */

public class MyConstant {
    private String urlPostData = "http://androidthai.in.th/rmuts/addDataMaster.php";

    private String urlGetAllUser = "http://androidthai.in.th/rmuts/getAllDataMaster.php";

    private String urlDeleteData = "http://androidthai.in.th/rmuts/deleteDataMaster.php";

    public String getUrlEditData() {
        return urlEditData;
    }

    private String urlEditData = "http://androidthai.in.th/rmuts/editDataMaster.php";

    public String getUrlDeleteData() {
        return urlDeleteData;
    }

    public String getUrlGetAllUser() {
        return urlGetAllUser;
    }

    public String getUrlPostData() {
        return urlPostData;
    }
}// main class
