package vn.edu.vnuk.vnuk_sharing.DataStructure;

/**
 * Created by quangngoc430 on 23/11/2017.
 */

public class Setting {
    private boolean isReceiveNews;
    private boolean isReceiveSyllabus;
    private boolean isReceiveDeadline;
    private boolean isReceiveAnnouncement;

    public Setting() {
    }

    public Setting(boolean isReceiveNews, boolean isReceiveSyllabus, boolean isReceiveDeadline, boolean isReceiveAnnouncement) {
        this.isReceiveNews = isReceiveNews;
        this.isReceiveSyllabus = isReceiveSyllabus;
        this.isReceiveDeadline = isReceiveDeadline;
        this.isReceiveAnnouncement = isReceiveAnnouncement;
    }

    public boolean isReceiveNews() {
        return isReceiveNews;
    }

    public void setReceiveNews(boolean receiveNews) {
        isReceiveNews = receiveNews;
    }

    public boolean isReceiveSyllabus() {
        return isReceiveSyllabus;
    }

    public void setReceiveSyllabus(boolean receiveSyllabus) {
        isReceiveSyllabus = receiveSyllabus;
    }

    public boolean isReceiveDeadline() {
        return isReceiveDeadline;
    }

    public void setReceiveDeadline(boolean receiveDeadline) {
        isReceiveDeadline = receiveDeadline;
    }

    public boolean isReceiveAnnouncement() {
        return isReceiveAnnouncement;
    }

    public void setReceiveAnnouncement(boolean receiveAnnouncement) {
        isReceiveAnnouncement = receiveAnnouncement;
    }

}
