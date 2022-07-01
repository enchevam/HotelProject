import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Room {
    private Integer roomNumber;
    private Date roomEnterDate;
    private Date roomFinishDate;
    private String roomNotes;
    private boolean isUsed;


    //Extras
    private short roomBeds;

    private boolean includesAC;
    private boolean includesBalcony;
    private boolean includesBreakfast;
    private boolean includesBedStead;

    public short getRoomBeds() {
        return roomBeds;
    }

    public void setRoomBeds(short roomBeds) {
        this.roomBeds = roomBeds;
    }

    public boolean isIncludesAC() {
        return includesAC;
    }

    public void setIncludesAC(boolean includesAC) {
        this.includesAC = includesAC;
    }

    public boolean isIncludesBalcony() {
        return includesBalcony;
    }

    public void setIncludesBalcony(boolean includesBalcony) {
        this.includesBalcony = includesBalcony;
    }

    public boolean isIncludesBreakfast() {
        return includesBreakfast;
    }

    public void setIncludesBreakfast(boolean includesBreakfast) {
        this.includesBreakfast = includesBreakfast;
    }

    public boolean isIncludesBedStead() {
        return includesBedStead;
    }

    public void setIncludesBedStead(boolean includesBedStead) {
        this.includesBedStead = includesBedStead;
    }

    public Room returnRoom() {
        return this;
    }

    public Room(int roomNum, String[] args) {
        this.roomNumber = roomNum;
        this.isUsed = false;
        this.roomNotes = "";
        try {
            this.roomEnterDate = new SimpleDateFormat("dd/MM/yyyy").parse("00/00/0000");
            this.roomFinishDate = new SimpleDateFormat("dd/MM/yyyy").parse("00/00/0000");
        } catch(Exception e) {}

        includesAC = false;
        includesBalcony = false;
        includesBedStead = false;
        includesBreakfast = false;

        for(String s : args) {
            if(s.equals("1")) {
                includesAC = true;
            } else if(s.equals("2")) {
                includesBalcony = true;
            } else if(s.equals("3")) {
                includesBreakfast = true;
            } else if(s.equals("4")) {
                includesBedStead = true;
            }
        }

        if(roomNum < 104) {
            roomBeds = 2;
        } else {
            roomBeds = 3;
        }
    }

    public void resetRoomData() {
        this.isUsed = false;
        this.roomNotes = "";
        try {
            this.roomEnterDate = new SimpleDateFormat("dd/MM/yyyy").parse("00/00/0000");
            this.roomFinishDate = new SimpleDateFormat("dd/MM/yyyy").parse("00/00/0000");
        } catch(Exception e) {}
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public Date getRoomEnterDate() {
        return roomEnterDate;
    }

    public void setRoomEnterDate(Date roomEnterDate) {
        this.roomEnterDate = roomEnterDate;
    }

    public Date getRoomFinishDate() {
        return roomFinishDate;
    }

    public void setRoomFinishDate(Date roomFinishDate) {
        this.roomFinishDate = roomFinishDate;
    }

    public String getRoomNotes() {
        return roomNotes;
    }

    public void setRoomNotes(String roomNotes) {
        this.roomNotes = roomNotes;
    }

    public boolean isUsed() {
        return isUsed;
    }

    public void setUsed(boolean isUsed) {
        this.isUsed = isUsed;
    }
}
