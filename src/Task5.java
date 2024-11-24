import java.util.*;

public class Task5 {
    public static void main(String[] args){
        System.out.println(sameLetterPattern("ABAB", "CDCD"));
        System.out.println(sameLetterPattern("FFFF", "ABCD"));
        System.out.println(memeSum(26,39));
        System.out.println(memeSum(122,81));
        System.out.println(digitsCount(121317));
        System.out.println(digitsCount(544));
        System.out.println(digitsCount(12345));
        String[] s1 = {"cat", "create", "sat"};
        String[] s2 = {"trance", "recant"};
        String[] s3 = {"dote", "dotes", "toes", "set", "dot", "dots", "sted"};
        System.out.println(totalPoints(s1, "caster"));
        System.out.println(totalPoints(s2, "recant"));
        System.out.println(totalPoints(s3, "tossed"));
        System.out.println(longestRun(new int[]{1, 2, 3, 5, 6, 7, 8, 9})); // 5
        System.out.println(longestRun(new int[]{1, 2, 3, 10, 11, 12})); // 3
        String[] w1 = {"95%", "83%", "90%", "87%", "88%", "93%"};
        String[] w2 = {"10%"};
        String[] w3 = {"53%", "79%"};
        System.out.println(takeDownAverage(w1));
        System.out.println(takeDownAverage(w2));
        System.out.println(takeDownAverage(w3));
        System.out.println(canMove("Rook", "A8", "H8")); //true
        System.out.println(canMove("Bishop", "A7", "G1")); //true
        System.out.println(maxPossible(9328, 456)); // 9658
        System.out.println(maxPossible(523, 76));  // 763
        System.out.println(timeDifference("Los Angeles", "April 1, 2011 23:23", "Canberra"));
        System.out.println(timeDifference("London", "July 31, 1983 23:01", "Rome") );
        System.out.println(timeDifference("New York", "December 31, 1970 13:40", "Beijing"));
        System.out.println(isNew(3));
        System.out.println(isNew(30));
    }

    public static boolean sameLetterPattern(String a, String b) {
        int x = b.charAt(0) - a.charAt(0);
        for (int i = 1; i < a.length(); i++) {
            if ((b.charAt(i) - a.charAt(i)) != x) {
                return false;
            }
        }
        return true;
    }

    public static int memeSum(int x, int y){
        int len = 1;
        if (Integer.toString(x).length() > Integer.toString(y).length())
            len = Integer.toString(x).length();
        else
            len = Integer.toString(y).length();
        int[][] findSum = new int[3][len];
        int i = 0;
        while (x != 0){
            findSum[0][i] = x % 10;
            x = x /10;
            i++;
        }
        i = 0;
        while (y != 0){
            findSum[1][i] = y % 10;
            y = y /10;
            i++;
        }
        for (i = 0; i < len; i++) {
            findSum[2][i] = findSum[0][i] + findSum[1][i];
        }
        String Ans = "";
        for (i = len -  1; i >= 0; i--){
            Ans += findSum[2][i];
        }
        return Integer.parseInt(Ans);
    }

    public static int digitsCount (long n) {
        if (n < 10) {
            return 1;
        }
        else {
            return 1 + digitsCount(n/10);
        }
    }

    public static int totalPoints (String[] words, String word) {
        int[] wordCount = new int[26];
        for (int i = 0; i < 26; i++) {
            wordCount[i] = 0;
        }
        for (int i = 0; i < word.length(); i++) {
            int ind = word.charAt(i) - 'a';
            wordCount[ind] += 1;
        }
        int answer = 0;
        for (String s : words) {
            int[] nCount = new int[26];
            for (int i = 0; i < 26; i++) {
                nCount[i] = 0;
            }
            for (int i = 0; i < s.length(); i++) {
                int ind = s.charAt(i) - 'a';
                nCount[ind] += 1;
            }
            int count = 0;
            boolean is_include_in_answer = true;
            for (int i = 0; i < 26; i++) {
                if (nCount[i] > wordCount[i]) {
                    is_include_in_answer = false;
                }
                count += nCount[i];
            }
            if (is_include_in_answer) {
                answer += (count - 2);
                if (count >= 6) {
                    answer += 50;
                }
            }
        }
        return answer;
    }
    public static int longestRun(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int maxLength = 1;
        int currentLength = 1;

        for (int i = 1; i < nums.length; i++) {
            if (Math.abs(nums[i] - nums[i - 1]) == 1) {
                currentLength++;
            } else {
                maxLength = Math.max(maxLength, currentLength);
                currentLength = 1;
            }
        }

        return Math.max(maxLength, currentLength);
    }

