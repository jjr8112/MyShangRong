package com.jjr8112.thread;

public class WelcomeApp {
    public static void main(String[]args){
        Thread welcomeThread = new WelcomeThread();
        //线程一次性
        welcomeThread.start();

        System.out.printf("1.welcome! I'm %s.%n", Thread.currentThread().getName());
    }
}

class WelcomeThread extends Thread{
    @Override
    public void run(){
        System.out.printf("2.welcome! I'm %s.%n", Thread.currentThread().getName());
    }
}
