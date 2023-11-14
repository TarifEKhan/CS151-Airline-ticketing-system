package com.example.uidesign;
// all the necessary imports to run the code.
import java.util.Random;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.util.Duration;
import javafx.animation.Interpolator;


public class UiDesign extends Application {
    private final ObservableMap<String, customerInfo> userData = FXCollections.observableHashMap(); // creating a hashmap as the data structure to store user info.
    // the key,value storage makes this an appealing choice

    public static void main(String[] args) {  // what launches the program
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Welcome to Flight Plan");

        // Creating a label for the banner text
        Label bannerLabel = new Label("Airline options from all over the world.");
        bannerLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: gold; -fx-background-color: black");

        Button signupButton = new Button("Sign Up"); // creating signup and login buttons and specifying their sizes.
        Button loginButton = new Button("Login");
        signupButton.setStyle("-fx-background-color: black; -fx-text-fill: white;");
        loginButton.setStyle("-fx-background-color: black; -fx-text-fill: white;");

        signupButton.setPrefSize(150, 50);
        loginButton.setPrefSize(150, 50);

        VBox buttonLayout = new VBox(10); // chose Vbox button layout
        buttonLayout.getChildren().addAll(bannerLabel, signupButton, loginButton);
        buttonLayout.setStyle("-fx-background-image: url('https://airhex.com/images/photos/airline-tail-logos.png');" +
                "-fx-background-size: cover;"); // Image covers the entire area
        buttonLayout.setAlignment(Pos.CENTER);
        buttonLayout.setPadding(new javafx.geometry.Insets(20));

        // Simple animation for buttons and label

        ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(1), signupButton);
        scaleTransition.setToX(1.5);
        scaleTransition.setToY(1.5);
        scaleTransition.setAutoReverse(true);
        scaleTransition.setCycleCount(Timeline.INDEFINITE);
        scaleTransition.setInterpolator(Interpolator.EASE_BOTH);
        scaleTransition.play();

        ScaleTransition scaleTransition2 = new ScaleTransition(Duration.seconds(1), loginButton);
        scaleTransition2.setToX(1.5);
        scaleTransition2.setToY(1.5);
        scaleTransition2.setAutoReverse(true);
        scaleTransition2.setCycleCount(Timeline.INDEFINITE);
        scaleTransition2.setInterpolator(Interpolator.EASE_BOTH);
        scaleTransition2.play();

