package bi.leo.picker.common.text;

import bi.leo.picker.common.text.processor.IContentProcessor;

import java.math.BigDecimal;
import java.util.List;

public class ContentProcessorAgent {

    private static Content getContentResult(Content content, List<IContentProcessor> contentProcessorList) {
        for (IContentProcessor contentProcessor : contentProcessorList) {
            content = contentProcessor.process(content);
        }

        return content;
    }

    public static Content getContentResult(String sourceText, ContentProcessorOption contentProcessorOption) {
        Content content = new Content(sourceText);

        if (contentProcessorOption == null) {
            contentProcessorOption = new ContentProcessorOption(0);
        }

        return getContentResult(content, ContentProcessorBuilder.build(contentProcessorOption));
    }

    public static String getStringContentResult(String sourceText, ContentProcessorOption contentProcessorOption) {
        Content content = getContentResult(sourceText, contentProcessorOption);
        return content.getResult();
    }

    public static BigDecimal getDecimalContentResult(String sourceText, ContentProcessorOption contentProcessorOption) {
        Content content = getContentResult(sourceText, contentProcessorOption);
        return content.getConvertedBigDecimalResult();
    }
}
