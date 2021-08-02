package com.Nike.NikeScrape;


import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class NikeScrape {

	private static WebDriver driver= null;
	ArrayList<POJO> employees = new ArrayList<>();
	Gson gson = new GsonBuilder()
			.setPrettyPrinting()
			.create(); 

	public void setup() {
		System.setProperty("webdriver.chrome.driver","D:\\chromedriver.exe");;
		driver= new ChromeDriver();
		driver.get("https://www.nike.com/in/");
		driver.manage().window().maximize();
	}

	public void info( ) throws InterruptedException, IOException {		 
		driver.findElement(By.id("VisualSearchInput")).sendKeys("phantom");
		driver.findElement(By.xpath("//*[@class=\"pre-search-input-icon\"]")).click();
		Thread.sleep(1000);
		int size = driver.findElements(By.xpath("//*[@class=\"product-card__link-overlay\"]")).size();

		for (int i=1; i<=size; i++ ) {	
			int finalShoePrice=0;
			String aLink= "//*[@class=\"product-card css-1lukt7x css-z5nr6i css-11ziap1 css-14d76vy css-dpr2cn product-grid__card \" and @data-product-position=\"";
			String bLink= "\"]";
			String detail= "//*[@class=\"css-1fxh5tw product-card__hero-image\"]";
			String Link= aLink + i + bLink + detail;

			WebElement element = driver.findElement(By.xpath(Link));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
			Thread.sleep(500); 

			String shoePriceLink= aLink + i + bLink + "//*[@class=\"product-price__wrapper css-cl9118\"]";
			String shoePrice= driver.findElement(By.xpath(shoePriceLink)).getText().replace(",", ""); 
			String shoeSubtitleLink = aLink + i + bLink + "//*[@class=\"product-card__subtitle\"]";
			String shoeSubtitle= driver.findElement(By.xpath(shoeSubtitleLink)).getText();

			if (shoePrice.length()>6) {
				shoePrice=shoePrice.substring(1,6).trim(); 
			}
			else {
				shoePrice=shoePrice.substring(1).trim();
			}

			finalShoePrice = Integer.parseInt(shoePrice);
			POJO pojo = new POJO();

			pojo.setShoeName(element.getAttribute("alt"));
			pojo.setShoeType(shoeSubtitle);
			pojo.setShoePrice(finalShoePrice);
			pojo.setLinkName( element.getAttribute("src"));
			employees.add(pojo);
			System.out.println("");
		}
	}

	void teardown () {	
		driver.close();
	}

	public void loopAndWriteFile() throws IOException {
		FileWriter file = new FileWriter("D:\\NikeDetail.text");
		file.write("[");
		int flag =1;
		for(POJO p: employees ) {
			file.write(gson.toJson(p));
			if(flag<employees.size()) {
				file.write(",");
				flag++;
			}		 
		}
		file.write("]");
		file.close();
	}
}