        signupButton.setOnAction(e -> showSignupForm(primaryStage)); // what happens when they are clicked
        loginButton.setOnAction(e -> showLoginPage(primaryStage));
        Scene welcomeScene = new Scene(buttonLayout, 800, 600); // Increased scene size for better visibility
        primaryStage.setScene(welcomeScene);
        primaryStage.show(); // to show the stuff on the scene
    }


    private void showSignupForm(Stage primaryStage) {
        Stage signupStage = new Stage();
        signupStage.setTitle("Welcome to the signup page for the flight of your lifetime!"); // title for the signUp pop-up.

        GridPane signupPane = new GridPane();  // Chose gridPane because it stores children in vertical and horizontal way
        signupPane.setHgap(5);
        signupPane.setVgap(5);
        signupPane.setPadding(new javafx.geometry.Insets(10, 10, 10, 10));
        signupPane.setStyle("-fx-background-image: url('https://wallpapers.com/images/featured/plane-desktop-background-dnc62a0eoyniwrfl.jpg');"); // Image from the web used as the background
        signupPane.setAlignment(Pos.CENTER);

        // necessary labels that prompt to the user as well as the labels we will be using to display error messages.
        Label firstNameLabel = new Label("First Name:");
        Label lastNameLabel = new Label("Last Name:");
        Label emailLabel = new Label("E-mail:");
        Label passwordLabel = new Label("Password:");
        Label errorLabel = new Label("");
        Label successfulLabel = new Label("");
        Label invalidPasswordLabel = new Label("");

        // shortcut way to apply colors to our text, tutors told me it is more efficient than .setStyle.
        applyLabelStyles(firstNameLabel, "black");
        applyLabelStyles(lastNameLabel, "black");
        applyLabelStyles(emailLabel, "black");
        applyLabelStyles(passwordLabel, "black");
        applyLabelStyles(errorLabel, "red");
        applyLabelStyles(successfulLabel, "black");
        applyLabelStyles(invalidPasswordLabel, "black");

        // Telling javaFx when to display a certain label
        errorLabel.setVisible(false);
        successfulLabel.setVisible(false);
        invalidPasswordLabel.setVisible(false);

        // text fields, where the user will type their details for each label.
        TextField firstNameField = new TextField();
        TextField lastNameField = new TextField();
        TextField emailField = new TextField();
        PasswordField passwordField = new PasswordField();
        Button submitButton = new Button("Submit"); // submit button

        submitButton.setOnAction(event -> { // What happens when the submit button is created
            errorLabel.setVisible(false);
            invalidPasswordLabel.setVisible(false);
            successfulLabel.setVisible(false);

            // the trim() helps us get rid of any unwanted spaces that the user might have left behind.
            String firstName = firstNameField.getText().trim();
            String lastName = lastNameField.getText().trim();
            String email = emailField.getText().trim();
            String password = passwordField.getText().trim();

            // try validates and sets the firstName, lastName, Email and Password and then assigns them to the values in the map. Also prints the success message.
            try {
                if (validateInput(firstName) && validateInput(lastName) && validateEmailInput(email)) {
                    customerInfo user = new customerInfo();
                    user.setFirstName(firstName);
                    user.setLastName(lastName);
                    user.setEmail(email);
                    validatePassword(password);
                    user.setPassword(password);
                    user.setUsername(generateUsername(firstName, lastName));
                    userData.put(user.getUsername(), user);

                    String successMessage = "You have fulfilled all requirements:\n" +
                            "Your username is: " + user.getUsername() +
                            "\nPlease use this username along with your password to login.";
                    successfulLabel.setVisible(true);
                    successfulLabel.setText(successMessage);
                } else { // if not validated, then it will print our errorMessage.
                    String errorMessage = "Please check your input fields:\n" +
                            "Your First name should have at least one lowercase and one uppercase letter\n" +
                            "Your last name should have at least one lowercase and one uppercase letter\n" +
                            "Your email is required and should have the @ symbol and a domain.com\n" +
                            "PASSWORD REQUIREMENTS:\n" +
                            "Your password must be 8 characters long\n" +
                            "Your password must have one uppercase and one lowercase character\n" +
                            "Your password must have one special character and one number character\n";

                    errorLabel.setVisible(true);
                    errorLabel.setText(errorMessage);
                }
            } catch (PasswordException e) { // Catch uses our parent exception class PasswordException that is extended by user-defined exceptions.
                System.out.println(e.getMessage()); //get message is included in the throwable class.
                invalidPasswordLabel.setText(e.getMessage());
                invalidPasswordLabel.setVisible(true);
            } catch (UpperCaseCharacterMissingException e) {
                throw new RuntimeException(e);
            } catch (SpecialCharacterMissingException e) {
                throw new RuntimeException(e);
            }
        });
// The signupPane is used as our layout container and here, we are just adding and sizing our necessary labels to it.
        signupPane.add(firstNameLabel, 0, 0);
        signupPane.add(firstNameField, 1, 0);
        signupPane.add(lastNameLabel, 0, 1);
        signupPane.add(lastNameField, 1, 1);
        signupPane.add(emailLabel, 0, 2);
        signupPane.add(emailField, 1, 2);
        signupPane.add(passwordLabel, 0, 3);
        signupPane.add(passwordField, 1, 3);
        signupPane.add(submitButton, 0, 4, 2, 1);
        signupPane.add(errorLabel, 0, 5, 2, 1);
        signupPane.add(successfulLabel, 0, 5, 2, 1);
        signupPane.add(invalidPasswordLabel, 1, 6, 3, 2);

        Scene signUpScene = new Scene(signupPane, 400, 300); // specifying the scene size and showing it on the stage.
        signupStage.setScene(signUpScene);
        signupStage.show();
    }

    private void showLoginPage(Stage primaryStage) { // Now, we create the login page
        Stage loginStage = new Stage();
        loginStage.setTitle("Welcome to the login page!");

        GridPane loginPane = new GridPane(); // again, decided to go with the gridPane because I felt comfortable with it.
        loginPane.setHgap(5);
        loginPane.setVgap(5);
        loginPane.setPadding(new javafx.geometry.Insets(10, 10, 10, 10));
        loginPane.setStyle("-fx-background-image: url('https://wallpapers.com/images/hd/airplane-background-jv09cnjzznp2coa7.jpg');"); // background image.
        loginPane.setAlignment(Pos.CENTER);

        Label passwordLabel = new Label("Password:"); // creating the 4 necessary labels needed for execution
        Label usernameLabel = new Label("Username");
        Label loginErrorLabel = new Label("");
        Button logIn = new Button("LogIn"); // login Button

        passwordLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: black; -fx-background-color: white; -fx-font-size: 19px;"); // setting font size, color with setStyle().
        usernameLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: black; -fx-background-color: white; -fx-font-size: 19px;");

        PasswordField passwordField = new PasswordField(); // Making new username and password fields for the user to enter their username and password.
        TextField usernameField = new TextField();

        VBox fieldContainer = new VBox(10); // Used the Vbox as my container to add the labels and fields.
        fieldContainer.setAlignment(Pos.CENTER);
        fieldContainer.getChildren().addAll(usernameLabel, usernameField, passwordLabel, passwordField, loginErrorLabel, logIn);
        loginPane.add(fieldContainer, 1, 3, 2, 1);

        logIn.setOnAction(event -> {  // What happens when login in clicked.
            String usernameEntered = usernameField.getText().trim();
            String passwordEntered = passwordField.getText().trim();

            if (userData.containsKey(usernameEntered)) { // the if-loop checks if the map has the username entered
                customerInfo user = userData.get(usernameEntered); // fetches it
                if (user.getPassword().equals(passwordEntered)) { // compares the username and password.
                    System.out.println("Logged in");
                    loginErrorLabel.setVisible(false);
                    Alert userInfoDialog = new Alert(Alert.AlertType.INFORMATION); // Used an alert because of time constraint. Would like to make a scene otherwise. (tutoring)
                    userInfoDialog.setTitle("Your Account Information");
                    userInfoDialog.setHeaderText("Hey! " + user.getFirstName() + " " + user.getLastName());
                    userInfoDialog.setContentText(
                            "Your Username: " + user.getUsername() + "\n" +
                                    "Email: " + user.getEmail() + "\n" +
                                    "Password: " + user.getPassword()
                    );
                    userInfoDialog.showAndWait();

                    loginStage.close(); // The dialogue closes when pressed ok
                } else { // Error checks and messages for incorrect password and username. Made them separate to get two different messages for user clarification.
                    loginErrorLabel.setVisible(true);
                    loginErrorLabel.setText("Oops! wrong password. Please try again.");
                }
            } else {
                loginErrorLabel.setVisible(true);
                loginErrorLabel.setText("Username does not exist. Please try again or SignUp if it is your first time logging in!");
            }
        });

        Scene loginScene = new Scene(loginPane, 400, 300);
        loginStage.setScene(loginScene);
        loginStage.show();
    }

    private void validatePassword(String input) throws PasswordException, UpperCaseCharacterMissingException, SpecialCharacterMissingException { // Found out about regex at tutoring and integrated it with exception checks for password checking.
        String upperCasePattern = ".*[A-Z].*";
        String lowerCasePattern = ".*[a-z].*";
        String specialCasePattern = ".*[@#$%^&+=].*";
        String numberCharacterPattern = ".*\\d.*";
        String lengthPattern = ".{8,}";

        if (!input.matches(upperCasePattern)) { // all the exceptions here are user defined.
            throw new UpperCaseCharacterMissingException("Your password must include at least one uppercase letter");
        }
        if (!input.matches(lowerCasePattern)) {
            throw new LowerCaseMissingException("Your password must include at least one lowercase letter");
        }
        if (!input.matches(specialCasePattern)) {
            throw new SpecialCharacterMissingException("Your password must include at least one special character.");
        }
        if (!input.matches(numberCharacterPattern)) {
            throw new NumberCharacterMissingException("Your password must include at least one number character.");
        }
        if (!input.matches(lengthPattern)) {
            throw new MinimumEightCharactersRequiredException("Your password must be at least 8 characters long");
        }
    }

    private boolean validateInput(String input) { // this is used to just validate the first and last name.
        String criteriaPattern = "^(?=.*[a-z])(?=.*[A-Z])[^0-9@#$%^&+=]+$";
        return input.matches(criteriaPattern); // input.matches is so much better rather than equalto.
    }

    private boolean validateEmailInput(String email) { // this boolean is only used for emails as it checks for @ and .com.
        String emailPattern = "^.+@.+(\\.com)$";
        return email.matches(emailPattern) && !email.isEmpty(); // also, checks that email field is not left empty.
    }

    private String generateUsername(String firstName, String lastName) { // generates random username after validating everything.
        char firstLetterFirstName = firstName.charAt(0); // Index 0 is the first letter
        char firstLetterLastName = lastName.charAt(0);
        Random random = new Random(); // from random class
        int randomNumbers = 1000 + random.nextInt(9000);
        return String.valueOf(firstLetterFirstName) + String.valueOf(firstLetterLastName) + randomNumbers; // combine values with the random numbers to print.
    }

    private void applyLabelStyles(Label label, String textColor) {  // makes the code look cleaner and helps us by avoiding repetition of code.
        label.setStyle("-fx-font-weight: bold; -fx-text-fill: " + textColor + ";");
    }
}
