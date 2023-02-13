package bi.leo.picker.common.text.processor;

import bi.leo.picker.common.text.Content;

public class TrimContentProcessor implements IContentProcessor{

    @Override
    public Content process(Content content) {
        content.setResult(content.getResult().trim());
        return content;
    }
}
