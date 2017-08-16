import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.remote.DesiredCapabilities
import org.openqa.selenium.chrome.ChromeOptions

// system property, the full path of chromedriver
System.setProperty('webdriver.chrome.driver', "/Users/owner99/Documents/share/chromedriver")

// for the purpose of setting random port, this property was setted at BaseGebSpec.groovy
// baseUrl = "http://localhost:8080"

driver = {
  ChromeOptions options = new ChromeOptions()
  options.addArguments("--headless", "--no-sandbox", "--disable-gpu")

  DesiredCapabilities capabilities = DesiredCapabilities.chrome()
  capabilities.setCapability(ChromeOptions.CAPABILITY, options)

  new ChromeDriver(capabilities)
}

// report output folder
reportsDir = "target/geb-reports"

/* only report if test failed */
reportOnTestFailureOnly = true