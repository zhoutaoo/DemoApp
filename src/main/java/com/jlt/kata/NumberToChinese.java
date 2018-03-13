package kata;

public class NumberToChinese {

    private final String[] chineses = new String[]{
            "零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖",
    };
    private final String[] units = new String[]{
            "", "拾", "佰", "仟", "万", "拾", "佰", "仟", "亿", "拾", "佰", "仟", "万"
    };
    private final String[][] unit = new String[][]{
            {"", "拾", "佰", "仟"},
            {"万", "拾", "佰", "仟"},
            {"亿", "拾", "佰", "仟", "万"}
    };

    public String translate(double number) {
        return translate(number, units.length - 1);
    }

    private String translate(double number, int a) {
        double numberBase = Math.pow(10, a);

        if (number < 10) return chineses[(int) number];

        String str = chineses[(int) (number / numberBase)] + units[a];

        if (number % numberBase == 0) return str;

        if (number < numberBase) return translate(number, a - 1);

        String lessTen = "";
        if (number % numberBase < numberBase / 10)
            lessTen = chineses[0];

//        units[gen - (gen % 4)] +

        return str + lessTen + translate(number % numberBase, a - 1);
    }
}