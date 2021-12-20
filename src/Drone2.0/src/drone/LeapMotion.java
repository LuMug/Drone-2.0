package drone;

import com.leapmotion.leap.*;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *  Class that handles LeapMotion
 * 
 * @author Alessandro Aloise
 * @version 18.11.2021
 */
public class LeapMotion extends Listener {

    /**
     * Queue.
     */
    private static volatile LinkedList<String> commandsBufferInput;
    public void setCommandsBufferInput(LinkedList<String> commandBufferInput) {
        this.commandsBufferInput = commandBufferInput;
    }

    /**
     * Empty Builder.
     */
    public LeapMotion() {
    }

    /**
     * Method invoked each time leap motion records a new piece of data.
     *
     * @param controller che gestisce il leap motion
     */
    public void onFrame(Controller controller) {
        Frame frame = controller.frame();
        Hand rightHand = null;
        Hand leftHand = null;
        Finger rightHandIndexFinger = null;
        Finger rightHandMiddleFinger = null;
        Finger leftHandIndexFinger= null;
        Finger leftHandMiddleFinger= null;
        String command = "";
        float highCommand = 0;
        float pitch = 0;
        float yaw = 0;
        float roll = 0;
        int highSpeed = 0;
        int pitchSpeed = 0;
        int rollSpeed = 0;
        int yawSpeed = 0;
        if (frame.hands().count() > 0) {
            Hand hand = frame.hands().get(0);
            if (hand.isRight()) {
                rightHand = frame.hands().rightmost();
                rightHandMiddleFinger = rightHand.fingers().fingerType(Finger.Type.TYPE_MIDDLE).get(0);
            } else {
                leftHand = frame.hands().leftmost();
                leftHandMiddleFinger = leftHand.fingers().fingerType(Finger.Type.TYPE_MIDDLE).get(0);
            }
            if (frame.hands().count() == 2) {
                rightHand = frame.hands().rightmost();
                leftHand = frame.hands().leftmost();
                if (!rightHand.isRight()) {
                    leftHand = frame.hands().rightmost();
                    rightHand = frame.hands().leftmost();
                }
                rightHandIndexFinger = rightHand.fingers().fingerType(Finger.Type.TYPE_INDEX).get(0);
                leftHandIndexFinger = leftHand.fingers().fingerType(Finger.Type.TYPE_INDEX).get(0);
                rightHandMiddleFinger = rightHand.fingers().fingerType(Finger.Type.TYPE_MIDDLE).get(0);
                leftHandMiddleFinger = leftHand.fingers().fingerType(Finger.Type.TYPE_MIDDLE).get(0);
            }
        }
        if (leftHand != null) {
            highCommand = leftHand.palmPosition().getY();
        }
        if (rightHand != null) {
            pitch = rightHand.direction().pitch();
            yaw = rightHand.direction().yaw();
            roll = rightHand.palmNormal().roll();
        }
        if (!rightHandIndexFinger.isExtended()) {
            command = "takeoff";
            commandsBufferInput.add(command);
        } else if (!rightHandMiddleFinger.isExtended()) {
            command = "rc 0 0 0 0";
            commandsBufferInput.add(command);
            command = "land";
            commandsBufferInput.add(command);
        }
        if (betweenExcluded(pitch, -0.25, 0.25)
                && betweenExcluded(roll, -0.40, 0.40)
                && betweenExcluded(yaw, -0.35, 0.15)
                && betweenExcluded(highCommand, 175, 225)) {
            command = "rc 0 0 0 0";
            commandsBufferInput.add(command);
        } else {
            try {
                if (pitch >= 0.25) {
                    if (pitch <= 0.60) {
                        pitchSpeed = -1 * convertRange(pitch, 0.25, 0.60, 10, 50);
                    } else {
                        pitchSpeed = -100;
                    }
                } else if (pitch <= -0.25) {
                    if (pitch >= -0.60) {
                        pitchSpeed = convertRange(pitch, -0.25, -0.60, 10, 50);
                    } else {
                        pitchSpeed = 100;
                    }
                }
                if (roll <= -0.40) {
                    if (roll >= -0.75) {
                        rollSpeed = convertRange(roll, -0.40, -0.75, 10, 50);
                    } else {
                        rollSpeed = 100;
                    }
                } else if (roll >= 0.40) {
                    if (roll <= 0.75) {
                        rollSpeed = -1 * convertRange(roll, 0.40, 0.75, 10, 50);
                    } else {
                        rollSpeed = -100;
                    }
                }
                if (yaw >= 0.15) {
                    if (yaw <= 0.25) {
                        yawSpeed = convertRange(yaw, 0.15, 0.25, 10, 50);
                    } else {
                        yawSpeed = 100;
                    }
                } else if (yaw <= -0.35) {
                    if (yaw >= -0.75) {
                        yawSpeed = -1 * convertRange(yaw, -0.35, -0.75, 10, 50);
                    } else {
                        yawSpeed = -100;
                    }
                }
                if (highCommand <= 175) {
                    if (highCommand != 0) {
                        if (highCommand < 75) {
                            highSpeed = -100;
                        } else {
                            highSpeed = -1 * (110 - convertRange(highCommand, 75, 175, 10, 50));
                        }
                    }
                } else if (highCommand >= 225) {
                    if (highCommand > 325) {
                        highSpeed = 100;
                    } else {
                        highSpeed = convertRange(highCommand, 225, 325, 10, 50);
                    }
                }
                command = "rc " + rollSpeed + " " + pitchSpeed + " " + highSpeed + " " + yawSpeed;
                Thread.sleep(200);
                commandsBufferInput.add(command);
            } catch (InterruptedException ex) {
                Logger.getLogger(LeapMotion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Converts the value of one range to a value of another range.
     *
     * @param value to map in the first range
     * @param r1Min minimum value of the first range
     * @param r1Max maximum value of the first range
     * @param r2Min minimum value of the second range
     * @param r2Max maximum value of the second range
     * @return value mapped in the second range
     */
    public int convertRange(double value, double r1Min, double r1Max, double r2Min, double r2Max) {
        return (int) (((value - r1Min) * (r2Max - r2Min)) / (r1Max - r1Min) + r2Min);
    }

    /**
     * Returns if the passed value is inside the range, minimum and maximum
     * excluded.
     *
     * @param value to check
     * @param min to check
     * @param max to check
     * @return if the value is or not in the range
     */
    public boolean betweenExcluded(double value, double min, double max) {
        if (value > min && value < max) {
            return true;
        } else {
            return false;
        }
    }
}