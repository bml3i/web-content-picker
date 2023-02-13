package bi.leo.picker.common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegularExpTest {

    public static void main(String[] args) {

        String text1 = "播放量:1300.15万-";
        String text2 = "点击次数：1112k。-";

        Pattern pattern = Pattern.compile("(\\D*)(\\d+[\\.]?\\d*)(['亿'｜'万'|'K'|'k']*)(.*)");
        System.out.println("result1: " + pattern.matcher(text1).matches());
        System.out.println("result2: " + pattern.matcher(text2).matches());

        Matcher matcher = pattern.matcher(text1);

        if (matcher.matches()) {
            String part1 = matcher.group(1);
            String part2 = matcher.group(2);
            String part3 = matcher.group(3);
            System.out.println("part1:" + part1);
            System.out.println("part2:" + part2);
            System.out.println("part3:" + part3);
        }
    }

}
