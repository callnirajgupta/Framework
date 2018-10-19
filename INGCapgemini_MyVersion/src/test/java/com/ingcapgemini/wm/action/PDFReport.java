package com.ingcapgemini.wm.action;

import java.awt.print.PrinterException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;

import com.github.mkolisnyk.cucumber.reporting.CucumberConsolidatedReport;
import com.github.mkolisnyk.cucumber.reporting.CucumberDetailedResults;
import com.github.mkolisnyk.cucumber.reporting.CucumberFeatureOverview;
import com.google.common.io.Files;
import com.itextpdf.text.Document;
import com.itextpdf.text.html.simpleparser.HTMLWorker;
import com.itextpdf.text.pdf.PdfWriter;
//import com.openhtmltopdf.DOMBuilder;
//import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;

import io.woo.htmltopdf.HtmlToPdf;
import io.woo.htmltopdf.HtmlToPdfObject;
import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import net.masterthought.cucumber.Reportable;

/*import com.github.mkolisnyk.cucumber.reporting.CucumberDetailedResults;*/

public class PDFReport {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		/*CucumberDetailedResults results = new CucumberDetailedResults();
        results.setOutputDirectory("target/");
        results.setOutputName("cucumber-results");
        results.setSourceFile("./target/cucumber.json");
        results.setScreenShotLocation("../src/test/resources/");
        results.executeDetailedResultsReport(true);*/
        
		
		/*CucumberDetailedResults results = new CucumberDetailedResults();
		//CucumberFeatureOverview results = new CucumberFeatureOverview();
		results.setOutputDirectory("target");
		results.setOutputName("cucumber-results");
		results.setSourceFile("./target/cucumber.json");
		results.execute(true);*/
		
		
		// consolidated report
	/*	String stringarray[]={"fff","fsfd"};
		CucumberConsolidatedReport results = new CucumberConsolidatedReport();
		results.setOutputDirectory("target");
		results.setOutputName("cucumber-results");
		results.setPdfPageSize("A4 landscape");
		results.setSourceFile("./src/test/resources/cucumber.json");
		
		results.execute(
			new File("./src/test/resources/consolidated-source/sample_batch.json"),
			true
		);
		*/
		
	
		
		/*File reportOutputDirectory = new File("target");
		List<String> jsonFiles = new ArrayList<>();
		
		File file = new File(System.getProperty("user.dir")+"\\target");
        String[] fileList = file.list();
     
        for(String name:fileList){
        	if(name.endsWith(".json")){
        		
        		jsonFiles.add(System.getProperty("user.dir")+"\\target\\"+name);
        		
        	}
            
        }
		//jsonFiles.add(System.getProperty("user.dir")+"\\target\\cucumber.json");
		//jsonFiles.add(System.getProperty("user.dir")+"\\target\\cucumber2.json");

		String buildNumber = "1";
		String projectName = "cucumberProject";
		boolean runWithJenkins = false;
		boolean parallelTesting = false;

		Configuration configuration = new Configuration(reportOutputDirectory, projectName);
		// optional configuration - check javadoc
		configuration.setRunWithJenkins(runWithJenkins);
		configuration.setBuildNumber(buildNumber);
	
		// addidtional metadata presented on main page
		configuration.addClassifications("Platform", "Windows");
		configuration.addClassifications("Browser", "Firefox");
		configuration.addClassifications("Branch", "release/1.0");

		// optionally add metadata presented on main page via properties file
		List<String> classificationFiles = new ArrayList<>();
		classificationFiles.add("properties-1.properties");
		classificationFiles.add("properties-2.properties");
		configuration.addClassificationFiles(classificationFiles);

		ReportBuilder reportBuilder = new ReportBuilder(jsonFiles, configuration);
		Reportable result = reportBuilder.generateReports();
		// and here validate 'result' to decide what to do
		// if report has failed features, undefined steps etc
		System.out.println("finished");*/
	
		// to generate PDF using openPDF
/*String html = FileUtils.readFileToString(new File("E:\\Project\\INGCapgemini_MyVersion\\target\\cucumber-html-reports\\overview-features.html"), "UTF-8");
		
		OutputStream os = new FileOutputStream("target/mytest-124.pdf");
		PdfRendererBuilder builder = new PdfRendererBuilder();
		org.w3c.dom.Document doc = DOMBuilder.jsoup2DOM(Jsoup.parse(html));
		builder.withW3cDocument(doc, "xxx");
		builder.toStream(os);
		builder.run();*/
		String path="target\\cucumber-html-reports\\";
		Iterator it=FileUtils.iterateFiles(new File(path), null, false);
		
		HtmlToPdf h=HtmlToPdf.create();
		List<HtmlToPdfObject> objs=Collections.synchronizedList(new ArrayList<HtmlToPdfObject>());
		
		objs.add(HtmlToPdfObject.forUrl(path+"overview-features.html"));
		while(it.hasNext()){
			File f=(File) it.next();
			if(Files.getFileExtension(f.getAbsolutePath()).equalsIgnoreCase("html") && f.getName().startsWith("report-feature")){
				System.out.println(f.getAbsolutePath());
				objs.add(HtmlToPdfObject.forUrl(f.getAbsolutePath()));
			}
		}
				
				
				
		objs.add(HtmlToPdfObject.forUrl(path+"overview-failures.html"));		
		for(HtmlToPdfObject htmlToPdfObject:objs){
			h.object(htmlToPdfObject);
		}
		boolean success=h.convert("target//"+"sample_details_report.pdf");
		System.out.println(success? "PDF report generated": "pdf report generation failed");
				
				
				
				
				
				
				
				
				
				
				
				
				
	}
}
