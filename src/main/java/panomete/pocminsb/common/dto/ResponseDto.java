package panomete.pocminsb.common.dto;

public record ResponseDto<T>(
        T data,
        String statusCode,
        String statusMessage
) {
}
