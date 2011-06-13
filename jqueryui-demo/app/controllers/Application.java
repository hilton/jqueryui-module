package controllers;

import controllers.jqueryui.Demo;
import play.mvc.Controller;

public class Application extends Controller {

    public static void index() {
        Demo.index();
    }

}
