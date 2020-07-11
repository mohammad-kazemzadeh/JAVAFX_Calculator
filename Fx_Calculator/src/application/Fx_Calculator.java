/* 
 * 		Sunday, May 24, 2020
 * 			Mohammad Kazemzadeh
 * 				Program capable of calculating expressions, (most of)
 * 								Parenthesis expressions, (Methods available)
 * 								BigNumbers(factorial,Power etc...)
 * 								negative number at first .....
 * 
 * 		i made a ToggleButton to switch between BigInt Calculations and 
 * 		expression calculations(priority based).
 * 		decimal numbers shouldn't be entered in BigInt Mode 
 * 		
 * 		i also could have added parenthesis expressions...
 * 
 * 		also... i tired to handle  value assignment progress to be Good(thats why there are lots of conditions)
 * 		in every situation 
 * 		but its not PERFECT 
 * 		
 * 
 */

package application;

import Expressions.PriorityExp2;
import bigInt.BigInt;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Fx_Calculator extends Application {

	private Label Leb;
	private TextField Tfield;
	private VBox Vertical_Box;
	private MButton[] Num_Buttons;
	private Button equ, Plus, Minus, Clear, dec, delete, Multiply, factorial, divide, Power;
	private ToggleButton B;
	private String fText;
	private String op;
	private String SecText;
	private BigInt a;
	private BigInt b;
	private BigInt Result = new BigInt();
	private BigInt Fact = new BigInt();

	public Fx_Calculator() {
		super();

		fText = op = SecText = "";
		Leb = new Label("0");

		Tfield = new TextField();
		Tfield.setEditable(false);

		Vertical_Box = new VBox();
		Num_Buttons = new MButton[10];
		for (int i = 0; i < 10; i++) {
			int value = i;
			// just to bypass the error "Local variable i defined in an enclosing scope must
			// be final
			// or effectively final"
			Num_Buttons[i] = new MButton(i + "");
			Num_Buttons[i].getStyleClass().add("num_buttons");
			Num_Buttons[i].setOnMouseClicked(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent event) {
					Tfield.appendText(value + "");

				}
			});
			Num_Buttons[i].setFocusTraversable(false);

		}
		B = new ToggleButton("Switch BigInt"); // bigInt switch button

		equ = new Button("\u003D");

		Plus = new Button("\u2795");

		Clear = new Button("C");

		Minus = new Button("\u2212");

		dec = new Button(".");

		delete = new Button("Del");

		Multiply = new Button("\u00D7");

		factorial = new Button("\u0021");

		divide = new Button("\u00F7");

		Power = new Button("xʸ");

	}

	@Override
	public void start(Stage primaryStage) {
		try {
			primaryStage.setTitle("Calculator");
			FlowPane Flow = new FlowPane();
			Flow.getStyleClass().add("FLowPn");

			BorderPane root = new BorderPane();

			Tfield.setId("Tfield");

			Leb.setId("Leb");

			B.getStyleClass().add("BigInt-switch-button");
			B.setLayoutX(380);
			B.setLayoutY(50);

			equ.getStyleClass().add("op_equ");
			equ.setOnMouseClicked(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent event) {
					if (!(Leb.getText().contains("!")) && !(B.isSelected()) && Tfield.getText().length() != 0) {

						String Expression = Leb.getText();
						String Extras = Tfield.getText();
						Expression += Extras;
						String Result = PriorityExp2.PriorityExp2_(Expression) + "";
						Tfield.setText(Result);
						Leb.setText("");
					}

					else if (Leb.getText().length() != 0 && Tfield.getText().length() != 0) // BigInt Switch active
					{
						SecText = Tfield.getText();

						b = new BigInt(SecText);
						Leb.setText(a + op + b);

						Result = new BigInt();

						// if op is +
						if (op.equals("+")) {
							Result.Sum(a, b);
						}
						// if op is -
						else if (op.equals("-")) {
							Result.Minus(a, b);
						}
						// if op is *
						else if (op.equals("*")) {
							Result.Multiply(a, b);
						}

						Tfield.setText(Result + "");
						Leb.setText("");

					}
				}

			});

			dec.getStyleClass().add("op_button_Dif");
			dec.setOnMouseClicked(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent event) {
					if (B.isSelected() == false) {
						if (Leb.getText().length() != 0 && Tfield.getText().length() != 0)
							if (Leb.getText().charAt(Leb.getText().length() - 1) != '.'
									&& !(Tfield.getText().indexOf('.') >= 0)) {
								Tfield.appendText(".");

							} else
								Tfield.appendText("");
						else
							Tfield.appendText(".");

					}
				}
			});

			Plus.getStyleClass().add("op_button_Dif");
			Plus.setOnMouseClicked(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent event) {
					String val = "";
					if (B.isSelected() == false) {
						if (Leb.getText().length() != 0) {
							if (Leb.getText().charAt(Leb.getText().length() - 1) != '+') {
								val = Leb.getText() + Tfield.getText() + ("+");

							} else
								val = Leb.getText() + Tfield.getText();
						} else
							val = Tfield.getText() + '+';
						Leb.setText(val);
						Tfield.setText("");
					}

					else if (B.isSelected() && Tfield.getText().length() != 0)
					// BigInt Switch active
					{

						fText = Tfield.getText();
						op = "+";
						a = new BigInt(fText);
						Leb.setText(fText + op);
						Tfield.setText("");

					}

				}
			});

			Clear.getStyleClass().add("op_button");
			Clear.setOnMouseClicked(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent event) {
					Tfield.setText("");
					Leb.setText("");

				}
			});

			Minus.getStyleClass().add("op_button_Dif");
			Minus.setOnMouseClicked(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent event) {
					String val = "";
					if (B.isSelected() == false) {
						if (Leb.getText().length() != 0)
							if (Leb.getText().charAt(Leb.getText().length() - 1) != '-') {
								val = Leb.getText() + Tfield.getText() + ("-");

							} else
								val = Leb.getText() + Tfield.getText();
						else
							val = Tfield.getText() + '-';
						Leb.setText(val);
						Tfield.setText("");
					}

					else if (B.isSelected() && Tfield.getText().length() != 0)
					// BigInt Switch active
					{
						fText = Tfield.getText();
						op = "-";
						a = new BigInt(fText);
						Leb.setText(fText + op);
						Tfield.setText("");

					}

				}
			});

			delete.getStyleClass().add("op_button");
			delete.setOnMouseClicked(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent event) {
					int len = Tfield.getText().length();
					if (len > 0) {
						String Temp = Tfield.getText().substring(0, len - 1);
						Tfield.setText(Temp);
					}

				}
			});

			Multiply.getStyleClass().add("op_button");
			Multiply.setOnMouseClicked(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent event) {
					String val = "";
					if (B.isSelected() == false) {
						if (Leb.getText().length() != 0)
							if (Leb.getText().charAt(Leb.getText().length() - 1) != '*') {
								val = Leb.getText() + Tfield.getText() + ("*");

							} else
								val = Leb.getText() + Tfield.getText();
						else
							val = Tfield.getText() + '*';
						Leb.setText(val);
						Tfield.setText("");

					} else if (B.isSelected() && Tfield.getText().length() != 0)
					// BigInt Switch active
					{
						fText = Tfield.getText();
						op = "*";
						a = new BigInt(fText);
						Leb.setText(fText + op);
						Tfield.setText("");

					}

				}
			});

			factorial.getStyleClass().add("op_button");
			factorial.setOnMouseClicked(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent event) {
					if (Tfield.getText().length() != 0) {
						String value = CleanDecimalForFactorial(Tfield.getText());
						Long factorial = (long) 0;
						factorial = Long.parseLong(value);
						Fact.Factorial(factorial);
						Leb.setText("");
						Tfield.setText(Fact + "");
					}
				}
			});

			divide.getStyleClass().add("op_button");
			divide.setOnMouseClicked(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent event) {
					String val = "";
					if (B.isSelected() == false) {
						if (Leb.getText().length() != 0)
							if (Leb.getText().charAt(Leb.getText().length() - 1) != '/') {
								val = Leb.getText() + Tfield.getText() + ("/");

							} else
								val = Leb.getText() + Tfield.getText();
						else
							val = Tfield.getText() + '/';
						Leb.setText(val);
						Tfield.setText("");
					}

				}
			});

			Power.getStyleClass().add("op_button_Dif");
			Power.setOnMouseClicked(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent event) {
					String val = "";
					if (B.isSelected() == false) {
						if (Leb.getText().length() != 0)
							if (Leb.getText().charAt(Leb.getText().length() - 1) != '^') {
								val = Leb.getText() + Tfield.getText() + ("^");

							} else
								val = Leb.getText() + Tfield.getText();
						else
							val = Tfield.getText() + '^';

						Leb.setText(val);
						Tfield.setText("");
					}

				}
			});

			Vertical_Box.setPadding(new Insets(5, 0, 5, 0));
			Vertical_Box.setSpacing(5);
			Vertical_Box.setAlignment(Pos.CENTER);
			Vertical_Box.getChildren().addAll(Leb, Tfield);
			root.setTop(Vertical_Box);

			Flow.setVgap(9.0);
			Flow.setHgap(9.0);
			Flow.setPadding(new Insets(3));
			Flow.setMinSize(root.getHeight(), root.getWidth());

			Flow.getChildren().addAll(Clear, Num_Buttons[1], Num_Buttons[2], Num_Buttons[3], delete, Num_Buttons[4],
					Num_Buttons[5], Num_Buttons[6], Multiply, Num_Buttons[7], Num_Buttons[8], Num_Buttons[9], factorial,
					Plus, Num_Buttons[0], Minus, divide, dec, Power, equ, B);
			root.setCenter(Flow);

			Scene scene = new Scene(root, 380, 730);

			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String CleanDecimalForFactorial(String Given) {
		if (Given.contains(".") && Given.length() != 0)
			Given = Given.substring(0, Given.indexOf('.'));

		return Given;
	}

	public static void main(String[] args) {
		launch(args);
	}

}
