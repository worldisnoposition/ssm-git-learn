package classloader;


import java.net.URL;
import java.net.URLClassLoader;

/**
 *
 * 两种方式
 * @author minggang.wumg
 *
 */

public class MyClassLoader {

    public static void main(String[] args) {
        try {
            //第一种  配置成文件格式
////            File file = new File("D:\\jarload\\test.txt");
////            BufferedReader in = new BufferedReader(new FileReader(file));
////            String s = new String();
//            while ((s = in.readLine()) != null) {

//                URL url = new URL(s);
//                s = null;
//                URLClassLoader myClassLoader = new URLClassLoader(new URL[]{url}, Thread.currentThread()
//                        .getContextClassLoader());
//                Class<? extends AbstractAction> myClass = (Class<? extends AbstractAction>) myClassLoader.loadClass("com.java.jarloader.TestAction");
//                AbstractAction action = (AbstractAction) myClass.newInstance();
//                String str = action.action();
//                System.out.println(str);

                //第二种 H:\data
                URL url1 = new URL("file:H:/data/com.AbstractActionImpl.jar");
                URLClassLoader myClassLoader1 = new URLClassLoader(new URL[]{url1}, Thread.currentThread()
                        .getContextClassLoader());
                Class<?> myClass1 = myClassLoader1.loadClass("com.AbstractActionImpl");
                Object o =  myClass1.newInstance();
                String str1 = o.toString();
                System.out.println(str1);
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
