package ui_app;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

import java.util.Objects;

public class CalcController {
    CalcInputSanitation sanitizer = new CalcInputSanitation();
    int leastRecentSlot = 0;

    @FXML private TextField formulaInput;
    @FXML private Button resultSlot1;
    @FXML private Button resultSlot2;
    @FXML private Button resultSlot3;
    @FXML private Button resultSlot4;
    @FXML private Button resultSlot5;
    @FXML private Tooltip resultTooltip1;
    @FXML private Tooltip resultTooltip2;
    @FXML private Tooltip resultTooltip3;
    @FXML private Tooltip resultTooltip4;
    @FXML private Tooltip resultTooltip5;
    @FXML private GridPane mainGrid;
    @FXML private GridPane keypadGrid;

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
        mainGrid.setVgap(10);
        keypadGrid.setHgap(30);
        formulaInput.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode().equals(KeyCode.ENTER)) {
                    onCalculateButtonClick();
                }
            }
        });
    }

    @FXML
    protected void onCalculateButtonClick() {
        String formula = formulaInput.getText();
        String result = sanitizer.sanitise(formula);
        boolean slotCheck1 = resultSlot1.getText().isEmpty();
        boolean slotCheck2 = resultSlot2.getText().isEmpty();
        boolean slotCheck3 = resultSlot3.getText().isEmpty();
        boolean slotCheck4 = resultSlot4.getText().isEmpty();
        boolean slotCheck5 = resultSlot5.getText().isEmpty();

        if(!Objects.equals(result, "ILL_OPERATION")) {
            formulaInput.setText(result);

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