module cecy.proyecto1grupo8 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens cecy.proyecto1grupo8 to javafx.fxml;
    exports cecy.proyecto1grupo8;
}
