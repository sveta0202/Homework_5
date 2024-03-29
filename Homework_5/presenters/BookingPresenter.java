package presenters;

// import models.TableService;
// import views.BookingView;
import exceptions.InvalidReservationNumberException;
import exceptions.InvalidTableNumberException;

import java.util.Date;

public class BookingPresenter implements ViewObserver {

    private Model model;
    private  View view;

    public BookingPresenter(Model model, View view){
        this.model = model;
        this.view = view;
        view.registerObserver(this);
    }

    public void updateTablesView(){
        view.showTables(model.loadTables());
    }

    private void updateReservationTableView(int reservationNo){
        view.showReservationTableResult(reservationNo);
    }

    private void updateRemoveReservationTableView(int oldReservation) {
        view.showRemoveReservationTableResult(oldReservation);
    }

    @Override
    public void onReservationTable(Date orderDate, int tableNo, String name) {
        try {
            int reservationNo = model.reservationTable(orderDate, tableNo, name);
            updateReservationTableView(reservationNo);
        }
        catch (InvalidTableNumberException e){
            updateReservationTableView(-1);
        }

    }

    @Override
    public void onChangeReservationTable(int oldReservation, Date reservationDate, int tableNo, String name) {
        try {
            model.removeReservationTable(oldReservation);
            updateRemoveReservationTableView(oldReservation);

            onReservationTable(reservationDate, tableNo, name);
        } catch (InvalidReservationNumberException e) {
            updateRemoveReservationTableView(-1);
        }
    }
}

