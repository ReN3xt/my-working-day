package it.orion.myworkingday.controller.grafico;

import it.orion.myworkingday.controller.applicativo.LoadWorkerController;
import it.orion.myworkingday.controller.applicativo.WorkerController;
import it.orion.myworkingday.model.Worker;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class WorkerControllerGUI {

    private Scene calendarScene;

    private Stage stage;

    @FXML
    private Label firstNameLabel;

    @FXML
    private Label lastNameLabel;

    @FXML
    private Label workLabel;

    @FXML
    private Label defaultWorkingHoursLabel;

    @FXML
    private Label defaultWorkingHoursColonLabel;

    @FXML
    private Label salaryPerHourLabel;

    @FXML
    private Label overtimeSalaryLabel;

    @FXML
    private TextField firstNameTextField;

    @FXML
    private TextField lastNameTextField;

    @FXML
    private TextField workTextField;

    @FXML
    private TextField salaryPerHourTextField;

    @FXML
    private TextField overtimeSalaryTextField;

    @FXML
    private ComboBox<String> defaultWorkingHoursH;

    @FXML
    private ComboBox<String> defaultWorkingHoursM;

    @FXML
    private Button editButton;

    @FXML
    private Button cancelButton;

    @FXML
    private Button saveButton;

    public void initialize() {

        initializePropertyBinding();

        LoadWorkerController loadWorkerController = new LoadWorkerController();

        loadWorkerController.loadWorker();
    }

    public void initializePropertyBinding() {
        labelPropertyBinding();

        comboBoxPropertyBinding();

        buttonPropertyBinding();

        textFieldPropertyBinding();
    }

    public void labelPropertyBinding() {
        firstNameLabel.disableProperty().bind(Worker.getInstance().firstNameDisableProperty());
        lastNameLabel.disableProperty().bind(Worker.getInstance().lastNameDisableProperty());
        workLabel.disableProperty().bind(Worker.getInstance().workDisableProperty());
        defaultWorkingHoursLabel.disableProperty().bind(Worker.getInstance().defaultWorkingHoursDisableProperty());
        defaultWorkingHoursColonLabel.disableProperty().bind(Worker.getInstance().defaultWorkingHoursColonDisableProperty());
        salaryPerHourLabel.disableProperty().bind(Worker.getInstance().salaryPerHourDisableProperty());
        overtimeSalaryLabel.disableProperty().bind(Worker.getInstance().overtimeSalaryDisableProperty());
    }

    public void comboBoxPropertyBinding() {
        defaultWorkingHoursH.disableProperty().bind(Worker.getInstance().defaultWorkingHoursHDisableProperty());
        Worker.getInstance().defaultWorkingHoursHSelectionModelProperty().bind(defaultWorkingHoursH.selectionModelProperty());

        defaultWorkingHoursM.disableProperty().bind(Worker.getInstance().defaultWorkingHoursMDisableProperty());
        Worker.getInstance().defaultWorkingHoursMSelectionModelProperty().bind(defaultWorkingHoursM.selectionModelProperty());
    }

    public void buttonPropertyBinding() {
        editButton.disableProperty().bind(Worker.getInstance().editButtonDisableProperty());
        cancelButton.disableProperty().bind(Worker.getInstance().cancelButtonDisableProperty());
        saveButton.disableProperty().bind(Worker.getInstance().saveButtonDisableProperty());
    }

    public void textFieldPropertyBinding() {
        firstNameTextField.disableProperty().bind(Worker.getInstance().firstNameContentDisableProperty());
        firstNameTextField.textProperty().bindBidirectional(Worker.getInstance().firstNameContentProperty());

        lastNameTextField.disableProperty().bind(Worker.getInstance().lastNameContentDisableProperty());
        lastNameTextField.textProperty().bindBidirectional(Worker.getInstance().lastNameContentProperty());

        workTextField.disableProperty().bind(Worker.getInstance().workContentDisableProperty());
        workTextField.textProperty().bindBidirectional(Worker.getInstance().workContentProperty());

        salaryPerHourTextField.disableProperty().bind(Worker.getInstance().salaryPerHourContentDisableProperty());
        salaryPerHourTextField.textProperty().bindBidirectional(Worker.getInstance().salaryPerHourContentProperty());

        overtimeSalaryTextField.disableProperty().bind(Worker.getInstance().overtimeSalaryContentDisableProperty());
        overtimeSalaryTextField.textProperty().bindBidirectional(Worker.getInstance().overtimeSalaryContentProperty());
    }

    @FXML
    protected void onCalendarButtonClick() {
        WorkerController controller = new WorkerController();

        controller.cancel();

        stage.setScene(calendarScene);
    }

    @FXML
    protected void onEditButtonClick() {
        WorkerController controller = new WorkerController();

        controller.disableWorkingForm(false);
    }

    @FXML
    public void onCancelButtonClick() {
        WorkerController controller = new WorkerController();

        controller.cancel();
    }

    @FXML
    public void onSaveButtonClick() {
        WorkerController controller = new WorkerController();

        controller.save();
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setCalendarScene(Scene calendarScene) {
        this.calendarScene = calendarScene;
    }

}
