package presenters;

import models.Reservation;
import models.Table;

import java.util.Collection;
import java.util.Date;

public interface Model {

    Collection<Table> loadTables();

    int reservationTable(Date reservationDate, int tableNo, String name);

    Reservation findReservationTable(int reservationNo);
    boolean removeReservationTable(int oldReservation);

}

