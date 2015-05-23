/**
 * Created by philla on 23.05.15.
 */
class Main {
    def host = 'localhost'
    def port = '8080'
    def apikey = '8427e429f9e5c0e363e7ff344aaa86f6'

    def sabNZBUrl = "$host:$port/sabnzbd/"

    Queue q = new Queue()

    public String getJSON(String mode='queue', String output='json'){
        String test = "http://${sabNZBUrl}api?mode=$mode&output=$output&apikey=$apikey".toURL().text
        return test
    }

    public void slurpJason(){
        q.slurpJson(getJSON())
        q.printAll()
    }

    public static void main(String[] args){
        //getJSON()
        Main m = new Main()
        m.slurpJason()
    }
}
