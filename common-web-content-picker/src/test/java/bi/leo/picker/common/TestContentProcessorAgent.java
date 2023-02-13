package bi.leo.picker.common;

import bi.leo.picker.common.text.ContentProcessorAgent;
import bi.leo.picker.common.text.ContentProcessorOption;

public class TestContentProcessorAgent {

    public static void main(String[] args) {

        String text1 = " 播放量: 1,300.1 万  -";
        String text2 = "点击次数：1,112 。  - ";

        ContentProcessorOption contentProcessorOption = new ContentProcessorOption(7);

        String result1 = ContentProcessorAgent.getContentResult(text1, contentProcessorOption);
        String result2 = ContentProcessorAgent.getContentResult(text2, contentProcessorOption);

        System.out.println("result1: [" + result1 + "]");
        System.out.println("result2: [" + result2 + "]");


    }

}
