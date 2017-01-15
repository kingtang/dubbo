package com.tang.huawei;

import org.com.tang.ReleaseRequest;

public class PlanManager
{
    private static PlanManager instance = new PlanManager();
    
    public String getPlan(ReleaseRequest request)
    {
        StringBuilder sb = new StringBuilder();
        sb.append("address=");
        sb.append(request.getAddress()).append("|");
        sb.append("id=").append(request.getId()).append("|");
        sb.append("msn=").append(request.getMsn());
        return sb.toString();
    }

    public void printHello()
    {
        System.out.println("Hello World");
    }
    
    public static PlanManager getInstance()
    {
        return instance;
    }
}
