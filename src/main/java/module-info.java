module com.example.demo6 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    //requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires mongo.java.driver;
    requires java.sql;
    requires com.jaspersoft.ireport;
    requires jasperreports;
    requires java.desktop;

    opens com.example.demo6 to javafx.fxml;
    exports com.example.demo6;


}