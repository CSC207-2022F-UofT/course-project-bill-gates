package billgates;

public class MainTest {

    public static void main(String[] args) {
        System.out.println(Double.MAX_VALUE);
        System.out.println(Double.MIN_VALUE);
        double a = 999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999.22;
        System.out.println(a);
        String s = """
                awdawdia
                awjdiawd
                awjidawd
                awjdiawd
                
                """;

        String format = String.format("""
                                
                %f
                                
                """, a);
        System.out.println(format);
    }

}
