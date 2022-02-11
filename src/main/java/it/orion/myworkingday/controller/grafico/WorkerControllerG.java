package it.orion.myworkingday.controller.grafico;

import it.orion.myworkingday.controller.applicativo.LoadWorkerController;
import it.orion.myworkingday.controller.applicativo.WorkerController;
import it.orion.myworkingday.model.Worker;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class WorkerControllerG {

    private Scene calendarScene;

    private Stage stage;

    private Worker worker;

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
        worker = new Worker();

        initializePropertyBinding();

        LoadWorkerController loadWorkerController = new LoadWorkerController();

        loadWorkerController.loadWorker(worker);
    }

    public void initializePropertyBinding() {
        labelPropertyBinding();

        comboBoxPropertyBinding();

        buttonPropertyBinding();

        textFieldPropertyBinding();
    }

    public void labelPropertyBinding() {
        firstNameLabel.disableProperty().bind(worker.firstNameDisableProperty());
        lastNameLabel.disableProperty().bind(worker.lastNameDisableProperty());
        workLabel.disableProperty().bind(worker.workDisableProperty());
        defaultWorkingHoursLabel.disableProperty().bind(worker.defaultWorkingHoursDisableProperty());
        defaultWorkingHoursColonLabel.disableProperty().bind(worker.defaultWorkingHoursColonDisableProperty());
        salaryPerHourLabel.disableProperty().bind(worker.salaryPerHourDisableProperty());
        overtimeSalaryLabel.disableProperty().bind(worker.overtimeSalaryDisableProperty());
    }

    public void comboBoxPropertyBinding() {
        defaultWorkingHoursH.disableProperty().bind(worker.defaultWorkingHoursHDisableProperty());
        worker.defaultWorkingHoursHSelectionModelProperty().bind(defaultWorkingHoursH.selectionModelProperty());

        defaultWorkingHoursM.disableProperty().bind(worker.defaultWorkingHoursMDisableProperty());
        worker.defaultWorkingHoursMSelectionModelProperty().bind(defaultWorkingHoursM.selectionModelProperty());
    }

    public void buttonPropertyBinding() {
        editButton.disableProperty().bind(worker.editButtonDisableProperty());
        cancelButton.disableProperty().bind(worker.cancelButtonDisableProperty());
        saveButton.disableProperty().bind(worker.saveButtonDisableProperty());
    }

    public void textFieldPropertyBinding() {
        firstNameTextField.disableProperty().bind(worker.firstNameContentDisableProperty());
        firstNameTextField.textProperty().bindBidirectional(worker.firstNameContentProperty());

        lastNameTextField.disableProperty().bind(worker.lastNameContentDisableProperty());
        lastNameTextField.textProperty().bindBidirectional(worker.lastNameContentProperty());

        workTextField.disableProperty().bind(worker.workContentDisableProperty());
        workTextField.textProperty().bindBidirectional(worker.workContentProperty());

        salaryPerHourTextField.disableProperty().bind(worker.salaryPerHourContentDisableProperty());
        salaryPerHourTextField.textProperty().bindBidirectional(worker.salaryPerHourContentProperty());

        overtimeSalaryTextField.disableProperty().bind(worker.overtimeSalaryContentDisableProperty());
        overtimeSalaryTextField.textProperty().bindBidirectional(worker.overtimeSalaryContentProperty());
    }



    @FXML
    protected void onCalendarButtonClick() {
        stage.setScene(calendarScene);
    }

    @FXML
    protected void onEditButtonClick() {
        WorkerController controller = new WorkerController();

        controller.disableWorkingForm(worker, false);
    }

    @FXML
    public void onCancelButtonClick() {
        WorkerController controller = new WorkerController();

        controller.cancel(worker);
    }

    @FXML
    public void onSaveButtonClick() {
        WorkerController controller = new WorkerController();

        controller.save(worker);
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setCalendarScene(Scene calendarScene) {
        this.calendarScene = calendarScene;
    }

    public Worker getWorker() {
        return this.worker;
    }


}
