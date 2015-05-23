/**
 * Created by philla on 23.05.15.
 */
class QueueItem {
    def index
    def fileName
    def status
    def size
    def sizeLeft
    def percentage
    def eta
    def id

    public QueueItem(def index, def filename, def status, def size, def sizeLeft, def percentage, def eta, def id){
        this.index = index
        this.fileName = filename
        this.status = status
        this.size = size
        this.sizeLeft = sizeLeft
        this.percentage = percentage
        this.eta = eta
        this.id = id
    }

    public void pause(){

    }

    @Override
    public String toString() {
        return "QueueItem{" +
                "index=" + index +
                ", fileName=" + fileName +
                ", status=" + status +
                ", size=" + size +
                ", sizeLeft=" + sizeLeft +
                ", percentage=" + percentage +
                ", eta=" + eta +
                ", id=" + id +
                '}';
    }
}
