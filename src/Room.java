import java.text.SimpleDateFormat;
import java.util.Date;

public class Room {
    private final int roomNumber;
    private Date roomEnterDate;
    private Date roomLeaveDate;
    private String roomNotes;
    private boolean isUsed;


    //Extras
    private final short roomBeds;
    private boolean includesSieView;
    private boolean includesBreakfast;
    private boolean includesBedStead;

    public short getRoomBeds() {
        return roomBeds;
    }

    public boolean isIncludesSeaView() {
        return includesSieView;
    }

    public void setIncludesAC(boolean includesSieView) { this.includesSieView = includesSieView; }

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


    public Room(int roomNum, String[] args) {
        this.roomNumber = roomNum;
        this.isUsed = false;
        this.roomNotes = "";
        try {
            this.roomEnterDate = new SimpleDateFormat("dd/MM/yyyy").parse("00/00/0000");
            this.roomLeaveDate = new SimpleDateFormat("dd/MM/yyyy").parse("00/00/0000");
        } catch(Exception ignored) {}

        includesSieView = false;
        includesBedStead = false;
        includesBreakfast = false;

        for(String s : args) {
            switch (s) {
                case "1" -> includesSieView = true;
                case "2" -> includesBreakfast = true;
                case "3" -> includesBedStead = true;
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
            this.roomLeaveDate = new SimpleDateFormat("dd/MM/yyyy").parse("00/00/0000");
        } catch(Exception ignored) {}
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
        return roomLeaveDate;
    }

    public void setRoomFinishDate(Date roomFinishDate) {
        this.roomLeaveDate = roomFinishDate;
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
