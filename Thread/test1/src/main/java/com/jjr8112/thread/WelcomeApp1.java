package com.jjr8112.thread;

public class WelcomeApp1 {
    public static void main(String[]args){
        Thread welcomeThread = new Thread(new WelcomeTask());
        welcomeThread.start();
        System.out.printf("1.welcome, I'm %s.%n", Thread.currentThread().getName());
    }
}

class WelcomeTask implements Runnable{
    @Override
    public void run(){
        System.out.printf("2.welcome, I'm %s.%n", Thread.currentThread().getName());
    }

}
