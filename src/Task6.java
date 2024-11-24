import java.util.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;


public class Task6 {
    public static void main(String[] args) {
        System.out.println(hiddenAnagram("My world evolves in a beautiful space called Tesh.", "sworn love lived"));
        System.out.println(hiddenAnagram("An old west action hero actor", "Clint Eastwood"));
//2
        System.out.println(stripUrlParams("https://edabit.com?a=1&b=2&a=2")); //https://edabit.com?a=2&b=2
        System.out.println(stripUrlParams("https://edabit.com?a=1&b=2&a=2", "a")); //https://edabit.com?b=2
//3
        System.out.println(nicoCipher("myworldevolvesinhers", "tesh")); //"yowmledrovlvsnieesrh"
        System.out.println(nicoCipher("andiloveherso", "tesha")); //"lnidaevheo s or"
//4
        System.out.println(Arrays.toString(twoProduct(new int[]{1, 2, 3, 9, 4, 5, 15, 3}, 45))); // [9, 5]
        System.out.println(Arrays.toString(twoProduct(new int[]{1, 2, 3, 9, 4, 15, 3, 5}, 45))); // [3, 15]
//5
        System.out.println(Arrays.toString(isExact(6)));
        System.out.println(Arrays.toString(isExact(24)));
//6
        System.out.println(fractions("0.(6)"));
//7
        System.out.println(string("33314444"));
        System.out.println(string(""));
//8
        System.out.println("Формула верна? " + formula("2*8=16=10+6=32/2"));
//9
        System.out.println(isValid("aabbcd"));
        System.out.println(isValid("aabbccddeefghi"));
//10
        System.out.println(palindromedescendant(11211230));
    }
        public static String hiddenAnagram (String text, String temp) {

            StringBuilder ntemp = new StringBuilder();
            temp = temp.toLowerCase();
            for (int i = 0; i < temp.length(); i++) {
                char c = temp.charAt(i);
                if ('a' <= c && c <= 'z') {
                    ntemp.append(c);
                }
            }
            temp = ntemp.toString();

            int len = temp.length();
            int n = text.length();

            StringBuilder ntext = new StringBuilder();
            text = text.toLowerCase();
            for (int i = 0; i < n; i++) {
                char c = text.charAt(i);
                if ('a' <= c && c <= 'z') {
                    ntext.append(c);
                }
            }
            text = ntext.toString();
            n = text.length();

            int[] tempCount = new int[26];
            for (int i = 0; i < len; i++) {
                int ind = temp.charAt(i) - 'a';
                if (0 <= ind && ind < 26) {
                    tempCount[ind] += 1;
                }
            }
            String ans = "notfound";
            for (int l = 0; l + len <= n; l++) {
                int r = l + len;
                int[] rayCount = new int[26];
                for (int j = l; j < r; j++) {
                    int ind = text.charAt(j) - 'a';
                    rayCount[ind] += 1;
                }

                boolean isAns = true;
                for (int i = 0; i < 26; i++) {
                    if (tempCount[i] != rayCount[i]) {
                        isAns = false;
                    }
                }
                if (isAns) {
                    ans = text.substring(l, r);
                    break;
                }
            }

            return ans;
        }

//2.

    public static String stripUrlParams(String urlString, String... paramsToRemove) {
    try {
        URL url = new URL(urlString);
        String protocol = url.getProtocol();
        String host = url.getHost();
        String path = url.getPath();
        String query = url.getQuery();

        Map<String, String> queryParams = parseQueryParams(query);
        Set<String> paramsToRemoveSet = paramsToRemove == null ? Set.of() : Set.of(paramsToRemove);
        queryParams.entrySet().removeIf(entry -> paramsToRemoveSet.contains(entry.getKey()));
        Map<String, String> uniqueQueryParams = new HashMap<>();
        for (Map.Entry<String, String> entry : queryParams.entrySet()) {
            uniqueQueryParams.put(entry.getKey(), entry.getValue());
        }


        String newQueryString = buildQueryString(uniqueQueryParams);
        return protocol + "://" + host + path + (newQueryString.isEmpty() ? "" : "?" + newQueryString);

    } catch (MalformedURLException e) {
        return urlString; // Return original URL if malformed
    }
}

