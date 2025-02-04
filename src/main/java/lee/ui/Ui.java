package lee.ui;

public class Ui {
    private final String HORlINE;
    private final String GREETS;
    private final String EXITS;

    public Ui() {
        this.HORlINE = "____________________________________________________________\n";
        this.GREETS = "Hello! I'm Lee.Lee.\nWhat can I do for you?\n";
        this.EXITS = "Bye. Hope to see you again soon!\n";
    }

    public void showLoadingError(Exception e) {
        System.out.println("Oops! An error occurred!\n" + e.getMessage());
    }

    public void startUi() {
        System.out.print(HORlINE + GREETS + HORlINE);
    }

    public void exitUi() {
        System.out.print(HORlINE + EXITS + HORlINE);
    }

    public void line() {
        System.out.print(HORlINE);
    }
}
