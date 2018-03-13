package mars;

public enum Around {
    R("R", -1), L("L", 1), NULL("NULL", 0);
    private int index;
    private String code;

    Around(String code, int index) {
        this.code = code;
        this.index = index;
    }

    public static Around codeOf(String code) {
        for (Around around : Around.values())
            if (around.code.equals(code))
                return around;
        return Around.NULL;
    }

    public int index() {
        return index;
    }
}
