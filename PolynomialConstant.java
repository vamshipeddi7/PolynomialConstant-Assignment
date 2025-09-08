import org.json.JSONObject;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.math.BigInteger;

public class PolynomialConstant {
    public static void main(String[] args) {
        try {
            String data = new String(Files.readAllBytes(Paths.get("testcase1.json")));
            JSONObject all = new JSONObject(data);
            JSONObject keys = all.getJSONObject("keys");
            int k = keys.getInt("k");
            BigInteger[] x = new BigInteger[k];
            BigInteger[] y = new BigInteger[k];
            for (int i = 1; i <= k; i++) {
                JSONObject point = all.getJSONObject(String.valueOf(i));
                int base = Integer.parseInt(point.getString("base"));
                String value = point.getString("value");
                x[i - 1] = BigInteger.valueOf(i);
                y[i - 1] = new BigInteger(value, base);
            }
            BigInteger c = lagrangeAtZero(x, y, k);
            System.out.println(c);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static BigInteger lagrangeAtZero(BigInteger[] x, BigInteger[] y, int k) {
        BigInteger result = BigInteger.ZERO;
        for (int i = 0; i < k; i++) {
            BigInteger term = y[i];
            for (int j = 0; j < k; j++) {
                if (i != j) {
                    BigInteger numerator = BigInteger.ZERO.subtract(x[j]);
                    BigInteger denominator = x[i].subtract(x[j]);
                    term = term.multiply(numerator).divide(denominator);
                }
            }
            result = result.add(term);
        }
        return result;
    }
}
