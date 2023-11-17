package ui_app;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

import java.util.Objects;

public class CalcController {
    CalcInputSanitation sanitizer = new CalcInputSanitation(); // Misnomer, meant to clean the String but
                                                               // limited by implementation. Prevents return
                                                               // errors and allows limited error handling.
    int leastRecentSlot = 0; // Used to determine which slot in result history was used least recently.
                             // Allows the result history logic to wipe it and reset it.

    // Future self note: These are how JavaFX (FXML) accesses the variables within the FXML code.
    // Similar to JS's object finding method, but more direct and easier (really good actually).
    @FXML private TextField formulaInput; // Main TextField input element.
    @FXML private Button resultSlot1; // Text for buttons which store the results of the user's calculations.
    @FXML private Button resultSlot2;
    @FXML private Button resultSlot3;
    @FXML private Button resultSlot4;
    @FXML private Button resultSlot5;
    @FXML private Tooltip resultTooltip1; // Hovering over the pseudo-buttons (faked for easier management)
    @FXML private Tooltip resultTooltip2; // will allow the user to see what formula they used to get that answer.
    @FXML private Tooltip resultTooltip3; // This is still limited as the original intent was to allow users to click
    @FXML private Tooltip resultTooltip4; // and copy the formulas, but the project was rushed.
    @FXML private Tooltip resultTooltip5;
    @FXML private GridPane mainGrid; // Organizational element, allows for better layout given poor knowledge/understanding.
                                     // Handles both the main keypad (numbers) and operators (special chars).
    @FXML private GridPane keypadGrid; // Organizational element, handles main keypad.

    @FXML
    public void initialize() {
        resultTooltip1.setShowDelay(Duration.millis(100));
        resultTooltip1.setHideDelay(Duration.millis(100));
        resultTooltip2.setShowDelay(Duration.millis(100));
        resultTooltip2.setHideDelay(Duration.millis(100));
        resultTooltip3.setShowDelay(Duration.millis(100));
        resultTooltip3.setHideDelay(Duration.millis(100));
        resultTooltip4.setShowDelay(Duration.millis(100));
        resultTooltip4.setHideDelay(Duration.millis(100));
        resultTooltip5.setShowDelay(Duration.millis(100));
        resultTooltip5.setHideDelay(Duration.millis(100));
        // Spaghetti. I know. This code is in spite of a better method, but it allows
        // tooltips to appear and disappear faster.

        mainGrid.setVgap(10); // Pseudo-Padding/Margins
        keypadGrid.setHgap(30);
    }

    @FXML
    protected void onCalculateButtonClick() {
        String formula = formulaInput.getText(); // Gets text from the TextField input
        String result = sanitizer.sanitise(formula); // Calls the function to actually get the result of calculation.
        boolean slotCheck1 = resultSlot1.getText().isEmpty(); // Stores boolean for result history, allows shorthand
        boolean slotCheck2 = resultSlot2.getText().isEmpty();
        boolean slotCheck3 = resultSlot3.getText().isEmpty();
        boolean slotCheck4 = resultSlot4.getText().isEmpty();
        boolean slotCheck5 = resultSlot5.getText().isEmpty();

        if(!Objects.equals(result, "ILL_OPERATION")) { // Checks for return from CalcInputSanitation.
            formulaInput.setText(result); // Prints the result to the TextField if not error.

            // Initial slots will all be empty, these are the if statements.
            // The switch statement handles the case that all the slots are occupied.
            // Spaghetti Monster logic. It works, but it's bad.
            if (slotCheck1 && leastRecentSlot == 0) {
                resultSlot1.setText(result);
                resultTooltip1.setText(formula);
//                leastRecentSlot = 2;
            } else if (slotCheck2 && leastRecentSlot == 0) {
                resultSlot2.setText(result);
                resultTooltip2.setText(formula);
//                leastRecentSlot = 3;
            } else if (slotCheck3 && leastRecentSlot == 0) {
                resultSlot3.setText(result);
                resultTooltip3.setText(formula);
//                leastRecentSlot = 4;
            } else if (slotCheck4 && leastRecentSlot == 0) {
                resultSlot4.setText(result);
                resultTooltip4.setText(formula);
//                leastRecentSlot = 5;
            } else if (slotCheck5 && leastRecentSlot == 0) {
                resultSlot5.setText(result);
                resultTooltip5.setText(formula);
                leastRecentSlot = 1;
            } else {
                switch (leastRecentSlot) {
                    case 1:
                        resultSlot1.setText(result);
                        resultTooltip1.setText(formula);
                        leastRecentSlot = 2;
                        break;
                    case 2:
                        resultSlot2.setText(result);
                        resultTooltip2.setText(formula);
                        leastRecentSlot = 3;
                        break;
                    case 3:
                        resultSlot3.setText(result);
                        resultTooltip3.setText(formula);
                        leastRecentSlot = 4;
                        break;
                    case 4:
                        resultSlot4.setText(result);
                        resultTooltip4.setText(formula);
                        leastRecentSlot = 5;
                        break;
                    case 5:
                        resultSlot5.setText(result);
                        resultTooltip5.setText(formula);
                        leastRecentSlot = 1;
                        break;
                    default:

                }
            }
        } else {
            formulaInput.setText("Input cannot be empty nor contain letters or special characters.");
            // Pseudo-error handling
        }
    }

