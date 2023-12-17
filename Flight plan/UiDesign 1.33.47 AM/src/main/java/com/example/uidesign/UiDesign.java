package com.example.uidesign;
// all the necessary imports to run the code.
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import javafx.application.Application;
import javafx.geometry.Insets;
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
        bannerLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: white; -fx-background-color: black");

        Button signupButton = new Button("Sign Up"); // creating signup and login buttons and specifying their sizes.
        Button loginButton = new Button("Login");
        signupButton.setStyle("-fx-background-color: black; -fx-text-fill: white;");
        loginButton.setStyle("-fx-background-color: black; -fx-text-fill: white;");

        signupButton.setPrefSize(150, 50);
        loginButton.setPrefSize(150, 50);

        VBox buttonLayout = new VBox(10); // chose Vbox button layout
        buttonLayout.getChildren().addAll(bannerLabel, signupButton, loginButton);
        buttonLayout.setStyle("-fx-background-image: url('https://wallpapercave.com/dwp2x/wp2577110.jpg');" +
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
        Scene welcomeScene = new Scene(buttonLayout, 1500, 750); // Increased scene size for better visibility
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
            } catch (
                    PasswordException e) { // Catch uses our parent exception class PasswordException that is extended by user-defined exceptions.
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

        Scene signUpScene = new Scene(signupPane, 1500, 750); // specifying the scene size and showing it on the stage.
        signupStage.setScene(signUpScene);
        signupStage.show();
    }

    private void showLoginPage(Stage primaryStage) {
        Stage loginStage = new Stage();
        loginStage.setTitle("Welcome to the login page!");

        GridPane loginPane = new GridPane();
        loginPane.setHgap(5);
        loginPane.setVgap(5);
        loginPane.setPadding(new javafx.geometry.Insets(10, 10, 10, 10));
        loginPane.setStyle("-fx-background-image: url('https://wallpapers.com/images/hd/airplane-background-jv09cnjzznp2coa7.jpg');");
        loginPane.setAlignment(Pos.CENTER);

        Label passwordLabel = new Label("Password:");
        Label usernameLabel = new Label("Username");
        Label loginErrorLabel = new Label("");
        Button logIn = new Button("LogIn");

        passwordLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: black; -fx-background-color: white; -fx-font-size: 19px;");
        usernameLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: black; -fx-background-color: white; -fx-font-size: 19px;");

        PasswordField passwordField = new PasswordField();
        TextField usernameField = new TextField();

        VBox fieldContainer = new VBox(10);
        fieldContainer.setAlignment(Pos.CENTER);
        fieldContainer.getChildren().addAll(usernameLabel, usernameField, passwordLabel, passwordField, loginErrorLabel, logIn);
        loginPane.add(fieldContainer, 1, 3, 2, 1);

        logIn.setOnAction(event -> {
            String usernameEntered = usernameField.getText().trim();
            String passwordEntered = passwordField.getText().trim();

            if (userData.containsKey(usernameEntered)) {
                customerInfo user = userData.get(usernameEntered);
                if (user.getPassword().equals(passwordEntered)) {
                    System.out.println("Logged in");
                    loginErrorLabel.setVisible(false);
                    showAirlineSelection(loginStage); // Display airline selection window
                } else {
                    loginErrorLabel.setVisible(true);
                    loginErrorLabel.setText("Oops! wrong password. Please try again.");
                }
            } else {
                loginErrorLabel.setVisible(true);
                loginErrorLabel.setText("Username does not exist. Please try again or SignUp if it is your first time logging in!");
            }
        });

        Scene loginScene = new Scene(loginPane, 1500, 750);
        loginStage.setScene(loginScene);
        loginStage.show();
    }
    private void showAirlineSelection(Stage loginStage) {
        Stage airlineStage = new Stage();
        airlineStage.setTitle("Please let us know your airline preferences");


        VBox airlineOptions = new VBox(10);
        airlineOptions.setAlignment(Pos.CENTER);
        airlineOptions.setStyle("-fx-background-image: url('https://images5.alphacoders.com/133/1336391.png');");

        Button airline1Button = new Button("Lufthansa");
        Button airline2Button = new Button("Emirates");
        Button airline3Button = new Button("British Airways");
        Button noSelection = new Button("I don't mind any.");

        airline1Button.setOnAction(event -> {
            System.out.println("User selected Airline 1");
            showPlaneSelection(loginStage); // Display plane selection window
            airlineStage.close();
        });
        airline2Button.setOnAction(event -> {
            System.out.println("User selected Airline 2");
            showPlaneSelection(loginStage); // Display plane selection window
            airlineStage.close();
        });
        airline3Button.setOnAction(event -> {
            System.out.println("User selected Airline 3");
            showPlaneSelection(loginStage); // Display plane selection window
            airlineStage.close();
        });
        noSelection.setOnAction(event -> {
            System.out.println("User selected nothing");
            showPlaneSelection(loginStage); // Display plane selection window
            airlineStage.close();
        });

        airlineOptions.getChildren().addAll(airline1Button, airline2Button, airline3Button, noSelection);

        Scene airlineScene = new Scene(airlineOptions, 1500, 750);
        airlineStage.setScene(airlineScene);
        airlineStage.show();
    }

    private void showPlaneSelection(Stage loginStage) {
        Stage showPlaneSelection = new Stage();
        showPlaneSelection.setTitle("Any jets you would like to use this opportunity to fly on");

        VBox airlineOptions = new VBox(10);
        airlineOptions.setAlignment(Pos.CENTER);
        airlineOptions.setStyle("-fx-background-image: url('https://images4.alphacoders.com/933/93368.jpg');");


        Button boeingButton = new Button("Boeing 777-ER: The iconic classic jumbo jet with an impeccable safety record.");
        Button airbusButton = new Button("Airbus A380: The only double-decker that has earned the most comfortable plane award.");
        Button noSelection = new Button("I don't mind any.");

        boeingButton.setOnAction(event -> {
            System.out.println("User selected Boeing");
            showTripDetails(loginStage); // Open Trip Details window when Boeing is selected
            showPlaneSelection.close();
        });
        airbusButton.setOnAction(event -> {
            System.out.println("User selected Airbus");
            showTripDetails(loginStage); // Open Trip Details window when Airbus is selected
            showPlaneSelection.close();
        });
        noSelection.setOnAction(event -> {
            System.out.println("User selected nothing");
            showTripDetails(loginStage); // Open Trip Details window when NoSelection is chosen
            showPlaneSelection.close();
        });



        airlineOptions.getChildren().addAll(boeingButton, airbusButton, noSelection);

        Scene showPlaneScene = new Scene(airlineOptions, 1500, 750);
        showPlaneSelection.setScene(showPlaneScene);
        showPlaneSelection.show();
    }

    private void showTripDetails(Stage loginStage) {
        Stage tripDetailsStage = new Stage();
        tripDetailsStage.setTitle("Trip Details");

        GridPane tripDetailsPane = new GridPane();
        tripDetailsPane.setHgap(5);
        tripDetailsPane.setVgap(5);
        tripDetailsPane.setPadding(new Insets(10, 10, 10, 10));
        tripDetailsPane.setAlignment(Pos.CENTER);
        tripDetailsPane.setStyle("-fx-background-image: url('https://wallpapers.com/images/featured-full/black-aesthetic-tumblr-laptop-8dpuouoi7eirjdd4.jpg');");

        Label leavingFromLabel = new Label("Leaving from:");
        Label goingToLabel = new Label("Going to:");
        Label dateLeavingFrom = new Label("Leaving on:");
        Label dateComingBackOn = new Label("Coming back on:");
        Label layoverPreference = new Label("Layover Preference (For Non-Stop: Enter 0) -> ");

        leavingFromLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: white; -fx-background-color: black; -fx-font-size: 19px;");
        goingToLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: white; -fx-background-color: black; -fx-font-size: 19px;");
        dateLeavingFrom.setStyle("-fx-font-weight: bold; -fx-text-fill: white; -fx-background-color: black; -fx-font-size: 19px;");
        dateComingBackOn.setStyle("-fx-font-weight: bold; -fx-text-fill: white; -fx-background-color: black; -fx-font-size: 19px;");
        layoverPreference.setStyle("-fx-font-weight: bold; -fx-text-fill: white; -fx-background-color: black; -fx-font-size: 19px;");






        DatePicker dateLeavingFromPicker = new DatePicker();
        dateLeavingFromPicker.setPromptText("MM/dd/yyyy"); // Set the desired date format as a prompt

        DatePicker dateComingBackOnPicker = new DatePicker();
        dateComingBackOnPicker.setPromptText("MM/dd/yyyy"); // Set the desired date format as a prompt

        TextField leavingFromField = new TextField();
        TextField goingToField = new TextField();
        TextField layoverField = new TextField();
        Label errorLabel = new Label();

        Button submitButton = new Button("Submit");

        submitButton.setOnAction(event -> {
            String leavingFromText = leavingFromField.getText().trim();
            String goingToText = goingToField.getText().trim();
            String layoverText = layoverField.getText().trim();
            LocalDate leavingOnDate = dateLeavingFromPicker.getValue();
            LocalDate comingBackDate = dateComingBackOnPicker.getValue();

            if (leavingFromText.isEmpty() || goingToText.isEmpty() || layoverText.isEmpty() ) {
                errorLabel.setText("Please complete Leaving From, Going To, and layover preference fields.");
            } else if (!leavingFromText.matches("[a-zA-Z]+") || !goingToText.matches("[a-zA-Z]+")) {
                errorLabel.setText("Please use only letters from A to Z.");
            } else if (leavingOnDate == null || comingBackDate == null) {
                errorLabel.setText("Please select valid dates for Leaving On and Coming Back On.");
            } else if (!layoverText.matches("^(0|[1-9]|[12]\\\\d|30)(\\\\.\\\\d+)?$")) {
                errorLabel.setText("Layover durations can only contain positive numbers.");

            } else {
                errorLabel.setText(""); // Clear any previous error message
                System.out.println("Leaving from: " + leavingFromText);
                System.out.println("Going to: " + goingToText);
                System.out.println("Leaving on: " + leavingOnDate);
                System.out.println("Coming back on: " + comingBackDate);

            }
        });
        Button proceedButton = new Button("Proceed");
        proceedButton.setOnAction(event -> showPreferences(loginStage));

        tripDetailsPane.add(leavingFromLabel, 0, 0);
        tripDetailsPane.add(leavingFromField, 1, 0);
        tripDetailsPane.add(goingToLabel, 0, 1);
        tripDetailsPane.add(goingToField, 1, 1);
        tripDetailsPane.add(dateLeavingFrom, 0, 2);
        tripDetailsPane.add(dateLeavingFromPicker, 1, 2);
        tripDetailsPane.add(dateComingBackOn, 0, 3);
        tripDetailsPane.add(dateComingBackOnPicker, 1, 3);
        tripDetailsPane.add(layoverPreference, 0, 4, 2, 1); // Spanning 2 columns for the layoverPreference label
        tripDetailsPane.add(layoverField, 2, 4); // Adjust the column for the layoverField
        tripDetailsPane.add(submitButton, 0, 5, 3, 2);

        tripDetailsPane.add(errorLabel, 0, 7, 3, 3);





        Scene tripDetailsScene = new Scene(tripDetailsPane, 1500, 750);
        tripDetailsStage.setScene(tripDetailsScene);
        tripDetailsStage.show();
        ListView<String> flightListView = new ListView<>();




        Button searchButton = new Button("Search Flights");
        searchButton.setOnAction(event -> {
            String leavingFromText = leavingFromField.getText().trim();
            String goingToText = goingToField.getText().trim();
            LocalDate leavingOnDate = dateLeavingFromPicker.getValue();
            LocalDate comingBackDate = dateComingBackOnPicker.getValue();

            if (leavingFromText.isEmpty() || goingToText.isEmpty() || leavingOnDate == null || comingBackDate == null) {
                errorLabel.setText("Please complete Leaving From, Going To, and valid dates.");
            } else {
                errorLabel.setText(""); // Clear any previous error message

                // Search for flights in the database
                List<Flight> searchResults = FlightDatabase.searchFlights(
                        leavingFromText, goingToText, leavingOnDate.toString(), comingBackDate.toString());

                // Display search results in the ListView
                flightListView.getItems().clear();
                for (Flight flight : searchResults) {
                    flightListView.getItems().add(flight.getAirline() + " - " + flight.getPlaneType() + " - $" + flight.getPrice()+" Layover: "+ flight.getLayover());
                }
            }
        });

        //tripDetailsPane.add(searchButton, 0, 8, 3, 2);
        tripDetailsPane.add(flightListView, 0, 6, 3, 2);

        tripDetailsPane.add(searchButton, 0, 8, 2, 1); // Use rowspan to increase space between buttons
        tripDetailsPane.add(new Label(""), 0, 9); // Add an empty label for spacing
        tripDetailsPane.add(proceedButton, 0, 10, 2, 1);


        flightListView.setPrefSize(300, 76); // Adjust the width and height as needed
        GridPane.setMargin(flightListView, new Insets(20, 0, 20, 0)); // Adjust the top margin as needed
        //VBox buttonContainer = new VBox(85); // Adjust the spacing between buttons as needed
        //buttonContainer.getChildren().addAll(submitButton, searchButton);
        //tripDetailsPane.add(buttonContainer, 0, 5, 3, 2);


    }

    private void showPreferences(Stage loginStage) {
        Stage preferencesStage = new Stage();
        preferencesStage.setTitle("Preferences");

        GridPane preferencesPane = new GridPane();
        preferencesPane.setHgap(10);
        preferencesPane.setVgap(10);
        preferencesPane.setPadding(new Insets(20, 20, 20, 20));
        preferencesPane.setAlignment(Pos.CENTER);
        preferencesPane.setStyle("-fx-background-image: url('https://www.everythingnewzealand.com/resources/cache/958x510/1/categories/Scenic-Plane-Flights-in-New-Zealand.jpg'); " +
                "-fx-background-size: cover;");

        Label seatLabel = new Label("Seat Preference:");
        ComboBox<String> seatComboBox = new ComboBox<>();
        seatComboBox.getItems().addAll("Aisle", "Window", "Extra Legroom", "No preference");
        seatComboBox.setValue("No preference"); // Default selection

        Label mealLabel = new Label("Meal Preference:");
        ComboBox<String> mealComboBox = new ComboBox<>();
        mealComboBox.getItems().addAll("Halal", "Kosher", "Vegan", "No preference");
        mealComboBox.setValue("No preference"); // Default selection

        Label luggageLabel = new Label("Luggage Accommodations:");
        ComboBox<String> luggageComboBox = new ComboBox<>();
        luggageComboBox.getItems().addAll("Standard", "Overhead Bin", "Checked");
        luggageComboBox.setValue("Standard"); // Default selection

        Label accomodationLabel = new Label("Special Accommodations:");
        ComboBox<String> classComboBox = new ComboBox<>();
        classComboBox.getItems().addAll("Wheelchair Assistance", "Child Care", "Other", "Not needed");
        classComboBox.setValue("Not needed"); // Default selection

        Button confirmButton = new Button("Confirm");
        confirmButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
        confirmButton.setOnAction(event -> {
            // Handle the preferences (e.g., store them or perform any necessary actions)
            // For simplicity, just show the confirmation screen for now
            showConfirmationScreen(loginStage);
            preferencesStage.close();
        });

        preferencesPane.add(seatLabel, 0, 0);
        preferencesPane.add(seatComboBox, 1, 0);
        preferencesPane.add(mealLabel, 0, 1);
        preferencesPane.add(mealComboBox, 1, 1);
       preferencesPane.add(accomodationLabel, 0, 2);
       preferencesPane.add(classComboBox, 1, 2);
        preferencesPane.add(luggageLabel,0,3);
        preferencesPane.add(luggageComboBox,1,3 );
        preferencesPane.add(confirmButton, 0, 4, 2, 1);

        Scene preferencesScene = new Scene(preferencesPane, 1500, 750);
        preferencesStage.setScene(preferencesScene);
        preferencesStage.show();
    }
    private void showConfirmationScreen(Stage loginStage) {
        Stage confirmationStage = new Stage();
        confirmationStage.setTitle("Booking Confirmation");

        VBox confirmationLayout = new VBox(10);
        confirmationLayout.setAlignment(Pos.CENTER);
        confirmationLayout.setStyle("-fx-background-image: url('https://i.redd.it/neat-livery-on-a-neat-aircraft-on-a-beautiful-scenery-ksfo-v0-qze5v8vjjvob1.png?width=2560&format=png&auto=webp&s=ef02b5b9faa29d7ca67444de4b3ba1c326bedb0d'); " +
                "-fx-background-size: cover;");

        // Generate a random ticket number with a mix of uppercase letters and numbers
        String ticketNumber = generateRandomTicketNumber();

        // Display the confirmation message and ticket number
        Label confirmationLabel = new Label("Thank you for booking with us!");
        Label ticketNumberLabel = new Label("Your Ticket Number: " + ticketNumber);

        confirmationLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: white;");
        ticketNumberLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: white;");

        confirmationLayout.getChildren().addAll(confirmationLabel, ticketNumberLabel);

        Scene confirmationScene = new Scene(confirmationLayout, 1500, 750);
        confirmationStage.setScene(confirmationScene);
        confirmationStage.show();
    }
    private String generateRandomTicketNumber() {
        int length = 8; // Adjust the length of the ticket number as needed
        StringBuilder ticketNumber = new StringBuilder();

        Random random = new Random();
        String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        for (int i = 0; i < length; i++) {
            if (i % 2 == 0) {
                // Add an uppercase letter
                char randomLetter = letters.charAt(random.nextInt(letters.length()));
                ticketNumber.append(randomLetter);
            } else {
                // Add a random digit
                int randomDigit = random.nextInt(10);
                ticketNumber.append(randomDigit);
            }
        }

        return ticketNumber.toString();
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
        return input.matches(criteriaPattern); // input.matches is so much better rather than equal to.
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
