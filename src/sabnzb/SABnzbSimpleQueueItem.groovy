package sabnzb

import java.math.RoundingMode

/**
 * Created by philla on 04.07.2015.
 */
class SABnzbSimpleQueueItem implements SABnzbQueueItemI {
    def id
    def filename
    def size
    def mbLeft
    def timeLeft

    public BigDecimal getPercentage(){
        if( this.size == 0 )
            return BigDecimal.ZERO
        return BigDecimal.ONE.subtract( mbLeft.divide( size, 2, RoundingMode.HALF_UP ) )
    }


    @Override
    public String toString() {
        return "SABnzbSimpleQueueItem{" +
                "filename=" + filename +
                ", size=" + size +
                ", mbLeft=" + mbLeft +
                ", timeLeft=" + timeLeft +
                ", percentage=" + getPercentage() +
                '}';
    }
}
