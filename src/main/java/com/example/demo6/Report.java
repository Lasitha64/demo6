package com.example.demo6;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JRViewer;
import net.sf.jasperreports.view.JasperViewer;

import java.io.InputStream;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import com.example.demo6.Database;

public class Report {

    private static JasperReport jreport;
    private static JasperViewer jviewer;
    private static JasperPrint jprint;
    private static Database con;
    private static HashMap hm;

    public static void createReport(Database connect, HashMap<String, Object> map, InputStream by){
        try{
            jreport = (JasperReport)JRLoader.loadObject(by);
            jprint = JasperFillManager.fillReport(jreport,map, (JRDataSource) connect);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void showReport(){
        JasperPrint jasperPrint = generateReport();
        JRViewer viewer = new JRViewer(jasperPrint);

        viewer.setVisible(true);
    }

    public static JasperPrint generateReport() {
        try {

            JasperPrint jasperPrint = null;


            try {
                /**You can also test this line if you want to display
                 * report from any absolute path other than the project root path*/
                //jasperPrint = JasperFillManager.fillReport("F:/testreport/"+reportName+".jasper",hm, con);
                jasperPrint = JasperFillManager.fillReport("C:\\Users\\MSI\\JaspersoftWorkspace\\test\\Invoice_1.jasper", hm, (Connection) con);
            } catch (JRException e) {
                e.printStackTrace();
            }
            return jasperPrint;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }


    }



}
