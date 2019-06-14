package com.cqupt.sirius.concurrent.chapter02;

public class ThreadConstruction {
    public static void main(String[] args) {
        Thread t1 = new Thread("t1");
        ThreadGroup group = new ThreadGroup("TestGroup");
        Thread t2 = new Thread(group,"t2");
        ThreadGroup mainThreadGroup = Thread.currentThread().getThreadGroup();
        System.out.println("Main thread belong group: " + mainThreadGroup.getName());
        System.out.println("t1 and main belong the same group:" + (mainThreadGroup == t1.getThreadGroup()));
        System.out.println("t2 thread group");
    }
}
