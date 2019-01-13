package com.spi;

import java.util.Iterator;
import java.util.ServiceLoader;

public class MainClass {
    public static void main(String[] args) {
        IOperation plus = new PlusOperationImpl();

        IOperation division = new DivisionOperationImpl();
        System.out.println(plus.operation(5, 3));

        System.out.println(division.operation(9, 3));

        //可以在这里动态加载类
        ServiceLoader<IOperation> operations = ServiceLoader.load(IOperation.class);
        Iterator<IOperation> operationIterator = operations.iterator();
        System.out.println("classPath:"+System.getProperty("java.class.path"));
        while (operationIterator.hasNext()) {
            IOperation operation = operationIterator.next();
            System.out.println(operation.operation(6, 3));
        }
    }
}
