package play.modules.basePlayProject;

import play.PlayPlugin;
import play.mvc.Router;

public class BasePlayProjectPlugin extends PlayPlugin
{
   @Override
   public void onRoutesLoaded()
   {
      Router.addRoute("GET", "/images/", "staticDir:public/images");
      Router.addRoute("GET", "/css/", "staticDir:public/css");
      Router.addRoute("GET", "/js/", "staticDir:public/js");
      Router.addRoute("GET", "/public/", "staticDir:public");
      Router.addRoute("GET", "/favicon.ico", "staticFile:public/images/favicon.ico");
   }
}