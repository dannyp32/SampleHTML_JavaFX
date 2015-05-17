package sample;
import javafx.geometry.*;
import javafx.geometry.Insets;
import netscape.javascript.JSObject;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.web.WebView;
import javafx.concurrent.Worker.State;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

import java.awt.*;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public WebView setupHomeWebView() {
        // Create a webview for each section
        WebView homeWebView = new WebView();
        String homeUrl = getClass().getResource("home.html").toExternalForm();

        homeWebView.getEngine().getLoadWorker().stateProperty().addListener((obs, oldValue, newValue) -> {
            System.out.println(newValue);
            if (newValue == State.SUCCEEDED) {
                System.out.println("finished loading");

                // This calls the javascript function initData in home.js - here we can pass in the initial data to the view
                homeWebView.getEngine().executeScript("home.initData('initial data to be loaded will be passed here...');");

                // This is creating a jsobject for my "home" object which can be found in home.js
                JSObject jsobj = (JSObject) homeWebView.getEngine().executeScript("home");

                // This adds HomeViewJava to the home object so that the HomeView java class's methods (in this case the jsHandler method in the HomeView class)
                // can be called by javascript. I can call home.HomeViewJava from my javascript now.
                // HomeViewJava is just the name I'm giving it. In home.html you can see how it is accessed home.HomeViewJava.jsHandler
                jsobj.setMember("HomeViewJava", new HomeView());
            }
        });

        homeWebView.getEngine().load(homeUrl);

        return homeWebView;
    }

    public WebView setupQuestionBankWebView() {
        WebView questionBankWebView= new WebView();
        String questionBankURL = getClass().getResource("question-bank.html").toExternalForm();
        questionBankWebView.getEngine().load(questionBankURL);

        return questionBankWebView;
    }

    public WebView setupCreateTestWebView() {
        WebView createTestWebView = new WebView();
        String createTestURL = getClass().getResource("create-test.html").toExternalForm();
        createTestWebView.getEngine().load(createTestURL);

        return createTestWebView;
    }

    public WebView setupTakeTestWebView() {
        WebView takeTestWebView = new WebView();
        String takeTestURL = getClass().getResource("take-test.html").toExternalForm();
        takeTestWebView.getEngine().load(takeTestURL);

        return takeTestWebView;
    }

    public WebView setupGradeTestWebView() {
        WebView gradeTestWebView = new WebView();
        String gradeTestURL = getClass().getResource("grade-test.html").toExternalForm();
        gradeTestWebView.getEngine().load(gradeTestURL);

        return gradeTestWebView;
    }

    public WebView setupAdminToolsWebView() {
        WebView adminToolsWebView= new WebView();
        String adminToolsURL = getClass().getResource("admin-tools.html").toExternalForm();
        adminToolsWebView.getEngine().load(adminToolsURL);

        return adminToolsWebView;
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Test Tool");
        Group root = new Group();
        Scene scene = new Scene(root, 768, 600, Color.web("#f1f6f8", 1.0));

        BorderPane mainPane = new BorderPane();
        TabPane tabPane = new TabPane();
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        //give all tabs relatively same width
        tabPane.setTabMinWidth(scene.getWidth()/8.0);

        //tabs resize with window resize
        scene.widthProperty().addListener(new ChangeListener<Number>() {
            @Override public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneWidth, Number newSceneWidth) {
                tabPane.setTabMinWidth(newSceneWidth.doubleValue() / 8.0);
            }
        });

        // Create Tabs
        Tab home = new Tab();
        Tab questionBank = new Tab();
        Tab createTest = new Tab();
        Tab takeTest = new Tab();
        Tab gradeTest = new Tab();
        Tab adminTools = new Tab();

        // Set Tab titles
        home.setText("Home");
        questionBank.setText("Question Bank");
        createTest.setText("Create Test");
        takeTest.setText("Take Test");
        gradeTest.setText("Grade Test");
        adminTools.setText("Administrative Tools");

        // Create a webview for each section
        WebView homeWebView = setupHomeWebView();
        WebView questionBankWebView = setupQuestionBankWebView();
        WebView createTestWebView = setupCreateTestWebView();
        WebView takeTestWebView = setupTakeTestWebView();
        WebView gradeTestWebView = setupGradeTestWebView();
        WebView adminToolsWebView = setupAdminToolsWebView();

        // Create a stackpane and display a webview inside its corresponding stackpane
        StackPane homeStack = new StackPane();
        homeStack.setAlignment(Pos.CENTER);
        homeStack.getChildren().add(homeWebView);

        StackPane questionBankStack = new StackPane();
        questionBankStack.setAlignment(Pos.CENTER);
        questionBankStack.getChildren().add(questionBankWebView);

        StackPane createTestStack = new StackPane();
        createTestStack.setAlignment(Pos.CENTER);
        createTestStack.getChildren().add(createTestWebView);

        StackPane takeTestStack = new StackPane();
        takeTestStack.setAlignment(Pos.CENTER);
        takeTestStack.getChildren().add(takeTestWebView);

        StackPane gradeTestStack = new StackPane();
        gradeTestStack.setAlignment(Pos.CENTER);
        gradeTestStack.getChildren().add(gradeTestWebView);

        StackPane adminToolsStack = new StackPane();
        adminToolsStack.setAlignment(Pos.CENTER);
        adminToolsStack.getChildren().add(adminToolsWebView);

        // Finally add each stackpanel to it's corresponding tab
        home.setContent(homeStack);
        questionBank.setContent(questionBankStack);
        createTest.setContent(createTestStack);
        takeTest.setContent(takeTestStack);
        gradeTest.setContent(gradeTestStack);
        adminTools.setContent(adminToolsStack);

        // Now that the tabs have been setup, add them to the tabpane
        tabPane.getTabs().add(home);
        tabPane.getTabs().add(questionBank);
        tabPane.getTabs().add(createTest);
        tabPane.getTabs().add(takeTest);
        tabPane.getTabs().add(gradeTest);
        tabPane.getTabs().add(adminTools);



        mainPane.setCenter(tabPane);
        mainPane.prefHeightProperty().bind(scene.heightProperty());
        mainPane.prefWidthProperty().bind(scene.widthProperty());

        root.getChildren().add(mainPane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

