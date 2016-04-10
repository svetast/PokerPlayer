import java.util.Random;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import PokerPlayer.PokerPlayer;


public class PokerTest {

	private static PokerPlayer player;
	private static SoftAssert softAssert = new SoftAssert();

	private static String closeAlertAndGetItsText(FirefoxDriver driver) {

		boolean acceptNextAlert = true;

		try {
			Alert alert = driver.switchTo().alert();
			String alertText = alert.getText();
			if (acceptNextAlert) {
				alert.accept();
			} else {
				alert.dismiss();
			}
			return alertText;
		} finally {
			acceptNextAlert = true;
		}
	}


	public static void login(String ID_USERNAME,
	                         String ID_PASSWORD,
	                         String ID_LOGIN,
	                         String td_username,
	                         String td_password,
	                         String td_pagetitle,
	                         FirefoxDriver driver) {
		// Login

		WebElement username = driver.findElement(By.id(ID_USERNAME));
		WebElement password = driver.findElement(By.id(ID_PASSWORD));
		WebElement login = driver.findElement(By.id(ID_LOGIN));
		username.sendKeys(td_username);
		password.sendKeys(td_password);
		login.click();
		softAssert.assertEquals(driver.getTitle(), td_pagetitle);
//  Assert.assertEquals(driver.getTitle(), td_pagetitle);
	}


