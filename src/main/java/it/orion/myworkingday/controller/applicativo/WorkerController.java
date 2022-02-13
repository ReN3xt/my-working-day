package it.orion.myworkingday.controller.applicativo;

import it.orion.myworkingday.model.Worker;

public class WorkerController {

    public void disableWorkingForm(Worker worker, boolean b) {

        worker.setEditButtonDisable(!b);
        worker.setCancelButtonDisable(b);
        worker.setSaveButtonDisable(b);

        worker.setFirstNameDisable(b);
        worker.setLastNameDisable(b);
        worker.setWorkDisable(b);
        worker.setDefaultWorkingHoursDisable(b);
        worker.setDefaultWorkingHoursColonDisable(b);
        worker.setSalaryPerHourDisable(b);
        worker.setOvertimeSalaryDisable(b);
        worker.setRemindersDisable(b);
        worker.setRemindersColonDisable(b);
        worker.setFirstNameContentDisable(b);
        worker.setLastNameContentDisable(b);
        worker.setWorkContentDisable(b);
        worker.setSalaryPerHourContentDisable(b);
        worker.setOvertimeSalaryContentDisable(b);
        worker.setDefaultWorkingHoursHDisable(b);
        worker.setDefaultWorkingHoursMDisable(b);
        worker.setRemindersHDisable(b);
        worker.setRemindersMDisable(b);
    }

    public void cancel(Worker worker) {
        if(worker.isLoad()){
            LoadWorkerController controller = new LoadWorkerController();

            controller.loadWorker(worker);
        } else {
            clearForm(worker);
        }

        disableWorkingForm(worker, true);
    }

    public void save(Worker worker) {
        SaveWorkerController controller = new SaveWorkerController();

        if(controller.saveWorker(worker)) {
            clearForm(worker);

            disableWorkingForm(worker, true);

            LoadWorkerController loadController = new LoadWorkerController();

            loadController.loadWorker(worker);
        }
    }

    public void clearForm(Worker worker) {
        worker.setFirstNameContent(null);
        worker.setLastNameContent(null);
        worker.setWorkContent(null);
        worker.getDefaultWorkingHoursHSelectionModel().clearSelection();
        worker.getDefaultWorkingHoursMSelectionModel().clearSelection();
        worker.setSalaryPerHourContent(null);
        worker.setOvertimeSalaryContent(null);
        worker.getRemindersHSelectionModel().clearSelection();
        worker.getRemindersMSelectionModel().clearSelection();
    }
}
