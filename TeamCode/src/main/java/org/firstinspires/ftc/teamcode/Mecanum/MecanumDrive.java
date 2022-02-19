/* Created by Team 5115 at Windsor High School for the 2017-18 Season
This OpMode runs motors in the following configuration:
       ////            \\\\
      /F1/ ------------ \F2\
     //// |            | \\\\
          |            |
          |            |
     \\\\ |            | ////
      \R1\ ------------ /R2/
       \\\\            ////
 */

package org.firstinspires.ftc.teamcode.Mecanum;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="Pogbot drive", group="teleop")
//@Disabled

public class MecanumDrive extends OpMode
{
    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor F1 = null;
    private DcMotor F2 = null;
    private DcMotor R1 = null;
    private DcMotor R2 = null;
    private DcMotor arm = null;
    private Servo leftClaw = null;
    private Servo rightClaw = null;
    private double posLeft = 0;
    private double posRight = 1;
    private DcMotor spinner = null;
    private boolean slow = false;


    private String status = "Off";
    private String speed = "Fast";

    //Code to run ONCE when the driver hits INIT
    @Override
    public void init() {
        // Set up the hardware
        telemetry.addData("Status", "Initialized");

        //GoBuilda 5202 motors go 12.3 inches in 537.6 encoder ticks
        F1 = hardwareMap.get(DcMotor.class, "F1");
        F2 = hardwareMap.get(DcMotor.class, "F2");
        R1 = hardwareMap.get(DcMotor.class, "R1");
        R2 = hardwareMap.get(DcMotor.class, "R2");
        arm = hardwareMap.get(DcMotor.class, "arm");
        leftClaw = hardwareMap.get(Servo.class, "leftClaw");
        rightClaw = hardwareMap.get(Servo.class, "rightClaw");
        spinner = hardwareMap.get(DcMotor.class, "spinner");
        rightClaw.setPosition(posLeft);
        leftClaw.setPosition(posRight);

        telemetry.addData("Status", status);
        telemetry.addData("Speed", speed);
        telemetry.addData("Left servo pos", posLeft);
        telemetry.addData("Right servo pos", posRight);
        telemetry.update();


        F1.setDirection(DcMotorSimple.Direction.REVERSE);
        F2.setDirection(DcMotorSimple.Direction.REVERSE);
        R1.setDirection(DcMotorSimple.Direction.REVERSE);
        R2.setDirection(DcMotorSimple.Direction.REVERSE);
        spinner.setDirection(DcMotor.Direction.FORWARD);
        arm.setDirection(DcMotor.Direction.FORWARD);

        F1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        F2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        R1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        R2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        // Initialize the hardware variables. Note that the strings used here as parameters
        // to 'get' must correspond to the names assigned during the robot configuration
        // step (using the FTC Robot Controller app on the phone).

        telemetry.addData("Status", "Initialized v1");


    }
    //Code to run ONCE when the driver hits PLAY
    @Override
    public void start() {
        runtime.reset();
        status = "On";
    }


    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    @Override
    public void loop() {
        double leftstick_x_sqr;
        double leftstick_y_sqr;
        double power;
        double angle;

        double powerF1;
        double powerF2;
        double powerR1;
        double powerR2;


        angle = Math.atan2(gamepad1.left_stick_x, gamepad1.left_stick_y);//finds angle of joystick
        telemetry.addData("angle: ", angle);//adds telemetry for troubleshooting code

        leftstick_x_sqr = gamepad1.left_stick_x * gamepad1.left_stick_x;
        leftstick_y_sqr = gamepad1.left_stick_y * gamepad1.left_stick_y;
        power = Math.sqrt(leftstick_x_sqr + leftstick_y_sqr);//finds relative power of joystick using pythagorean theorem
        telemetry.addData("power: ", power);

        double Pi = Math.PI/4;//to avoid having to type this everytime

        double lcPos = leftClaw.getPosition(); //Setting left Claw position
        double rcPos = rightClaw.getPosition(); //Setting Right Claw position

/*      if(gamepad1.left_trigger >0.9 && gamepad1.right_trigger > 0.9) {
            if (!slow) {
                slow = true;
                speed = "slow";
            } else if (slow) {
                slow = false;
                speed = "fast";
            }
*/

//        if(!gamepad1.y) {
//
//        }




        //                This controls the arm lol
        if (gamepad1.dpad_up) {
            //move arm up
            arm.setDirection(DcMotorSimple.Direction.REVERSE);
            arm.setPower(0.8);
            status = "arm up";
        } else if (gamepad1.dpad_down) {
            //move claw down
            arm.setDirection(DcMotorSimple.Direction.FORWARD);
            arm.setPower(0.8);
            status = "arm down";
        } else {
            arm.setPower(0);
            status = null;
        }

        if(gamepad1.left_trigger > 0.9 && gamepad1.right_trigger >0.9) {
            slow = true;
            speed = "slow";
            power = 0.5;
        } else if(gamepad1.left_trigger > 0){
            arm.setDirection(DcMotorSimple.Direction.REVERSE);
            arm.setPower(gamepad1.left_trigger);
            status = "arm up";

            //turning off slow mode
            slow = false;
            speed = "fast";
        } else if(gamepad1.right_trigger > 0){
            arm.setDirection(DcMotorSimple.Direction.FORWARD);
            arm.setPower(gamepad1.right_trigger);
            //turning off slow mode
            slow = false;
            speed = "fast";
        } else{
            //turning off slow mode
            slow = false;
            speed = "fast";
        }



        //These control the servos
        if (gamepad1.dpad_right || gamepad1.right_bumper) {
            leftClaw.setPosition(lcPos -= 0.1);
            rightClaw.setPosition(rcPos += 0.1);
        } else if (gamepad1.dpad_left || gamepad1.left_bumper) {
            leftClaw.setPosition(lcPos += 0.1);
            rightClaw.setPosition(rcPos -= 0.1);
        }

        //Spinner controls
        if(gamepad1.a){
            spinner.setDirection(DcMotorSimple.Direction.FORWARD);
            if(slow){
                spinner.setPower(0.5);
            } else{
                spinner.setPower(1);
            }
        } else if(gamepad1.b){
            spinner.setDirection(DcMotorSimple.Direction.REVERSE);
            if(slow){
                spinner.setPower(0.5);
            } else{
                spinner.setPower(1);
            }
        } else{
            spinner.setPower(0);
        }


        powerF2 = (power * Math.cos(angle - Pi)) + gamepad1.right_stick_x;
        powerF1 = (power * -Math.cos(angle + Pi)) + gamepad1.right_stick_x;//assigning each motor power based on calculated sin wave
        powerR1 = (power * -Math.cos(angle - Pi)) + gamepad1.right_stick_x;//right stick is used for y axis rotation
        powerR2 = (power * Math.cos(angle + Pi)) + gamepad1.right_stick_x;



        powerF1 = Range.clip(powerF1,-1, 1);//clips values to avoid program errors
        powerF2 = Range.clip(powerF2,-1, 1);
        powerR1 = Range.clip(powerR1,-1.0, 1.0);
        powerR2 = Range.clip(powerR2,-1, 1);

        telemetry.addData("right stick x: ", gamepad1.right_stick_x);
        telemetry.addData("Status",status);
        telemetry.addData("Speed",speed);

        if(power > .1 || Math.abs(gamepad1.right_stick_x)>.1){
            F1.setPower(powerF1);
            F2.setPower(powerF2);//applies motor power if joystick is moved
            R1.setPower(powerR1);
            R2.setPower(powerR2);
        }
        else{
            F1.setPower(0);
            F2.setPower(0);
            R1.setPower(0);//stops robot when no joystick is pressed 
            R2.setPower(0);
        }
    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
    }

}