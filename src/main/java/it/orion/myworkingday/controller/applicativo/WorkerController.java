package it.orion.myworkingday.controller.applicativo;

import it.orion.myworkingday.model.Worker;

public class WorkerController {

    public void disableWorkingForm(boolean b) {

        Worker.getInstance().setEditButtonDisable(!b);
        Worker.getInstance().setCancelButtonDisable(b);
        Worker.getInstance().setSaveButtonDisable(b);

        Worker.getInstance().setFirstNameDisable(b);
        Worker.getInstance().setLastNameDisable(b);
        Worker.getInstance().setWorkDisable(b);
        Worker.getInstance().setDefaultWorkingHoursDisable(b);
        Worker.getInstance().setDefaultWorkingHoursColonDisable(b);
        Worker.getInstance().setSalaryPerHourDisable(b);
        Worker.getInstance().setOvertimeSalaryDisable(b);
        Worker.getInstance().setFirstNameContentDisable(b);
        Worker.getInstance().setLastNameContentDisable(b);
        Worker.getInstance().setWorkContentDisable(b);
        Worker.getInstance().setSalaryPerHourContentDisable(b);
        Worker.getInstance().setOvertimeSalaryContentDisable(b);
        Worker.getInstance().setDefaultWorkingHoursHDisable(b);
        Worker.getInstance().setDefaultWorkingHoursMDisable(b);
    }

    public void cancel() {
        if(Worker.getInstance().isLoad()){
            LoadWorkerController controller = new LoadWorkerController();

            controller.loadWorker();
        } else {
            clearForm();
        }

        disableWorkingForm(true);
    }

    public void save() {
        SaveWorkerController controller = new SaveWorkerController();

        if(controller.saveWorker()) {
            clearForm();

            disableWorkingForm(true);

            LoadWorkerController loadController = new LoadWorkerController();

            loadController.loadWorker();
        }
    }

    public void clearForm() {
        Worker.getInstance().setFirstNameContent(null);
        Worker.getInstance().setLastNameContent(null);
        Worker.getInstance().setWorkContent(null);
        Worker.getInstance().getDefaultWorkingHoursHSelectionModel().clearSelection();
        Worker.getInstance().getDefaultWorkingHoursMSelectionModel().clearSelection();
        Worker.getInstance().setSalaryPerHourContent(null);
        Worker.getInstance().setOvertimeSalaryContent(null);
    }
}
