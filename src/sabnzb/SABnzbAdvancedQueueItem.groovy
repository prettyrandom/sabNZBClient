package sabnzb

import java.math.RoundingMode

/**
 * Created by philla on 04.07.2015.
 */
class SABnzbAdvancedQueueItem {
    def id
    def status
    def index
    def eta
    def filename
    def size
    def mbLeft
    def timeLeft
    def percentage
    def avgAge
    def priority
    def category

    @Override
    public String toString() {
        return "SABnzbAdvancedQueueItem{" +
                "id=" + id +
                ", status=" + status +
                ", index=" + index +
                ", eta=" + eta +
                ", filename=" + filename +
                ", size=" + size +
                ", mbLeft=" + mbLeft +
                ", timeLeft=" + timeLeft +
                ", percentage=" + percentage +
                ", avgAge=" + avgAge +
                ", priority=" + priority +
                ", category=" + category +
                '}';
    }
}
