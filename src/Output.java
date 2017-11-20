class Output{

    public synchronized void print(String s){
        System.out.println(s);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}

class Library extends Thread{
    Output output;

    public Library(Output output) {
        this.output = output;
    }

    public void run(){
        output.print("Library Print.");
    }
}

class University extends Thread{
    Output output;

    public University(Output output) {
        this.output = output;
    }

    public void run(){
        output.print("University Print.");
    }


public static void main(String[] args) {
    Output output = new Output();
    Library library = new Library(output);
    University university = new University(output);
    library.start();
    university.start();
}
}