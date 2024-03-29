package models;

import presenters.Model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import exceptions.InvalidReservationNumberException;

public class TableService implements Model {

    private Collection<Table> tables;

    @Override
    public Collection<Table> loadTables() {
        if (tables == null) {
            tables = new ArrayList<>();

            tables.add(new Table());
            tables.add(new Table());
            tables.add(new Table());
            tables.add(new Table());
            tables.add(new Table());
            tables.add(new Table());
            tables.add(new Table());
            tables.add(new Table());
        }

        return tables;
    }

    @Override
    public int reservationTable(Date reservationDate, int tableNo, String name) {
        for (Table table : tables) {
            if (table.getNo() == tableNo) {
                Reservation reservation = new Reservation(table, reservationDate, name);
                table.getReservations().add(reservation);
                return reservation.getId();
            }
        }
        throw new RuntimeException("Некорректный номер столика");
    }


    @Override
    public Reservation findReservationTable(int reservationNo) {
        return tables.stream()
                .flatMap(t -> t.getReservations().stream())
                .filter(r -> r.getId() == reservationNo)
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean removeReservationTable(int oldReservation) {
        Reservation reservation = findReservationTable(oldReservation);
        if (reservation != null) {
            return reservation.getTable().getReservations().remove(reservation);
        }
        throw new InvalidReservationNumberException("Некорректный номер брони");
    }
}
