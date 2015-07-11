package sabnzb

import groovyx.net.http.ContentType
import groovyx.net.http.HTTPBuilder
import groovyx.net.http.HttpResponseException
import groovyx.net.http.Method
import org.apache.logging.log4j.LogManager

public class SABnzbApi {
    private log = LogManager.getLogger(SABnzbApi)

    private String uri
    private String url
    private int port
    private String apikey

    private final String OUTPUT_TYPE = "json"
    private final String MODE_SIMPLEQUEUE = "qstatus"
    private final String MODE_ADVANCEDQUEUE = "queue"
    private final String MODE_VERSION = "version"
    private final String MODE_CATEGORIES = "get_cats"
    private final String MODE_AUTH = "auth"

    HTTPBuilder http

    private SABnzbQueueI simpleQueue
    private SABnzbQueueI advQueue

    public SABnzbApi( url, uri, port, apikey){
        this.uri = uri
        this.url = url
        this.port = port
        this.http = new HTTPBuilder( "$url:$port" )
        this.apikey = apikey

        this.simpleQueue = new SABnzbSimpleQueue()
        this.advQueue = new SABnzbAdvancedQueue()

    }

    public SABnzbQueueI getSimpleQueue() {
        log.info "Trying to get SimpleQueue"
        try {
            http.get( path: uri, query: [mode: MODE_SIMPLEQUEUE, output: OUTPUT_TYPE, apikey: this.apikey] ) { resp, json ->
                if( resp.status != 200 )
                    return null

                //simpleQueue.updateQueue( json.timeleft as String, json.mb as BigDecimal, json.mbleft as BigDecimal, json.paused as boolean, json.kbpersec as BigDecimal )
                simpleQueue.updateQueue( json.timeleft, json.mb, json.mbleft, json.paused, json.kbpersec )
                //simpleQueue.updateQueue( null, null, null, null, null )
                //json.jobs.each { item ->
                //    simpleQueue.addItem( item )
                //}
            }
        } catch ( HttpResponseException ex ) {
            ex.printStackTrace()
            return null
        } catch ( ConnectException ex ) {
            ex.printStackTrace()
            return null
        }
        return simpleQueue
    }

    public SABnzbQueueI getAdvancedQueue() {
        log.info "Trying to get AdvancedQueue"
        try {
            http.get( path: uri, query: [mode: MODE_ADVANCEDQUEUE, output: OUTPUT_TYPE, apikey: this.apikey] ) { resp, json ->
                if( resp.status != 200 )
                    return null

                advQueue.updateQueue( json.queue.timeleft, json.queue.mb, json.queue.mbleft, json.queue.paused, json.queue.kbpersec )
                json.queue.slots.each { item ->
                    advQueue.addItem( item as Map )
                }
            }
        } catch ( HttpResponseException ex ) {
            log.error "Couldn't connect via Http"
            ex.printStackTrace()
            return null
        } catch ( ConnectException ex ) {
            log.error "Connection problem"
            ex.printStackTrace()
            return null
        }
        return advQueue
    }

    public String getVersion() {
        log.info "Trying to get Version"
        try {
            http.get( path: uri, query: [mode: MODE_VERSION, output: OUTPUT_TYPE, apikey: this.apikey] ) { resp, json ->
                if( resp.status != 200 )
                    return null

                return json.version
            }
        } catch ( HttpResponseException ex ) {
            ex.printStackTrace()
            return null
        } catch ( ConnectException ex ) {
            ex.printStackTrace()
            return null
        }
    }

    public List getCategories() {
        log.info "Trying to get Categories"
        try {
            http.get( path: uri, query: [mode: MODE_CATEGORIES, output: OUTPUT_TYPE, apikey: this.apikey] ) { resp, json ->
                if( resp.status != 200 )
                    return null

                return json.categories
            }
        } catch ( HttpResponseException ex ) {
            ex.printStackTrace()
            return null
        } catch ( ConnectException ex ) {
            ex.printStackTrace()
            return null
        }
    }

    public String getAuthType() {
        log.info "Trying to get authentication type"
        try {
            http.request(Method.GET, ContentType.TEXT) { req ->
                uri.path = this.uri // overrides any path in the default URL
                uri.query = [mode: MODE_AUTH]
                headers.'User-Agent' = 'Mozilla/5.0'

                response.success = { resp, reader ->
                    assert resp.status == 200
                    return reader.text
                }

                // called only for a 404 (not found) status code:
                response.'404' = { resp ->
                    println 'Not found'
                }
            }
        } catch ( HttpResponseException ex ) {
            ex.printStackTrace()
            return null
        } catch ( ConnectException ex ) {
            ex.printStackTrace()
            return null
        }
    }
}