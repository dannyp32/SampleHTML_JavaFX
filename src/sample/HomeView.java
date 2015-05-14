package sample;

/**
 * Created by Daniel on 5/13/2015.
 */
public class HomeView {
    public String jsHandler(String name) {
        System.out.println("this is getting called by Javascript.");

        return name + "Java method jsHandler in class HomeView got the message";
    }
}
