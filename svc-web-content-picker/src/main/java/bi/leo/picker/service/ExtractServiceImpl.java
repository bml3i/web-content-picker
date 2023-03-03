package bi.leo.picker.service;

import bi.leo.picker.common.text.Content;
import bi.leo.picker.common.text.ContentProcessorAgent;
import bi.leo.picker.common.text.ContentProcessorOption;
import bi.leo.picker.exception.CustomWebDriverException;
import bi.leo.picker.model.ExtractRequest;
import bi.leo.picker.model.ExtractResult;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExtractServiceImpl implements ExtractService{

    @Autowired
    WebDriverManager webDriverManager;

    @Override
    public ExtractResult extractFieldValue(ExtractRequest extractRequest) throws CustomWebDriverException {
        WebDriver webDriver = null;
        try {
            webDriver = webDriverManager.getWebDriver();

            webDriver.get(extractRequest.getExtractUrl());

            WebElement webElement = webDriver.findElement(By.xpath(extractRequest.getExtractExpression()));

            String contentText = webElement.getText();

            ContentProcessorOption option = new ContentProcessorOption(extractRequest.getExtractOption());
            Content contentResult = ContentProcessorAgent.getContentResult(contentText, option);

            ExtractResult extractResult = new ExtractResult();

            if (option.isReadNumberEnabled() && contentResult.getBigDecimalResult() != null) {
                extractResult.setValue(String.valueOf(contentResult.getConvertedBigDecimalResult()));
            } else {
                extractResult.setValue(contentResult.getResult());
            }

            return extractResult;
        } catch (CustomWebDriverException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if(webDriver != null) {
                webDriver.quit();
            }
        }
    }
}
