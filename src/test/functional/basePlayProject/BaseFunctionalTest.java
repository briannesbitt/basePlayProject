package test.functional.basePlayProject;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import helpers.basePlayProject.Globals;
import net.sourceforge.jwebunit.htmlunit.HtmlUnitTestingEngineImpl;
import net.sourceforge.jwebunit.junit.WebTester;
import org.junit.Before;
import play.mvc.Router;
import play.test.FunctionalTest;

import java.util.Map;

public abstract class BaseFunctionalTest extends FunctionalTest
{
   protected WebTester wt;

   protected static WebTester createWebTester(BrowserVersion browserVersion)
   {
      WebTester wt = new WebTester();
      wt.getTestContext().setUserAgent(browserVersion.getUserAgent());
      if (wt.getTestingEngine() instanceof HtmlUnitTestingEngineImpl)
      {
         ((HtmlUnitTestingEngineImpl) wt.getTestingEngine()).setDefaultBrowserVersion(browserVersion);
      }
      wt.setBaseUrl(Globals.getUrlBaseWithSlash());
      wt.getTestingEngine().setIgnoreFailingStatusCodes(false);
      return wt;
   }
   public BrowserVersion getDefaultBrowser()
   {
      return BrowserVersion.INTERNET_EXPLORER_8;
   }
   @Before
   public void beforeCreateWebTester()
   {
      wt = createWebTester(getDefaultBrowser());
   }
   protected String getRoute(String action)
   {
      return Router.reverse(action).url;
   }
   protected String getRoute(String action, Map<String,Object> args)
   {
      return Router.reverse(action, args).url;
   }
}