    private static Map<String, String> parseQueryParams(String query) {
        Map<String, String> queryParams = new HashMap<>();
        if (query != null) {
            String[] pairs = query.split("&");
            for (String pair : pairs) {
                String[] parts = pair.split("=");
                if (parts.length == 2) {
                    try {
                        queryParams.put(parts[0], URLDecoder.decode(parts[1], "UTF-8"));
                    } catch (Exception e) {
                        //Handle decoding errors as needed
                    }
                }
            }
        }
        return queryParams;
    }

    private static String buildQueryString(Map<String, String> queryParams) {
        StringBuilder queryString = new StringBuilder();
        for (Map.Entry<String, String> entry : queryParams.entrySet()) {
            if (queryString.length() > 0) {
                queryString.append("&");
            }
            queryString.append(entry.getKey()).append("=").append(entry.getValue());
        }
        return queryString.toString();
    }

//3
    public static String nicoCipher(String message,String key) {
            if (message == null || key == null || message.isEmpty()) {
                return "";
            }

            StringBuilder encodedMessage = new StringBuilder();
            int keyIndex = 0;

            for (int i = 0; i < message.length(); i++) {
                if (keyIndex >= key.length()) {
                    keyIndex = 0;
                }
                int keyCharIndex = key.charAt(keyIndex) - 'a';
                int messageCharIndex = message.charAt(i) - 'a';

                int newIndex = (messageCharIndex + keyCharIndex) % 26;
                encodedMessage.append((char) ('a' + newIndex));
                keyIndex++;
            }
            return encodedMessage.toString();
        }


//4
    public static int[] twoProduct(int[] arr, int n) {
        if (arr == null || arr.length < 2) {
            return new int[0];
        }

        int minDiff = Integer.MAX_VALUE;
        int[] result = new int[0];

        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] * arr[j] == n) {
                    int diff = j - i;
                    if (diff < minDiff) {
                        minDiff = diff;
                        result = new int[]{arr[i], arr[j]};
                    }
                }
            }
        }
        return result;
    }

//5
    public static int[] isExact (int n) {
        return isExactly(n, 1, 1);
    }

    public static int[] isExactly(int n, int p, int z) {
        if (p >= n) {
            int[] ans = new int[0];
            if (p == n) {
                ans = new int[] {p, z-1};
            }
            return ans;
        }
        else {
            return isExactly(n, p*z, z+1);
        }
    }

//6

    public static long gcd (long a, long b) {
        if (b == 0) {
            return a;
        }
        else {
            return gcd(b, a % b);
        }
    }

    public static String fractions (String s) {
        String[] x = s.split("\\(");
        String[] xx = x[0].split("\\.");
        String x0 = xx[0];
        String x1;
        if (xx.length == 1) {
            x1 = "0";
        }
        else {
            x1 = xx[1];
        }
        String x2 = x[1].substring(0, x[1].length()-1);
        StringBuilder val1 = new StringBuilder();
        for (int i = 0; i < x2.length(); i++) {
            val1.append("9");
        }
        for (int i = 0; i < x[0].length()-2; i++) {
            val1.append("0");
        }

        long val1Int = Long.parseLong(val1.toString());
        long val2Int = (long) Math.pow(10, x1.length());
        long A = Long.parseLong(x0) * val1Int * val2Int + Long.parseLong(x1) * val1Int + Long.parseLong(x2) * val2Int;
        long B = val1Int * val2Int;

        long g = gcd(A, B);
        A /= g;
        B /= g;

        return Long.toString(A) + "/" + Long.toString(B);
    }

