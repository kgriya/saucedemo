package com.saucedemo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Swaglabs {
	private WebDriver driver;

	public Swaglabs() {

	}

	public static void main(String[] args) {

		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");

		Swaglabs sw = new Swaglabs();
		sw.openChrome();
		sw.loginToHome();
		sw.filterByPrice();
		sw.addFirstItem();
		sw.addRandomItem();
		sw.addRandomItem();
		sw.openCart();
		sw.quitChrome();

	}

	public void openChrome() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.saucedemo.com/");
	}

	private void loginToHome() {
		WebElement username = driver.findElement(By.id("user-name"));
		username.sendKeys("standard_user");

		WebElement password = driver.findElement(By.id("password"));
		password.sendKeys("secret_sauce");

		sleep(1000);

		WebElement login = driver.findElement(By.id("login-button"));
		login.click();

		sleep(1000);

		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='peek']")));

	}

	private void filterByPrice() {
		Select se = new Select(driver.findElement(By.tagName("select")));
		se.selectByValue("lohi");
		sleep(1000);
	}

	private void addFirstItem() {
		WebElement container = driver.findElement(By.id("inventory_container"));
		List<WebElement> listofItems  = container.findElements(By.xpath("//div[@class='inventory_list']/div"));
		WebElement firstElement = listofItems.get(0);
		WebElement button = firstElement.findElement(By.xpath("//button[@class='btn btn_primary btn_small btn_inventory']"));
		button.click();
		sleep(3000);
	}
	
	private void addRandomItem() {
		WebElement container = driver.findElement(By.id("inventory_container"));
		List<WebElement> listofItems  = container.findElements(By.xpath("//div[@class='inventory_list']/div/"));
		System.out.println(listofItems.size());
		
		Random random = new Random();
		int position = random.nextInt(listofItems.size());
		System.out.println(position);
		
		WebElement element = listofItems.get(position);
		WebElement button = element.findElement(By.xpath("//button[@class='btn btn_primary btn_small btn_inventory']"));
		button.click();
		sleep(3000);
	}

	private void openCart() {
		WebElement ItemsInCart = driver.findElement(By.xpath("//a[@class='shopping_cart_link']"));
		ItemsInCart.click();

		sleep(1000);

		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("checkout")));
	}

	private void quitChrome() {
		sleep(2000);
		driver.close();
		driver.quit();
	}

	private void sleep(long time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
