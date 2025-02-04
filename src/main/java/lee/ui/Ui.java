package lee.ui;

/**
 * Aggregates all ui related methods for tui.
 */
public class Ui {
    private final String HORlINE;
    private final String GREETS;
    private final String EXITS;

    /**
     * Sets up the basic ui string literals.
     */
    public Ui() {
        this.HORlINE = "____________________________________________________________\n";
        this.GREETS = "Hello! I'm Lee.Lee.\nWhat can I do for you?\n";
        this.EXITS = "Bye. Hope to see you again soon!\n";
    }

    /**
     * Prints warnings and error messages.
     *
     * @param e The exception encountered during runtime.
     */
    public void showLoadingError(Exception e) {
        System.out.println("Oops! An error occurred!\n" + e.getMessage());
    }

    /**
     * Prints the start UI.
     */
    public void startUi() {
        System.out.print(HORlINE + GREETS + HORlINE);
    }

    /**
     * Prints the exit UI.
     */
    public void exitUi() {
        System.out.print(HORlINE + EXITS + HORlINE);
    }

    /**
     * Prints a separating line.
     */
    public void line() {
        System.out.print(HORlINE);
    }
}
