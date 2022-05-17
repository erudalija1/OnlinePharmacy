package ba.unsa.etf.onlinepharmacy.Response;

import lombok.Data;

@Data
public class Response {
    private String message;
    private Integer statusCode;

    public Response(String message) {
        this(message, 200);
    }

    public Response(String message, Integer statusCode) {
        this.message = message;
        this.statusCode = statusCode;
    }
}