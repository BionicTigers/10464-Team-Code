package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

/**
 * Created by Sean O on 11/23/2016.
 */
@Autonomous(name="Blue Shoot 8", group="Blue")
public class BlueShoot8 extends AutonomousBase {
    @Override
    public void gameState() {
        super.gameState();
        switch(gameState) {
            case 0: //Start
                if (getRuntime() > 3 && !gyro.isCalibrating()) {
                    gameState = 1;
                    map.setRobot(8,11.25);
                }
                break;
            case 1: //moves to shooter post
                map.setGoal(7, 8.5);
                if (linedUp()) {
                    moveState = MoveState.FORWARD;
                } else {
                    moveState = MoveState.TURN_TOWARDS_GOAL;
                }
                if (map.distanceToGoal() <= .1) {
                    moveState = MoveState.STOP;
                    gameState = 2;
                }
                break;
            case 2: // turns ...
                desiredAngle = 180;
                if (linedUpAngle()) {
                    moveState = MoveState.STOP;
                    gameState = 3;
                    sTime = getRuntime();
                } else {
                    moveState = MoveState.TURN_TOWARDS_ANGLE;
                }
                break;
            case 3: // ... and shoots
                moveState = MoveState.SHOOT_WHEEL;
                if (getRuntime() - sTime >= 1) {
                    moveState = MoveState.SHOOT_CONVEYOR;
                }
                if (getRuntime() - sTime >= 3) {
                    moveState = MoveState.SHOOT_STOP;
                    gameState = 4;
                }
                break;
            case 4:
                map.setGoal(4, 10);
                if (linedUp()) {
                    moveState = MoveState.FORWARD;
                } else {
                    moveState = MoveState.TURN_TOWARDS_GOAL;
                }
                if (map.distanceToGoal() <= .1) {
                    moveState = MoveState.STOP;
                    gameState = 5;
                }
                break;
            case 5: //MOVE TO KNOCK OFF BALL
                map.setGoal(6.5, 6.5);
                if (linedUp()) {
                    moveState = MoveState.FORWARD;
                } else {
                    moveState = MoveState.TURN_TOWARDS_GOAL;
                }
                if (map.distanceToGoal() <= .1) {
                    moveState = MoveState.STOP;
                }
                break;
            case 777:
                moveState = MoveState.STOP;
                break;
        }
    }
}

