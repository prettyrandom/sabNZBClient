import groovy.json.JsonSlurper

/**
 * Created by philla on 23.05.15.
 */
class Queue {
    ArrayList<QueueItem> items;

    public Queue(){
        this.items = new ArrayList<QueueItem>()
    }

    public slurpJson(String json){
        def JsonSlurper jsonSlurper = new JsonSlurper()
        def jsonObject = jsonSlurper.parseText(json)

        List queueItems = jsonObject.queue.slots
        if(queueItems==null || queueItems.isEmpty()){
            println "Queue is empty."
            return
        }
        queueItems.each { slot ->
            addQueueItem(new QueueItem(slot.index, slot.filename, slot.status, slot.size, slot.sizeleft, slot.percentage, slot.eta, slot.nzo_id))
            println "Added "+slot.filename+": "+slot.size-slot.sizeLeft+"/"+slot.size
        }
    }

    public addQueueItem(QueueItem item){
        items.add(item)
    }

    public void pauseAll(){
        items.each { item ->
            item.pause()
        }
    }

    public void printAll(){
        items.each { item ->
            println item
        }
    }
}
