package com.ambitoscala.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.http.HttpStatus;

/**
 * <p>Generic class that represent all the API Responses.</p>
 *
 * @param <T> generic data
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BasicResponse<T> {

    @Builder.Default
    private String message = Strings.EMPTY;

    @Builder.Default
    private int statusCode = HttpStatus.OK.value();

    private T data;

    /**
     * <p>Response without data when there is no need to return data to the caller of the API.</p>
     *
     * @param message message to be returned
     * @return response ok
     */
    public static <T> BasicResponse<T> ok(final String message) {
        return BasicResponse.<T>builder()
                .message(message)
                .build();
    }

    /**
     * <p>Response with data to be returned to the caller of the API.</p>
     *
     * @param data object to be returned
     * @param <T>  generic type
     * @return the basic response
     */
    public static <T> BasicResponse<T> withData(final T data) {
        return BasicResponse.<T>builder()
                .data(data)
                .build();
    }

    /**
     * <p>Response with data and message to be returned to the caller of the API.</p>
     *
     * @param data    object to be returned
     * @param <T>     generic type
     * @param message message to be returned
     * @return the basic response
     */
    public static <T> BasicResponse<T> withDataAndMessage(final T data, final String message) {
        return BasicResponse.<T>builder()
                .message(message)
                .data(data)
                .build();
    }

    /**
     * <p>Failed Response to be returned to the call of the API.</p>
     *
     * @param statusCode status code of the error
     * @param message    msg of the error
     * @param <T>        generic type
     * @return the basic response
     */
    public static <T> BasicResponse<T> fail(final int statusCode, final String message, final T data) {
        return BasicResponse.<T>builder()
                .statusCode(statusCode)
                .message(message)
                .data(data)
                .build();
    }

    /**
     * <p>Failed Response to be returned to the call of the API.</p>
     *
     * @param statusCode status code of the error
     * @param message    msg of the error
     * @return the basic response
     */
    public static <T> BasicResponse<T> fail(final int statusCode, final String message) {
        return fail(statusCode, message, null);
    }

}
