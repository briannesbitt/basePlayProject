package helpers.basePlayProject;

import play.Logger;

public class ProjectLogger
{
   public static void info(String message, Object... args)
   {
      Logger.info("[" + Globals.getApplicationName() + "] " + message, args);
   }
   public static void info(Throwable e, String message, Object... args)
   {
      Logger.info(e, "[" + Globals.getApplicationName() + "] " + message, args);
   }
   public static void fatal(String message, Object... args)
   {
      Logger.fatal("[" + Globals.getApplicationName() + "] " + message, args);
   }
   public static void fatal(Throwable e, String message, Object... args)
   {
      Logger.fatal(e, "[" + Globals.getApplicationName() + "] " + message, args);
   }
   public static void debug(String message, Object... args)
   {
      Logger.debug("[" + Globals.getApplicationName() + "] " + message, args);
   }
   public static void debug(Throwable e, String message, Object... args)
   {
      Logger.debug(e, "[" + Globals.getApplicationName() + "] " + message, args);
   }
   public static void trace(String message, Object... args)
   {
      Logger.trace("[" + Globals.getApplicationName() + "] " + message, args);
   }
   public static void error(String message, Object... args)
   {
      Logger.error("[" + Globals.getApplicationName() + "] " + message, args);
   }
   public static void error(Throwable e, String message, Object... args)
   {
      Logger.error(e, "[" + Globals.getApplicationName() + "] " + message, args);
   }
}