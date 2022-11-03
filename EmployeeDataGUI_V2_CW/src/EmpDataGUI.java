import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class EmpDataGUI extends Application {
    private BorderPane main = new BorderPane();	
    private BorderPane empData = new BorderPane();
    private static ListController controller = new ListController();
    // create private TextField variables for Name, SSN, Salary and Years
    // so that they can be accessed by methods inside this class.
	private TextField tfFName;
	private TextField tfLName;
	private TextField tfSSN;
	private TextField tfAge;
	private TextField tfPronouns;
	private TextField tfSalary;
	private TextField tfYears;
	private RadioButton rbEng;
	private RadioButton rbMktg;
	private RadioButton rbMfg;
	private RadioButton rbFin;
	private RadioButton rbHR;
	private RadioButton rbCustSupp;
	private RadioButton rbMgmt;
	private ToggleGroup tgDept = new ToggleGroup();	
    @Override
    public void start(Stage primaryStage) {
    
    	Scene scene = new Scene(main,400,500);
    	Scene scene2 = new Scene(empData,400,400);

    	main.setTop(new Label("EMPLOYEE DATA ENTRY"));
    	createAndAddGridPane();
  
        // Create Add Employee Button, and write the setOnAction handler to error check data then call controller
    	// to add the new Employee data
    	Button addEmp = new Button("Add Employee");
    	addEmp.setOnAction(e -> checkDataAddEmployee());
    	
    	Button viewEmp = new Button("View Employee");
    	viewEmp.setOnAction(e -> {viewEmployeeDB(); 
    							  primaryStage.setScene(scene2);
    	
    	});
    	
    	Button writeDB = new Button("Save Employee DB");
    	writeDB.setOnAction(e -> controller.writeObjectToDisk());
    	
    	HBox btnBox = new HBox(15);
    	btnBox.getChildren().addAll(addEmp, viewEmp,writeDB);
    	main.setBottom(btnBox);
   	
    	// add all the labels, textfields and button to gridpane main.        
        // setting scene2
        empData.setTop(new Label("Employee Data View"));
        HBox viewBtnBox = new HBox(15);
        Button back = new Button("Back");
        back.setOnAction(e -> primaryStage.setScene(scene));
        
        // buttons to sort the employee list either by employee ID or by name...
        RadioButton sortName = new RadioButton("Sort By Name");
        RadioButton sortID = new RadioButton("Sort By ID");

        sortName.setOnAction(e -> controller.sortByName());
        sortID.setOnAction(e -> controller.sortByID());
        viewEmployeeDB();
        
        // add buttons to the viewBtnBox along with the back button... 
        viewBtnBox.getChildren().add(back);
        viewBtnBox.getChildren().add(sortName);
        viewBtnBox.getChildren().add(sortID);
        empData.setBottom(viewBtnBox);
        
    	primaryStage.setTitle("Employees");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void createAndAddGridPane() {
    	GridPane gp = new GridPane();
    	// create Labels for Name, SSN, Salary and Years
    	Label lblFName = new Label("First Name: ");
    	Label lblLName = new Label("Last Name: ");
    	Label lblSSN = new Label("SSN: ");
    	Label lblAge = new Label("Age: ");
    	Label lblPronouns = new Label("Pronouns: ");
    	Label lblSalary = new Label("Salary: ");
	    Label lblYears = new Label("Years: ");
	    Label lblDept = new Label("Dept: ");
    	
    	// initialize TextFields (already created above) for Name, SSN, Salary and Years
	    tfFName = new TextField();
	    tfLName = new TextField();
    	tfSSN = new TextField();
    	tfAge = new TextField();
    	tfPronouns = new TextField();
    	tfSalary = new TextField();
    	tfYears = new TextField();
    	
    	// add the departments as RadioButtons
    	rbEng = new RadioButton("Engineering");
    	rbEng.setSelected(true);
    	rbMktg = new RadioButton("Marketing/Sales");
    	rbMfg = new RadioButton("Manufacturing");
    	rbFin = new RadioButton("Finance");
    	rbHR = new RadioButton("Human Resources");
    	rbCustSupp = new RadioButton("Customer Support");
    	rbMgmt = new RadioButton("Management");
    	

    	rbEng.setToggleGroup(tgDept);
    	rbMktg.setToggleGroup(tgDept);
    	rbMfg.setToggleGroup(tgDept);
    	rbFin.setToggleGroup(tgDept);
    	rbHR.setToggleGroup(tgDept);
    	rbCustSupp.setToggleGroup(tgDept);
    	rbMgmt.setToggleGroup(tgDept);
     	
    	VBox rbBox = new VBox(10);
    	rbBox.getChildren().addAll(rbEng, rbMktg, rbMfg, rbFin, rbHR, rbCustSupp, rbMgmt);
    

    	gp.add(lblLName,0, 0);
    	gp.add(lblFName,0,1);
        gp.add(lblSSN,0, 2);
        gp.add(lblAge,0,3);
        gp.add(lblPronouns,0,4);
        gp.add(lblSalary,0, 5);
        gp.add(lblYears,0, 6);
        gp.add(lblDept,0,7);
        
        gp.add(tfLName,1, 0);
        gp.add(tfFName,1, 1);
        gp.add(tfSSN,1, 2);
        gp.add(tfAge,1,3);
        gp.add(tfPronouns,1,4);
        gp.add(tfSalary,1,5);
        gp.add(tfYears,1, 6);
        gp.add(rbBox,1,7);
        main.setCenter(gp);
    }
    
    // Get a String array of all employee information (one record per String)
    // put it in a listview and add it to the borderpane.
    private void viewEmployeeDB() {
    	String[] empDataStr = controller.getEmployeeDataStr();
    	ListView<String> lv = new ListView<>(FXCollections.observableArrayList(empDataStr));
    	lv.setPrefWidth(400);
    	empData.setCenter(new ScrollPane(lv));
    }
    
    // checks if any of the textfields are blank - if they are - raises an alert
    // otherwise adds the employee and clears the text fields...
    private void checkDataAddEmployee() {
    	if (tfFName.getText().isEmpty() ||
       		tfLName.getText().isEmpty() ||
    		tfSSN.getText().isEmpty() ||
    		tfAge.getText().isEmpty() ||
    		tfPronouns.getText().isEmpty() ||
    		tfSalary.getText().isEmpty() || 
    		tfYears.getText().isEmpty()) {
    		// something is blank
    		Alert alert = new Alert(AlertType.WARNING);
    		alert.setHeaderText("Add Employee Failed!");
    		alert.setContentText("Make sure that all text fields are populated.");
    		alert.showAndWait();
    	} else {
    		controller.addEmployee(tfFName.getText(), tfLName.getText(),tfSSN.getText(), tfAge.getText(),
    				               tfPronouns.getText(),tfSalary.getText(), tfYears.getText(), getSelectedDept());
    		clearTextFields();
    	}
    }
    
    private String getSelectedDept() {
    	RadioButton selectedToggle = (RadioButton) tgDept.getSelectedToggle();
    	return selectedToggle.getText();
    }
    
    private void clearTextFields() {
    	tfFName.clear();
    	tfLName.clear();
    	tfAge.clear();
    	tfPronouns.clear();
    	tfSSN.clear();
    	tfSalary.clear();
    	tfYears.clear();
    	rbEng.setSelected(true);
    }
    
  /**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		controller.initializeDataStructures(); // restore the employees array from disk if it exists
		Application.launch(args);
	}

} ;