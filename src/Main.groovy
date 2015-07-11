import sabnzb.SABnzbHost

/**
 * Created by philla on 23.05.15.
 */
class Main {
    static def host = 'http://localhost'
    static def port = 8085
    static String apikey = 'bd3cd88aa1855a9f9ebaee9a34bb621b'

    public static void main(String[] args){
        SABnzbHost host = new SABnzbHost(host, "/sabnzbd/api", port, apikey )
        //println "Simple: $host.api.simpleQueue"
        println "Advanced: $host.api.advancedQueue"
        println host.api.version
        println host.api.categories
        println host.api.authType
    }
}
