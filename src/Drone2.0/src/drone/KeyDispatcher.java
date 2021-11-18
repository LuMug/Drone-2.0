package drone;

import java.awt.KeyEventDispatcher;
import java.awt.event.KeyEvent;
import java.util.LinkedList;

/**
 *
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
            if (evt.getExtendedKeyCode() == 13) {
                commandBufferInput.add("emergency");
            }
            pressing = true;
            ++dummyCounter;
        }
        if (evt.getID() == KeyEvent.KEY_RELEASED) {
            commandBufferInput.add("rc 0 0 0 0");
            pressing = false;
        }

        // allow the event to be redispatched
        return false;
    }

    public void setCommandBufferInput(LinkedList<String> commandBufferInput) {
        this.commandBufferInput = commandBufferInput;
    }
}
