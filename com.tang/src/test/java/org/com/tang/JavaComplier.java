package org.com.tang;

import com.alibaba.dubbo.common.compiler.support.JavassistCompiler;

public class JavaComplier
{
    public static void main(String[] args)
    {
        JavassistCompiler compiler = new CustomJavaCompiler();
        String code = "package org.com.tang;\n"+
"import com.tang.huawei.PlanManager;\n"+
"public class Plan$Adpative implements org.com.tang.PlanGenerator\n"+
"{\n"+
"    public String getPlan(org.com.tang.ReleaseRequest request)\n"+
"    {\n"+
"        PlanManager.getInstance().printHello();\n"+
"        StringBuilder sb = new StringBuilder();\n"+
"        sb.append(\"address=\");\n"+
"        sb.append(request.getAddress()).append(\"|\");\n"+
"        sb.append(\"id=\").append(request.getId()).append(\"|\");\n"+
"        sb.append(\"msn=\").append(request.getMsn());\n"+
"        return sb.toString();\n"+
"    }\n"+
"}";
        Class<?> protocol = compiler.compile(code, JavaComplier.class.getClassLoader());
        System.out.println(protocol.getSimpleName());
        try
        {
            PlanGenerator instance = (PlanGenerator)protocol.newInstance();
            ReleaseRequest request = new ReleaseRequest();
            request.setAddress("aaa");
            request.setId("bbb");
            request.setMsn("ccc");
            String plan = instance.getPlan(request);
            System.out.println(plan);
        }
        catch (InstantiationException e)
        {
            e.printStackTrace();
        }
        catch (IllegalAccessException e)
        {
            e.printStackTrace();
        }
    }
}