    public static String takeDownAverage (String[] arr) {
        int n = arr.length;
        double sum = 0;
        for (int i = 0; i < n; i++) {
            arr[i] = arr[i].replaceAll("%", "");
            sum += Integer.parseInt(arr[i]);
        }
        int ans = (int)Math.round(sum / n - 5 * (n+1)) ;

        return Integer.toString(ans) + "%";
    }

    public static boolean canMove(String figure, String startPos, String endPos) {
        String[] start = startPos.split("");
        String[] end = endPos.split("");
        String startLetter = start[0];
        String endLetter = end[0];
        char startChar = startLetter.charAt(0);
        char endChar = endLetter.charAt(0);
        int startNum = Integer.parseInt(start[1]);
        int endNum = Integer.parseInt(end[1]);
        boolean x = false;

        if (startNum > 0 && endNum < 9 && startLetter.matches("[A,B,C,D,E,F,G,H]") && endLetter.matches("[A,B,C,D,E,F,G,H]")) {

            if (figure.equals("Pawn")) {
                if (startLetter.equals(endLetter) && (startNum + 1 == endNum || startNum - 1 == endNum))
                    x = true;
            }

            if (figure.equals("Horse")) {
                if (((startNum + 2 == endNum || startNum - 2 == endNum) && (startChar - endChar == 1 || startChar - endChar == -1)) ||
                        ((startNum + 1 == endNum || startNum - 1 == endNum) && (startChar - endChar == 2 || startChar - endChar == -2)))
                    x = true;
            }

            if (figure.equals("Bishop")) {
                if (Math.abs(startNum - endNum) == Math.abs(startChar - endChar))
                    x = true;
            }

            if (figure.equals("Rook")) {
                if (startChar == endChar ^ startNum == endNum)
                    x = true;
            }

            if (figure.equals("Queen")) {
                if (startChar == endChar ^ startNum == endNum ^ Math.abs(startNum - endNum) == Math.abs(startChar - endChar))
                    x = true;
            }

            if (figure.equals("King")) {
                if (Math.abs(startNum - endNum) == 1 || Math.abs(startChar - endChar) == 1) {
                    x = true;
                }
            }
        }
        return x;
    }

        public static long maxPossible(long n, long m) {
            String nStr = String.valueOf(n);
            String mStr = String.valueOf(m);
            char[] nArr = nStr.toCharArray();
            char[] mArr = mStr.toCharArray();
            Arrays.sort(mArr); // Sort m's digits in ascending order

            int mIndex = mArr.length - 1; // Pointer for m's digits

            for (int i = 0; i < nArr.length; i++) {
                if (mIndex >= 0 && nArr[i] < mArr[mIndex]) {
                    nArr[i] = mArr[mIndex]; //Replace if digit in m is bigger
                    mIndex--;
                }
            }

            return Long.parseLong(new String(nArr));
        }


