
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RoomQueries {

    private static Connection connection;
    private static PreparedStatement addRoom;

    private static PreparedStatement getRoomList;
    private static ResultSet resultSet;

    public static ArrayList<RoomEntry> getAllPossibleRooms() {
        connection = DBConnection.getConnection();
        ArrayList<RoomEntry> rooms = new ArrayList<RoomEntry>();
        try {
            getRoomList = connection.prepareStatement("select name, seats from rooms order by seats");
            resultSet = getRoomList.executeQuery();

            while (resultSet.next()) {
                RoomEntry room = new RoomEntry(resultSet.getString(1), resultSet.getInt(2));
                rooms.add(room);
                 System.out.println("+++++++++++++++++++Room Names "+room.getName()+"  "+room.getSeats());
            }
            
            System.out.println("------------------ end ----------------------");
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return rooms;
    }

    /**
     * 
     * @return 
     */
    public static ArrayList<String> getAllRoomNames() {
        connection = DBConnection.getConnection();
        ArrayList<String> rooms = new ArrayList<>();
        try {
            getRoomList = connection.prepareStatement("select name from rooms");
            resultSet = getRoomList.executeQuery();

            while (resultSet.next()) {
                String name = resultSet.getString(1);
                rooms.add(name);
                System.out.println("+++++++++++++++++++Room Names "+name);
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return rooms;
    }

    public static boolean addRoom(String room, int seats) {

        //check room name if already exists
        if (getAllRoomNames().contains(room)) {
            return false;
        }
        connection = DBConnection.getConnection();
        try {
            addRoom = connection.prepareStatement("insert into rooms (name, seats) values (?,?)");
            addRoom.setString(1, room);
            addRoom.setInt(2, seats);
            addRoom.executeUpdate();
            return true;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return false;
        }

    }

    public static void dropRoom(String room) {
        connection = DBConnection.getConnection();
        ArrayList<WaitlistEntry> waitlist = new ArrayList<>();
        int count = 0;
        try {
            getRoomList = connection.prepareStatement("delete from rooms where name = ?");
            getRoomList.setString(1, room);
            count = getRoomList.executeUpdate();

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }
    
    

}
