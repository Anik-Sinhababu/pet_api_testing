package apiEndpoint;

public class Routes {

    // User model

    public static String base_url = "https://petstore.swagger.io/v2";

    public static String login_url = base_url + "/user/login";
    public static String post_url = base_url + "/user";
    public static String get_url = base_url + "/user/{username}";
    public static String update_url = base_url + "/user/{username}";
    public static String delete_url = base_url + "/user/{username}";
    public static String logout_url = base_url + "/user/logout";

    //Store Model
    public static String get_Store_details = base_url + "/store/inventory";
    public static String post_order = base_url + "/store/order";

    //Pet Model

    public static String post_pet_utl = base_url + "/pet/{petId}/uploadImage";
    public static String get_pet_url = base_url + "/pet/{petId}";

}
