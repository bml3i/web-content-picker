package bi.leo.picker.common;

import bi.leo.picker.common.text.ContentProcessorAgent;
import bi.leo.picker.common.text.ContentProcessorOption;

public class TestContentProcessorAgent {

    public static void main(String[] args) {

        String text1 = " 播放量: 1,300.15 万  -";
        String text2 = " 点击次数：1,112 k 。  - ";

        ContentProcessorOption contentProcessorOption = new ContentProcessorOption(15);

        String result1 = ContentProcessorAgent.getContentResult(text1, contentProcessorOption).getResult();
        String result2 = ContentProcessorAgent.getContentResult(text2, contentProcessorOption).getResult();

        System.out.println("result1: [" + result1 + "]");
        System.out.println("result2: [" + result2 + "]");


    }

}
