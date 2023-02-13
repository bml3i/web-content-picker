package bi.leo.picker.service;

import bi.leo.picker.common.text.Content;
import bi.leo.picker.common.text.ContentProcessorAgent;
import bi.leo.picker.common.text.ContentProcessorOption;
import bi.leo.picker.exception.WebDriverPoolException;
import bi.leo.picker.model.ExtractRequest;
import bi.leo.picker.model.ExtractResult;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ExtractServiceImpl implements ExtractService{

    @Autowired
    WebDriverPoolManager webDriverPoolManager;

    @Override
    public ExtractResult extractFieldValue(ExtractRequest extractRequest) throws WebDriverPoolException{
        WebDriver webDriver = null;
        try {
            webDriver = webDriverPoolManager.getWebDriver();

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
        } catch (WebDriverPoolException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if(webDriver != null) {
                webDriverPoolManager.releaseWebDriver(webDriver);
            }
        }
    }
}
