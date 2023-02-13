package bi.leo.picker.common.text.processor;

import bi.leo.picker.common.text.Content;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReadNumberContentProcessor implements IContentProcessor{

    private static Pattern readNumberPattern = Pattern.compile("(\\D*)(\\d+[\\.]?\\d*)(['亿'｜'万'|'K'|'k']*)(.*)", Pattern.CASE_INSENSITIVE);

    @Override
    public Content process(Content content) {

        Matcher matcher = readNumberPattern.matcher(content.getResult());

        if (matcher.matches()) {
            String numberPart = matcher.group(2);
            String unitPart = matcher.group(3);

            content.setBigDecimalResult(new BigDecimal(numberPart));

            if ("亿".equals(unitPart)) {
                content.setConversionRate(100000000);
            } else if ("万".equals(unitPart)) {
                content.setConversionRate(10000);
            } else if ("K".equalsIgnoreCase(unitPart)) {
                content.setConversionRate(1000);
            }

            content.setResult(numberPart + unitPart);
        }

        return content;
    }
}
