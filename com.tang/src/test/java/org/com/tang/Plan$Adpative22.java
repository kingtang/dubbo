package org.com.tang;
import com.tang.huawei.PlanManager;
public class Plan$Adpative22 implements org.com.tang.PlanGenerator
{
    public String getPlan(org.com.tang.ReleaseRequest request)
    {
        PlanManager.getInstance().printHello();
        StringBuilder sb = new StringBuilder();
        sb.append("address=");
        sb.append(request.getAddress()).append("|");
        sb.append("id=").append(request.getId()).append("|");
        sb.append("msn=").append(request.getMsn());
        return sb.toString();
    }
}