package com.example.uidesign;

import com.example.uidesign.Flight;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class PreferenceScreen {
    public static void showPreferences(Stage previousStage, Flight selectedFlight) {
        Stage preferenceStage = new Stage();
        preferenceStage.setTitle("Preferences");

        GridPane preferencePane = new GridPane();
        preferencePane.setHgap(5);
        preferencePane.setVgap(5);
        preferencePane.setPadding(new javafx.geometry.Insets(10, 10, 10, 10));

        Label seatLabel = new Label("Choose Your Seat:");
        Label mealLabel = new Label("Meal Preferences:");
        Label luggageLabel = new Label("Luggage Accommodation:");
        Label specialAccommodationLabel = new Label("Special Accommodation:");

        ComboBox<String> seatComboBox = new ComboBox<>();
        seatComboBox.getItems().addAll("Aisle", "Window", "Extra Legroom");
        seatComboBox.setPromptText("Select Seat");

        ComboBox<String> mealComboBox = new ComboBox<>();
        mealComboBox.getItems().addAll("Halal", "Kosher", "Vegan", "No Preference");
        mealComboBox.setPromptText("Select Meal");

        ComboBox<String> luggageComboBox = new ComboBox<>();
        luggageComboBox.getItems().addAll("Standard", "Overhead Bin", "Checked");
        luggageComboBox.setPromptText("Select Luggage Accommodation");

        ComboBox<String> specialAccommodationComboBox = new ComboBox<>();
        specialAccommodationComboBox.getItems().addAll("Wheelchair Assistance", "Child Care", "Other");
        specialAccommodationComboBox.setPromptText("Select Special Accommodation");

        Button submitButton = new Button("Submit");

        submitButton.setOnAction(event -> {
            String selectedSeat = seatComboBox.getValue();
            String selectedMeal = mealComboBox.getValue();
            String selectedLuggage = luggageComboBox.getValue();
            String selectedSpecialAccommodation = specialAccommodationComboBox.getValue();

            if (selectedSeat != null && selectedMeal != null && selectedLuggage != null && selectedSpecialAccommodation != null) {
                System.out.println("Selected Seat: " + selectedSeat);
                System.out.println("Selected Meal: " + selectedMeal);
                System.out.println("Selected Luggage: " + selectedLuggage);
                System.out.println("Selected Special Accommodation: " + selectedSpecialAccommodation);


                preferenceStage.close();
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Incomplete Preferences");
                alert.setHeaderText(null);
                alert.setContentText("Please select seat, meal, luggage accommodation, and special accommodation.");
                alert.showAndWait();
            }
        });

        preferencePane.add(seatLabel, 0, 0);
        preferencePane.add(seatComboBox, 1, 0);
        preferencePane.add(mealLabel, 0, 1);
        preferencePane.add(mealComboBox, 1, 1);
        preferencePane.add(luggageLabel, 0, 2);
        preferencePane.add(luggageComboBox, 1, 2);
        preferencePane.add(specialAccommodationLabel, 0, 3);
        preferencePane.add(specialAccommodationComboBox, 1, 3);
        preferencePane.add(submitButton, 0, 4, 2, 1);

        Scene preferenceScene = new Scene(preferencePane, 350, 250);
        preferenceStage.setScene(preferenceScene);

        preferenceStage.initModality(javafx.stage.Modality.APPLICATION_MODAL);
        preferenceStage.initOwner(previousStage);

        preferenceStage.show();
    }
}