//7
    public static String string (String s) {
        StringBuilder cur, ans = new StringBuilder();
        String pi = "314159265358979";
        char[] pis = pi.toCharArray();
        int[] pisInts = new int[pis.length];
        for (int i = 0; i < pis.length; i++) {
            pisInts[i] = pis[i] - '0';
        }

        int cnt = 0;
        for (int p : pisInts) {
            for (int i = 0; i < p; i++) {
                if (cnt >= s.length() && !s.isEmpty()) {
                    ans.append(s.charAt(s.length()-1));
                }
                else if(!s.isEmpty()) {
                    ans.append(s.charAt(cnt));
                }
                cnt++;
            }

            if (cnt >= s.length()) {
                break;
            }
            ans.append(" ");
        }
        return ans.toString();
    }

//8
    public static boolean formula(String formula) {
        String [] part = formula.split("=");
        String [] rez = new String[part.length];
        for(int i = 0; i < part.length; i++) {
            rez[i] = part[i];
            if (part[i].contains("*")) {
                int a = Integer.parseInt(part[i].substring(0, part[i].indexOf("*")));
                int b = Integer.parseInt(part[i].substring(part[i].indexOf("*")+1));
                rez[i] = Integer.toString(a*b);
            }
            if (part[i].contains("+")) {
                int a = Integer.parseInt(part[i].substring(0, part[i].indexOf("+")));
                int b = Integer.parseInt(part[i].substring(part[i].indexOf("+")+1));
                rez[i] = Integer.toString(a+b);
            }
            if (part[i].contains("/")) {
                int a = Integer.parseInt(part[i].substring(0, part[i].indexOf("/")));
                int b = Integer.parseInt(part[i].substring(part[i].indexOf("/")+1));
                rez[i] = Integer.toString(a/b);
            }
            if (part[i].contains("-")) {
                int a = Integer.parseInt(part[i].substring(0, part[i].indexOf("-")));
                int b = Integer.parseInt(part[i].substring(part[i].indexOf("-")+1));
                rez[i] = Integer.toString(a-b);
            }
        }
        boolean x = true;
        for (int i = 0; i < rez.length-1; i++) {
            for (int j = i+1; j < rez.length ;j++) {
                if (!rez[i].equals(rez[j]))
                    x = false;
            }
        }
        return x;
    }

//9
    public static String isValid(String s) {
        char[] cs = s.toCharArray();
        Arrays.sort(cs);
        boolean used = false;
        int a1 = 0, a2 = 0, n = 0;
        int cnt1 = 0, cnt2 = 0, curCnt = 1;
        for (int i = 1; i < cs.length; i++) {
            if (cs[i] != cs[i-1]) {
                if (a1 == 0) {
                    a1 = curCnt;
                    cnt1++;
                }
                else if (curCnt != a1 && a2 == 0) {
                    a2 = curCnt;
                    cnt2++;
                }
                else {
                    if (curCnt == a1) cnt1++;
                    else if (curCnt == a2) cnt2++;
                    else return "NO";
                }
                n++;
                curCnt = 1;
            }
            else {
                curCnt++;
            }
        }
        n++;
        if (curCnt == a1) cnt1++;
        else if (curCnt == a2) cnt2++;
        else return "NO";
        if ((cnt1 == n-1 || cnt2 == n-1) && Math.abs(a1-a2) == 1) {
            return "YES";
        }
        else if (a2 == 0) {
            return "YES";
        }
        else {
            return "NO";
        }
    }

//10
    public static boolean palindromedescendant(int num) {
        int l = Integer.toString(num).length();
        int[] pair = new int[l];
        int i = 0;
        while (num != 0) {
            pair[i] = num % 10;
            num = num / 10;
            i += 1;
        }
        boolean x = true;
        for (int j = 0; j < l/2; j++) {
            if (pair[j] != pair[l-1-j]) {
                x = false;
            }
        }
        boolean y = true;
        for (int j = 0; j < pair.length/2; j++){
            if (pair[j]+pair[j+1] != pair[l-1-j]+pair[l-2-j])
                y = false;
            j += 1;
        }
        return x||y;
    }
}

