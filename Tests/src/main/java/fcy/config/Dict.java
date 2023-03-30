package fcy.config;

public enum Dict {
    DEFAULT(0), START(1), DOWN(2), UNKNOWN(3);

    private int code;

    Dict(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
