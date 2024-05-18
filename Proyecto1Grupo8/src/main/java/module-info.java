module cecy.proyecto1grupo8 {
    requires javafx.controls;
    requires javafx.fxml;

    opens cecy.proyecto1grupo8 to javafx.fxml;
    exports cecy.proyecto1grupo8;
}
