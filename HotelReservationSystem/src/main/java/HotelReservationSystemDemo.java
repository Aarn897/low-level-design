import com.lld.hotel.reservation.system.service.room.RoomType;
import com.lld.hotel.reservation.system.service.payment.paymentmethods.CashPayment;
import com.lld.hotel.reservation.system.service.payment.paymentmethods.CreditCardPayment;
import java.time.LocalDate;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HotelReservationSystemDemo {

    public static void main(String[] args) {
        HotelManagementSystem hms = new HotelManagementSystem();

        // Adding Rooms
        hms.addRoom(101, RoomType.SINGLE);
        hms.addRoom(102, RoomType.DOUBLE);
        hms.addRoom(201, RoomType.DELUXE);
        hms.addRoom(301, RoomType.SUITE);

        // Adding Guests
        hms.addGuest("G001", "Ankita Ranjan", "ankita@example.com");
        hms.addGuest("G002", "Pooja Jain", "pooja@example.com");

        // Booking Rooms Concurrently
        ExecutorService executor = Executors.newFixedThreadPool(2);
        Runnable task1 = () -> {
            try {
                String resId = hms.bookRoom("G001", RoomType.SINGLE, LocalDate.now(), LocalDate.now().plusDays(3));
                hms.processPayment(new CashPayment(), 300.0);
                hms.checkIn(resId);
                hms.checkOut(resId);
            } catch (Exception e) {
                log.info("Task1: " + e.getMessage());
            }
        };

        Runnable task2 = () -> {
            try {
                String resId = hms.bookRoom("G002", RoomType.SINGLE, LocalDate.now(), LocalDate.now().plusDays(2));
                hms.processPayment(new CreditCardPayment("1234-5678-9012-3456"), 200.0);
                hms.checkIn(resId);
                hms.checkOut(resId);
            } catch (Exception e) {
                log.info("Task2: " + e.getMessage());
            }
        };

        executor.submit(task1);
        executor.submit(task2);

        executor.shutdown();
        try {
            executor.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Generating Reports
        hms.generateOccupancyReport();
        hms.generateRevenueReport();
    }
}