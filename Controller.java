package sample;

import java.util.Iterator;
import java.util.LinkedList;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Controller {
    boolean nameBool = false;
    boolean priceBool = true;
    LinkedList<String> data = new LinkedList();
    LinkedList<Integer> quantityData = new LinkedList();
    LinkedList<Double> priceData = new LinkedList();
    int sum = 0;
    int numAdded = 0;
    @FXML
    private Label sumLabel;
    @FXML
    private Button btnClear;
    @FXML
    private TextField name;
    @FXML
    private TextField price;
    @FXML
    private TextField quantity;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnQuit;
    @FXML
    private Button btnRemove;
    @FXML
    private Label label;

    public Controller() {
    }

    void updateSum() {
        this.sum = 0;

        for(int i = 0; i < this.numAdded; ++i) {
            this.sum = (int)((double)this.sum + (Double)this.priceData.get(i) * (double)(Integer)this.quantityData.get(i));
        }

        this.sumLabel.setText("Price(w/o VAT): " + this.sum + " \n Price(w/ VAT): " + this.sum * 120 / 100);
    }

    @FXML
    void btnRemoveOnAction(ActionEvent event) {
        if (this.numAdded > 0) {
            this.data.remove(this.numAdded - 1);
            this.quantityData.remove(this.numAdded - 1);
            this.priceData.remove(this.numAdded - 1);
            --this.numAdded;
            this.label.setText(this.dataToString());
            this.updateSum();
        }

    }

    String dataToString() {
        String result = "Products List:\n";

        String i;
        for(Iterator var2 = this.data.iterator(); var2.hasNext(); result = result + i) {
            i = (String)var2.next();
        }

        return result;
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        this.data = new LinkedList();
        this.priceData = new LinkedList();
        this.quantityData = new LinkedList();
        this.numAdded = 0;
        this.name.setText("");
        this.price.setText("");
        this.quantity.setText("");
        this.label.setText("Products List:\n");
        this.sum = 0;
        this.updateSum();
    }

    @FXML
    void nameClear() {
        if (this.name.getText().equals("Name:")) {
            this.name.setText("");
        }

    }

    @FXML
    void priceClear() {
        if (this.price.getText().equals("Price(w/o VAT):")) {
            this.price.setText("");
        }

    }

    @FXML
    void quantityClear() {
        if (this.quantity.getText().equals("Quantity:")) {
            this.quantity.setText("");
        }

    }

    @FXML
    void btnADD(ActionEvent event) {
        this.ADD();
    }

    void ADD() {
        if (!this.name.getText().equals("Name:") && !this.name.getText().equals("") && !this.price.getText().equals("Price(w/o VAT):") && !this.price.getText().equals("")) {
            Label var10000;
            String var10001;
            LinkedList var3;
            try {
                var10000 = this.label;
                var10001 = this.label.getText();
                var10000.setText(var10001 + this.name.getText() + " - " + this.price.getText() + "lv. per 1  | " + Integer.parseInt(this.quantity.getText()) + "  bought \n");
                var3 = this.data;
                var10001 = this.name.getText();
                var3.add(var10001 + " - " + this.price.getText() + "lv. per 1  | " + Integer.parseInt(this.quantity.getText()) + "  bought \n");
                this.priceData.add(Double.parseDouble(this.price.getText()));
                this.quantityData.add(Integer.parseInt(this.quantity.getText()));
            } catch (Exception var2) {
                var10000 = this.label;
                var10001 = this.label.getText();
                var10000.setText(var10001 + this.name.getText() + " - " + this.price.getText() + "lv. per 1  | " + Integer.parseInt(this.quantity.getText()) + "  bought \n");
                var3 = this.data;
                var10001 = this.name.getText();
                var3.add(var10001 + " - " + this.price.getText() + "lv. per 1  | " + Integer.parseInt(this.quantity.getText()) + "  bought. \n");
                this.priceData.add(Double.parseDouble(this.price.getText()));
                this.quantityData.add(1);
            }

            System.out.println(this.numAdded);
            ++this.numAdded;
        }

        this.updateSum();
    }

    @FXML
    void nameEntered() {
        if (!this.price.getText().equals("") && !this.price.getText().equals("Price(w/o VAT):")) {
            this.ADD();
        }

    }

    @FXML
    void priceEntered() {
        if (!this.name.getText().equals("") && !this.name.getText().equals("Name:")) {
            this.ADD();
        }

    }

    @FXML
    void quantityEntered() {
        if (!this.name.getText().equals("") || !this.name.getText().equals("Name:") || !this.price.getText().equals("") || !this.price.getText().equals("Price(w/o VAT):")) {
            this.ADD();
        }

    }

    @FXML
    void btnQuitOnAction(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    void focusName(KeyEvent event) {
        if (event.getCode() == KeyCode.DOWN) {
            if (this.name.getText().equals("")) {
                this.name.setText("Name:");
            }

            this.price.requestFocus();
            if (this.price.getText().equals("Price(w/o VAT):")) {
                this.price.setText("");
            }
        }

        if (event.getCode() == KeyCode.UP) {
            if (this.name.getText().equals("")) {
                this.name.setText("Name:");
            }

            this.quantity.requestFocus();
            if (this.quantity.getText().equals("Quantity:")) {
                this.quantity.setText("");
            }
        }

    }

    @FXML
    void focusQuantity(KeyEvent event) {
        if (event.getCode() == KeyCode.DOWN) {
            if (this.quantity.getText().equals("")) {
                this.quantity.setText("Quantity:");
            }

            this.name.requestFocus();
            if (this.name.getText().equals("Name:")) {
                this.name.setText("");
            }
        }

    }

    @FXML
    void focusPrice(KeyEvent event) {
        if (event.getCode() == KeyCode.UP) {
            if (this.price.getText().equals("")) {
                this.price.setText("Price(w/o VAT):");
            }

            this.name.requestFocus();
            if (this.name.getText().equals("Name:") && this.name.getText().equals("Name:")) {
                this.name.setText("");
            }

            System.out.println("Да живее социалистическият работнически ред!");
        }

    }

    @FXML
    void initialize() {
        assert this.label != null : "fx:id=\"label\" was not injected: check your FXML file 'sample.fxml'.";

        assert this.btnClear != null : "fx:id=\"btnClear\" was not injected: check your FXML file 'sample.fxml'.";

        assert this.btnQuit != null : "fx:id=\"btnQuit\" was not injected: check your FXML file 'sample.fxml'.";

        this.label.setText("Products list:\n");

        assert this.btnRemove != null : "fx:id=\"btnRemove\" was not injected: check your FXML file 'sample.fxml'.";

        assert this.btnAdd != null : "fx:id=\"btnAdd\" was not injected: check your FXML file 'sample.fxml'.";

        assert this.name != null : "fx:id=\"name\" was not injected: check your FXML file 'sample.fxml'.";

        assert this.price != null : "fx:id=\"price\" was not injected: check your FXML file 'sample.fxml'.";

        assert this.sumLabel != null : "fx:id=\"sumLabel\" was not injected: check your FXML file 'sample.fxml'.";

        assert this.quantity != null : "fx:id=\"quantity\" was not injected: check your FXML file 'sample.fxml'.";

    }
}


//style="-fx-background-image: url('https://i1.wp.com/opendoorpride.org/wp-content/uploads/2017/05/simple-one-color-grey-background-1920x1200.jpg?resize=768%2C480&amp;ssl=1'); -fx-border-color: red;" 20
//style="-fx-background-image: url('https://img.freepik.com/free-vector/abstract-background-red_1078-436.jpg?size=626&amp;ext=jpg'); -fx-border-color: black; -fx-border-width: 2;" 12