    // BASIC KEYPAD 0-9.00
    @FXML protected void onClickKeypad1() {formulaInput.appendText("1");}
    @FXML protected void onClickKeypad2() {formulaInput.appendText("2");}
    @FXML protected void onClickKeypad3() {formulaInput.appendText("3");}
    @FXML protected void onClickKeypad4() {formulaInput.appendText("4");}
    @FXML protected void onClickKeypad5() {formulaInput.appendText("5");}
    @FXML protected void onClickKeypad6() {formulaInput.appendText("6");}
    @FXML protected void onClickKeypad7() {formulaInput.appendText("7");}
    @FXML protected void onClickKeypad8() {formulaInput.appendText("8");}
    @FXML protected void onClickKeypad9() {formulaInput.appendText("9");}
    @FXML protected void onClickKeypad0() {formulaInput.appendText("0");}
    @FXML protected void onClickKeypad00() {formulaInput.appendText("00");}
    @FXML protected void onClickKeypadPeriod() {formulaInput.appendText(".");}

    // OPERATOR KEYPAD Backspace+-*/^sqrt=()
    @FXML protected void onClickKeypadBackspace() {
        String formula = formulaInput.getText();
        try {
            formula = formula.substring(0, formula.length() - 1);
        } catch (Exception ignored) {}
        formulaInput.setText(formula);
    }
    @FXML protected void onClickKeypadOpenParenthesis() {formulaInput.appendText("(");}

    @FXML protected void onClickKeypadCloseParenthesis() {formulaInput.appendText(")");}

    @FXML protected void onClickKeypadAdd() {formulaInput.appendText("+");}
    @FXML protected void onClickKeypadSubtract() {formulaInput.appendText("-");}
    @FXML protected void onClickKeypadMultiply() {formulaInput.appendText("*");}
    @FXML protected void onClickKeypadDivide() {formulaInput.appendText("/");}
    @FXML protected void onClickKeypadExponent() {formulaInput.appendText("^");}
    @FXML protected void onClickKeypadSquareRoot() {formulaInput.appendText("s");}
    @FXML protected void onClickKeypadClear() {formulaInput.setText("");}
    @FXML protected void onClickKeypadAllClear() {
        formulaInput.setText("");
        resultSlot1.setText("");
        resultSlot2.setText("");
        resultSlot3.setText("");
        resultSlot4.setText("");
        resultSlot5.setText("");
        resultTooltip1.setText("");
        resultTooltip2.setText("");
        resultTooltip3.setText("");
        resultTooltip4.setText("");
        resultTooltip5.setText("");
        leastRecentSlot = 0;
    }
}