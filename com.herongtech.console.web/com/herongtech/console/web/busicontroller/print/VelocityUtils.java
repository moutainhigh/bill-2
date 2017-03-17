package com.herongtech.console.web.busicontroller.print;

import java.util.Map;


public class VelocityUtils  {
  private VelocityUtils() {
  }

  /**
   * generate template content.
   * 
   * @param content
   *          String
   * @param context
   *          Map
   * @throws Exception
   *           if there was an error.
   * @return new rendered content as String
   */
  public static String renderContent(String content, Map context)
      throws Exception {
    return VelocityAdapter.getInstance().renderContent(content, context);
  }

}