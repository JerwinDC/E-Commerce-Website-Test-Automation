package WebTesting;

import org.testng.annotations.Test;

import BaseTest.TestBase;

public class loginTest extends TestBase {

	@Test
	public void login() {
		app.openBrowser("browserName");
		app.openUrl("url");
		app.enterKeyOnElement("username", "user_id");
		app.enterKeyOnElement("password", "pass_id");
		app.clickButton("logiBtn_id");
		app.isLoginSuccessful();
		
	}
}