    public static String timeDifference (String cityA, String timeA, String cityB) {

        HashMap<String, int[] > cityDifference = new HashMap<>();
        HashMap<String, Integer> months = new HashMap<>();
        HashMap<String, Integer> daysInMonths = new HashMap<>();

        String[] monthsArray = {"January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"};

        cityDifference.put("Los Angeles", new int[] {-8, 0});
        cityDifference.put("New York", new int[] {-5, 0});
        cityDifference.put("Caracas", new int[] {-4, 30});
        cityDifference.put("Buenos Aires", new int[] {-3, 0});
        cityDifference.put("London", new int[] {0, 0});
        cityDifference.put("Rome", new int[] {1, 0});
        cityDifference.put("Moscow", new int[] {3, 0});
        cityDifference.put("Tehran", new int[] {3, 30});
        cityDifference.put("New Delhi", new int[] {5, 30});
        cityDifference.put("Beijing", new int[] {8, 0});
        cityDifference.put("Canberra", new int[] {10, 0});

        daysInMonths.put("January", 31);
        daysInMonths.put("February", 28);
        daysInMonths.put("March", 31);
        daysInMonths.put("April", 30);
        daysInMonths.put("May", 31);
        daysInMonths.put("June", 30);
        daysInMonths.put("July", 31);
        daysInMonths.put("August", 31);
        daysInMonths.put("September", 30);
        daysInMonths.put("October", 31);
        daysInMonths.put("November", 30);
        daysInMonths.put("December", 31);

        months.put("January", 1);
        months.put("February", 2);
        months.put("March", 3);
        months.put("April", 4);
        months.put("May", 5);
        months.put("June", 6);
        months.put("July", 7);
        months.put("August", 8);
        months.put("September", 9);
        months.put("October", 20);
        months.put("November", 11);
        months.put("December", 12);
        String[] time = timeA.split(" ");
        time[1] = time[1].replaceAll(",", "");
        int[] timeInCityA = cityDifference.get(cityA);
        int[] timeInCityB = cityDifference.get(cityB);
        int[] timeDifference = new int[] {timeInCityB[0] - timeInCityA[0],
                timeInCityB[1] - timeInCityA[1]};
        String[] currentTimeInString = time[3].split(":");
        int[] currentTime = {Integer.parseInt(currentTimeInString[0]),
                Integer.parseInt(currentTimeInString[1])};
        int[] newTime = new int[] {currentTime[0] + timeDifference[0],
                currentTime[1] + timeDifference[1]};

        int month = months.get(time[0]);
        int date = Integer.parseInt(time[1]);
        int year = Integer.parseInt(time[2]);

        if (newTime[1] < 0) {
            newTime[1] = 60 + newTime[1];
            newTime[0] -= 1;
            if (newTime[0] < 0) {
                newTime[0] = 24 - newTime[0];
                date -= 1;
                if (date == 0) {
                    month -= 1;
                    if (month >= 1) {
                        date = daysInMonths.get(monthsArray[month - 1]);
                    }
                    else {
                        year -= 1;
                        month = 12;
                        date = 31;
                    }
                }
            }
        }

        if (newTime[1] >= 60) {
            newTime[1] = newTime[1] % 60;
            newTime[0] += 1;
        }

        if (newTime[0] >= 24) {
            newTime[0] -= 24;
            date += 1;
            if (date > daysInMonths.get(monthsArray[month - 1])) {
                month += 1;
                date = 1;
                if (month > 11) {
                    month = 1;
                    year += 1;
                }
            }
        }

        StringBuilder ans = new StringBuilder();
        ans.append(Integer.toString(year));
        ans.append("-");
        ans.append(Integer.toString(month));
        ans.append("-");
        ans.append(Integer.toString(date));
        ans.append(" ");
        if (newTime[0] <= 9) {
            ans.append("0");
        }
        ans.append(newTime[0]);
        ans.append(":");
        if (newTime[1] <= 9) {
            ans.append("0");
        }
        ans.append(newTime[1]);

        return ans.toString();
    }

    public static boolean isNew (int a) {
        char[] digs = ("" + a).toCharArray();
        for (int i = 1; i < digs.length; i++) {
            if (digs[i] < digs[i-1] && digs[i] != '0') {
                return false;
            }
        }
        return true;
    }
}
