package bi.leo.picker.common.text;

import bi.leo.picker.common.text.processor.*;

import java.util.ArrayList;
import java.util.List;

public class ContentProcessorBuilder {

    public static List<IContentProcessor> build(ContentProcessorOption option) {

        List<IContentProcessor> contentProcessorList = new ArrayList<>();

        if (option == null || option.getOptionDigit() <= 0) {
            contentProcessorList.add(new DefaultContentProcessor());
        }

        if (option.isRemoveSpaceEnabled()) {
            contentProcessorList.add(new RemoveSpacesContentProcessor());
        }

        if (option.isRemoveCommaEnabled()) {
            contentProcessorList.add(new RemoveCommaContentProcessor());
        }

        if (option.isTrimEnabled()) {
            contentProcessorList.add(new TrimContentProcessor());
        }

        if (option.isReadNumberEnabled()) {
            contentProcessorList.add(new ReadNumberContentProcessor());
        }

        return contentProcessorList;
    }

}
