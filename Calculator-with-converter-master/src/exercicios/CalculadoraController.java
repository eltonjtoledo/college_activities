package exercicios;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;

public class CalculadoraController {
    @FXML
    GridPane grid = new GridPane();
    @FXML
    Label txt_valor = new Label();
    @FXML
    Button btn_1 = new Button();
    @FXML
    Button btn_2 = new Button();
    @FXML
    Button btn_3 = new Button();
    @FXML
    Button btn_4 = new Button();
    @FXML
    Button btn_5 = new Button();
    @FXML
    Button btn_6 = new Button();
    @FXML
    Button btn_7 = new Button();
    @FXML
    Button btn_8 = new Button();
    @FXML
    Button btn_9 = new Button();
    @FXML
    Button btn_0 = new Button();
    @FXML
    Button btn_ac = new Button();
    @FXML
    Button btn_a = new Button();
    @FXML
    Button btn_b = new Button();
    @FXML
    Button btn_c = new Button();
    @FXML
    Button btn_d = new Button();
    @FXML
    Button btn_e = new Button();
    @FXML
    Button btn_f = new Button();
    @FXML
    Button btn_div = new Button();
    @FXML
    Button btn_cross = new Button();
    @FXML
    Button btn_minus = new Button();
    @FXML
    Button btn_plus = new Button();
    @FXML
    Button btn_ponto = new Button();
    @FXML
    Button btn_igual = new Button();
   @FXML
    ComboBox convert_from = new ComboBox();
   @FXML
    ComboBox convert_to = new ComboBox();
   @FXML
    Button btn_convert = new Button();


    String TextoAnt = "";
    String Texto = "";
    String operationUsed = "";
    String textoConvert = "";

    @FXML
    public void initialize(){
        convert_from.setValue("Decimal");
        convert_to.setValue("Decimal");
        btn_ac.setOnAction(actionEvent -> { Texto = "0"; txt_valor.setText(Texto); TextoAnt = ""; operationUsed = ""; textoConvert = "";});
        btn_3.setOnAction(actionEvent -> { validText("3"); });
        btn_2.setOnAction(actionEvent -> { validText("2"); });
        btn_1.setOnAction(actionEvent -> { validText("1"); });
        btn_4.setOnAction(actionEvent -> { validText("4"); });
        btn_5.setOnAction(actionEvent -> { validText("5"); });
        btn_6.setOnAction(actionEvent -> { validText("6"); });
        btn_7.setOnAction(actionEvent -> { validText("7"); });
        btn_8.setOnAction(actionEvent -> { validText("8"); });
        btn_9.setOnAction(actionEvent -> { validText("9"); });
        btn_0.setOnAction(actionEvent -> { validText("0"); });
        btn_a.setOnAction(actionEvent -> { validText("A"); });
        btn_b.setOnAction(actionEvent -> { validText("B"); });
        btn_c.setOnAction(actionEvent -> { validText("C"); });
        btn_d.setOnAction(actionEvent -> { validText("D"); });
        btn_e.setOnAction(actionEvent -> { validText("E"); });
        btn_f.setOnAction(actionEvent -> { validText("F"); });

        btn_div.setOnAction(actionEvent -> {operation("/");});
        btn_cross.setOnAction(actionEvent -> {operation("*");});
        btn_minus.setOnAction(actionEvent -> {operation("-");});
        btn_plus.setOnAction(actionEvent -> {operation("+");});
        btn_igual.setOnAction(actionEvent -> { operation("=");});

        btn_convert.setOnAction(actionEvent -> {convert();});


        grid.setOnKeyPressed(keyEvent -> {
            if(keyEvent.getCode() == KeyCode.BACK_SPACE){
                Texto = Texto.substring(0, Texto.length() -1).toUpperCase();
                if(Texto.length() == 0){
                    setText("0");
                }else{
                    txt_valor.setText(Texto);
                }
            }else{
                if(keyEvent.getText().matches("^(\\+|-|\\*|/|=)$")){
                    operation(keyEvent.getText());
                }
                validText(keyEvent.getText());
            }
        });
    }

    private void validText(String value){
        if(value.matches("^[A-Fa-f0-9]")){
            if(!convert_from.getValue().equals("Hexadecimal") && value.matches("^\\d")){
                setText(value);
            }else if(convert_from.getValue().equals("Hexadecimal")){
                setText(value);
            }
        }else if(!Texto.contains(",") && value.matches("^[\\.\\,]")){
            setText(".");
        }
    }

    private void setText(String valor){
        Texto = (Texto.equals("0") ? "" : Texto);

        if (Texto.length() <= 13){
            Texto = Texto.concat(valor);
            txt_valor.setText(Texto.toUpperCase());
        }
    }

    private void convert(){
        var valor = txt_valor.getText();
        switch (convert_from.getValue().toString()){
            case "Hexadecimal":
                textoConvert = Integer.toString(Integer.parseInt(valor, 16));
                break;
            case "Binario":
                textoConvert = Integer.toString(Integer.parseInt(valor, 2));
                break;
            case "Decimal":
                textoConvert = Integer.toString(Integer.parseInt(valor));
                break;
            case "Octal":
                textoConvert = Integer.toString(Integer.parseInt(valor, 8));
                break;
        }

        switch (convert_to.getValue().toString()){
            case "Hexadecimal":
                Texto = Integer.toHexString(Integer.parseInt(textoConvert));
                break;
            case "Binario":
                Texto = Integer.toBinaryString(Integer.parseInt(textoConvert));
                break;
            case "Decimal":
                Texto = Integer.toString(Integer.parseInt(textoConvert));
                break;
            case "Octal":
                Texto = Integer.toOctalString(Integer.parseInt(valor, 8));
                break;
        }
        txt_valor.setText(Texto);
    }

    private void operation(String operator){
        if(TextoAnt.equals("")){
            operationUsed = operator;
            TextoAnt = Texto;
            Texto = "0";
        }else{
            switch (operationUsed){
                case "+":
                    Texto = String.valueOf(Float.parseFloat(TextoAnt) + Float.parseFloat(Texto));
                    break;
                case "-":
                    Texto = String.valueOf(Float.parseFloat(TextoAnt) - Float.parseFloat(Texto));
                    break;
                case "*":
                    Texto = String.valueOf(Float.parseFloat(TextoAnt) * Float.parseFloat(Texto));
                    break;
                case "/":
                    Texto = String.valueOf(Float.parseFloat(TextoAnt) / Float.parseFloat(Texto));
                    break;
                case "=":
                    Texto = TextoAnt;
                    break;
            }
            double valueDouble = Double.parseDouble(Texto);
            if(Math.rint(valueDouble) == valueDouble){
                int inteiro = (int)valueDouble;
                Texto = Integer.toString(inteiro);
            }
                operationUsed = operator;
                TextoAnt = Texto;
                Texto = "0";
        }
        txt_valor.setText(TextoAnt);
    }
}
