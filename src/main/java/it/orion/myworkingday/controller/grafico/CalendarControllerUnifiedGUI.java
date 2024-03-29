package it.orion.myworkingday.controller.grafico;

import it.orion.myworkingday.Main;
import it.orion.myworkingday.controller.applicativo.CalendarController;
import it.orion.myworkingday.controller.applicativo.SalaryController;
import it.orion.myworkingday.model.Calendar;
import it.orion.myworkingday.model.Worker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CalendarControllerUnifiedGUI {

    private Stage stage;

    private Scene dayScene;
    private Scene workerScene;

    private Button[] days;

    DayControllerGUI dayController;

    WorkerControllerGUI workerController;

    @FXML
    public Button nextMonth;

    @FXML
    public Button prevMonth;

    @FXML
    public Button nextYear;

    @FXML
    public Button prevYear;

    @FXML
    private Button day1;

    @FXML
    private Button day2;

    @FXML
    private Button day3;

    @FXML
    private Button day4;

    @FXML
    private Button day5;

    @FXML
    private Button day6;

    @FXML
    private Button day7;

    @FXML
    private Button day8;

    @FXML
    private Button day9;

    @FXML
    private Button day10;

    @FXML
    private Button day11;

    @FXML
    private Button day12;

    @FXML
    private Button day13;

    @FXML
    private Button day14;

    @FXML
    private Button day15;

    @FXML
    private Button day16;

    @FXML
    private Button day17;

    @FXML
    private Button day18;

    @FXML
    private Button day19;

    @FXML
    private Button day20;

    @FXML
    private Button day21;

    @FXML
    private Button day22;

    @FXML
    private Button day23;

    @FXML
    private Button day24;

    @FXML
    private Button day25;

    @FXML
    private Button day26;

    @FXML
    private Button day27;

    @FXML
    private Button day28;

    @FXML
    private Button day29;

    @FXML
    private Button day30;

    @FXML
    private Button day31;

    @FXML
    private Button day32;

    @FXML
    private Button day33;

    @FXML
    private Button day34;

    @FXML
    private Button day35;

    @FXML
    private Button day36;

    @FXML
    private Button day37;

    @FXML
    private Label yearText;

    @FXML
    private Label monthText;

    @FXML
    private Label monthValueText;

    @FXML
    private TextField estimateSalary;

    @FXML
    private Button workerButton;

    public void initialize() {

        initializeButtonArray();

        initializePropertyBinding();

        CalendarController calendarController = new CalendarController();

        // Adjust calendar based on month and year
        calendarController.updateCalendar();

        // Load FXML of Worker View
        FXMLLoader workerFxml = Main.getFxmlLoader("workerView.fxml");

        // Create a Scene for Day View
        setWorkerScene(Main.getScene(workerFxml));

        // Get Worker View Controller Reference
        workerController = workerFxml.getController();
    }

    public void initializeButtonArray() {

        days = new Button[37];

        days[0] = day1;
        days[1] = day2;
        days[2] = day3;
        days[3] = day4;
        days[4] = day5;
        days[5] = day6;
        days[6] = day7;
        days[7] = day8;
        days[8] = day9;
        days[9] = day10;
        days[10] = day11;
        days[11] = day12;
        days[12] = day13;
        days[13] = day14;
        days[14] = day15;
        days[15] = day16;
        days[16] = day17;
        days[17] = day18;
        days[18] = day19;
        days[19] = day20;
        days[20] = day21;
        days[21] = day22;
        days[22] = day23;
        days[23] = day24;
        days[24] = day25;
        days[25] = day26;
        days[26] = day27;
        days[27] = day28;
        days[28] = day29;
        days[29] = day30;
        days[30] = day31;
        days[31] = day32;
        days[32] = day33;
        days[33] = day34;
        days[34] = day35;
        days[35] = day36;
        days[36] = day37;
    }

    public void initializePropertyBinding() {

        estimateSalary.textProperty().bind(Calendar.getInstance().monthSalaryProperty());

        yearText.textProperty().bind(Calendar.getInstance().yearProperty());

        monthText.textProperty().bind(Calendar.getInstance().monthProperty());

        monthValueText.textProperty().bind(Calendar.getInstance().monthValueProperty());

        for (int i = 0; i < 37; i++) {
            days[i].textProperty().bind(Calendar.getInstance().daysProperty(i));
            days[i].visibleProperty().bind(Calendar.getInstance().daysVisibilityProperty(i));
            days[i].underlineProperty().bind(Calendar.getInstance().daysUnderlineProperty(i));
            days[i].textFillProperty().bind(Calendar.getInstance().daysColorProperty(i));
        }
    }

    @FXML
    protected void onResetClick() {
        CalendarController calendarController = new CalendarController();

        calendarController.resetCalendar();
        calendarController.updateColor();
    }

    @FXML
    protected void onPrevMonthClick() {
        CalendarController calendarController = new CalendarController();

        calendarController.updateSelectedDate("month", "prev");
        calendarController.updateColor();
    }

    @FXML
    protected void onNextMonthClick() {
        CalendarController calendarController = new CalendarController();

        calendarController.updateSelectedDate("month", "next");
        calendarController.updateColor();
    }

    @FXML
    protected void onPrevYearClick() {
        CalendarController calendarController = new CalendarController();

        calendarController.updateSelectedDate("year", "prev");
        calendarController.updateColor();
    }

    @FXML
    protected void onNextYearClick() {
        CalendarController calendarController = new CalendarController();

        calendarController.updateSelectedDate("year", "next");
        calendarController.updateColor();
    }

    @FXML
    protected void onWorkerButtonClick() {

        // Pass Stage to Worker Controller
        workerController.setStage(stage);

        // Pass Calendar Scene to Worker Controller
        workerController.setCalendarScene(workerButton.getScene());

        stage.setScene(workerScene);
    }

    @FXML
    protected void onDayButtonClick(ActionEvent e) {

        // Load FXML of Day View
        FXMLLoader dayFxml = Main.getFxmlLoader("dayView.fxml");

        // Create a Scene for Day View
        setDayScene(Main.getScene(dayFxml));

        // Get Day View Controller Reference
        dayController = dayFxml.getController();

        // Pass Stage to Day Controller
        dayController.setStage(stage);

        // Pass Calendar Scene Reference to Day Controller
        dayController.setCalendarScene(day1.getScene());

        dayController.loadDay(((Button) e.getSource()).getText());

        stage.setScene(dayScene);
    }

    @FXML
    protected void onSalaryButtonClick() {
        SalaryController controller = new SalaryController();

        controller.calculateSalary();
    }

    @FXML
    protected void onSwitchInterfaceButtonClick() {

        FXMLLoader calendarFxml;

        if(Calendar.getInstance().isSecondView()) {
            calendarFxml = Main.getFxmlLoader("calendarPrimaryView.fxml");
        } else {
            calendarFxml = Main.getFxmlLoader("calendarSecondaryView.fxml");
        }

        Scene calendarScene = Main.getScene(calendarFxml);

        CalendarControllerUnifiedGUI calendarController = calendarFxml.getController();

        calendarController.setStage(stage);
        calendarController.setSecondView(!Calendar.getInstance().isSecondView());

        stage.setScene(calendarScene);
    }

    public void setSecondView(boolean b) {
        Calendar.getInstance().setSecondView(b);

        CalendarController calendarController = new CalendarController();

        calendarController.updateColor();
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }


    public void setDayScene(Scene dayScene) {
        this.dayScene = dayScene;
    }

    public void setWorkerScene(Scene workerScene) {
        this.workerScene = workerScene;
    }
}