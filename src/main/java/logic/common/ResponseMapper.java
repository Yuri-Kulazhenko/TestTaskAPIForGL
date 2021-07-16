package logic.common;

import com.google.gson.Gson;

public class ResponseMapper {

    public  static <T> T parseResponse(String responseBody, Class<T> classOfT) {
        return new Gson().fromJson(responseBody, classOfT);
    }
}
