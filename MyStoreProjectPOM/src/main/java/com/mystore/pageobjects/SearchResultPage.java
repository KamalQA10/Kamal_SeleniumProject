
package com.mystore.pageobjects;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.mystore.actiondriver.Action;
import com.mystore.base.BaseClass;

public class SearchResultPage extends BaseClass {	
	
		
	@FindBy(xpath="//*[@class='product_img_link']/img")
	 WebElement productResult;
	
	@FindBy(xpath="//*[@class='product_img_link']/img")
	 List<WebElement> productResults;
	
	@FindBy(xpath="//*[@class='product-image-container']")
	WebElement searchResult;

		public SearchResultPage() {
			PageFactory.initElements(getDriver(), this);
		}

		public boolean isProductAvailable() throws Throwable {
			return Action.isDisplayed(getDriver(), productResult);
		}
		
		public AddToCartPage clickOnProduct() throws Throwable {
			Action.click(getDriver(), productResult);
			return new AddToCartPage();	
		}
		
		public int CountTotalSearchProducts() {
			return Action.getElementCount(productResults);
		}
		


}
