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

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="notworking drive", group="teleop")
@Disabled

public class MecanumDriveOld extends OpMode
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


        F1.setDirection(DcMotorSimple.Direction.REVERSE); //
        F2.setDirection(DcMotorSimple.Direction.FORWARD);
        R1.setDirection(DcMotorSimple.Direction.REVERSE); //
        R2.setDirection(DcMotorSimple.Direction.FORWARD);
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




        angle = Math.atan2(gamepad1.left_stick_x, gamepad1.left_stick_y);//finds angle of joystick
        telemetry.addData("angle: ", angle);//adds telemetry for troubleshooting code





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
                spinner.setPower(0.7);
            }
        } else if(gamepad1.b){
            spinner.setDirection(DcMotorSimple.Direction.REVERSE);
            if(slow){
                spinner.setPower(0.5);
            } else{
                spinner.setPower(0.7);
            }
        } else{
            spinner.setPower(0);
        }

        while(gamepad1.back){
            F1.setPower(-0.5);
            F2.setPower(-0.5);
            R1.setPower(0.5);
            R2.setPower(0.5);
        }



        double y = -gamepad1.left_stick_y; // Remember, this is reversed!
        double x = gamepad1.left_stick_x * 1.1; // Counteract imperfect strafing
        double rx = gamepad1.right_stick_x;

        // Denominator is the largest motor power (absolute value) or 1
        // This ensures all the powers maintain the same ratio, but only when
        // at least one is out of the range [-1, 1]
        double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
        double frontLeftPower = (y + x + rx) / denominator;
        double backLeftPower = (y - x + rx) / denominator;
        double frontRightPower = (y - x - rx) / denominator;
        double backRightPower = (y + x - rx) / denominator;

        F1.setPower(frontLeftPower);
        R1.setPower(backLeftPower);
        F2.setPower(frontRightPower);
        R2.setPower(backRightPower);
    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
    }

}