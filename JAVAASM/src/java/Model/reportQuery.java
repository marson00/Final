package Model;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mikae
 */
import java.io.Serializable;
import java.time.*;
import java.util.Objects;

public class reportQuery
{
    private String reportTitle;
    private String listSize;
    private String startTime;
    private String  endTime;
    private String product;
    private String category;
    
    public reportQuery()
    {}

    public reportQuery(String reportTitle, String listSize, String startTime, String endTime, String product, String category) 
    {
        this.reportTitle = reportTitle;
        this.listSize = listSize;
        this.startTime = startTime;
        this.endTime = endTime;
        this.product = product;
        this.category = category;
    }

    public String getReportTitle() 
    {
        return reportTitle;
    }

    public String getListSize() 
    {
        return listSize;
    }

    public String getStartTime() 
    {
        return startTime;
    }

    public String getEndTime() 
    {
        return endTime;
    }

    public String getProduct() 
    {
        return product;
    }

    public String getCategory() 
    {
        return category;
    }

    public void setReportTitle(String reportTitle) 
    {
        this.reportTitle = reportTitle;
    }

    public void setListSize(String listSize) 
    {
        this.listSize = listSize;
    }

    public void setStartTime(String startTime) 
    {
        this.startTime = startTime;
    }

    public void setEndTime(String endTime) 
    {
        this.endTime = endTime;
    }

    public void setProduct(String product) 
    {
        this.product = product;
    }

    public void setCategory(String category) 
    {
        this.category = category;
    }

    @Override
    public int hashCode() 
    {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.reportTitle);
        return hash;
    }

    @Override
    public boolean equals(Object obj) 
    {
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass()) 
        {
            return false;
        }
        final reportQuery other = (reportQuery) obj;
        if (!Objects.equals(this.reportTitle, other.reportTitle)) 
        {
            return false;
        }
        return true;
    }
    
    

    @Override
    public String toString() 
    {
        return "Report{" + "reportTitle=" + reportTitle + ", listSize=" + listSize + ", startTime=" + startTime + ", endTime=" + endTime + ", product=" + product + ", category=" + category + '}';
    }

    
}
