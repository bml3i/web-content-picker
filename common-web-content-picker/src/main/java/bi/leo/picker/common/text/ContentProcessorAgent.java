package bi.leo.picker.common.text;

import bi.leo.picker.common.text.processor.IContentProcessor;

import java.util.List;

public class ContentProcessorAgent {

    private static String getContentResult(Content content, List<IContentProcessor> contentProcessorList) {

        for (IContentProcessor contentProcessor : contentProcessorList) {
            content = contentProcessor.process(content);
        }

        return content.getResult();
    }

    public static String getContentResult(String sourceText, ContentProcessorOption contentProcessorOption) {
        Content content = new Content(sourceText);

        if (contentProcessorOption == null) {
            contentProcessorOption = new ContentProcessorOption(0);
        }

        return getContentResult(content, ContentProcessorBuilder.build(contentProcessorOption));
    }

}
