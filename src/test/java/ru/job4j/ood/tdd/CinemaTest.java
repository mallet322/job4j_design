package ru.job4j.ood.tdd;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import ru.job4j.ood.tdd.cinema.Account;
import ru.job4j.ood.tdd.cinema.Cinema;
import ru.job4j.ood.tdd.cinema.Session;
import ru.job4j.ood.tdd.cinema.Ticket;
import ru.job4j.ood.tdd.cinema.impl.AccountCinema;
import ru.job4j.ood.tdd.cinema.impl.Cinema3D;
import ru.job4j.ood.tdd.cinema.impl.Session3D;
import ru.job4j.ood.tdd.cinema.impl.Ticket3D;

import java.util.Calendar;
import java.util.List;

@Ignore
public class CinemaTest {

    @Test
    public void whenBuyTicket() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2020, 10, 10, 23, 00);
        Ticket ticket = cinema.buy(account, 1, 1, date);
        Assert.assertThat(ticket, Matchers.is(new Ticket3D()));
    }

    /**
     * Покупка билета с просроченной датой, должно свалиться с ошибкой.
     * Либо мы предусматриваем такое поведение и кидаем exception.
     */
    @Test(expected = IllegalArgumentException.class)
    public void whenBuyIncorrectTicketWithOverdueDateThenExpectedError() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(1900, 10, 10, 23, 00);
        Ticket ticket = cinema.buy(account, 1, 1, date);
    }

    /**
     * Покупка билета на место, которое уже зарезервировано, должно свалиться с ошибкой.
     * Либо мы предусматриваем такое поведение и кидаем exception.
     */
    @Test(expected = IllegalArgumentException.class)
    public void whenBuyIncorrectTicketOnReservedSeatThenExpectedError() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2022, 1, 10, 23, 00);
        Ticket ticket = cinema.buy(account, 1, 1, date);
        Calendar anotherDate = Calendar.getInstance();
        anotherDate.set(2022, 1, 10, 23, 00);
        Ticket anotherTicket = cinema.buy(account, 1, 1, date);
    }

    /**
     * Покупка билета на невалидное место, должно свалиться с ошибкой.
     * Либо мы предусматриваем такое поведение и кидаем exception.
     */
    @Test(expected = IllegalArgumentException.class)
    public void whenBuyIncorrectTicketOnInvalidSeatThenExpectedError() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(1900, 10, 10, 23, 00);
        Ticket ticket = cinema.buy(account, 1, 1, date);
    }

    @Test
    public void whenFindWithTrueCondition() {
        Cinema cinema = new Cinema3D();
        Session session = new Session3D();
        cinema.add(session);
        List<Session> sessions = cinema.find(s -> true);
        Assert.assertThat(sessions, Matchers.is(List.of(session)));
    }

    @Test
    public void whenFindWithFalseCondition() {
        Cinema cinema = new Cinema3D();
        Session session = new Session3D();
        cinema.add(session);
        List<Session> sessions = cinema.find(s -> false);
        Assert.assertThat(sessions, Matchers.is(List.of(session)));
    }

    /**
     * Добавление нескольких сеансов. (Например в разное время).
     */
    @Test
    public void addSomeSessions() {
        Session expectedSession1 = new Session3D();
        Session expectedSession2 = new Session3D();
        Cinema cinema = new Cinema3D();
        cinema.add(expectedSession1);
        cinema.add(expectedSession2);
        Session actualSession1 = cinema.find(session -> true).get(0);
        Session actualSession2 = cinema.find(session -> true).get(1);
        Assert.assertEquals(expectedSession1, actualSession1);
        Assert.assertEquals(expectedSession2, actualSession2);
    }

    /**
     * Отмена покупки билета. (Если полльзователь ошибся или резко передмал идти на сеанс).
     * Предполагает добавление метода для отмены покупки.
     */
    @Test
    public void cancelTicketPurchase() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2022, 10, 10, 23, 00);
    }

    /**
     * Возврат денежных средств за билеты.
     * Предполагает добавление метода для возврата.
     */
    @Test
    public void refundOfMoneyForTickets() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2022, 10, 10, 23, 00);
    }

}