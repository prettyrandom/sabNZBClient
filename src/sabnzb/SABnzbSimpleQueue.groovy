package sabnzb
/**
 * Created by philla on 04.07.2015.
 */
class SABnzbSimpleQueue implements SABnzbQueueI {
    Map queueItems
    Date lastUpdate

    def timeLeft
    def size
    def mbLeft
    def paused
    def kbPerSec

    public SABnzbSimpleQueue(){
        this.queueItems = [:]
    }

    public void updateQueue( timeLeft, size, mbLeft, paused, kbPerSec){
        this.size = size
        this.mbLeft = mbLeft
        this.paused = paused
        this.kbPerSec = kbPerSec
        this.timeLeft = timeLeft
        lastUpdate = new Date()
    }

    public void addItem( SABnzbAdvancedQueueItem qi ){
        queueItems.put( qi.getId(), qi )
    }

    public void addItem( Map item ){
        def qi = new SABnzbSimpleQueueItem()

        qi.id = item?.nzo_id
        qi.filename = item?.filename
        qi.size = item?.mb
        qi.mbLeft = item?.mbleft
        qi.timeLeft = item?.timeleft

        addItem( qi )
    }

    @Override
    public String toString() {
        return "SABnzbAdvancedQueue{" +
                "lastUpdate=" + lastUpdate +
                ", timeLeft=" + timeLeft +
                ", size=" + size +
                ", mbLeft=" + mbLeft +
                ", paused=" + paused +
                ", kbPerSec=" + kbPerSec +
                ", queueItems=" + queueItems +
                '}';
    }
}
