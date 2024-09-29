import com.lld.hotel.reservation.system.service.room.RoomType;
import com.lld.hotel.reservation.system.model.Guest;
import com.lld.hotel.reservation.system.model.Reservation;
import com.lld.hotel.reservation.system.model.Room;
import com.lld.hotel.reservation.system.service.payment.paymentmethods.PaymentMethod;
import com.lld.hotel.reservation.system.service.guest.GuestService;
import com.lld.hotel.reservation.system.service.guest.GuestServiceImpl;
import com.lld.hotel.reservation.system.service.payment.PaymentService;
import com.lld.hotel.reservation.system.service.payment.PaymentServiceImpl;
import com.lld.hotel.reservation.system.service.report.ReportService;
import com.lld.hotel.reservation.system.service.report.ReportServiceImpl;
import com.lld.hotel.reservation.system.service.reservation.ReservationServiceImpl;
import com.lld.hotel.reservation.system.service.room.RoomService;
import com.lld.hotel.reservation.system.service.room.RoomServiceImpl;
import java.time.LocalDate;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HotelManagementSystem {
    private final RoomService roomService;
    private final GuestService guestService;
    private final ReservationServiceImpl reservationService;
    private final PaymentService paymentService;
    private final ReportService reportService;

    public HotelManagementSystem() {
        this.roomService = new RoomServiceImpl();
        this.guestService = new GuestServiceImpl();
        this.reservationService = new ReservationServiceImpl(roomService, guestService);
        this.paymentService = new PaymentServiceImpl();
        this.reportService = new ReportServiceImpl(reservationService);
    }

    //Room Management
    public void addRoom(int roomNumber, RoomType roomType) {
        Room room = new Room(roomNumber, roomType);
        roomService.addRoom(room);
    }

    //Guest Management
    public void addGuest(String guestId, String name, String contactInfo) {
        Guest guest = new Guest(guestId, name, contactInfo);
        guestService.addGuest(guest);
    }

    public Guest getGuest(String guestId) {
        return guestService.getGuest(guestId);
    }

    //Reservation Management
    public String bookRoom(String guestId, RoomType roomType, LocalDate checkIn, LocalDate checkOut) throws Exception {
        Guest guest = guestService.getGuest(guestId);
        if (guest == null) {
            throw new Exception("Guest not found.");
        }
        Reservation reservation = reservationService.bookRoom(guest, roomType, checkIn, checkOut);
        log.info("Room booked with Reservation ID: " + reservation.getReservationId());
        return reservation.getReservationId();
    }

    public void checkIn(String reservationId) throws Exception {
        reservationService.checkIn(reservationId);
    }

    public void checkOut(String reservationId) throws Exception {
        reservationService.checkOut(reservationId);
    }

    // Payment Processing
    public void processPayment(PaymentMethod paymentMethod, double amount) {
        paymentService.processPayment(paymentMethod, amount);
    }

    // Reporting
    public void generateOccupancyReport() {
        reportService.generateOccupancyReport();
    }

    public void generateRevenueReport() {
        reportService.generateRevenueReport();
    }
}