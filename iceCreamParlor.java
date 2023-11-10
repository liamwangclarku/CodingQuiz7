import java.io.*;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

class Result {

    /*
     * Complete the 'icecreamParlor' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts following parameters:
     *  1. INTEGER m
     *  2. INTEGER_ARRAY arr
     */

    public static Integer icecreamParlor(int m, List<Integer> arr) {
        int row = arr.size() + 1;
        int col = m + 1;
        Boolean[][] CountTable = new Boolean[row][col];
        int result = 0;
        for (int i = 0; i < row; i++) {
            CountTable[i][0] = false;
        }

        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                CountTable[0][j] = false;
                int keep = arr.get(i - 1);

                if (keep == j) {
                    CountTable[i][j] = true;
                } else {
                    CountTable[i][j] = CountTable[i - 1][j];
                }
            }
        }

        for (int i = 1; i < row; i++) {
            int keep = arr.get(i - 1);
            if (m > keep && CountTable[i - 1][m - keep]) {
                result++;
            }
        }

        return result;
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int t = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, t).forEach(tItr -> {
            try {
                int m = Integer.parseInt(bufferedReader.readLine().trim());

                int n = Integer.parseInt(bufferedReader.readLine().trim());

                List<Integer> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                        .map(Integer::parseInt)
                        .collect(toList());

                Integer result = Result.icecreamParlor(m, arr);
                bufferedWriter.write(result.toString() + "\n");

                // bufferedWriter.write(
                //     result.stream()
                //         .map(Object::toString)
                //         .collect(joining(" "))
                //     + "\n"
                // );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
        bufferedWriter.close();
    }
}
