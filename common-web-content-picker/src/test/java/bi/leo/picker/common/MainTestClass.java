package bi.leo.picker.common;

import bi.leo.picker.common.text.Content;
import bi.leo.picker.common.text.ContentProcessorBuilder;
import bi.leo.picker.common.text.processor.IContentProcessor;
import bi.leo.picker.common.text.processor.TrimContentProcessor;

import java.util.ArrayList;
import java.util.List;

public class MainTestClass {

    public static void main(String[] args) {

        String text1 = " 播放量: 300.1 万 -";
        String text2 = " 点击次数：1,112 。 -";

        ContentProcessorBuilder builder = new ContentProcessorBuilder();

        List<IContentProcessor> contentProcessorList = new ArrayList<>();
        contentProcessorList.add(new TrimContentProcessor());


        Content content1 = new Content(text1);

        for (IContentProcessor contentProcessor : contentProcessorList) {
            content1 = contentProcessor.process(content1);
        }

        System.out.println("content1.result [" + content1.getResult() + "]");

        Content content2 = new Content(text2);

        for (IContentProcessor contentProcessor : contentProcessorList) {
            content2 = contentProcessor.process(content2);
        }

        System.out.println("content2.result [" + content2.getResult() + "]");



    }
}
