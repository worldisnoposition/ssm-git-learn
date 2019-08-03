package com.auto.deal.classloader;

import java.io.*;

public class DynamicClassLoader extends ClassLoader {
    private String classpath;

    public DynamicClassLoader(String classpath) {
        this.classpath = classpath;
    }

    @Override
    protected synchronized Class<?> loadClass(String name,boolean resolve)throws ClassNotFoundException{
        //check the class has been loaded or not
        Class c = findLoadedClass(name);
        if(c == null){
            try{
                if(getParent() != null){
                    c = super.loadClass(name,false);
                }else{
                    c = findClass(name);
                }
            }catch(ClassNotFoundException e){
                //if throws the exception ,the father can not complete the load
            }
            if(c == null){
                c = findClass(name);
            }
        }
        if(resolve){
            resolveClass(c);
        }
        return c;
    }
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            byte[] classDate = getDate(name);
            if (classDate == null) {
                throw new FileNotFoundException();
            } else {
                //defineClass方法将字节码转化为类
                return defineClass(name, classDate, 0, classDate.length);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return super.findClass(name);
    }

    //返回类的字节码
    private byte[] getDate(String className) throws IOException {
        InputStream in = null;
        ByteArrayOutputStream out = null;
        String path = classpath + File.separatorChar + className.replace('.', File.separatorChar) + ".class";
        try {
            in = new FileInputStream(path);
            out = new ByteArrayOutputStream();
            byte[] buffer = new byte[2048];
            int len = 0;
            while ((len = in.read(buffer)) != -1) {
                out.write(buffer, 0, len);
            }
            return out.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            in.close();
            out.close();
        }
        return null;
    }
}