import com.microsoft.appcenter.appium.EnhancedAndroidDriver;
import com.microsoft.appcenter.appium.Factory;
import io.appium.java_client.MobileElement;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.junit.After;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestWatcher;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

public class baseTest {
  
  @Rule
  public TestWatcher watcher = Factory.createWatcher();
  
  private static EnhancedAndroidDriver<MobileElement> driver;
  
  public static EnhancedAndroidDriver<MobileElement> startApp() throws IOException {
    DesiredCapabilities capabilities = new DesiredCapabilities();
    capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "android");
    capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "11");
    capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel3aTest");
    capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 100);
    capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
    capabilities.setCapability(MobileCapabilityType.APP_PACKAGE, "ms.appcenter.sampleapp.android");
    capabilities.setCapability(MobileCapabilityType.APP_ACTIVITY, ".MainActivity");
    
    driver = Factory.createAndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    return driver;
  }
  
  @Test
  public void canStartAppInTest() throws IOException {
    driver = startApp();
    driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
    
    // Verify homepage
    MobileElement elem = driver.findElementByXPath("//android.widget.TextView[@text='Welcome']");
    Assert.assertEquals(elem.getText(), "Welcome");
    
    // Click on build & verify build page
    MobileElement build = driver.findElementByXPath("//android.widget.TextView[@text='Build']");
    build.click();
    
    MobileElement buildtext = driver.findElementByAccessibilityId("ms.appcenter.sampleapp.android:id/buildTextView");
    buildtext.click();
    
    Assert.assertTrue(buildtext.getText().contains("Create an installable app package automatically"));
  }
  
  @After
  public void after() {
    if(driver != null) {
      driver.lable("Stopping App");
      driver.quit();
    }
  }
  
  public static void startEmulator() throws IOException {
    Runtime.getRuntime().exec(System.getProperty("user.dir") + "\\src\\main\\resources\\startEmulator.bat");
    try {
      Thread.sleep(7000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
