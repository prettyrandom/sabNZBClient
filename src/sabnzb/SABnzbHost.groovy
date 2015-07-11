package sabnzb

class SABnzbHost {
    private String uri
    private String url
    private String apikey
    private int port


    SABnzbApi api

    public SABnzbHost( url, uri, port, apikey) {
        this.uri = uri
        this.url = url
        this.port = port
        this.apikey = apikey
        this.api = new SABnzbApi(url, uri, port, apikey) //"/sabnzbd/api"
    }
}