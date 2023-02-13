package bi.leo.picker.common.text;

public class ContentProcessorOption {

    public static final int OPTION_REMOVE_SPACE             = 0B0000000001;
    public static final int OPTION_REMOVE_COMMA             = 0B0000000010;
    public static final int OPTION_TRIM                     = 0B0000000100;
    public static final int OPTION_READ_NUMBER              = 0B0000001000;


    public ContentProcessorOption(int optionDigit) {

        this.optionDigit = optionDigit;

        if ((optionDigit & OPTION_REMOVE_SPACE) > 0) {
            this.removeSpaceEnabled = true;
        }

        if ((optionDigit & OPTION_REMOVE_COMMA) > 0) {
            this.removeCommaEnabled = true;
        }

        if ((optionDigit & OPTION_TRIM) > 0) {
            this.trimEnabled = true;
        }

        if ((optionDigit & OPTION_READ_NUMBER) > 0) {
            this.readNumberEnabled = true;
        }

    }

    private int optionDigit;

    private boolean removeSpaceEnabled;

    private boolean removeCommaEnabled;

    private boolean trimEnabled;

    private boolean readNumberEnabled;

    public boolean isRemoveSpaceEnabled() {
        return removeSpaceEnabled;
    }

    public ContentProcessorOption setRemoveSpaceEnabled(boolean removeSpaceEnabled) {
        this.removeSpaceEnabled = removeSpaceEnabled;

        this.calculateOptionDigit();
        return this;
    }

    public boolean isTrimEnabled() {
        return trimEnabled;
    }

    public ContentProcessorOption setTrimEnabled(boolean trimEnabled) {
        this.trimEnabled = trimEnabled;

        this.calculateOptionDigit();
        return this;
    }

    public boolean isRemoveCommaEnabled() {
        return removeCommaEnabled;
    }

    public ContentProcessorOption setRemoveCommaEnabled(boolean removeCommaEnabled) {
        this.removeCommaEnabled = removeCommaEnabled;

        this.calculateOptionDigit();
        return this;
    }

    public boolean isReadNumberEnabled() {
        return readNumberEnabled;
    }

    public ContentProcessorOption setReadNumberEnabled(boolean readNumberEnabled) {
        this.readNumberEnabled = readNumberEnabled;

        this.calculateOptionDigit();
        return this;
    }

    public int getOptionDigit() {
        return optionDigit;
    }

    // re-calculate optionDigit value
    private void calculateOptionDigit() {
        // reset to zero
        optionDigit = 0;

        if (this.isRemoveSpaceEnabled()) {
            optionDigit = optionDigit | OPTION_REMOVE_SPACE;
        }

        if (this.isRemoveCommaEnabled()) {
            optionDigit = optionDigit | OPTION_REMOVE_COMMA;
        }

        if (this.isTrimEnabled()) {
            optionDigit = optionDigit | OPTION_TRIM;
        }

        if (this.isReadNumberEnabled()) {
            optionDigit = optionDigit | OPTION_READ_NUMBER;
        }

    }
}
