package lotto.model;

public enum LottoErrorType {
    NULL("[ERROR] 로또 번호가 비어있습니다."),
    INVALID_SIZE("[ERROR] 로또 번호는 %d개여야 합니다."),
    DUPLICATE("[ERROR] 로또 번호는 서로 중복될 수 없습니다."),
    INVALID_RANGE("[ERROR] 로또 번호는 %d~%d 범위여야 합니다.");

    private final String message;

    LottoErrorType(String message) {
        this.message = message;
    }

    public String getMessage(Object... args) {
        if (args.length == 0) {
            return message;
        }
        return message.formatted(args);
    }
}
