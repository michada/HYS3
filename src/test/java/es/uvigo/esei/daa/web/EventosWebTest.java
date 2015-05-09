package es.uvigo.esei.daa.web;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import es.uvigo.esei.daa.TestUtils;

public class EventosWebTest {
	private static final int DEFAULT_WAIT_TIME = 1;
	private WebDriver driver;
	private StringBuffer verificationErrors = new StringBuffer();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		TestUtils.createFakeContext();
		TestUtils.clearTestDatabase();
	}

	@Before
	public void setUp() throws Exception {
		TestUtils.initTestDatabase();

		final String baseUrl = "http://localhost:9080/DAAExample/";

		driver = new FirefoxDriver();
		driver.get(baseUrl);
		driver.manage().addCookie(new Cookie("token", "bXJqYXRvOm1yamF0bw=="));

		driver.manage().timeouts()
				.implicitlyWait(DEFAULT_WAIT_TIME, TimeUnit.SECONDS);

		driver.get(baseUrl + "index.html");
		driver.findElement(By.id("eventos-list"));
	}

	@After
	public void tearDown() throws Exception {
		TestUtils.clearTestDatabase();

		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

	@Test
	public void testListFiltradoProvincia() throws Exception {
		verifyXpathCount("//tr[@localidad='" + "Pontevedra" + "']", 2);
		WebElement element = driver.findElement(By.name("tituloEvento"));
		assertEquals("Evento numero 1", element.getTagName());
	}

	@Test
	public void testListFiltradoCategoria() throws Exception {
		verifyXpathCount("//tr[@categoria='" + "Libros" + "']", 1);
		WebElement element = driver.findElement(By.name("categoriaEvento"));
		assertEquals("Libros", element.getTagName());
	}

	@Test
	public void testGetEvento() throws Exception {
		driver.findElement(
				By.xpath("//tr[@localidad='" + "Pontevedra"
						+ "']//a[text()='consultar']")).click();
		verifyXpathCount("//tr[@eventos='" + "1" + "']", 1);

		assertEquals("Evento numero 1", driver.findElement(
				By.xpath("//tr[@eventos='" + "1" + "']/td[@class='titulo']"))
				.getText());
		assertEquals("Libros", driver.findElement(
				By.xpath("//tr[@id='" + "1" + "']/td[@class='categoria']"))
				.getText());
		assertEquals(
				"descripcion larga1",
				driver.findElement(
						By.xpath("//tr[@id='" + 1
								+ "']/td[@class='descripcionDetallada']"))
						.getText());
	}
	
	@Test
	public void testBuscar() throws Exception {
		verifyXpathCount("//tr[@cadenaBusqueda='" + "Evento" + "']", 3);
		verifyXpathCount("//tr[@cadenaBusqueda='" + "Evento numero 2" + "']", 1);
		
		WebElement element = driver.findElement(By.name("titulo"));
		assertEquals("Evento numero 2", element.getTagName());
	}

	private void verifyXpathCount(String xpath, int count) {
		try {
			assertEquals(count, driver.findElements(By.xpath(xpath)).size());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
	}
}
