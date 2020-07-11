package application;

import javafx.geometry.Insets;
import javafx.scene.control.Button;

public class MButton extends Button {

	public MButton(String Given) {
		super(Given);
		this.setPrefSize(85, 85);
		this.setMinHeight(85);
		this.setMinWidth(85);
		this.setPadding(Insets.EMPTY);
	}
}
