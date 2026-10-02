package org.example;

import java.util.Comparator;

public class TicketTimeComparator implements Comparator<Ticket> {

    @Override
    public int compare(Ticket t1, Ticket t2) {
        // Вычисляем время полета для каждого билета (время прилета - время вылета)
        int flightTime1 = t1.getTimeTo() - t1.getTimeFrom();
        int flightTime2 = t2.getTimeTo() - t2.getTimeFrom();

        // Сравниваем время полета.
        // Integer.compare возвращает отрицательное число, если первое меньше второго,
        // 0, если равны, и положительное, если первое больше второго.
        return Integer.compare(flightTime1, flightTime2);
    }
}
