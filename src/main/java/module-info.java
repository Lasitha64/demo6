module com.example.demo6 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires mongo.java.driver;

    opens com.example.demo6 to javafx.fxml;
    exports com.example.demo6;
}