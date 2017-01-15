package org.com.tang;

public class ReleaseRequest
{
    private String id;
    
    private String msn;
    
    private String address;

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getMsn()
    {
        return msn;
    }

    public void setMsn(String msn)
    {
        this.msn = msn;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }
}