	public static WebDriverWait insert(PokerPlayer player,
	                                   String ID_INSERT,
	                                   String ID_EDIT_USERNAME,
	                                   String ID_EDIT_EMAIL,
	                                   String ID_EDIT_PASSWORD,
	                                   String ID_EDIT_CONFIRMPASSWORD,
	                                   String ID_EDIT_SAVE,
	                                   String td_pagetitle,
	                                   String td_newpassword,
	                                   String id_firstName,
	                                   String id_lastName,
	                                   String id_city,
	                                   String id_address,
	                                   String id_phone,
	                                   FirefoxDriver driver) {
		WebElement insertlink = driver.findElement(By.xpath(ID_INSERT));
		insertlink.click();

		//Wait for new page to load
		WebDriverWait wait = new WebDriverWait(driver, 3);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(ID_EDIT_USERNAME)));

		// Fill in the form
		WebElement edit_username = driver.findElement(By.xpath(ID_EDIT_USERNAME));
		WebElement edit_email = driver.findElement(By.xpath(ID_EDIT_EMAIL));
		WebElement edit_password = driver.findElement(By.xpath(ID_EDIT_PASSWORD));
		WebElement edit_confirmpassword = driver.findElement(By.xpath(ID_EDIT_CONFIRMPASSWORD));
		WebElement edit_save = driver.findElement(By.name(ID_EDIT_SAVE));

		edit_username.sendKeys(player.getUsername());
		edit_email.sendKeys(player.getEmail());
		edit_password.sendKeys(td_newpassword);
		edit_confirmpassword.sendKeys(td_newpassword);

		WebElement editFirstname = driver.findElement(By.id(id_firstName));
		WebElement editLastname = driver.findElement(By.id(id_lastName));
		WebElement editCity = driver.findElement(By.id(id_city));
		WebElement editAddress = driver.findElement(By.id(id_address));
		WebElement editPhone = driver.findElement(By.id(id_phone));

		editFirstname.sendKeys(player.getFirstname());
		editLastname.sendKeys(player.getLastname());
		editCity.sendKeys(player.getCity());
		editAddress.sendKeys(player.getAddress());
		editPhone.sendKeys(player.getPhone());
		edit_save.click();

		softAssert.assertEquals(driver.getTitle(), td_pagetitle);
		// Assert.assertEquals(driver.getTitle(), td_pagetitle);
		return wait;
	}

	public static void deleteUser(String ID_TABLE_DELETE, FirefoxDriver driver) {
		driver.findElement(By.xpath(ID_TABLE_DELETE)).click();
		Assert.assertTrue(closeAlertAndGetItsText(driver).matches("^Do you really want to delete the record[\\s\\S]$"));
	}

	public static void compareUsers(PokerPlayer player,
	                                String ID_TABLE_USERNAME,
	                                String ID_EDIT_EMAIL,
	                                FirefoxDriver driver) {
		String actualUserName = driver.findElement(By.xpath(ID_TABLE_USERNAME)).getText();
		Assert.assertEquals(actualUserName, player.getUsername());

		driver.findElement(By.cssSelector("img[alt=\"Edit\"]")).click();
		softAssert.assertEquals("Players - Edit", driver.getTitle());
		// Assert.assertEquals("Players - Edit", driver.getTitle());

		String firstName = driver.findElement(By.id("ff14642ac1c__us_fname")).getAttribute("value");
		String lastName = driver.findElement(By.id("ff14642ac1c__us_lname")).getAttribute("value");
		String city = driver.findElement(By.id("ff14642ac1c__us_city")).getAttribute("value");
		String address = driver.findElement(By.id("ff14642ac1c__us_address")).getText();
		String phone = driver.findElement(By.id("ff14642ac1c__us_phone")).getAttribute("value");

		driver.findElement(By.name("button_save")).click();
		softAssert.assertEquals("Players", driver.getTitle());


		softAssert.assertEquals(firstName, player.getFirstname());
		softAssert.assertEquals(lastName, player.getLastname());
		softAssert.assertEquals(city, player.getCity());
		softAssert.assertEquals(address, player.getAddress());
		softAssert.assertEquals(phone, player.getPhone());
	}

	private static void search(PokerPlayer player,
	                           String ID_SEARCH_USERNAME,
	                           String ID_BTN_SEARCH,
	                           String ID_TABLE_USERNAME,
	                           FirefoxDriver driver,
	                           WebDriverWait wait) {
		//Search
		driver.findElement(By.xpath(ID_SEARCH_USERNAME)).clear();
		driver.findElement(By.xpath(ID_SEARCH_USERNAME)).sendKeys(player.getUsername());
		driver.findElement(By.name(ID_BTN_SEARCH)).click();

		//Wait for table reload
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(ID_TABLE_USERNAME)));
	}

	public static void editUser(FirefoxDriver driver, PokerPlayer user) {
		driver.findElement(By.cssSelector("img[alt=\"Edit\"]")).click();
		Assert.assertEquals("Players - Edit", driver.getTitle());
		driver.findElement(By.id("ff14642ac1c__us_fname")).clear();
		driver.findElement(By.id("ff14642ac1c__us_fname")).sendKeys(user.getFirstname());
		driver.findElement(By.id("ff14642ac1c__us_lname")).clear();
		driver.findElement(By.id("ff14642ac1c__us_lname")).sendKeys(user.getLastname());
		driver.findElement(By.id("ff14642ac1c__us_city")).clear();
		driver.findElement(By.id("ff14642ac1c__us_city")).sendKeys(user.getCity());
		driver.findElement(By.id("ff14642ac1c__us_address")).clear();
		driver.findElement(By.id("ff14642ac1c__us_address")).sendKeys(user.getAddress());
		driver.findElement(By.id("ff14642ac1c__us_phone")).clear();
		driver.findElement(By.id("ff14642ac1c__us_phone")).sendKeys(user.getPhone());
		driver.findElement(By.name("button_save")).click();
		Assert.assertEquals("Players", driver.getTitle());
	}

	public static void main(String[] args) throws InterruptedException {

		// Identifiers
		final String Host = "http://80.92.229.236:81/";
		final String ID_USERNAME = "username";
		final String ID_PASSWORD = "password";
		final String ID_LOGIN = "logIn";
		final String ID_INSERT = "//a[text()='Insert']";

		final String ID_EDIT_USERNAME = "//label[text()='Username']/../..//input";
		final String ID_EDIT_EMAIL = "//label[text()='E-mail']/../..//input";
		final String ID_EDIT_PASSWORD = "//label[text()='Password']/../..//input";
		final String ID_EDIT_CONFIRMPASSWORD = "//label[text()='Confirm Password']/../..//input";
		final String ID_EDIT_SAVE = "button_save";

		final String ID_SEARCH_USERNAME = "//div[@id='filter_panel_filter__login']//input";
		final String ID_BTN_SEARCH = "search";

		final String ID_TABLE_USERNAME = "//div[@class='datagrid_table_topcontainer']//tr[2]/td[3]/a";
		final String ID_TABLE_DELETE = "//div[@class='datagrid_table_topcontainer']//tr[2]/td[14]/a";

		//Test data
		final String td_username = "admin";
		final String td_password = "123";

		final String td_pagetitle = "Players";
		final String td_newusername = "nsemenov" + getRandomString();
		final String td_newpassword = "123456";
		final String td_newemail = td_newusername + "@gmail.com";

		String td_firstname_edit = "ff14642ac1c__us_fname";
		String td_lastname_edit = "ff14642ac1c__us_lname";
		String td_city_edit = "ff14642ac1c__us_city";
		String td_address_edit = "ff14642ac1c__us_address";
		String td_phone_edit = "ff14642ac1c__us_phone";

		// Create a new instance of the Firefox driver
		FirefoxDriver driver = new FirefoxDriver();
		// And now use this to visit Poker
		driver.get(Host);

		PokerPlayer user = new PokerPlayer(
				  "Ivan" + getRandomString(),
				  "ivan" + getRandomString() + "@gmail.com",
				  "Ivan",
				  "Ivanov",
				  "Ivanovo",
				  "Ivanova street, 11",
				  "1234567"
		);
		System.out.println(user);

		login(ID_USERNAME, ID_PASSWORD, ID_LOGIN, td_username, td_password, td_pagetitle, driver);
		//Insert
		WebDriverWait wait = insert(user,
				  ID_INSERT,
				  ID_EDIT_USERNAME,
				  ID_EDIT_EMAIL,
				  ID_EDIT_PASSWORD,
				  ID_EDIT_CONFIRMPASSWORD,
				  ID_EDIT_SAVE,
				  td_pagetitle,
				  td_newpassword,
				  td_firstname_edit,
				  td_lastname_edit,
				  td_city_edit,
				  td_address_edit,
				  td_phone_edit,
				  driver);

		search(user,
				  ID_SEARCH_USERNAME,
				  ID_BTN_SEARCH,
				  ID_TABLE_USERNAME,
				  driver,
				  wait);

		compareUsers(user,
				  ID_TABLE_USERNAME,
				  ID_EDIT_EMAIL,
				  driver);

		search(user,
				  ID_SEARCH_USERNAME,
				  ID_BTN_SEARCH,
				  ID_TABLE_USERNAME,
				  driver,
				  wait);

		user.setEmail("new@email.com");
		user.setFirstname("Petr");
		user.setLastname("Petrov");
		user.setCity("Odessa");
		user.setAddress("Deribasovkaya");
		user.setPhone("9874563");
		editUser(driver, user);

     


		search(user,
				  ID_SEARCH_USERNAME,
				  ID_BTN_SEARCH,
				  ID_TABLE_USERNAME,
				  driver,
				  wait);
		// Добавить метод поиска search для новых данных.
		//Assert if correct user found
		compareUsers(user,
				  ID_TABLE_USERNAME,
				  ID_EDIT_EMAIL,
				  driver);

		search(user,
				  ID_SEARCH_USERNAME,
				  ID_BTN_SEARCH,
				  ID_TABLE_USERNAME,
				  driver,
				  wait);

		//Delete user
		deleteUser(ID_TABLE_DELETE, driver);

		Thread.sleep(3000);
		//Close the browser
		//        driver.wait();
	}

	public static String getRandomString(){
		Random randomGenerator = new Random();
		Integer randInt = randomGenerator.nextInt(10000);
		return randInt.toString();

	}


}

