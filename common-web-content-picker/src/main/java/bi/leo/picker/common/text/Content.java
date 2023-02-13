package bi.leo.picker.common.text;

import java.math.BigDecimal;

public class Content {

    public Content(String source) {
        this.source = source;

        if (this.result == null) {
            this.result = source;
        }
    }

    private String source;

    private String result;

    private BigDecimal bigDecimalResult;

    private Integer conversionRate = 1;

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public BigDecimal getBigDecimalResult() {
        return bigDecimalResult;
    }

    public void setBigDecimalResult(BigDecimal bigDecimalResult) {
        this.bigDecimalResult = bigDecimalResult;
    }

    public Integer getConversionRate() {
        return conversionRate;
    }

    public void setConversionRate(Integer conversionRate) {
        this.conversionRate = conversionRate;
    }

    public BigDecimal getConvertedBigDecimalResult() {
        if (this.getBigDecimalResult() != null) {
            return this.getBigDecimalResult().multiply(new BigDecimal(this.getConversionRate()));
        } else {
            return null;
        }
    }
}
