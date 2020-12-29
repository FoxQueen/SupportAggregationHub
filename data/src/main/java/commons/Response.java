package commons;

import java.util.Map;

public enum Response {
    OK(200, "OK"),
    INTERNAL_SERVER_ERROR(500, "Internal Server Error");

    private final Integer code;
    private final String status;

    Response(Integer code, String status) {
        this.code = code;
        this.status = status;
    }

    public static Map<String, Object> getResponse(Response response) {
        return Map.of(
                "code", response.code,
                "status", response.status
        );
    }
}
