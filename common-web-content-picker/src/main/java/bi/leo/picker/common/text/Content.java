package bi.leo.picker.common.text;

public class Content {

    public Content(String source) {
        this.source = source;

        if (this.result == null) {
            this.result = source;
        }
    }

    private String source;

    private String result;

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
}
