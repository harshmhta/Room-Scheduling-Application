
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.JOptionPane;

public class ReservationQueries {

    private static Connection connection;
    private static PreparedStatement addReservation;
    private static PreparedStatement select_reservations;
    private static PreparedStatement select_all_rooms;
    private static ResultSet data_results_set;
    private static String roomReserved;

    private static ArrayList<RoomEntry> rooms;

    public static ArrayList<ReservationEntry> getReservationsByDate(Date date) {

        connection = DBConnection.getConnection();

        ArrayList<ReservationEntry> reservation = new ArrayList<>();

        try {
            PreparedStatement select_reservations = connection.prepareStatement("select faculty, room, seats, timestamp from reservations where date = ?");
            select_reservations.setDate(1, date);
            data_results_set = select_reservations.executeQuery();

            while (data_results_set.next()) {
                ReservationEntry reserved = new ReservationEntry(data_results_set.getString(1), data_results_set.getString(2), date, data_results_set.getInt(3), data_results_set.getTimestamp(4));
                reservation.add(reserved);
            }
        } catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, sqlException);
        }
        return reservation;
    }

    /**
     * getRoomsReservedByDate(Date date)
     *
     * @param date
     * @return
     */
    public static ArrayList<RoomEntry> getRoomsReservedByDate(Date date) {

        connection = DBConnection.getConnection();
        ArrayList<RoomEntry> rooms = new ArrayList<>();

        try {
            PreparedStatement select_all_rooms_by_date = connection.prepareStatement("select room, seats from reservations where date = ?");
            select_all_rooms_by_date.setDate(1, date);
            data_results_set = select_all_rooms_by_date.executeQuery();

            while (data_results_set.next()) {
                RoomEntry room = new RoomEntry(data_results_set.getString(1), data_results_set.getInt(2));
                rooms.add(room);
            }
        } catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, sqlException);
        }
        return rooms;
    }
    
    
    public static ArrayList<ReservationEntry> getReservedByRoom(String room) {

        connection = DBConnection.getConnection();
        ArrayList<ReservationEntry> reservations = new ArrayList<>();

        try {
            PreparedStatement select_all_rooms_by_date = connection.prepareStatement("select faculty, room, date, seats from reservations where room = ?");
            select_all_rooms_by_date.setString(1, room);
            data_results_set = select_all_rooms_by_date.executeQuery();

            while (data_results_set.next()) {
                ReservationEntry reservation = new ReservationEntry(data_results_set.getString(1),data_results_set.getString(2),data_results_set.getDate(3),data_results_set.getInt(4),null);
                reservations.add(reservation);
            }
        } catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, sqlException);
        }
        return reservations;
    }
    
    /**
     * 
     * @param date
     * @param Faculty
     * @return 
     */
    public static ArrayList<RoomEntry> getRoomsReservedByDate(Date date,String Faculty) {

        connection = DBConnection.getConnection();
        ArrayList<RoomEntry> rooms = new ArrayList<>();

        try {
            PreparedStatement select_all_rooms_by_date = connection.prepareStatement("select room, seats from reservations where date = ? AND faculty = ?");
            select_all_rooms_by_date.setDate(1, date);
            select_all_rooms_by_date.setString(2, Faculty);
            data_results_set = select_all_rooms_by_date.executeQuery();

            while (data_results_set.next()) {
                RoomEntry room = new RoomEntry(data_results_set.getString(1), data_results_set.getInt(2));
                rooms.add(room);
            }
        } catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, sqlException);
        }
        return rooms;
    }
    
    
    public static boolean hasReserve(Date date,String Faculty) {

        connection = DBConnection.getConnection();
        ArrayList<RoomEntry> rooms = new ArrayList<>();

        try {
            PreparedStatement select_all_rooms_by_date = connection.prepareStatement("select room, seats from reservations where date = ? AND faculty = ?");
            select_all_rooms_by_date.setDate(1, date);
            select_all_rooms_by_date.setString(2, Faculty);
            data_results_set = select_all_rooms_by_date.executeQuery();

            while (data_results_set.next()) {
                RoomEntry room = new RoomEntry(data_results_set.getString(1), data_results_set.getInt(2));
                rooms.add(room);
            }
        } catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, sqlException);
        }
        
        return rooms.size() > 0;
       
    }

    /**
     * addReservationEntry(String faculty, Date date, int seats)
     *
     * @param faculty
     * @param date
     * @param seats
     * @return
     */
    public static boolean addReservationEntry(String faculty, Date date, int seats) {
        
        java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());

        connection = DBConnection.getConnection();
        ArrayList<RoomEntry> rooms = RoomQueries.getAllPossibleRooms();
        
        System.out.println("+++++++++++++++++++++++++++++++++++++++ received +++++++++++++++++++++++++");
        for(int y = 0; y < rooms.size(); y++){
        
            System.out.println("==== >> Room Name "+rooms.get(y).getName()+" ==== >>> Room Size "+rooms.get(y).getSeats()+" Index "+y);
        }
        
        System.out.println("+++++++++++++++++++++++++++++++++++++++ end +++++++++++++++++++++++++");
        
        System.out.println("Current Number of Rooms "+rooms.size());
        
        
        for (int i = 0; i < rooms.size(); i++) {

            
            System.out.println(" ==== >> Room Name "+rooms.get(i).getName()+" ==== >>> Room Size "+rooms.get(i).getSeats()+" Required Rooms "+seats+" Index "+i);
            
            if (rooms.get(i).getSeats() >= seats) {
                
                System.out.println("1. Rooms Size "+rooms.size());
                
                System.out.println(" [>=] Rooms Seats" + rooms.get(i).getSeats()+" Room Name "+rooms.get(i).getName());
                System.out.println(" Date "+date);
                
                System.out.println("2. Rooms Size "+rooms.size());
                
                ArrayList<RoomEntry> roomsReserved = getRoomsReservedByDate(date);
                ArrayList<String> roomNames = new ArrayList<>();

                for (int x = 0; x < roomsReserved.size(); x++) {

                    roomNames.add(roomsReserved.get(x).getName());
                    System.out.println(roomsReserved.get(x).getName()+" === "+date);

                }
                
                System.out.println("3. Rooms Size "+rooms.size());
                System.out.println(roomsReserved.isEmpty() +"   ===++ === "+rooms.get(i).getName());
                
                if (roomsReserved.isEmpty() || !roomNames.contains(rooms.get(i).getName())) {
                    try {
                        addReservation = connection.prepareStatement("insert into reservations (faculty, room, date, seats, timestamp) values (?,?,?,?,?)");
                        addReservation.setString(1, faculty);
                        addReservation.setString(2, rooms.get(i).getName());
                        addReservation.setDate(3, date);
                        addReservation.setInt(4, seats);
                        addReservation.setTimestamp(5, currentTimestamp);
                        addReservation.executeUpdate();
                        roomReserved = rooms.get(i).getName();
                        return true;

                    } catch (SQLException sqlException) {

                        JOptionPane.showMessageDialog(null, "Failed To Add Data...");
                        System.out.println(sqlException);
                    }
                    return true;
                }
            }else{
            
                System.out.println(" [<= Failed Test ] Rooms Seats" + rooms.get(i).getSeats()+" Room Name "+rooms.get(i).getName());
            
            }
        }
        return false;
    }
    
    public static String  roomReserved(){
    
        return roomReserved;
    
    }
    
    public static void  setroomReserved(){
    
        roomReserved = "";
    
    }

    /**
     * cancelReservation(String faculty, Date date)
     *
     * @param faculty
     * @param date
     */
    public static void cancelReservation(String faculty, Date date) {
        connection = DBConnection.getConnection();
        int count = 0;
        try {
            select_all_rooms = connection.prepareStatement("delete from reservations where faculty = ? and date = ?");
            select_all_rooms.setString(1, faculty);
            select_all_rooms.setDate(2, date);
            count = select_all_rooms.executeUpdate();

        } catch (SQLException sqlException) {

            JOptionPane.showMessageDialog(null, "Failed To Add Data...");
            System.out.println(sqlException);
        }
    }

    /**
     * getReservationsByFaculty(String faculty)
     *
     * @param faculty
     * @return
     */
    public static ArrayList<ReservationEntry> getReservationsByFaculty(String faculty) {

        connection = DBConnection.getConnection();
        ArrayList<ReservationEntry> reservation = new ArrayList<>();
        try {
            select_reservations = connection.prepareStatement("select room, date, seats, timestamp from reservations where faculty = ?");
            select_reservations.setString(1, faculty);
            data_results_set = select_reservations.executeQuery();

            while (data_results_set.next()) {

                ReservationEntry reserve = new ReservationEntry(faculty, data_results_set.getString(1), data_results_set.getDate(2), data_results_set.getInt(3), data_results_set.getTimestamp(4));
                reservation.add(reserve);
            }
        } catch (SQLException sqlException) {

            JOptionPane.showMessageDialog(null, "Failed To Add Data...");
            System.out.println(sqlException);

        }

        return reservation;
    }

    /**
     * deleteReservation(String room)
     *
     * @param room
     */
    public static void deleteReservation(String room) {
        connection = DBConnection.getConnection();
        int count = 0;
        
        
        try {
            select_all_rooms = connection.prepareStatement("delete from reservations where room = ?");
            select_all_rooms.setString(1, room);
            count = select_all_rooms.executeUpdate();

        } catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Failed To Add Data...");
            System.out.println(sqlException);
        }
    }

}
