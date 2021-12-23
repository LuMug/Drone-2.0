package drone;

import java.awt.KeyEventDispatcher;
import java.awt.event.KeyEvent;
import java.util.LinkedList;

/**
 * The KeyDispatcher class is used to drive the drone from the keyboard.
 * 
 * @author Gianni Grasso
 */
// custom dispatcher
class KeyDispatcher implements KeyEventDispatcher {
    
    private static volatile LinkedList<String> commandBufferInput;
    
    private boolean pressing = false;
    private int dummyCounter = 0;

    @Override
    public boolean dispatchKeyEvent(KeyEvent evt) {
        if (evt.getID() == KeyEvent.KEY_TYPED) {
        }
       
        if (evt.getID() == KeyEvent.KEY_PRESSED) {
            if (evt.getExtendedKeyCode() == 87) {
                if (!pressing || (dummyCounter & 1) == 0)
                commandBufferInput.add("rc 0 70 0 0");
            }
            if (evt.getExtendedKeyCode() == 65) {
                if (!pressing || (dummyCounter & 1) == 0)
                commandBufferInput.add("rc -70 0 0 0");
            }
            if (evt.getExtendedKeyCode() == 83) {
                if (!pressing || (dummyCounter & 1) == 0)
                commandBufferInput.add("rc 0 -70 0 0");
            }
            if (evt.getExtendedKeyCode() == 68) {
                if (!pressing || (dummyCounter & 1) == 0)
                commandBufferInput.add("rc 70 0 0 0");
            }
            if (evt.getExtendedKeyCode() == 37) {
                if (!pressing || (dummyCounter & 1) == 0)
                commandBufferInput.add("rc 0 0 0 -70");
            }
            if (evt.getExtendedKeyCode() == 39) {
                if (!pressing || (dummyCounter & 1) == 0)
                commandBufferInput.add("rc 0 0 0 70");
            }
            if (evt.getExtendedKeyCode() == 40) {
                if (!pressing || (dummyCounter & 1) == 0)
                commandBufferInput.add("rc 0 0 -79 0");
            }
            if (evt.getExtendedKeyCode() == 38) {
                if (!pressing || (dummyCounter & 1) == 0)
                commandBufferInput.add("rc 0 0 70 0");
            }
            if (evt.getExtendedKeyCode() == 32) {
                if (!pressing || (dummyCounter & 1) == 0)
                commandBufferInput.add("rc 0 0 0 0");
            }
            if (evt.getExtendedKeyCode() == 84) {
                commandBufferInput.add("takeoff");
            }
            if (evt.getExtendedKeyCode() == 76) {
                commandBufferInput.add("land");
            }
            //NB: 13? | 10? 
            if (evt.getExtendedKeyCode() == 10) {
                commandBufferInput.add("emergency");
            }
            if (evt.getExtendedKeyCode() == 85) {
                commandBufferInput.add("flip f");
            }
            if (evt.getExtendedKeyCode() == 74) {
                commandBufferInput.add("flip b");
            }
            if (evt.getExtendedKeyCode() == 75) {
                commandBufferInput.add("flip r");
            }
            if (evt.getExtendedKeyCode() == 72) {
                commandBufferInput.add("flip l");
            }
            pressing = true;
            ++dummyCounter;
        }
        if (evt.getID() == KeyEvent.KEY_RELEASED) {
            commandBufferInput.add("rc 0 0 0 0");
            pressing = false;
        }

        return false;
    }

    public void setCommandBufferInput(LinkedList<String> commandBufferInput) {
        this.commandBufferInput = commandBufferInput;
    }
}
