import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class App {

    final static String[] roomExtras = {"1 3","2 3 4", "3 4 5", "1 3 5", "1 2 4"};

    public static String checkDots(String str) {
        StringBuilder newString = new StringBuilder();
        for(int i = 0; i < str.length(); i++) {
            if(str.charAt(i) != '.')

                newString.append(str.charAt(i));
            else
                newString.append("/");
        }
        return newString.toString();
    }

    public static void reserveRoom(Scanner sc, List<Room> rooms) throws ParseException {
        System.out.println("Enter room number: ");
        String room = sc.next();

        try {
            Integer.parseInt(room);
        } catch(Exception exc) {
            System.out.println("Please enter a valid room from (101-105).");
            return;
        }
        Room mainRoom = null;

        for(Room rm : rooms) {
            if(rm.getRoomNumber() == Integer.parseInt(room)) {
                mainRoom = rm;
                if(rm.isUsed()) {
                    System.out.println("Room is already being used.");
                    return;
                }
            }
        }

        if(mainRoom == null) {
            System.out.println("Please enter a valid room from (101-105).");
            return;
        }

        System.out.println("Date Format: (Day/Month/Year) also can be done with (Day.Month.Year)");
        System.out.println("Enter start date: ");
        Date startDate = new SimpleDateFormat("dd/MM/yyyy").parse(checkDots(sc.next()));
        System.out.println("Enter end date: ");
        Date endDate = new SimpleDateFormat("dd/MM/yyyy").parse(checkDots(sc.next()));
        System.out.println("Enter notes: ");
        sc.next();
        String notes = sc.nextLine();

        if(startDate.after(endDate)) {
            System.out.println("Start date is further than end date.");
            return;
        }

        mainRoom.setRoomEnterDate(startDate);
        mainRoom.setRoomFinishDate(endDate);
        mainRoom.setUsed(true);
        mainRoom.setRoomNotes(notes);

        System.out.println("Reservation was created.");
    }


    public static void checkOutRoom(List<Room> rooms, int roomId) {
        boolean isRoomFound = false;

        for (Room room : rooms) {
            if (room.getRoomNumber() == roomId) {
                if (room.isUsed()) {
                    room.resetRoomData();
                    System.out.println("Successfully checked out from room.");
                } else {
                    System.out.println("Room is not occupied.");
                    return;
                }
                isRoomFound = true;
                break;
            }
        }
        if(!isRoomFound)
            System.out.println("Couldn't find room.");
    }

    public static void stats(List<Room> rooms, Scanner sc) throws ParseException{

        System.out.println("Enter start date: ");
        Date startDate = new SimpleDateFormat("dd/MM/yyyy").parse(checkDots(sc.next()));
        System.out.println("Enter end date: ");
        Date endDate = new SimpleDateFormat("dd/MM/yyyy").parse(checkDots(sc.next()));
        long difference = 0;
        for(Room room : rooms) {
            if((room.getRoomFinishDate().compareTo(endDate) != 0) && (room.getRoomEnterDate().compareTo(startDate) != 0 )){
                System.out.println("- " + room.getRoomNumber() + ": " + difference + " day/s");

            }else {

                difference = room.getRoomFinishDate().getTime() - room.getRoomEnterDate().getTime();
                TimeUnit time = TimeUnit.DAYS;
                difference = time.convert(difference, TimeUnit.MILLISECONDS);
                System.out.println("- " + room.getRoomNumber() + ": " + difference + " day/s");
            }
        }
    }

    public static void availableRooms(List<Room> rooms) {
        System.out.println("Currently Free Rooms");
        for (Room room : rooms) {
            if (!room.isUsed()) {
                System.out.println("- Room " + room.getRoomNumber());
            }
        }
    }

    public static void findRoom(List<Room> rooms, Scanner sc) {
        int count = 0;
        try {
            System.out.print("Enter amount of beds: ");
            int bedsInput = Integer.parseInt(sc.next());
            System.out.println("Rooms that have " + bedsInput + " beds:");
            for(Room room : rooms) {
                if(bedsInput == room.getRoomBeds()) {
                    System.out.println("- " + room.getRoomNumber());
                    count++;
                }
            }
            if (count <= 0)
                System.out.println("No rooms found with " + bedsInput + " beds.");
        } catch(NumberFormatException exc) {
            System.out.println("Please enter a valid number of beds.");
        }
    }

    public static void updateMenu(Room currentRoom, Scanner sc){
        while(true) {
            System.out.println("Which parts of the room would you like to update?");
            System.out.println("1. With a sea view. - " + (currentRoom.isIncludesSeaView() ? "Yes" : "No"));
            System.out.println("2. Room Includes breakfast - " + (currentRoom.isIncludesBreakfast() ? "Yes" : "No"));
            System.out.println("3. Room Includes a bedstead - " + (currentRoom.isIncludesBedStead() ? "Yes" : "No"));
            System.out.println("0. Go Back");

            String input = sc.next();
            switch(input) {
                case "1":
                    currentRoom.setIncludesAC(!currentRoom.isIncludesSeaView());
                    break;
                case "2":
                    currentRoom.setIncludesBreakfast(!currentRoom.isIncludesBreakfast());
                    break;
                case "3":
                    currentRoom.setIncludesBedStead(!currentRoom.isIncludesBedStead());
                    break;
                case "0":
                    return;
            }
        }
    }

    public static void updateRoom(List<Room> rooms, Scanner sc) {
        System.out.println("Choose a room from (101-105) to update.");
        Room currentRoom = null;
        try {
            int roomNumber = Integer.parseInt(sc.next());
            for(Room room : rooms) {
                if(room.getRoomNumber() == roomNumber) {
                    currentRoom = room;
                    break;
                }
            }
        } catch (NumberFormatException exc) {
            System.out.println("Please enter a valid room number.");
        }
        if(currentRoom == null) {
            System.out.println("Room doesn't exist.");
            return;
        }

        updateMenu(currentRoom, sc);

    }

    public static void hotelMenu(Scanner sc, List<Room> rooms ) throws ParseException {

        boolean shouldStop = false;
        do {
            System.out.println("Please select what you want to do:\n1. Make a reservation\n2. List free rooms\n3. Checkout room\n4. Stats\n5. Find a room\n6. Update a room\n0. Exit Program");
            String choice = sc.next();
            choice.trim();
            switch (choice) {
                case "1":
                    try {
                        reserveRoom(sc, rooms);
                    } catch (ParseException ignored) {
                    }
                    break;
                case "2":
                    availableRooms(rooms);
                    break;
                case "3":
                    System.out.print("Enter your room number: ");
                    try {
                        int id = Integer.parseInt(sc.next());
                        checkOutRoom(rooms, id);
                    } catch (NumberFormatException exc) {
                        System.out.println("Please enter a valid room.");
                    }
                    break;
                case "4":
                    stats(rooms, sc);
                    break;
                case "5":
                    findRoom(rooms, sc);
                    break;
                case "6":
                    updateRoom(rooms, sc);
                    break;
                case "0":
                    shouldStop = true;
                    break;
            }
        } while (!shouldStop);
    }

    public static void generateRoom(List<Room> rooms){

        for(int i = 0; i < 5; i++) {
            rooms.add(new Room(101 + i, roomExtras[i].split(" ")));
        }
    }


    public static void main(String[] args) throws ParseException {
        Scanner sc = new Scanner(System.in);
        List<Room> rooms = new ArrayList<>();

        generateRoom(rooms);
        hotelMenu(sc,rooms);

    }

}
