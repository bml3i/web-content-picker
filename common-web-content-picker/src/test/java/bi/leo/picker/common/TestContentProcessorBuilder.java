package bi.leo.picker.common;

import bi.leo.picker.common.text.Content;
import bi.leo.picker.common.text.ContentProcessorBuilder;
import bi.leo.picker.common.text.ContentProcessorOption;
import bi.leo.picker.common.text.processor.IContentProcessor;

import java.util.List;

public class TestContentProcessorBuilder {

    public static void main(String[] args) {

        String text1 = " 播放量: 300.1 万 -";
        String text2 = " 点击次数：1,112 。 -";

        List<IContentProcessor> contentProcessorList1 = ContentProcessorBuilder.build(new ContentProcessorOption(7));
        List<IContentProcessor> contentProcessorList2 = ContentProcessorBuilder.build(new ContentProcessorOption(5).setRemoveCommaEnabled(true));

        Content content1 = new Content(text1);

        for (IContentProcessor contentProcessor : contentProcessorList1) {
            content1 = contentProcessor.process(content1);
        }

        System.out.println("content1.result [" + content1.getResult() + "]");

        Content content2 = new Content(text2);

        for (IContentProcessor contentProcessor : contentProcessorList2) {
            content2 = contentProcessor.process(content2);
        }

        System.out.println("content2.result [" + content2.getResult() + "]");

    }

}
