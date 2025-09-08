import java.nio.file.*;
import java.math.BigInteger;
import java.util.*;
import java.util.regex.*;

public class PolynomialConstant {
    public static void main(String[] args) {
        try {
            // List all JSON files in current folder
            Files.list(Paths.get("."))
                 .filter(p -> p.toString().endsWith(".json"))
                 .forEach(PolynomialConstant::processFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void processFile(Path filePath) {
        try {
            String data = new String(Files.readAllBytes(filePath));

            // Extract k
            Matcher keyMatcher = Pattern.compile("\"k\"\\s*:\\s*(\\d+)").matcher(data);
            int k = 0;
            if (keyMatcher.find()) {
                k = Integer.parseInt(keyMatcher.group(1));
            } else {
                throw new RuntimeException("Could not find 'k' in " + filePath);
            }

            // Extract first k points
            BigInteger[] x = new BigInteger[k];
            BigInteger[] y = new BigInteger[k];

            Matcher entryMatcher = Pattern.compile("\"(\\d+)\"\\s*:\\s*\\{\\s*\"base\"\\s*:\\s*\"(\\d+)\",\\s*\"value\"\\s*:\\s*\"([^\"]+)\"\\s*\\}").matcher(data);

            int count = 0;
            while (entryMatcher.find() && count < k) {
                int base = Integer.parseInt(entryMatcher.group(2));
                String value = entryMatcher.group(3);

                x[count] = BigInteger.valueOf(count + 1);
                y[count] = new BigInteger(value, base);
                count++;
            }

            if (count < k) {
                throw new RuntimeException("Not enough points in " + filePath + " to satisfy k=" + k);
            }

            BigInteger c = lagrangeAtZero(x, y, k);
            System.out.println(filePath.getFileName() + " -> Constant c = " + c);

        } catch (Exception e) {
            System.err.println("Error processing " + filePath + ": " + e.getMessage());
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
