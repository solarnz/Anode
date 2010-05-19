package net.haltcondition.anode;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Copyright Steve Smith (tarkasteve@gmail.com): 16/05/2010
 */
public class Usage
{
    private Long totalQuota;
    private Calendar rollOver;
    private Long used;

    private SimpleDateFormat dateParser = new SimpleDateFormat("yyyy-MM-dd");

    public Usage()
    {
    }

    public Long getTotalQuota()
    {
        return totalQuota;
    }

    public void setTotalQuota(String str)
    {
        totalQuota = Long.valueOf(str);
    }

    public Calendar getRollOver()
    {
        return rollOver;
    }

    public void setRollOver(String str)
        throws ParseException
    {
        Date d = dateParser.parse(str);
        rollOver = new GregorianCalendar();
        rollOver.setTime(d);
        rollOver.setLenient(true);
    }

    public Long getUsed()
    {
        return used;
    }

    public void setUsed(String str)
    {
        used = Long.valueOf(str);
    }

    public Double getPercentageUsed()
    {
        return ((double)used) / ((double)totalQuota) * 100;
    }

    public Integer getDaysInPeriod()
    {
        Calendar c = (Calendar)rollOver.clone();

        // Assumption: Internode periods always cross a month boundary.
        // This seems reasonable, otherwise how would the roll-over e.g. periods
        // ending on the 31st?
        c.add(Calendar.MONTH, -1);
        return c.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

}
