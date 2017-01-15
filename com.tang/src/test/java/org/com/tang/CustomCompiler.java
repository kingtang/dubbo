package org.com.tang;

import com.alibaba.dubbo.common.utils.ClassHelper;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtNewMethod;
import javassist.LoaderClassPath;
import javassist.NotFoundException;

public class CustomCompiler
{
    private final String PACKAGENAME = "com.tang.huawei";
    public Class<?> getGeneratorClass()
    {
        ClassPool pool = new ClassPool(true);
        pool.appendClassPath(new LoaderClassPath(ClassHelper.getCallerClassLoader(getClass())));
        String importName = "com.tang.huawei.PlanManager";
        pool.importPackage(importName);
        String className = PACKAGENAME + ".PlanGenerator$ReleaseRequest";
        CtClass ctl = pool.makeClass(className);
        try
        {
            ctl.addInterface(pool.get("org.com.tang.PlanGenerator"));
            String methodBody = "public String getPlan(org.com.tang.ReleaseRequest request)\n"+
"    {\n"+
"        PlanManager.getInstance().printHello();\n"+
"        StringBuilder sb = new StringBuilder();\n"+
"        sb.append(\"address=\");\n"+
"        sb.append(request.getAddress()).append(\"|\");\n"+
"        sb.append(\"id=\").append(request.getId()).append(\"|\");\n"+
"        sb.append(\"msn=\").append(request.getMsn());\n"+
"        return sb.toString();\n"+
"    }\n";
            ctl.addMethod(CtNewMethod.make(methodBody, ctl));
            
            return ctl.toClass(CustomCompiler.class.getClassLoader(),null);
        }
        catch (NotFoundException e)
        {
            e.printStackTrace();
        }
        catch (CannotCompileException e)
        {
            e.printStackTrace();
        }
        return null;
    }
    
    public static void main(String[] args)
    {
        CustomCompiler compiler = new CustomCompiler();
        Class<?> clazz = compiler.getGeneratorClass();
        try
        {
            PlanGenerator instance = (PlanGenerator)clazz.newInstance();
            ReleaseRequest request = new ReleaseRequest();
            request.setAddress("aaa");
            request.setId("bbb");
            request.setMsn("ccc");
            System.out.println(instance.getPlan(request));
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
