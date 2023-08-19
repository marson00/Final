/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Date;
import java.util.Objects;

/**
 *
 * @author mikae
 */
public class report 
{
    private String reportID;
    private String reportTitle;
    private String reportQuery;
    private Date reportCreatedDate;
    
    public report()
    {}

    public report(String reportID, String reportTitle, String reportQuery, Date reportCreatedDate) 
    {
        this.reportID = reportID;
        this.reportTitle = reportTitle;
        this.reportQuery = reportQuery;
        this.reportCreatedDate = reportCreatedDate;
    }

    public String getReportID() 
    {
        return reportID;
    }

    public String getReportTitle() 
    {
        return reportTitle;
    }

    public String getReportQuery() 
    {
        return reportQuery;
    }

    public Date getReportCreatedDate() 
    {
        return reportCreatedDate;
    }

    public void setReportID(String reportID) 
    {
        this.reportID = reportID;
    }

    public void setReportTitle(String reportTitle) 
    {
        this.reportTitle = reportTitle;
    }

    public void setReportQuery(String reportQuery) 
    {
        this.reportQuery = reportQuery;
    }

    public void setReportCreatedDate(Date reportCreatedDate) 
    {
        this.reportCreatedDate = reportCreatedDate;
    }

    @Override
    public int hashCode() 
    {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.reportID);
        return hash;
    }

    @Override
    public boolean equals(Object obj) 
    {
        if (this == obj) 
        {
            return true;
        }
        if (obj == null) 
        {
            return false;
        }
        if (getClass() != obj.getClass())
        {
            return false;
        }
        final report other = (report) obj;
        return true;
    }

    @Override
    public String toString() 
    {
        return "report{" + "reportID=" + reportID + ", reportTitle=" + reportTitle + ", reportQuery=" + reportQuery + ", reportCreatedDate=" + reportCreatedDate + '}';
    }
    
    
}
